package www.econ.kobe.u.ac.jp;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

import org.apache.commons.codec.binary.Base64;

import com.data.generator.util.Precondition;

public class ReadImage {

	public String getEmail(String argImageUrl) {
		InputStream in = null;
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try {
			URL url = new URL(argImageUrl);
			URLConnection con = url.openConnection();
			con.setConnectTimeout(10000);
			con.setReadTimeout(10000);
			in = con.getInputStream();
			byte[] buffer = new byte[1024];
			baos = new ByteArrayOutputStream();
			int bytesRead;
			while ((bytesRead = in.read(buffer)) != -1) {
				baos.write(buffer, 0, bytesRead);
			}
			byte[] imageBytes = baos.toByteArray();
			return Base64.encodeBase64String(imageBytes);
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (Precondition.checkNotNull(in)) {
				try {
					in.close();
				} catch (Exception ignore) {
					// ignore
				}
			}
			if (Precondition.checkNotNull(baos)) {
				try {
					baos.close();
				} catch (Exception ignore) {
					// ignore
				}
			}
		}
		return "";
	}
}
