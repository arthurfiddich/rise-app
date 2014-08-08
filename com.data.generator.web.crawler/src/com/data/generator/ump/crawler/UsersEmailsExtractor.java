package com.data.generator.ump.crawler;

import java.util.ArrayList;
import java.util.List;

import net.htmlparser.jericho.Source;

import com.data.generator.extractor.HtmlExtractor;
import com.data.generator.util.Precondition;
import com.data.generator.web.crawler.WebContentDownloader;

public class UsersEmailsExtractor {

	private final HtmlExtractor htmlExtractor;

	public UsersEmailsExtractor(HtmlExtractor argHtmlExtractor) {
		super();
		this.htmlExtractor = argHtmlExtractor;
	}

	public List<User> getUsersList(String argHomePageUrl) {
		String homePage = argHomePageUrl;
		List<String> linksList = new ArrayList<String>();
		linksList.add(homePage);
		for (int i = 1; i <= 11; i++) {
			String link = prepareLink(i, homePage);
			linksList.add(link);
		}
		for (String link : linksList) {
			List<User> usersList = getUsers(link);
		}
		return null;
	}

	private String prepareLink(int argI, String argHomePage) {
		// http://fkee.ump.edu.my/index.php/en/component/comprofiler/userslist/People?Itemid=105&limit=10&start=10
		StringBuilder linkBuilder = new StringBuilder();
		linkBuilder.append(argHomePage);
		linkBuilder.append("&limit=10&start=");
		linkBuilder.append(argI * 10);
		return linkBuilder.toString();
	}

	public List<User> getUsers(String argHomePageUrl) {
		String url = Precondition.ensureNotEmpty(argHomePageUrl, "URL");
		WebContentDownloader webContentDownloader = new WebContentDownloader();
		String htmlToken = webContentDownloader.getHtmlContent(url);
		Source source = this.getHtmlExtractor().getSourceBasedOnHtmlString(
				htmlToken);
//		source.getAllEl
		return null;
	}

	public HtmlExtractor getHtmlExtractor() {
		return this.htmlExtractor;
	}

}
