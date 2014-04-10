package com.data.generator.http.component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;

public class HttpClientGetExample {
	public static void main(String[] args) throws ClientProtocolException,
			IOException {
		// String url = "http://www.google.com/search?q=httpClient";
		String url = "https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=-33.8670522,151.1957362&radius=500&types=food&name=harbour&sensor=false&key=AIzaSyBA0Hu3is9qIJ5v6NEuofigk0y-aQwqiP0";

		HttpClient client = HttpClientBuilder.create().build();
		HttpGet request = new HttpGet(url);

		// add request header
		request.addHeader("User-Agent", "User-Agent");
		HttpResponse response = client.execute(request);

		System.out.println("Response Code : "
				+ response.getStatusLine().getStatusCode());

//		BufferedReader rd = new BufferedReader(new InputStreamReader(response
//				.getEntity().getContent()));
//
//		StringBuffer result = new StringBuffer();
//		String line = "";
//		while ((line = rd.readLine()) != null) {
//			result.append(line);
//		}
		Header[] headerArray = response.getAllHeaders();
		for (Header header : headerArray) {
			System.out.println("Header Name: " + header.getName()
					+ " Header Value: " + header.getValue());
		}
//		System.out.println(result);
		System.out.println(parseAsString(response));
	}

	public static String parseAsString(HttpResponse argHttpResponse)
			throws IOException {
		HttpEntity entity = argHttpResponse.getEntity();
		InputStream content = getContent(entity);
		if (content == null) {
			return "";
		}
		try {
			long contentLength = entity.getContentLength();
			int bufferSize = contentLength == -1 ? 4096 : (int) contentLength;
			int length = 0;
			byte[] buffer = new byte[bufferSize];
			byte[] tmp = new byte[4096];
			int bytesRead;
			while ((bytesRead = content.read(tmp)) != -1) {
				if (length + bytesRead > bufferSize) {
					bufferSize = Math.max(bufferSize << 1, length + bytesRead);
					byte[] newbuffer = new byte[bufferSize];
					System.arraycopy(buffer, 0, newbuffer, 0, length);
					buffer = newbuffer;
				}
				System.arraycopy(tmp, 0, buffer, length, bytesRead);
				length += bytesRead;
			}
			return new String(buffer, 0, length);
		} finally {
			content.close();
		}
	}

	private static InputStream getContent(HttpEntity argEntity)
			throws IllegalStateException, IOException {
		return argEntity.getContent();
	}
}
