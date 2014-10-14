package www.econ.kobe.u.ac.jp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import net.htmlparser.jericho.Element;
import net.htmlparser.jericho.HTMLElementName;
import net.htmlparser.jericho.Source;

import com.data.generator.extractor.HtmlExtractor;
import com.data.generator.util.Precondition;
import com.data.generator.web.crawler.WebContentDownloader;

public class CollectUserInfo {
	private final HtmlExtractor htmlExtractor;
	private ReadImage readImage = new ReadImage();

	public CollectUserInfo(HtmlExtractor argHtmlExtractor) {
		super();
		this.htmlExtractor = argHtmlExtractor;
	}

	public HtmlExtractor getHtmlExtractor() {
		return this.htmlExtractor;
	}

	public List<User> imageUrl(Map<String, String> argNameVsEmailUrlMap) {
		@SuppressWarnings("unchecked")
		Map<String, String> nameVsEmailUrlMap = (Map<String, String>) Precondition
				.ensureNotEmpty(argNameVsEmailUrlMap, "Name Vs Email URL Map");
		Set<Entry<String, String>> iterable = nameVsEmailUrlMap.entrySet();
		List<User> usersList = new ArrayList<User>();
		for (Entry<String, String> entry : iterable) {
			String emailUrl = entry.getValue();
			String name = entry.getKey();
			if (Precondition.checkNotEmpty(emailUrl)
					&& Precondition.checkNotEmpty(name)) {
				String imageUrl = getEmailImageUrl(emailUrl, name);
				if (Precondition.checkNotEmpty(imageUrl)) {
					String email = readImage.getEmail(imageUrl);
					if (Precondition.checkNotEmpty(email)) {
						User user = new User(name, email);
						usersList.add(user);
					}
				}
			}
		}
		return usersList;
	}

	private String getEmailImageUrl(String argEmailUrl, String argName) {
		WebContentDownloader webContentDownloader = new WebContentDownloader();
		String emailContent = webContentDownloader.getHtmlContent(argEmailUrl);
		if (Precondition.checkNotEmpty(emailContent)) {
			Source source = this.getHtmlExtractor().getSourceBasedOnHtmlString(
					emailContent);
			if (Precondition.checkNotNull(source)) {
				Element element = source.getAllElementsByClass("teacher_right")
						.get(0);
				if (Precondition.checkNotNull(element)) {
					Element imageElement = element.getAllElements(
							HTMLElementName.IMG).get(0);
					if (Precondition.checkNotNull(imageElement)) {
						String imageUrl = imageElement.getAttributeValue("src");
						if (Precondition.checkNotEmpty(imageUrl)) {
							imageUrl = "http://www.econ.kobe-u.ac.jp/en/people/course/"
									+ imageUrl;
							return imageUrl;
						}
					}
				}
			}
		}
		return null;
	}

	public static void main(String[] args) {
		HtmlExtractor htmlExtractor = new HtmlExtractor();
		CollectUserInfo collectUserInfo = new CollectUserInfo(htmlExtractor);
		Map<String,String> map = new HashMap<String,String>();
		map.put("ashiya", "http://www.econ.kobe-u.ac.jp/en/people/course/theory/ashiya.html");
		collectUserInfo.imageUrl(map);
		
	}
}
