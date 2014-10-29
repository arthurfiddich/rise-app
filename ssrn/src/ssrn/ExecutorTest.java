package ssrn;

import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;

public class ExecutorTest implements Runnable {

	private String url;
	private HttpClient httpClient;
	private Parse parse;
	private int pageCount;

	@Override
	public void run() {
		HttpRequest httGetTable = new HttpGet(this.getUrl());
		httGetTable
				.setHeader(
						"User-Agent",
						"Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/38.0.2125.104 Safari/537.36");
		String response = execute(this.getHttpClient(), httGetTable);
		Precondition.ensureNotEmpty(response, "Html Response");
		Map<String, String> nameVsEmailUrlMap = this.getParse()
				.getNameVsEmailUrlMap(response);
		List<User> userInfoList = this.parse.getUserInfo(nameVsEmailUrlMap,
				this.getHttpClient());
		Precondition.ensureNotEmpty(userInfoList, "UserInfoList");
		this.getParse().write(userInfoList, pageCount);
	}

	public ExecutorTest() {
	}

	public ExecutorTest(String argUrl) {
		this.url = argUrl;
	}

	public ExecutorTest(String argUrl, HttpClient argHttpClient) {
		super();
		this.url = argUrl;
		this.httpClient = argHttpClient;
		this.parse = new Parse();
	}

	public HttpClient getHttpClient() {
		return this.httpClient;
	}

	public String getUrl() {
		return this.url;
	}

	// public static void main(String[] args) {
	// HttpClient login = new HttpClient();
	// HttpRequest loginRequest = new HttpGet(
	// "https://hq.ssrn.com/login/loginAjax.cfm?login=true&username=shivaprasad.amar@gmail.com&pass=Cipher@123");
	// loginRequest
	// .setHeader(
	// "User-Agent",
	// "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/38.0.2125.104 Safari/537.36");
	// HttpClient.displayOriginRequestHeaders(loginRequest);
	// execute(login, loginRequest);
	// HttpRequest httGetTable = new HttpGet(
	// "http://hq.ssrn.com/rankings/Ranking_Display.cfm?npage=1&RequestTimeout=5000&TMY_gID=2&TRN_gID=32&order=ASC&runid=48791");
	// httGetTable
	// .setHeader(
	// "User-Agent",
	// "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/38.0.2125.104 Safari/537.36");
	// execute(login, httGetTable);
	// }

	public int getPageCount() {
		return this.pageCount;
	}

	public void setPageCount(int argPageCount) {
		this.pageCount = argPageCount;
	}

	public Parse getParse() {
		return this.parse;
	}

	protected String execute(HttpClient login, HttpRequest httpost) {
		System.out.println(httpost.getRequestLine().getUri());
		HttpResponse httpPostResponse = login.execute(httpost);
		HttpClient.displayOriginResponseHeaders(httpPostResponse);
		if (HttpClient.hasResponseContent(httpPostResponse)) {
			InputStream responseStream = null;
			try {
				responseStream = HttpClient
						.getResponseContent(httpPostResponse);
				return IOUtils.toString(responseStream);
			} catch (Exception e) {
				throw new RuntimeException(
						"Exception while getting the POST Response: ", e);
			} finally {
				if (responseStream != null) {
					try {
						responseStream.close();
					} catch (Exception ignore) {
						// ignore
					}
				}
			}
		}
		return null;
	}

	public static void main(String[] args) {
		HttpClient httpClient = new HttpClient();
		ExecutorTest executorTest = new ExecutorTest("", httpClient);
		HttpRequest loginRequest = new HttpGet(
				"https://hq.ssrn.com/login/loginAjax.cfm?login=true&username=shivaprasad.amar@gmail.com&pass=Cipher@123");
		loginRequest
				.setHeader(
						"User-Agent",
						"Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/38.0.2125.104 Safari/537.36");
		HttpClient.displayOriginRequestHeaders(loginRequest);
		executorTest.execute(httpClient, loginRequest);

		// Parse parse = new Parse();
		// List<String> emailsList = parse.getEmails(
		// "http://papers.ssrn.com/sol3/cf_dev/AbsByAuth.cfm?per_id=9#",
		// httpClient);
		// if (Precondition.checkNotEmpty(emailsList)) {
		// for (String email : emailsList) {
		// System.out.println(email);
		// }
		// }else{
		// System.out.println("################# ...............EMPTY............... #################");
		// }

		ExecutorService executorService = new ExecutorServiceImpl()
				.getExecutorService();
		Precondition.ensureNotNull(executorService, "Executor Service");
		for (int i = 41; i <= 120; i++) {
			String url = "http://hq.ssrn.com/rankings/Ranking_Display.cfm?npage="
					+ i
					+ "&RequestTimeout=5000&TMY_gID=2&TRN_gID=2&order=ASC&runid=48373";
			ExecutorTest innerExecutorTest = new ExecutorTest(url, httpClient);
			innerExecutorTest.setPageCount(i);
			executorService.execute(innerExecutorTest);
		}
	}
}
