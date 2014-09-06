package georgetown;

import java.util.ArrayList;
import java.util.List;

import net.htmlparser.jericho.Element;
import net.htmlparser.jericho.Source;

import com.data.generator.extractor.HtmlExtractor;
import com.data.generator.util.Precondition;
import com.data.generator.web.crawler.WebContentDownloader;

public class CollectUserInfo {

	private static final String PHONE_CLASS_NAME = "gu_profileitem gu_profileitem_phone";
	private static final String EMAIL_CLASS_NAME = "gu_profileitem gu_profileitem_gu_email";
	private static final String ALTERNATIVE_EMAIL_CLASS_NAME = "gu_profileitem gu_profileitem_gu_alternateemail";
	private static final String ITEM_LABEL = "gu_profileitemlabel";
	private static final String ITEM_CONTENT = "gu_profileitemcontent";

	private final HtmlExtractor htmlExtractor;

	public CollectUserInfo(HtmlExtractor argHtmlExtractor) {
		super();
		this.htmlExtractor = argHtmlExtractor;
	}

	public HtmlExtractor getHtmlExtractor() {
		return this.htmlExtractor;
	}

	public List<User> getMemberInfo(String argUrl) {
		String homePageUrl = Precondition.ensureNotEmpty(argUrl,
				"Home Page URL");
		WebContentDownloader webContentDownloader = new WebContentDownloader();
		String htmlContent = webContentDownloader.getHtmlContent(homePageUrl);
		// Precondition.ensureNotEmpty(htmlContent, "Html Content");
		if (Precondition.checkNotEmpty(htmlContent)) {

			Source source = this.getHtmlExtractor().getSourceBasedOnHtmlString(
					htmlContent);
			Precondition.ensureNotNull(source, "Html Source");
			List<User> usersList = new ArrayList<User>();
			String phoneNumber = getContent(source, PHONE_CLASS_NAME);
			String email = getContent(source, EMAIL_CLASS_NAME);
			String alternateEmail = getContent(source,
					ALTERNATIVE_EMAIL_CLASS_NAME);
			List<String> emailsList = new ArrayList<String>();
			if (Precondition.checkNotEmpty(email)) {
				emailsList.add(email);
			}
			if (Precondition.checkNotEmpty(alternateEmail)) {
				emailsList.add(alternateEmail);
			}
			User user = new User(phoneNumber, emailsList);
			usersList.add(user);
			return usersList;
		}
		return null;
	}

	private String getContent(Source argSource, String argEmailClassName) {
		List<Element> emailElementsList = argSource
				.getAllElementsByClass(argEmailClassName);
		if (emailElementsList.size() == 1) {
			Element emailElement = emailElementsList.get(0);
			if (Precondition.checkNotNull(emailElement)) {
				List<Element> emailContentList = emailElement
						.getAllElementsByClass(ITEM_CONTENT);
				if (emailContentList.size() == 1) {
					Element emailContentElement = emailContentList.get(0);
					if (Precondition.checkNotNull(emailContentElement)) {
						return emailContentElement.getContent()
								.getTextExtractor().toString();
					}
				} else {
					System.out
							.println("Email Content List Greater/Lesser Than One");
				}
			}
		} else {
			System.out.println("Email Element List Greater/Lesser Than One");
		}
		return null;
	}

	private String getPhoneNumbers(Source argSource) {
		List<Element> phoneElementsList = argSource
				.getAllElementsByClass(PHONE_CLASS_NAME);
		if (phoneElementsList.size() == 1) {
			Element phoneElement = phoneElementsList.get(0);
			if (Precondition.checkNotNull(phoneElement)) {
				List<Element> phoneContentList = phoneElement
						.getAllElementsByClass(ITEM_CONTENT);
				if (phoneContentList.size() == 1) {
					Element phoneContentElement = phoneContentList.get(0);
					if (Precondition.checkNotNull(phoneContentElement)) {
						return phoneContentElement.getContent()
								.getTextExtractor().toString();
					}
				} else {
					System.out
							.println("Phone Content List Greater/Lesser Than One");
				}
			} else {
				System.out
						.println("Phone Element List Greater/Lesser Than One");
			}
		}
		return null;
	}
}
