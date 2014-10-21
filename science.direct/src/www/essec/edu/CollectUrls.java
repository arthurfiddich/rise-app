package www.essec.edu;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.htmlparser.jericho.Element;
import net.htmlparser.jericho.HTMLElementName;
import net.htmlparser.jericho.Source;
import www.iese.edu.PageSource;

import com.data.generator.extractor.HtmlExtractor;
import com.data.generator.util.Precondition;

public class CollectUrls {

	private final HtmlExtractor htmlExtractor;
	private final PageSource pageSource;
	private static final String HOME_PAGE_URL = "http://www.essec.edu";

	public CollectUrls(HtmlExtractor argHtmlExtractor, PageSource argPageSource) {
		super();
		this.htmlExtractor = argHtmlExtractor;
		this.pageSource = argPageSource;
	}

	public Map<String, Map<String, String>> getDepartmentVsNameVsEmailrlMap(
			String argUrl) {
		String url = Precondition.ensureNotEmpty(argUrl, "URL");
		String htmlContent = this.getPageSource().getHtmlContent(url);
		Precondition.ensureNotEmpty(htmlContent, "Html Content");
		Source source = this.getHtmlExtractor().getSourceBasedOnHtmlString(
				htmlContent);
		Precondition.ensureNotNull(source, "Html Source");
		List<Element> elementsList = source.getAllElementsByClass("cache");
		Precondition.ensureNotEmpty(elementsList, "Elements List");
		Map<String, Map<String, String>> departmentNameVsNameVsEmailUrlMap = new HashMap<String, Map<String, String>>();
		for (Element element : elementsList) {
			List<Element> spanElementsList = element
					.getAllElements(HTMLElementName.SPAN);
			if (Precondition.checkNotEmpty(spanElementsList)) {
				String departmentName = getDepartmentName(spanElementsList);
				Map<String, String> nameVsEmailUrlMap = prepareNameAndEmailUrlMap(element);
				if (Precondition.checkNotEmpty(departmentName)
						&& Precondition.checkNotEmpty(nameVsEmailUrlMap)) {
					departmentNameVsNameVsEmailUrlMap.put(departmentName,
							nameVsEmailUrlMap);
				}
			}
		}
		return departmentNameVsNameVsEmailUrlMap;
	}

	private Map<String, String> prepareNameAndEmailUrlMap(Element argElement) {
		List<Element> anchorTagElementsList = argElement
				.getAllElementsByClass("Prof_bleu");
		Map<String, String> nameVsEmailUrlMap = new HashMap<String, String>();
		if (Precondition.checkNotEmpty(anchorTagElementsList)) {
			for (Element anchorTagElement : anchorTagElementsList) {
				String name = anchorTagElement.getContent().getTextExtractor()
						.toString();
				String attributeValue = anchorTagElement
						.getAttributeValue("href");
				if (Precondition.checkNotEmpty(attributeValue)) {
					String emailUrl = HOME_PAGE_URL + attributeValue;
					if (Precondition.checkNotEmpty(name)
							&& Precondition.checkNotEmpty(emailUrl)) {
						nameVsEmailUrlMap.put(name, emailUrl);
					}
				}
			}
		}
		return nameVsEmailUrlMap;
	}

	protected String getDepartmentName(List<Element> spanElementsList) {
		Element spanElement = spanElementsList.get(0);
		if (Precondition.checkNotNull(spanElement)) {
			return spanElement.getContent().getTextExtractor().toString();
		}
		return null;
	}

	public HtmlExtractor getHtmlExtractor() {
		return this.htmlExtractor;
	}

	public PageSource getPageSource() {
		return this.pageSource;
	}

}
