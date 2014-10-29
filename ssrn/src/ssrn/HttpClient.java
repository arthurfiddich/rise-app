package ssrn;

import java.io.IOException;
import java.io.InputStream;

import org.apache.http.ConnectionReuseStrategy;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.client.RedirectStrategy;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.config.RequestConfig.Builder;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultConnectionKeepAliveStrategy;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.LaxRedirectStrategy;
import org.apache.http.protocol.HttpContext;

public class HttpClient {

	private HttpClientBuilder httpClient;
	private CloseableHttpClient httpClientBuilder;
	private RedirectStrategy redirectStrategy;

	/** The Constant CONNECTION_TIMEOUT_MILLIS. */
	private static final int CONNECTION_TIMEOUT_MILLIS = 1800000;

	/** The Constant SOCKET_TIMEOUT_MILLIS. */
	private static final int SOCKET_TIMEOUT_MILLIS = 1800000;

	/** The Constant DEFAULT_KEEPALIVE. */
	private static final int DEFAULT_KEEPALIVE = 60000;

	private static String connectionKeepAlive = System.getProperty(
			"com.ciphercloud.client.http.connectionKeepAlive", "false");

	public HttpClient() {
		this.httpClient = HttpClientBuilder.create();
		initialize();
	}

	public void initialize() {
		this.getHttpClient().disableConnectionState();
		this.getHttpClient()
				.setUserAgent(
						"Mozilla/5.0 (Windows; U; Windows NT 5.1; en-GB; rv:1.9.1.11) Gecko/20100701 Firefox/3.5.11 GTBDFff GTB7.0 (.NET CLR 3.5.30729) GTBA");
		Builder builder = RequestConfig.custom();
		RequestConfig requestConfig = null;

		builder.setSocketTimeout(SOCKET_TIMEOUT_MILLIS);
		// HttpConnectionParams.setSoTimeout(params, SOCKET_TIMEOUT_MILLIS);

		builder.setConnectTimeout(CONNECTION_TIMEOUT_MILLIS);
		// HttpConnectionParams.setConnectionTimeout(params,
		// CONNECTION_TIMEOUT_MILLIS);

		builder.setStaleConnectionCheckEnabled(false);
		// HttpConnectionParams.setStaleCheckingEnabled(params, false);

		builder.setRelativeRedirectsAllowed(true);
		builder.setRedirectsEnabled(true); // confused which one is correct
		// params.setParameter(ClientPNames.HANDLE_REDIRECTS, true);

		builder.setCookieSpec(CookieSpecs.BROWSER_COMPATIBILITY);
		// this.getHttpClient().getParams().setParameter(ClientPNames.COOKIE_POLICY,
		// CookiePolicy.BROWSER_COMPATIBILITY);

		requestConfig = builder.build();
		this.getHttpClient().setDefaultRequestConfig(requestConfig);
		// this.getHttpClient().setParams(params);

		this.getHttpClient().addInterceptorFirst(
				new GzipHttpRequestInterceptor());
		this.getHttpClient().addInterceptorFirst(new GzipResponseInterceptor());
		String doNotReuseConnectionVal = System
				.getProperty("com.ciphercloud.proxy.do.not.reuse.connection");
		boolean doNotReuseConnection = (doNotReuseConnectionVal != null && doNotReuseConnectionVal
				.equalsIgnoreCase("true"));
		if (doNotReuseConnection) {
			this.getHttpClient().setConnectionReuseStrategy(
					new ConnectionReuseStrategy() {
						@Override
						public boolean keepAlive(HttpResponse arg0,
								HttpContext arg1) {
							return false;
						}
					});
		}
		this.getHttpClient().setKeepAliveStrategy(
				new DefaultConnectionKeepAliveStrategy() {
					@Override
					public long getKeepAliveDuration(
							HttpResponse argHttpResponse,
							HttpContext argHttpContext) {
						long keepAlive = super.getKeepAliveDuration(
								argHttpResponse, argHttpContext);
						if (keepAlive == -1) {
							keepAlive = DEFAULT_KEEPALIVE;
						}
						return keepAlive;
					}
				});

		setCookies();
	}

