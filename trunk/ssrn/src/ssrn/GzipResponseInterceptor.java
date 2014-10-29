package ssrn;

import java.io.IOException;

import org.apache.http.Header;
import org.apache.http.HeaderElement;
import org.apache.http.HttpEntity;
import org.apache.http.HttpException;
import org.apache.http.HttpResponse;
import org.apache.http.HttpResponseInterceptor;
import org.apache.http.client.entity.GzipDecompressingEntity;
import org.apache.http.protocol.HttpContext;

public class GzipResponseInterceptor implements HttpResponseInterceptor {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.apache.http.HttpResponseInterceptor#process(org.apache.http.HttpResponse
	 * , org.apache.http.protocol.HttpContext)
	 */
	@Override
	public void process(HttpResponse argHttpResponse, HttpContext argHttpContext)
			throws HttpException, IOException {
		HttpEntity entity = argHttpResponse.getEntity();
		if (entity != null) {
			Header contentEncodingHeader = entity.getContentEncoding();
			if (contentEncodingHeader != null) {
				HeaderElement[] codecs = contentEncodingHeader.getElements();
				for (int i = 0; i < codecs.length; i++) {
					if (codecs[i].getName().equalsIgnoreCase("gzip")) {
						argHttpResponse.setEntity(new GzipDecompressingEntity(
								argHttpResponse.getEntity()));
						return;
					}
				}
			}
		} else {
			System.out
					.println("No entity found. Must have been cached on the browser side?");
		}
	}

}
