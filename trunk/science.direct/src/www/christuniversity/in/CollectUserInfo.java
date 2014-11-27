package www.christuniversity.in;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import net.htmlparser.jericho.Element;
import net.htmlparser.jericho.HTMLElementName;
import net.htmlparser.jericho.Source;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.data.generator.extractor.HtmlExtractor;
import com.data.generator.util.Precondition;

public class CollectUserInfo {

	private HtmlExtractor htmlExtractor;
	private WebDriver driver;

	public CollectUserInfo(HtmlExtractor argHtmlExtractor) {
		super();
		this.htmlExtractor = argHtmlExtractor;
		this.driver = new ChromeDriver();
		this.driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		this.driver.manage().window().maximize();
	}

	public List<User> getUserInfoList(List<String> argUrlsList) {
		List<String> urlsList = (List<String>) Precondition.ensureNotEmpty(
				argUrlsList, "URL's List");
		List<User> usersList = new ArrayList<User>();
		for (String url : urlsList) {
			String pageUrl = getViewPageLink(url);
			if (Precondition.checkNotEmpty(pageUrl)) {
				User user = getContent(pageUrl);
				if (Precondition.checkNotNull(user)) {
					usersList.add(user);
				}
			}
		}
		return usersList;
	}

	private User getContent(String argPageUrl) {
		this.getDriver().get(argPageUrl);
		String content = this.getDriver().getPageSource();
		if (Precondition.checkNotEmpty(content)) {
			Source source = this.getHtmlExtractor().getSourceBasedOnHtmlString(
					content);
			List<Element> tableElementsList = source
					.getAllElementsByClass("sample");
			if (Precondition.checkNotEmpty(tableElementsList)) {
				User user = getInfo(tableElementsList.get(0));
				if (Precondition.checkNotNull(user)
						&& tableElementsList.size() > 2) {
					populateJournals(tableElementsList.get(2), user);
					makeEquals(user);
				} else {
					List<List<String>> totalTokensList = new ArrayList<List<String>>();
					totalTokensList.add(user.getValuesList());
					user.setTotalTokensList(totalTokensList);
				}
				return user;
			}
		}
		return null;
	}

	private void makeEquals(User argUser) {
		List<List<String>> journals = argUser.getJournalsList();
		List<String> values = argUser.getValuesList();
		List<List<String>> totalTokensList = new ArrayList<List<String>>();
		int valuesSize = values.size();
		values.addAll(journals.get(0));
		totalTokensList.add(values);
		int balance = journals.size() - 1;
		for (int i = 0; i < balance; i++) {
			List<String> tokens = new ArrayList<String>(balance);
			for (int j = 0; j < valuesSize; j++) {
				tokens.add("");
			}
			tokens.addAll(journals.get(i));
			totalTokensList.add(tokens);
		}
		argUser.setTotalTokensList(totalTokensList);
	}

	private void populateJournals(Element argTableElement, User argUser) {
		List<Element> trElementsList = argTableElement
				.getAllElements(HTMLElementName.TR);
		if (Precondition.checkNotEmpty(trElementsList)) {
			List<String> headersList = getHeaders(trElementsList.get(0));
			if (Precondition.checkNotEmpty(headersList)) {
				argUser.getKeysList().addAll(headersList);
				List<List<String>> tokens = new ArrayList<List<String>>();
				for (int i = 1; i < trElementsList.size(); i++) {
					List<String> tokensList = new ArrayList<String>();
					tokensList.addAll(getHeaders(trElementsList.get(i)));
					if (headersList.size() == tokensList.size()) {
						tokens.add(tokensList);
					}
				}
				argUser.setJournalsList(tokens);
			}
		}
	}

	private List<String> getHeaders(Element argElement) {
		List<Element> tdElementsList = argElement
				.getAllElementsByClass("student_new");
		List<String> headersList = new ArrayList<String>();
		if (Precondition.checkNotEmpty(tdElementsList)) {
			for (Element tdElement : tdElementsList) {
				headersList.add(tdElement.getContent().getTextExtractor()
						.toString());
			}
		}
		return headersList;
	}

	private User getInfo(Element argTableElement) {
		if (Precondition.checkNotNull(argTableElement)) {
			List<Element> trElementsList = argTableElement
					.getAllElements(HTMLElementName.TR);
			if (Precondition.checkNotEmpty(trElementsList)) {
				List<String> keysList = new ArrayList<String>();
				List<String> valuesList = new ArrayList<String>();
				for (Element trElement : trElementsList) {
					List<Element> tdElementsList = trElement
							.getAllElementsByClass("style5");
					if (Precondition.checkNotEmpty(tdElementsList)
							&& tdElementsList.size() == 2) {
						String key = tdElementsList.get(0).getContent()
								.getTextExtractor().toString();
						String value = tdElementsList.get(1).getContent()
								.getTextExtractor().toString();
						keysList.add(key);
						valuesList.add(value);
					} else {
						System.out.println("More TD'S");
					}
				}
				User user = new User();
				user.setKeysList(keysList);
				user.setValuesList(valuesList);
				return user;
			}
		}
		return null;
	}

	public String getViewPageLink(String argUrl) {
		if (Precondition.checkNotEmpty(argUrl)) {
			String url = argUrl;
			this.getDriver().get(url);
			String emailContent = this.getDriver().getPageSource();
			if (Precondition.checkNotEmpty(emailContent)) {
				Source source = this.getHtmlExtractor()
						.getSourceBasedOnHtmlString(emailContent);
				List<Element> addressElementsList = source
						.getAllElementsByClass("address-1");
				if (Precondition.checkNotEmpty(addressElementsList)) {
					for (Element addressElement : addressElementsList) {
						Element anchorElement = addressElement
								.getFirstElement(HTMLElementName.A);
						if (Precondition.checkNotNull(anchorElement)) {
							String attributeValue = anchorElement
									.getAttributeValue("href");
							if (Precondition.checkNotEmpty(attributeValue)
									&& attributeValue
											.startsWith("JavaScript:newPopup")) {
								attributeValue = attributeValue.replaceAll(
										"JavaScript:newPopup\\('", "");
								attributeValue = attributeValue.replaceAll(
										"'\\);", "");
								return attributeValue;
							}
						}
					}
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

	public static void main(String[] args) {
		System.setProperty("webdriver.chrome.driver",
				"C:\\devhome\\tools\\chromedriver_win32\\chromedriver.exe");
		HtmlExtractor htmlExtractor = new HtmlExtractor();
		CollectUserInfo collectUserInfo = new CollectUserInfo(htmlExtractor);
		ArrayList<String> arrayList = new ArrayList<String>();
		arrayList
				.add("http://www.christuniversity.in/FacultyProfile/guestfaculty_det.php?webid=33&guest_id=168");
		List<User> usersList = collectUserInfo.getUserInfoList(arrayList);
		ListWriter listWriter = new ListWriter(
				"./output/christuniversity/user.csv");
		listWriter.write(usersList);
	}

}
