package com.data.generator.geo.names.api.test;

import java.io.IOException;
import java.io.InputStream;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;

public class FireRequest {

	public static void main(String[] args) throws ClientProtocolException,
			IOException {
		String url = "https://maps.googleapis.com/maps/api/place/details/xml?reference=CoQBcQAAAEZ7yCju-0lhU7sZIBBe_On9jYImWzZ9Zt5rIg1tX6zaH02dHrQMHF1LFHY1_yUuXzsUf6m6-rrQJ8Ec_mGxBYtV85Wyb4anakaUi3QuZj7ygJXB3Fd5x69k_4UnDKMmEBNa410vbCXgQOGIkHCbNpcbC8ENxmVlUrqiifmdfuLgEhCtPATMhFRdsjuyAL_j__OEGhTnqujRRMYy_5-kxzcqCdMY4_1dbA&sensor=true&key=AIzaSyBA0Hu3is9qIJ5v6NEuofigk0y-aQwqiP0";

		HttpClient client = HttpClientBuilder.create().build();
		HttpGet request = new HttpGet(url);

		// add request header
		request.addHeader("User-Agent", "User-Agent");
		HttpResponse response = client.execute(request);

		System.out.println("Response Code : "
				+ response.getStatusLine().getStatusCode());

		// BufferedReader rd = new BufferedReader(new InputStreamReader(response
		// .getEntity().getContent()));
		//
		// StringBuffer result = new StringBuffer();
		// String line = "";
		// while ((line = rd.readLine()) != null) {
		// result.append(line);
		// }
		Header[] headerArray = response.getAllHeaders();
		for (Header header : headerArray) {
			System.out.println("Header Name: " + header.getName()
					+ " Header Value: " + header.getValue());
		}
		// System.out.println(result);
		try {
			// parse(response);
		} catch (Exception e) {
			e.printStackTrace();
		}
		// System.out.println(parseAsString(response));
	}

	// private static void parse(HttpResponse argResponse)
	// throws IllegalStateException, IOException, JAXBException,
	// SAXException, ParserConfigurationException {
	// HttpEntity entity = argResponse.getEntity();
	// InputStream content = getContent(entity);
	// GenericJaxbHelper<PlaceDetailsResponse> jaxbHelper = new
	// GenericJaxbHelper<PlaceDetailsResponse>(
	// com.generator.data.xmlns.gplace.api.v1.ObjectFactory.class
	// .getPackage().getName());
	//
	// JAXBElement<PlaceDetailsResponse> dataSourceConfigurationElement =
	// jaxbHelper
	// .customUnmarshller(content);
	// PlaceDetailsResponse placeDetailsResponse =
	// dataSourceConfigurationElement
	// .getValue();
	// String status = placeDetailsResponse.getStatus();
	// System.out.println(status);
	// }

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
