package ssrn;

import java.io.IOException;

import org.apache.http.HttpException;
import org.apache.http.HttpRequest;
import org.apache.http.HttpRequestInterceptor;
import org.apache.http.protocol.HttpContext;

public class GzipHttpRequestInterceptor implements HttpRequestInterceptor {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.apache.http.HttpRequestInterceptor#process(org.apache.http.HttpRequest
	 * , org.apache.http.protocol.HttpContext)
	 */
	@Override
	public void process(HttpRequest argHttpRequest, HttpContext argHttpContext)
			throws HttpException, IOException {
		if (!argHttpRequest.containsHeader("Accept-Encoding")) {
			argHttpRequest.addHeader("Accept-Encoding", "gzip");
		}
	}

}
