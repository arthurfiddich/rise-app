package audencia;

import java.util.ArrayList;
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

	public CollectUserInfo(HtmlExtractor argHtmlExtractor) {
		super();
		this.htmlExtractor = argHtmlExtractor;
	}

	public HtmlExtractor getHtmlExtractor() {
		return this.htmlExtractor;
	}

	public List<User> getUserInfoList(Map<String, String> argNameVsEmailUrlMap) {
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
				User user = getUserInfo(emailUrl, name);
				if (Precondition.checkNotNull(user)) {
					usersList.add(user);
				}
			}
		}
		return usersList;
	}

	private User getUserInfo(String argEmailUrl, String argName) {
		WebContentDownloader webContentDownloader = new WebContentDownloader();
		String emailContent = webContentDownloader.getHtmlContent(argEmailUrl);
		if (Precondition.checkNotEmpty(emailContent)) {
			Source source = this.getHtmlExtractor().getSourceBasedOnHtmlString(
					emailContent);
			if (Precondition.checkNotNull(source)) {
				List<Element> emailElementsList = source
						.getAllElementsByClass("two-cols-left");
				List<String> emailsList = getValues(emailElementsList);
				List<Element> phoneElementsList = source
						.getAllElementsByClass("two-cols-right");
				List<String> phonesList = getValues(phoneElementsList);
				User user = new User(argName, emailsList, phonesList);
				return user;
			}
		}
		return null;
	}

	protected List<String> getValues(List<Element> phoneElementsList) {
		if (Precondition.checkNotEmpty(phoneElementsList)) {
			List<String> PhonesList = new ArrayList<String>();
			for (Element element : phoneElementsList) {
				String phone = getValuePhone(element);
				if (Precondition.checkNotEmpty(phone)) {
					PhonesList.add(phone);
				}
			}
			return PhonesList;
		}
		return null;
	}

	protected String getValuePhone(Element argElement) {
		List<Element> spanElementsList = argElement
				.getAllElements(HTMLElementName.SPAN);
		if (Precondition.checkNotEmpty(spanElementsList)) {
			Element spanElement = spanElementsList.get(0);
			if (Precondition.checkNotNull(spanElement)) {
				return spanElement.getContent().getTextExtractor().toString();
			}
		}
		return null;
	}

	protected String getValue(Element argElement) {
		List<Element> spanElementsList = argElement
				.getAllElements(HTMLElementName.SPAN);
		if (Precondition.checkNotEmpty(spanElementsList)) {
			for (Element spanElement : spanElementsList) {
				List<Element> anchorTagElementsList = spanElement
						.getAllElements(HTMLElementName.A);
				if (Precondition.checkNotEmpty(anchorTagElementsList)) {
					for (Element anchorTagElement : anchorTagElementsList) {
						String hrefValue = anchorTagElement
								.getAttributeValue("href");
						if (Precondition.checkNotEmpty(hrefValue)) {
							return anchorTagElement.getContent()
									.getTextExtractor().toString();
						}
					}
				}
			}
		}
		return null;
	}
}
