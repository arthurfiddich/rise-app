package keio.university;

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
import com.data.generator.util.Precondition;

public class CollectUrls {

	private HtmlExtractor htmlExtractor;
	private WebDriver driver;

	public CollectUrls(HtmlExtractor argHtmlExtractor) {
		super();
		this.htmlExtractor = argHtmlExtractor;
		this.driver = new FirefoxDriver();
		this.driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	public Map<String, String> extract(String argUrl) {
		String url = Precondition.ensureNotEmpty(argUrl, "URL");
		List<String> urlsList = new ArrayList<String>();
		urlsList.add(url);
		next(url, urlsList);
		if (Precondition.checkNotEmpty(urlsList)) {
			Map<String, String> maps = new HashMap<String, String>();
			for (String urlToken : urlsList) {
				Map<String, String> map = collectNameVsEmailUrlMap(urlToken);
				if(Precondition.checkNotEmpty(map)){
					maps.putAll(map);
				}
			}
			return maps;
		}
		return null;
	}

	protected void next(String url, List<String> argUrlsList) {
		this.getDriver().get(url);
		String htmlContent = this.getDriver().getPageSource();
		Precondition.ensureNotEmpty(htmlContent, "Html Content");
		Source source = this.getHtmlExtractor().getSourceBasedOnHtmlString(
				htmlContent);
		Precondition.ensureNotNull(source, "Source");
		List<Element> navigationsList = source.getAllElementsByClass("hitnv");
		if (Precondition.checkNotEmpty(navigationsList)) {
			Element navigationElement = navigationsList.get(0);
			if (Precondition.checkNotNull(navigationElement)) {
				List<Element> anchorTagElementsList = navigationElement
						.getAllElements(HTMLElementName.A);
				if (Precondition.checkNotEmpty(anchorTagElementsList)) {
					for (Element anchorTagElement : anchorTagElementsList) {
						String value = anchorTagElement.getContent()
								.getTextExtractor().toString();
						String nextUrl = null;
						if (value.equals("next &gt")) {
							nextUrl = anchorTagElement
									.getAttributeValue("href");
							argUrlsList.add(nextUrl);
						} else if (value.equals("next >")) {
							nextUrl = anchorTagElement
									.getAttributeValue("href");
							argUrlsList.add(nextUrl);
						}
						if (Precondition.checkNotEmpty(nextUrl)) {
							next(nextUrl, argUrlsList);
						}
					}
				}
			}
		}
	}

	public Map<String, String> collectNameVsEmailUrlMap(String argUrl) {
		String url = Precondition.ensureNotEmpty(argUrl, "URL");
		this.getDriver().get(url);
		String htmlContent = this.getDriver().getPageSource();
		Source source = this.getHtmlExtractor().getSourceBasedOnHtmlString(
				htmlContent);
		Precondition.checkNotNull(source);
		List<Element> elementsList = source
				.getAllElementsByClass("staffsrchbox lbox");
		if (Precondition.checkNotEmpty(elementsList)) {
			Map<String, String> nameVsEmailUrlMap = new HashMap<String, String>();
			for (Element element : elementsList) {
				List<Element> insideBoxElementsList = element
						.getAllElementsByClass("insidebox");
				if (Precondition.checkNotEmpty(insideBoxElementsList)) {
					for (Element insideBoxElement : insideBoxElementsList) {
						List<Element> memebersElementList = insideBoxElement
								.getAllElementsByClass("numb");
						if (Precondition.checkNotEmpty(memebersElementList)) {
							for (Element memberElement : memebersElementList) {
								List<Element> anchorTagElementsList = memberElement
										.getAllElements(HTMLElementName.A);
								if (Precondition
										.checkNotEmpty(anchorTagElementsList)) {
									for (Element anchorTagElement : anchorTagElementsList) {
										String hrefValue = anchorTagElement
												.getAttributeValue("href");
										String name = anchorTagElement
												.getContent()
												.getTextExtractor().toString();
										if (Precondition
												.checkNotEmpty(hrefValue)) {
											nameVsEmailUrlMap.put(name,
													hrefValue);
										}
									}
								}
							}
						}
					}
				}
			}
			return nameVsEmailUrlMap;
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
