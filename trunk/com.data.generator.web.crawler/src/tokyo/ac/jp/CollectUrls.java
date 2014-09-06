package tokyo.ac.jp;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.htmlparser.jericho.Element;
import net.htmlparser.jericho.HTMLElementName;
import net.htmlparser.jericho.Source;

import com.data.generator.extractor.HtmlExtractor;
import com.data.generator.util.Precondition;
import com.data.generator.web.crawler.WebContentDownloader;

public class CollectUrls {

	private static final String SELF = "_self";
	private static final String BLANK = "_blank";
	private final HtmlExtractor htmlExtractor;

	public CollectUrls(HtmlExtractor argHtmlExtractor) {
		super();
		this.htmlExtractor = argHtmlExtractor;
	}

	public HtmlExtractor getHtmlExtractor() {
		return this.htmlExtractor;
	}

	/**
	 * This method will catch all the ANCHOR TAGS and in that it filters based
	 * on an attribute value i.e., target="_self" and target="_blank".
	 * 
	 * Example Anchor Tag: <a href="abe/abe.e/abe01.e.html"
	 * target="_self"><b>ABE, Makoto</b> </a>
	 * 
	 * <a href="http://www.e.u-tokyo.ac.jp/finance-g/english/teacher/18.html"
	 * target="_blank">FUJII, Masaaki</a>
	 * 
	 * @param argHomePageUrl
	 * @param argAttributeValue
	 * @return
	 */
	public Map<String, Map<String, String>> collectNameVsUrl(
			String argHomePageUrl) {
		String homePageUrl = Precondition.ensureNotEmpty(argHomePageUrl,
				"Home Page URL");
		WebContentDownloader webContentDownloader = new WebContentDownloader();
		String htmlContent = webContentDownloader.getHtmlContent(homePageUrl);
		Precondition.ensureNotEmpty(htmlContent, "Html Content");
		Source source = this.getHtmlExtractor().getSourceBasedOnHtmlString(
				htmlContent);
		Precondition.ensureNotNull(source, "Html Source");
		List<Element> divElementsList = source
				.getAllElements(HTMLElementName.DIV);
		Precondition.ensureNotEmpty(divElementsList, "Div Elements List");
		Map<String, Map<String, String>> targetVsNameVsEmailUrlMap = new HashMap<String, Map<String, String>>();
		for (Element divElement : divElementsList) {
			String divAttributeValue = divElement.getAttributeValue("id");
			if (Precondition.checkNotEmpty(divAttributeValue)
					&& divAttributeValue.equals("main")) {
				List<Element> tableElementsList = divElement
						.getAllElements(HTMLElementName.TABLE);
				if (Precondition.checkNotEmpty(tableElementsList)) {
					for (Element tableElement : tableElementsList) {
						String tableAttributeValue = tableElement
								.getAttributeValue("cellpadding");
						Map<String, Map<String, String>> map = getElements(
								tableElement, tableAttributeValue);
						if (Precondition.checkNotEmpty(map)) {
							targetVsNameVsEmailUrlMap.putAll(map);
						}
					}
				}
			}
		}
		return targetVsNameVsEmailUrlMap;
	}

	protected Map<String, Map<String, String>> getElements(
			Element tableElement, String tableAttributeValue) {
		if (Precondition.checkNotEmpty(tableAttributeValue)
				&& tableAttributeValue.equals("2")) {
			List<Element> anchorTagElementsList = tableElement
					.getAllElements(HTMLElementName.A);
			Precondition.ensureNotEmpty(anchorTagElementsList,
					"Anchor Tag Elements List");
			Map<String, Map<String, String>> targetVsNameVsEmailUrlMap = new HashMap<String, Map<String, String>>();
			Map<String, String> nameVsEmailUrlMap = new HashMap<String, String>();
			Map<String, String> targetBlankNameVsEmailUrlMap = new HashMap<String, String>();
			for (Element anchorTagElement : anchorTagElementsList) {
				String value = anchorTagElement.getAttributeValue("target");
				String hrefValue = anchorTagElement.getAttributeValue("href");
				String name = anchorTagElement.getContent().getTextExtractor()
						.toString();
				if (Precondition.checkNotEmpty(hrefValue)
						&& Precondition.checkNotEmpty(name)) {
					if (!hrefValue.startsWith("http://")) {
						hrefValue = "http://www.e.u-tokyo.ac.jp/fservice/faculty/"
								+ hrefValue;
					}
					if (Precondition.checkNotEmpty(value) && value.equals(SELF)) {
						nameVsEmailUrlMap.put(name, hrefValue);
					} else if (Precondition.checkNotEmpty(value)
							&& value.equals(BLANK)) {
						targetBlankNameVsEmailUrlMap.put(name, hrefValue);
					}
				}
			}
			if (Precondition.checkNotEmpty(nameVsEmailUrlMap)) {
				targetVsNameVsEmailUrlMap.put(SELF, nameVsEmailUrlMap);
			}
			if (Precondition.checkNotEmpty(targetBlankNameVsEmailUrlMap)) {
				targetVsNameVsEmailUrlMap.put(BLANK,
						targetBlankNameVsEmailUrlMap);
			}
			return targetVsNameVsEmailUrlMap;
		}
		return null;
	}

}
