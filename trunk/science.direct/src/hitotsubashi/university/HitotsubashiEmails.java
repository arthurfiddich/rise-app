package hitotsubashi.university;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.imageio.ImageIO;

import com.data.generator.extractor.HtmlExtractor;
import com.data.generator.file.impl.CsvWriter;
import com.data.generator.util.Precondition;

public class HitotsubashiEmails {

	public String getEmailPart(String argUrl) throws IOException {
		String imageUrl = Precondition.ensureNotEmpty(argUrl, "Image URL");
		URL url = new URL(imageUrl);
		BufferedImage bufferedImage = ImageIO.read(url);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ImageIO.write(bufferedImage, "txt", baos);
		baos.flush();
		byte[] bytes = baos.toByteArray();
		baos.close();
		return new String(bytes);
	}

	public void extractAndWrite(Map<String, Map<String, String>> argMap)
			throws IOException {
		Iterator<Entry<String, Map<String, String>>> outerIterator = argMap
				.entrySet().iterator();
		List<List<String>> tokensList = new ArrayList<List<String>>();
		while (outerIterator.hasNext()) {
			Entry<String, Map<String, String>> outerEntry = outerIterator
					.next();
			Map<String, String> map = outerEntry.getValue();
			String name = outerEntry.getKey();
			if (Precondition.checkNotEmpty(map)
					&& Precondition.checkNotEmpty(name)) {
				Iterator<Entry<String, String>> innerIterator = map.entrySet()
						.iterator();
				while (innerIterator.hasNext()) {
					List<String> list = new ArrayList<String>();
					Entry<String, String> innerEntry = innerIterator.next();
					String emailPart = innerEntry.getKey();
					String url = innerEntry.getValue();
					String email = getEmailPart(url);
					if (Precondition.checkNotEmpty(emailPart)
							&& Precondition.checkNotEmpty(email)) {
						email = emailPart + email;
						list.add(name);
						list.add(email);
						tokensList.add(list);
					}
				}
			}
		}
		Map<String[], List<List<String>>> resultMap = new HashMap<String[], List<List<String>>>();
		String[] tokensArray = { "name", "email" };
		resultMap.put(tokensArray, tokensList);
		CsvWriter csvWriter = new CsvWriter(
				"./output/Hitotsubashi/Hitotsubashi.csv");
		csvWriter.write(resultMap);
	}

	public static void main(String[] args) throws IOException {
		HtmlExtractor htmlExtractor = new HtmlExtractor();
		HitotsubashiList list = new HitotsubashiList(htmlExtractor);
		Map<String, Map<String, String>> map = list
				.prepareMap("http://www.econ.hit-u.ac.jp/~koho/english/faculty/");
		HitotsubashiEmails emails = new HitotsubashiEmails();
		emails.extractAndWrite(map);
	}
}
