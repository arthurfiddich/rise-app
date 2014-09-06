package tokyo.ac.jp;

import java.util.ArrayList;
import java.util.List;

import net.htmlparser.jericho.Element;
import net.htmlparser.jericho.HTMLElementName;
import net.htmlparser.jericho.Source;

import com.data.generator.extractor.HtmlExtractor;
import com.data.generator.util.Precondition;
import com.data.generator.web.crawler.WebContentDownloader;

public class CollectEmails {

	private final HtmlExtractor htmlExtractor;

	public CollectEmails(HtmlExtractor argHtmlExtractor) {
		super();
		this.htmlExtractor = argHtmlExtractor;
	}

	public HtmlExtractor getHtmlExtractor() {
		return this.htmlExtractor;
	}

	public List<String> collectEmails(String argEmailUrl) {
		String emailUrl = Precondition.ensureNotEmpty(argEmailUrl, "Email URL");
		WebContentDownloader webContentDownloader = new WebContentDownloader();
		String htmlContent = webContentDownloader.getHtmlContent(emailUrl);
		// Precondition.ensureNotEmpty(htmlContent, "Html Content");
		if (Precondition.checkNotEmpty(htmlContent)) {
			Source source = this.getHtmlExtractor().getSourceBasedOnHtmlString(
					htmlContent);
			Precondition.ensureNotNull(source, "Html Source");
			List<Element> trElementsList = source
					.getAllElements(HTMLElementName.TR);
			Precondition.checkNotEmpty(trElementsList);
			List<Element> list = getTrElementsList(trElementsList);
			List<String> emailsList = new ArrayList<String>();
			if (list.size() == 2) {
				Element element = list.get(1);
				List<Element> tdElementsList = element
						.getAllElements(HTMLElementName.TD);
				if (tdElementsList.size() == 2) {
					Element tdElement = tdElementsList.get(1);
					String content = tdElement.getContent().getTextExtractor()
							.toString();
					int index = content.indexOf("Please replace \"at\" with@");
					if (Precondition.checkNonNegative(index)) {
						String email = content.substring(0, index);
						email = email.replaceFirst(" at ", "@").trim();
						email = email.replaceAll(" dot ", ".");
						System.out.println(email);
						emailsList.add(email);
					}
				}
			}
			return emailsList;
		} else {
			System.out.println(argEmailUrl);
		}
		return null;
	}

	protected List<Element> getTrElementsList(List<Element> trElementsList) {
		List<Element> list = new ArrayList<Element>();
		for (Element trElement : trElementsList) {
			String attributeValue = trElement.getAttributeValue("valign");
			if (Precondition.checkNotEmpty(attributeValue)
					&& attributeValue.equals("top")) {
				list.add(trElement);
			}
		}
		return list;
	}

	public static void main(String[] args) {
		HtmlExtractor htmlExtractor = new HtmlExtractor();
		CollectEmails collectEmails = new CollectEmails(htmlExtractor);
		List<String> emailsList = collectEmails
				.collectEmails("http://www.e.u-tokyo.ac.jp/fservice/faculty/abe/abe.e/abe01.e.html");
		for (String email : emailsList) {
			System.out.println(email);
		}
	}
}
