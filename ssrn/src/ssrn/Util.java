package ssrn;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;

public class Util {

	public static List<byte[]> getResponseAsByteArray(
			List<Future<HttpResponse>> argFutureList) {
		List<Future<HttpResponse>> futureList = (List<Future<HttpResponse>>) Precondition
				.ensureNotEmpty(argFutureList, "Future Object List");
		List<byte[]> resultedByteArraysList = new ArrayList<byte[]>();
		for (Future<HttpResponse> future : futureList) {
			HttpResponse httpResponse = null;
			try {
				httpResponse = future.get();
			} catch (Exception e) {
				throw new RuntimeException(
						"Exception while getting the HTTPResponse from the Future Object: ",
						e);
			}
			try {
				HttpEntity entity = httpResponse.getEntity();
				if (Precondition.checkNull(entity)) {
					InputStream inputStream = entity.getContent();
					try {
						if (Precondition.checkNotNull(inputStream)) {
							byte[] resultedByteArray = IOUtils
									.toByteArray(inputStream);
							if (Precondition.checkNotEmpty(resultedByteArray)) {
								resultedByteArraysList.add(resultedByteArray);
							}
						}
					} finally {
						if (Precondition.checkNotNull(inputStream)) {
							try {
								inputStream.close();
							} catch (Exception ignore) {
								// ignore
							}
						}
					}
				}
			} catch (Exception e) {
				throw new RuntimeException(
						"Exception while getting the content/inputstream from a HTTPResponse: ",
						e);
			}
		}
		return null;
	}
}
