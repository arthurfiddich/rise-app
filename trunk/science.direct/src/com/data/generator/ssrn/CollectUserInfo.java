package com.data.generator.ssrn;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.TimeUnit;

import net.htmlparser.jericho.Attribute;
import net.htmlparser.jericho.Element;
import net.htmlparser.jericho.HTMLElementName;
import net.htmlparser.jericho.Source;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.data.generator.extractor.HtmlExtractor;
import com.data.generator.util.Precondition;

public class CollectUserInfo {

	private HtmlExtractor htmlExtractor;
	private WebDriver driver;

	private CollectUserInfo(HtmlExtractor argHtmlExtractor) {
		super();
		this.htmlExtractor = argHtmlExtractor;
		this.driver = new FirefoxDriver();
		this.driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	public Map<String, List<String>> pageWiseExtraction(
			Map<String, String> argNameVsUrlMap) {
		@SuppressWarnings("unchecked")
		Map<String, String> nameVsUrlMap = (Map<String, String>) Precondition
				.ensureNotEmpty(argNameVsUrlMap, "Name Vs URL Map");
		Iterator<Entry<String, String>> iterator = nameVsUrlMap.entrySet()
				.iterator();
		while (iterator.hasNext()) {
			Entry<String, String> entry = iterator.next();
			List<String> emailsList = getEmailsList(entry.getValue());
		}
		return null;
	}

	public List<String> getEmailsList(String argEmailUrl) {
		if (Precondition.checkNotEmpty(argEmailUrl)) {
			this.driver.get(argEmailUrl);
			String htmlContent = this.driver.getPageSource();
			System.out.println(htmlContent);
			if (Precondition.checkNotEmpty(htmlContent)) {
				Source source = this.getHtmlExtractor()
						.getSourceBasedOnHtmlString(htmlContent);
				if (Precondition.checkNotNull(source)) {
					List<Element> affiliationElementList = source
							.getAllElementsByClass("affiliation");
					if (Precondition.checkNotEmpty(affiliationElementList)) {
						Element affiliationElement = affiliationElementList
								.get(0);
						List<Element> anchorTagElementsList = affiliationElement
								.getAllElements(HTMLElementName.A);
						if (Precondition.checkNotEmpty(anchorTagElementsList)) {
							for (Element anchorTagElement : anchorTagElementsList) {
								Attribute a = anchorTagElement.getAttributes()
										.get(1);
								List<Element> e = a.getAllElements();
								String attributeValue = anchorTagElement
										.getAttributeValue("onClick");
								String emailUrl = getUrl(attributeValue);
								if (Precondition.checkNotEmpty(emailUrl)) {
									this.driver.get(emailUrl);
									String emailContent = this.driver
											.getPageSource();
									return getEmails(emailContent);
								}
							}
						}
					}
				}
			}
		}
		return null;
	}

	protected List<String> getEmails(String emailContent) {
		if (Precondition.checkNotEmpty(emailContent)) {
			Source emailSource = this.getHtmlExtractor()
					.getSourceBasedOnHtmlString(emailContent);
			if (Precondition.checkNotNull(emailSource)) {
				List<Element> anchorTagElementsList = emailSource
						.getAllElements(HTMLElementName.A);
				if (Precondition.checkNotEmpty(anchorTagElementsList)) {
					for (Element anchorTagElement : anchorTagElementsList) {
						String attributeValue = anchorTagElement
								.getAttributeValue("href");
						if (Precondition.checkNotEmpty(attributeValue)
								&& attributeValue.startsWith("")) {

						}
					}
				}
			}
		}
		return null;
	}

	private String getUrl(String argAttributeValue) {
		String attributeValue = Precondition.ensureNotEmpty(argAttributeValue,
				"Attribute Value");
		if (attributeValue.startsWith("window.open(")) {
			attributeValue = StringUtils.replace(attributeValue,
					"window.open(", "");
			if (attributeValue.endsWith(")")) {
				attributeValue = attributeValue.substring(0,
						attributeValue.length() - 1);
				String[] parts = attributeValue.split(",");
				String firstPart = parts[0];
				firstPart = firstPart.substring(1);
				firstPart = firstPart.substring(0, firstPart.length() - 1);
				firstPart = firstPart.substring(3);
				return "http://papers.ssrn.com/sol3/" + firstPart;

			}
		}
		return null;
	}

	public HtmlExtractor getHtmlExtractor() {
		return this.htmlExtractor;
	}

	public static void main(String[] args) {
		CollectUserInfo c = new CollectUserInfo(new HtmlExtractor());
		List<String> emailsList = c
				.getEmailsList("http://papers.ssrn.com/sol3/cf_dev/AbsByAuth.cfm?per_id=9#");
	}
}
