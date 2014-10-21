package www.essec.edu;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import net.htmlparser.jericho.Element;
import net.htmlparser.jericho.HTMLElementName;
import net.htmlparser.jericho.Source;

import www.iese.edu.PageSource;
import www.iese.edu.User;

import com.data.generator.extractor.HtmlExtractor;
import com.data.generator.util.Precondition;

public class CollectUserInfo {
	private final HtmlExtractor htmlExtractor;
	private final PageSource pageSource;
	private String keyWord = "javascript:ecleaf_mail(";

	public CollectUserInfo(HtmlExtractor argHtmlExtractor) {
		super();
		this.htmlExtractor = argHtmlExtractor;
		this.pageSource = new PageSource();
	}

	public HtmlExtractor getHtmlExtractor() {
		return this.htmlExtractor;
	}

	public PageSource getPageSource() {
		return this.pageSource;
	}

	public List<User> getUserInfoList(Map<String, String> argNameVsEmailUrlMap) {
		@SuppressWarnings("unchecked")
		Map<String, String> nameVsEmailUrlMap = (Map<String, String>) Precondition
				.ensureNotEmpty(argNameVsEmailUrlMap, "Name Vs Email URL Map");
		Iterator<Entry<String, String>> iterator = nameVsEmailUrlMap.entrySet()
				.iterator();
		List<User> usersList = new ArrayList<User>();
		while (iterator.hasNext()) {
			Entry<String, String> entry = iterator.next();
			String name = entry.getKey();
			String emailUrl = entry.getValue();
			List<String> emailsList = getEmails(emailUrl);
			if (Precondition.checkNotEmpty(emailsList)
					&& Precondition.checkNotEmpty(name)) {
				User user = new User();
				user.setName(name);
				user.setEmailsList(emailsList);
				usersList.add(user);
			}
		}
		return usersList;
	}

	public List<String> getEmails(String argEmailUrl) {
		if (Precondition.checkNotEmpty(argEmailUrl)) {
			String emailUrl = argEmailUrl;
			String emailContent = this.getPageSource().getHtmlContent(emailUrl);
			if (Precondition.checkNotEmpty(emailContent)) {
				Source source = this.getHtmlExtractor()
						.getSourceBasedOnHtmlString(emailContent);
				if (Precondition.checkNotNull(source)) {
					List<Element> elementsList = source
							.getAllElementsByClass("bloc_vert_clair_2");
					if (Precondition.checkNotEmpty(elementsList)) {
						List<String> emailsList = new ArrayList<String>();
						Element element = elementsList.get(0);
						List<Element> anchorTagElementsList = element
								.getAllElements(HTMLElementName.A);
						if (Precondition.checkNotEmpty(anchorTagElementsList)) {
							for (Element anchorTagElement : anchorTagElementsList) {
								String attributeValue = anchorTagElement
										.getAttributeValue("href");
								if (Precondition.checkNotEmpty(attributeValue)
										&& attributeValue
												.startsWith(this.keyWord)) {
									attributeValue = attributeValue.replace(
											this.keyWord, "");
									attributeValue = attributeValue.replace(
											")", "");
									attributeValue = attributeValue.replaceAll(
											"\'", "");
									String[] parts = attributeValue.split(",");
									if (Precondition.checkNotEmpty(parts)
											&& parts.length == 2) {
										String email = parts[0] + "@"
												+ parts[1];
										if (Precondition.checkNotEmpty(email)) {
											emailsList.add(email);
										}
									}
								}
							}
						}
						return emailsList;
					}
				}
			}
		}
		return null;
	}

	public static void main(String[] args) {
		HtmlExtractor htmlExtractor = new HtmlExtractor();
		CollectUserInfo collectUserInfo = new CollectUserInfo(htmlExtractor);
		List<String> emailsList = collectUserInfo
				.getEmails("http://www.essec.edu/faculty/celik-gorkem");
		for (String email : emailsList) {
			System.out.println(email);
		}
	}
}
