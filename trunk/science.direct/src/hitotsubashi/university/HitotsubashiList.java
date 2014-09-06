package hitotsubashi.university;

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
import com.data.generator.util.Precondition;

public class HitotsubashiList {

	private HtmlExtractor htmlExtractor;
	private WebDriver driver;

	public HitotsubashiList(HtmlExtractor argHtmlExtractor) {
		super();
		this.htmlExtractor = argHtmlExtractor;
		this.driver = new FirefoxDriver();
		this.driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	public Map<String, Map<String, String>> prepareMap(String argHomePageUrl) {
		String homePageUrl = Precondition.ensureNotEmpty(argHomePageUrl,
				"Home Page URL");
		this.getDriver().get(homePageUrl);
		String htmlContent = this.getDriver().getPageSource();
		Precondition.ensureNotEmpty(htmlContent, "Html Content");
		Source source = this.getHtmlExtractor().getSourceBasedOnHtmlString(
				htmlContent);
		Precondition.ensureNotNull(source, "Source");
		List<Element> tableElementsList = source
				.getAllElementsByClass("flame_table");
		Precondition.ensureNotEmpty(tableElementsList, "Table Element List");
		Element tableElement = tableElementsList.get(0);
		List<Element> trElementsList = tableElement
				.getAllElements(HTMLElementName.TR);
		Precondition.ensureNotEmpty(trElementsList, "TR Elements List");
		Map<String, Map<String, String>> nameVsEmailPartVsUrlMap = new HashMap<String, Map<String, String>>();
		for (Element trElement : trElementsList) {
			List<Element> tdElementsList = trElement
					.getAllElements(HTMLElementName.TD);
			// Precondition.ensureNotEmpty(tdElementsList, "TD Elements List");
			if (Precondition.checkNotEmpty(tdElementsList)) {
				String name = getName(tdElementsList.get(0));
				Map<String, String> emailPartVsUrlMap = new HashMap<String, String>();
				for (Element tdElement : tdElementsList) {
					List<Element> imageElementsList = tdElement
							.getAllElements(HTMLElementName.IMG);
					if (Precondition.checkNotEmpty(imageElementsList)) {
						Element imageElement = imageElementsList.get(0);
						if (Precondition.checkNotNull(imageElement)) {
							String value = imageElement
									.getAttributeValue("src");
							if (Precondition.checkNotEmpty(value)
									&& value.startsWith("../common")) {
								value = value.replace("../", "");
								value = "http://www.econ.hit-u.ac.jp/~koho/english/"
										+ value;
								String emailPart = tdElement.getContent()
										.getTextExtractor().toString();
								if (Precondition.checkNotEmpty(emailPart)
										&& Precondition.checkNotEmpty(value)) {
									System.out.println(name);
									emailPartVsUrlMap.put(emailPart, value);
								}
							}
						}
					}
				}
				if (Precondition.checkNotEmpty(name)
						&& Precondition.checkNotEmpty(emailPartVsUrlMap)) {
					nameVsEmailPartVsUrlMap.put(name, emailPartVsUrlMap);
				}
			}
		}
		return nameVsEmailPartVsUrlMap;
	}

	private String getName(Element argTdElement) {
		List<Element> anchorTagElementsList = argTdElement
				.getAllElements(HTMLElementName.A);
		boolean found = false;
		if (Precondition.checkNotEmpty(anchorTagElementsList)) {
			for (Element anchorTagElement : anchorTagElementsList) {
				String value = anchorTagElement.getAttributeValue("href");
				if (Precondition.checkNotEmpty(value)
						&& (value.startsWith("https://hri.ad.hit-u.ac.jp") || value
								.startsWith("http://www.econ.hit-u.ac.jp"))) {
					if (Precondition.checkNotNull(anchorTagElement)) {
						found = true;
						return anchorTagElement.getContent().getTextExtractor()
								.toString();
					}
				}
			}
		}
		if (!found) {
			List<Element> fontElementsList = argTdElement
					.getAllElements(HTMLElementName.FONT);
			if (Precondition.checkNotEmpty(fontElementsList)) {
				for (Element fontElement : fontElementsList) {
					String fontValue = fontElement.getAttributeValue("color");
					if (Precondition.checkNotEmpty(fontValue)
							&& fontValue.startsWith("#336699")) {
						return fontElement.getContent().getTextExtractor()
								.toString();
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

}
