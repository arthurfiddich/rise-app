package www.christuniversity.in;

import java.util.ArrayList;
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

public class CollectData {

	private HtmlExtractor htmlExtractor;
	private WebDriver driver;

	public CollectData(HtmlExtractor argHtmlExtractor) {
		super();
		this.htmlExtractor = argHtmlExtractor;
		this.driver = new FirefoxDriver();
		this.driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	public Map<List<String>, List<List<String>>> extract(String argUrl) {
		String url = Precondition.ensureNotEmpty(argUrl, "URL");
		Map<List<String>, List<List<String>>> tokens = next(url);
		return tokens;
	}

	protected Map<List<String>, List<List<String>>> next(String url) {
		this.getDriver().get(url);
		String htmlContent = this.getDriver().getPageSource();
		Precondition.ensureNotEmpty(htmlContent, "Html Content");
		Source source = this.getHtmlExtractor().getSourceBasedOnHtmlString(
				htmlContent);
		Precondition.ensureNotNull(source, "Source");
		List<Element> tableElementsList = source
				.getAllElementsByClass("student_new");
		Precondition.ensureNotEmpty(tableElementsList, "Table Elements List");
		List<String> headersList = null;
		List<List<String>> tokens = new ArrayList<List<String>>();
		for (Element tableElement : tableElementsList) {
			List<Element> trElementsList = tableElement
					.getAllElements(HTMLElementName.TR);
			if (Precondition.checkNotEmpty(trElementsList)) {
				if (Precondition.checkEmpty(headersList)) {
					headersList = collectValues(trElementsList.get(0));
				}
				for (int i = 1; i < trElementsList.size(); i++) {
					List<String> tokensList = collectValues(trElementsList
							.get(i));
					if (Precondition.checkNotEmpty(tokensList)) {
						tokens.add(tokensList);
					}
				}
			}
		}
		return null;
	}

	private List<String> collectValues(Element argElement) {
		List<Element> tdElementsList = argElement
				.getAllElementsByClass("student_new");
		if (Precondition.checkNotEmpty(tdElementsList)) {
			List<String> tokensList = new ArrayList<String>();
			for (Element tdElement : tdElementsList) {
				Element strongElement = tdElement
						.getFirstElement(HTMLElementName.STRONG);
				if (Precondition.checkNotNull(strongElement)) {
					String value = strongElement.getContent()
							.getTextExtractor().toString();
					if(Precondition.checkEmpty(value)){
						value = "";
					}
					if(value.equals("Profile")){
						value = "";
					}
					tokensList.add(value);
				}
			}
			return tokensList;
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
