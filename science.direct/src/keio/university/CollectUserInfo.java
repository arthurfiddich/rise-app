package keio.university;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.TimeUnit;

import net.htmlparser.jericho.Element;
import net.htmlparser.jericho.HTMLElementName;
import net.htmlparser.jericho.Source;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.data.generator.extractor.HtmlExtractor;
import com.data.generator.util.Precondition;

public class CollectUserInfo {

	private HtmlExtractor htmlExtractor;
	private WebDriver driver;

	public CollectUserInfo(HtmlExtractor argHtmlExtractor) {
		super();
		this.htmlExtractor = argHtmlExtractor;
		this.driver = new FirefoxDriver();
		this.driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
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
			this.getDriver().get(emailUrl);
			String emailContent = this.getDriver().getPageSource();
			if (Precondition.checkNotEmpty(emailContent)) {
				Source source = this.getHtmlExtractor()
						.getSourceBasedOnHtmlString(emailContent);
				List<Element> tableElementsList = source
						.getAllElementsByClass("staff stfsub1");
				if (Precondition.checkNotEmpty(tableElementsList)) {
					for (Element tableElement : tableElementsList) {
						List<Element> trElementsList = tableElement
								.getAllElements(HTMLElementName.TR);
						if (Precondition.checkNotEmpty(trElementsList)) {
							List<String> emails = new ArrayList<String>();
							for (Element trElement : trElementsList) {
								List<Element> imageElementsList = trElement
										.getAllElements(HTMLElementName.IMG);
								if (Precondition
										.checkNotEmpty(imageElementsList)) {
									StringBuilder emailBuilder = new StringBuilder();
									List<String> emailsList = new ArrayList<String>();
									boolean found = false;
									for (int i = 1; i < imageElementsList
											.size(); i++) {
										Element imageElement = imageElementsList
												.get(i);
										String emailToken = getEmailCharacter(imageElement);
										if (Precondition
												.checkNotEmpty(emailToken)) {
											if (emailToken.equals("atmark")) {
												emailToken = "@";
											} else if (emailToken.equals("dot")) {
												emailToken = ".";
											} else if (emailToken.equals(",")) {
												emailsList.add(emailBuilder
														.toString());
												emailBuilder = new StringBuilder();
												found = true;
											}else{
												found = false;
											}
											if (!found) {
												emailBuilder.append(emailToken);
											}
										}
									}
									String email = emailBuilder.toString();
									if (Precondition.checkNotEmpty(email)) {
										emailsList.add(email);
									}
									emails.addAll(emailsList);
								}
							}
							return emails;
						}
					}
				}
			}
		}
		return null;
	}

	private String getEmailCharacter(Element argImageElement) {
		Element imageElement = argImageElement;
		String srcValue = imageElement.getAttributeValue("src");
		if (Precondition.checkNotEmpty(srcValue)) {
			int index = srcValue.lastIndexOf("/");
			if (Precondition.checkNonNegative(index)) {
				String emailToken = srcValue.substring(index + 1,
						srcValue.length());
				int gifIndex = emailToken.indexOf(".gif");
				if (Precondition.checkNonNegative(gifIndex)) {
					return emailToken.substring(0, gifIndex);
				}
			}
		}
		return null;
	}

	public HtmlExtractor getHtmlExtractor() {
		return this.htmlExtractor;
	}

	public WebDriver getDriver() {
		return this.driver;
	}

}
