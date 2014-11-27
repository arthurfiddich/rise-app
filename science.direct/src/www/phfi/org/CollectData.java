package www.phfi.org;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import net.htmlparser.jericho.Element;
import net.htmlparser.jericho.HTMLElementName;
import net.htmlparser.jericho.Source;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.data.generator.extractor.HtmlExtractor;
import com.data.generator.file.impl.CsvListWriter;
import com.data.generator.util.Precondition;

public class CollectData {

	private HtmlExtractor htmlExtractor;
	private WebDriver driver;

	public CollectData(HtmlExtractor argHtmlExtractor) {
		super();
		this.htmlExtractor = argHtmlExtractor;
		this.driver = new ChromeDriver();
		this.driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		this.driver.manage().window().maximize();
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
		List<List<String>> tokensList = new ArrayList<List<String>>();
		List<Element> oddElementsList = source.getAllElementsByClass("odd");
		List<String> headersList = getHeaders(oddElementsList.get(0));
		List<List<String>> oddTokensList = getTokens(oddElementsList, 1);
		List<Element> evenElementsList = source.getAllElementsByClass("even");
		List<List<String>> evenTokensList = getTokens(evenElementsList, 0);
		tokensList.addAll(oddTokensList);
		tokensList.addAll(evenTokensList);
		Map<List<String>, List<List<String>>> map = new HashMap<List<String>, List<List<String>>>();
		map.put(headersList, tokensList);
		return map;
	}

	private List<List<String>> getTokens(List<Element> argOddElementsList,
			int argStartIndex) {
		List<List<String>> tokensList = new ArrayList<List<String>>();
		for (int i = argStartIndex; i < argOddElementsList.size(); i++) {
			Element element = argOddElementsList.get(i);
			if (Precondition.checkNotNull(element)) {
				List<Element> tdElementsList = element
						.getAllElements(HTMLElementName.TD);
				List<String> tokens = new ArrayList<String>();
				for (Element tdElement : tdElementsList) {
					Element anchorTagElement = tdElement
							.getFirstElement(HTMLElementName.A);
					String value = "";
					if (Precondition.checkNotNull(anchorTagElement)) {
						value = anchorTagElement.getAttributeValue("href");
						if (Precondition.checkNotEmpty(value)) {
							if (value.startsWith("mailto:")) {
								value = value.replaceAll("mailto:", "");
							} else {
								value = "http://www.phfi.org" + value;
							}
						}
					} else {
						value = tdElement.getContent().getTextExtractor()
								.toString();
					}
					tokens.add(value);
				}
				System.out.println("###################### " + i
						+ " ################### " + tokens.toString());
				tokensList.add(tokens);
			}
		}
		return tokensList;
	}

	private List<String> getHeaders(Element argElement) {
		List<Element> thElementsList = argElement
				.getAllElements(HTMLElementName.TH);
		List<String> headersList = new ArrayList<String>();
		if (Precondition.checkNotEmpty(thElementsList)) {
			for (Element thElement : thElementsList) {
				headersList.add(thElement.getContent().getTextExtractor()
						.toString());
			}
		}
		return headersList;
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
		CollectData collectData = new CollectData(htmlExtractor);
		Map<List<String>, List<List<String>>> map = collectData
				.extract("http://www.phfi.org/faculty-a-researchers");
		CsvListWriter csvListWriter = new CsvListWriter(
				"./output/phfi/phfi.csv");
		csvListWriter.write(map);
	}
}
