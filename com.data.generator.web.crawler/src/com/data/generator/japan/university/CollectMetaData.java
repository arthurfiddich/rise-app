package com.data.generator.japan.university;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.htmlparser.jericho.Element;
import net.htmlparser.jericho.HTMLElementName;
import net.htmlparser.jericho.Source;

import org.apache.commons.lang3.StringUtils;

import com.data.generator.extractor.HtmlExtractor;
import com.data.generator.util.Precondition;
import com.data.generator.web.crawler.WebContentDownloader;

public class CollectMetaData {

	private final HtmlExtractor htmlExtractor;
	private String regex = "img\\ssrc=\\\"(.*?)\\\"\\>(.*?)<";

	public CollectMetaData(HtmlExtractor argHtmlExtractor) {
		super();
		this.htmlExtractor = argHtmlExtractor;
	}

	public HtmlExtractor getHtmlExtractor() {
		return this.htmlExtractor;
	}

	public List<String> getEmailsList(String argUrl) {
		String url = Precondition.ensureNotEmpty(argUrl, "URL");
		WebContentDownloader webContentDownloader = new WebContentDownloader();
		String htmlToken = webContentDownloader.getHtmlContent(url);
		Source source = this.getHtmlExtractor().getSourceBasedOnHtmlString(
				htmlToken);
		List<Element> divElements = source.getAllElementsByClass("clearfix");
		if (Precondition.checkNotEmpty(divElements)) {
			List<String> emailsList = new ArrayList<String>();
			for (Element divElement : divElements) {
				List<Element> paragraphElementsList = divElement
						.getAllElements(HTMLElementName.P);
				if (Precondition.checkNotEmpty(paragraphElementsList)) {
					for (Element paragraphElement : paragraphElementsList) {
						StringBuilder emailBuilder = new StringBuilder();
						String content = paragraphElement.toString();
						Pattern pattern = Pattern.compile(regex);
						Matcher matcher = pattern.matcher(content);
						while (matcher.find()) {
							String firstGroup = matcher.group(1);
							String secondGroup = matcher.group(2);
							String value = getContent(firstGroup);
							if (Precondition.checkNotEmpty(value)) {
								emailBuilder.append(value);
								if (Precondition.checkNotEmpty(secondGroup)) {
									if (!secondGroup.equals("...")) {
										emailBuilder.append(secondGroup);
									}
								}
							}
						}
						String email = emailBuilder.toString();
						if (Precondition.checkNotEmpty(email)) {
							emailsList.add(email);
						}
					}
				}
			}
			return emailsList;
		}
		return null;
	}

	private String getContent(String argValue) {
		String value = StringUtils.replace(argValue, "../", "");
		if (value.startsWith("alpha")) {
			value = StringUtils.replace(value, "alpha/", "");
			if (Precondition.checkNotEmpty(value)) {
				value = StringUtils.replace(value, ".gif", "");
				if (Precondition.checkNotEmpty(value)) {
					return value;
				}
			}
		}
		return null;
	}
}
