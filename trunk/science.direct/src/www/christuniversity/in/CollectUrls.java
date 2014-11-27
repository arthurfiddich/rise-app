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

public class CollectUrls {

	private HtmlExtractor htmlExtractor;
	private WebDriver driver;

	public CollectUrls(HtmlExtractor argHtmlExtractor) {
		super();
		this.htmlExtractor = argHtmlExtractor;
		this.driver = new ChromeDriver();
		this.driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		this.driver.manage().window().maximize();
	}

	public List<String> extract(String argUrl) {
		String url = Precondition.ensureNotEmpty(argUrl, "URL");
		List<String> urlsList = new ArrayList<String>();
		urlsList.add(url);
		return next(url);
	}

	public List<String> next(String url) {
		this.getDriver().get(url);
		String htmlContent = this.getDriver().getPageSource();
		Precondition.ensureNotEmpty(htmlContent, "Html Content");
		Source source = this.getHtmlExtractor().getSourceBasedOnHtmlString(
				htmlContent);
		Precondition.ensureNotNull(source, "Source");
		List<Element> navigationsList = source
				.getAllElementsByClass("student_new");
		if (Precondition.checkNotEmpty(navigationsList)) {
			List<String> urlsList = new ArrayList<String>();
			for (Element element : navigationsList) {
				Element anchorTagElement = element
						.getFirstElement(HTMLElementName.A);
				if (Precondition.checkNotNull(anchorTagElement)) {
					String attributeValue = anchorTagElement
							.getAttributeValue("href");
					if (Precondition.checkNotEmpty(attributeValue)) {
						attributeValue = attributeValue.replaceAll(
								"JavaScript:popUpCal\\('", "");
						attributeValue = attributeValue.replaceAll("'\\);", "");
						if (attributeValue.startsWith("http")) {
							urlsList.add(attributeValue);
						}
					}
				}
			}
			return urlsList;
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
