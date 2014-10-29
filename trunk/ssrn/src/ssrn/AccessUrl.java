package ssrn;

import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.protocol.HTTP;

public class AccessUrl {

	public static void main(String[] args) {
		HttpClient login = new HttpClient();
		String domainUrl = "https://hq.ssrn.com/login/pubSignInJoin.cfm";
		// HttpRequest httpGet = new HttpGet(domainUrl);
		// HttpResponse httpResponse = login.execute(httpGet);
		// if (Login.hasResponseContent(httpResponse)) {
		// InputStream inputStream = null;
		// List<NameValuePair> nameVsValuePair = null;
		// try {
		// inputStream = Login.getResponseContent(httpResponse);
		// nameVsValuePair = Execute.buildNameValuePairs(inputStream,
		// "shivaprasad.amar@gmail.com", "Cipher@123");
		// } catch (Exception e) {
		// throw new RuntimeException(
		// "Exception while getting the inputstream from the a response: ",
		// e);
		// } finally {
		// if (inputStream != null) {
		// try {
		// inputStream.close();
		// } catch (Exception ignore) {
		// // ignore
		// }
		// }
		// }
		// if (nameVsValuePair != null && nameVsValuePair.size() > 0) {
		HttpRequest httpost = new HttpGet(
				"https://hq.ssrn.com/login/loginAjax.cfm?login=true&username=shivaprasad.amar@gmail.com&pass=Cipher@123");
		httpost.setHeader(
				"User-Agent",
				"Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/38.0.2125.104 Safari/537.36");
		HttpClient.displayOriginRequestHeaders(httpost);
		// try {
		// httpost.setEntity(new UrlEncodedFormEntity(nameVsValuePair,
		// HTTP.UTF_8));
		// } catch (UnsupportedEncodingException e) {
		// throw new RuntimeException(
		// "Unsupported Encoding Exception: ", e);
		// }
		execute(login, httpost);
		// HttpRequest hr = new HttpGet(
		// "http://hq.ssrn.com/rankings/Ranking_Display.cfm?TMY_gID=2&redirectFrom=true");
		// hr.setHeader(
		// "User-Agent",
		// "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/38.0.2125.104 Safari/537.36");
		// Login.displayOriginRequestHeaders(hr);
		// execute(login, hr);
		// }
		// }
		HttpRequest httGetTable = new HttpGet(
				"http://hq.ssrn.com/rankings/Ranking_Display.cfm?npage=1&RequestTimeout=5000&TMY_gID=2&TRN_gID=32&order=ASC&runid=48791");
		httGetTable
				.setHeader(
						"User-Agent",
						"Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/38.0.2125.104 Safari/537.36");
		execute(login, httGetTable);
	}

	protected static void execute(HttpClient login, HttpRequest httpost) {
		System.out.println(httpost.getRequestLine().getUri());
		HttpResponse httpPostResponse = login.execute(httpost);
		HttpClient.displayOriginResponseHeaders(httpPostResponse);
		if (HttpClient.hasResponseContent(httpPostResponse)) {
			InputStream responseStream = null;
			try {
				responseStream = HttpClient.getResponseContent(httpPostResponse);
				String response = IOUtils.toString(responseStream);
				System.out.println(response);
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
	}
}