	private void setCookies() {
		this.httpClient.setDefaultCookieStore(new BasicCookieStore());
		this.httpClient.setRedirectStrategy(new LaxRedirectStrategy());
	}

	public static void displayOriginRequestHeaders(HttpRequest argOriginRequest) {
		Header[] headers = argOriginRequest.getAllHeaders();
		if (headers != null) {
			System.out
					.println("======================= ORIGIN REQUEST HEADERS : BEGIN  =============================");
			System.out.println(argOriginRequest.getRequestLine().toString());
			for (Header header : headers) {
				System.out.println(header.getName() + ": " + header.getValue());
			}
			System.out
					.println("======================= ORIGIN REQUEST HEADERS : END    =============================");
		}
	}

	public static void displayOriginResponseHeaders(
			HttpResponse argOriginResponse) {
		Header[] headers = argOriginResponse.getAllHeaders();
		if (headers != null) {
			System.out
					.println("======================= RESPONSE HEADERS : BEGIN  =============================");
			System.out.println(argOriginResponse.getStatusLine().toString());
			for (Header header : headers) {
				System.out.println(header.getName() + ": " + header.getValue());
			}
			System.out
					.println("======================= RESPONSE HEADERS : END    =============================");
		}
	}

	public HttpClientBuilder getHttpClient() {
		return this.httpClient;
	}

	public void setHttpClient(HttpClientBuilder argHttpClient) {
		this.httpClient = argHttpClient;
	}

	public CloseableHttpClient getHttpClientBuilder() {
		return this.httpClientBuilder;
	}

	public void setHttpClientBuilder(CloseableHttpClient argHttpClientBuilder) {
		this.httpClientBuilder = argHttpClientBuilder;
	}

	public RedirectStrategy getRedirectStrategy() {
		return this.redirectStrategy;
	}

	public void setRedirectStrategy(RedirectStrategy argRedirectStrategy) {
		this.redirectStrategy = argRedirectStrategy;
	}

	private CloseableHttpClient getHttpClientbuilder() {
		if (httpClientBuilder == null) {
			httpClientBuilder = this.getHttpClient().build();
		}
		return httpClientBuilder;

	}

	public HttpResponse execute(HttpRequest argRequest) {
		try {
			if (connectionKeepAlive != null && !connectionKeepAlive.isEmpty()) {
				if (connectionKeepAlive.equalsIgnoreCase("false")) {
					if (argRequest.containsHeader("Connection")) {
						argRequest.setHeader("Connection", "close");
					} else {
						argRequest.addHeader("Connection", "close");
					}
				}
			}
			// org.apache.http.Header[] headersArray = argRequest
			// .getHeaders("Cookie");
			// if (headersArray.length == 0) {
			// System.out.println(argRequest.getHeaders("Cookie")[0]
			// .getValue());
			// }
			return this.getHttpClientbuilder().execute(
					(HttpUriRequest) argRequest);
		} catch (Exception e) {
			throw new RuntimeException("error while getting reponse for url: "
					+ argRequest.getRequestLine(), e);
		}
	}

	/**
	 * Checks for response content.
	 * 
	 * @param argHttpResponse
	 *            the http response
	 * @return true, if successful
	 */
	public static boolean hasResponseContent(HttpResponse argHttpResponse) {
		HttpEntity httpEntity = argHttpResponse.getEntity();
		return httpEntity != null;
	}

	/**
	 * Gets the response content.
	 * 
	 * @param argHttpResponse
	 *            the http response
	 * @return the response content
	 * @throws IllegalStateException
	 *             the illegal state exception
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public static InputStream getResponseContent(HttpResponse argHttpResponse)
			throws IllegalStateException, IOException {
		HttpEntity httpEntity = argHttpResponse.getEntity();
		InputStream responseContent = null;
		if (httpEntity != null) {
			responseContent = httpEntity.getContent();
		}
		return responseContent;
	}
}
