package kelly.university;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import net.htmlparser.jericho.Element;
import net.htmlparser.jericho.HTMLElementName;
import net.htmlparser.jericho.Source;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.data.generator.extractor.HtmlExtractor;
import com.data.generator.file.impl.CsvWriter;
import com.data.generator.util.Precondition;

public class KellyList {

	private HtmlExtractor htmlExtractor;
	private WebDriver driver;

	public KellyList(HtmlExtractor argHtmlExtractor) {
		super();
		this.htmlExtractor = argHtmlExtractor;
		this.driver = new FirefoxDriver();
		this.driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	public List<User> getNameVsUrlMap(String argHomePageUrl) {
		String homePageUrl = Precondition.ensureNotEmpty(argHomePageUrl,
				"Home Page URL");
		this.getDriver().get(homePageUrl);
		String htmlContent = this.getDriver().getPageSource();
		Precondition.ensureNotEmpty(htmlContent, "Html Content");
		Source source = this.getHtmlExtractor().getSourceBasedOnHtmlString(
				htmlContent);
		Precondition.ensureNotNull(source, "Source");
		List<Element> facultyDirectoryElementsList = source
				.getAllElementsByClass("facultyDirectory");
		Precondition.ensureNotEmpty(facultyDirectoryElementsList,
				"Faculty Directory ElementsList");
		List<User> usersList = new ArrayList<User>();
		for (Element facultyDirectoryElement : facultyDirectoryElementsList) {
			List<Element> list = facultyDirectoryElement
					.getAllElementsByClass("contact");
			if (Precondition.checkNotEmpty(list)) {
				for (Element element : list) {
					List<Element> anchorTagElementsList = element
							.getAllElements(HTMLElementName.A);
					if (Precondition.checkNotEmpty(anchorTagElementsList)) {
						for (Element anchorTagElement : anchorTagElementsList) {
							String value = anchorTagElement
									.getAttributeValue("href");
							if (Precondition.checkNotEmpty(value)
									&& value.startsWith("mailto:")) {
								int index = value.indexOf("mailto:");
								if (Precondition.checkNonNegative(index)) {
									value = value.substring(
											0 + "mailto:".length(),
											value.length());
									Element staffNameElement = element
											.getAllElementsByClass("staffName")
											.get(0);
									String name = staffNameElement.getContent()
											.getTextExtractor().toString();
									User user = new User(name, value);
									usersList.add(user);
								}
							}
						}
					}
				}
			}
		}
		return usersList;
	}

	public WebDriver getDriver() {
		return this.driver;
	}

	public HtmlExtractor getHtmlExtractor() {
		return this.htmlExtractor;
	}

	public static void main(String[] args) {
		HtmlExtractor htmlExtractor = new HtmlExtractor();
		KellyList kellyTest = new KellyList(htmlExtractor);
		List<User> usersList = kellyTest
				.getNameVsUrlMap("http://kelley.iu.edu/facultyglobal/directory/Directory.cfm");
		Map<String[], List<List<String>>> headersArrayVsListMap = new HashMap<String[], List<List<String>>>();
		List<List<String>> outerList = new ArrayList<List<String>>();
		if (Precondition.checkNotEmpty(usersList)) {
			for (User user : usersList) {
				List<String> innerList = new ArrayList<String>();
				innerList.add(user.getName());
				innerList.add(user.getEmail());
				outerList.add(innerList);
			}
			String[] headersArray = { "Name", "Email" };
			headersArrayVsListMap.put(headersArray, outerList);
			CsvWriter csvWriter = new CsvWriter("./output/kelly/kelly.csv");
			csvWriter.write(headersArrayVsListMap);
		}
	}
}
