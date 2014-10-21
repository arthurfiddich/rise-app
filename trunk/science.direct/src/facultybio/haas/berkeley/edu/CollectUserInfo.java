package facultybio.haas.berkeley.edu;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

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
					List<Element> elementsList = source.getAllElements("id",
							"id_email", true);
					List<String> emailsList = new ArrayList<String>();
					if (Precondition.checkNotEmpty(elementsList)) {
						for (Element emailElement : elementsList) {
							String emailDataPart = emailElement
									.getAttributeValue("data-email");
							String dataAtPart = emailElement
									.getAttributeValue("data-at");
							if (Precondition.checkNotEmpty(emailDataPart)
									&& Precondition.checkNotEmpty(dataAtPart)) {
								String email = emailDataPart + "@" + dataAtPart;
								emailsList.add(email);
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
				.getEmails("http://facultybio.haas.berkeley.edu/faculty-list/calderon-jorge");
		for (String email : emailsList) {
			System.out.println(email);
		}
	}
}
