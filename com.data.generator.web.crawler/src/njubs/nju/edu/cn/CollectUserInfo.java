package njubs.nju.edu.cn;

import java.util.ArrayList;
import java.util.Arrays;
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
		String htmlContent = webContentDownloader.getHtmlContent(argEmailUrl);
		Source source = this.getHtmlExtractor().getSourceBasedOnHtmlString(
				htmlContent);
		if (Precondition.checkNotNull(source)) {
			List<Element> paragraphElementsList = source
					.getAllElements(HTMLElementName.P);
			if (Precondition.checkNotEmpty(paragraphElementsList)) {
				List<String> emailsList = new ArrayList<String>();
				List<String> phonesList = new ArrayList<String>();
				for (Element paragraphElement : paragraphElementsList) {
					String value = paragraphElement.getContent()
							.getTextExtractor().toString();
					String startsWithEmail = startsWithEmail(value);
					boolean foundEmail = false;
					if (Precondition.checkNotEmpty(startsWithEmail)) {
						value = value.toLowerCase();
						value = value.substring(startsWithEmail.length() + 1)
								.trim();
						String[] emailsArray = value.split(",");
						if (Precondition.checkNotEmpty(emailsArray)) {
							emailsList.addAll(Arrays.asList(emailsArray));
						} else {
							emailsList.add(value);
						}
						foundEmail = true;
					}
					if (!foundEmail) {
						String startsWith = startsWithPhone(value);
						if (Precondition.checkNotEmpty(startsWith)) {
							value = value.substring(startsWith.length() + 1)
									.trim();
							String[] phoneArray = value.split(",");
							if (phoneArray.length < 2) {
								phoneArray = value.split("；");
							}
							if (Precondition.checkNotEmpty(phoneArray)) {
								phonesList.addAll(Arrays.asList(phoneArray));
							} else {
								phonesList.add(value);
							}
						}
					}
				}
				return new User(argName, emailsList, phonesList);
			}
		} else {
			System.out.println("Source Empty...........");
		}
		return null;
	}

	private String startsWithEmail(String argValue) {
		if (argValue.startsWith("Email")) {
			return "Email";
		} else if (argValue.startsWith("EMAIL")) {
			return "EMAIL";
		} else if (argValue.startsWith("Email Address")) {
			return "Email Address";
		} else if (argValue.startsWith("电子邮箱")) {
			return "电子邮箱";
		} else if (argValue.startsWith("电子信箱")) {
			return "电子信箱";
		} else if (argValue.startsWith("E-mail")) {
			return "E-mail";
		} else if (argValue.startsWith("email")) {
			return "email";
		} else if (argValue.startsWith("电子邮件")) {
			return "电子邮件";
		} else if (argValue.startsWith("E－MAIL")) {
			return "E－MAIL";
		}
		return "";
	}

	private String startsWithPhone(String argValue) {
		if (argValue.startsWith("Phone")) {
			return "Phone";
		} else if (argValue.startsWith("PHONE")) {
			return "PHONE";
		} else if (argValue.startsWith("Tel")) {
			return "Tel";
		} else if (argValue.startsWith("TEL(O)")) {
			return "TEL(O)";
		} else if (argValue.startsWith("联系电话")) {
			return "联系电话";
		} else if (argValue.startsWith("Tel/Fax")) {
			return "Tel/Fax";
		} else if (argValue.startsWith("tel")) {
			return "tel";
		} else if (argValue.startsWith("Tel.")) {
			return "Tel.";
		} else if (argValue.startsWith("office")) {
			return "office";
		} else if (argValue.startsWith("TEL")) {
			return "TEL";
		} else if (argValue.startsWith("(O)")) {
			return "(O";
		} else if (argValue.startsWith("联系方式")) {
			return "联系方式";
		} else if (argValue.startsWith("电话")) {
			return "电话";
		} else if (argValue.startsWith("电　　话")) {
			return "电　　话";
		} else if (argValue.startsWith("Telephone")) {
			return "Telephone";
		}
		return "";
	}
}
