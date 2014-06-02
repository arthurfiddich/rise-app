package com.data.generato.econpapers.test;

import java.util.List;

import com.data.generato.econpapers.EconPaperEmailExtractor;
import com.data.generato.econpapers.Email;
import com.data.generato.econpapers.HtmlExtractor;

public class EconPaperHtmlExtractorTest {
	public static void main(String[] args) {
		String sourceUrlString = "./output/input.html";
		HtmlExtractor htmlExtractor = new HtmlExtractor();
		EconPaperEmailExtractor econPaperHtmlExtractor = new EconPaperEmailExtractor(
				htmlExtractor);
		List<Email> emailsList = econPaperHtmlExtractor
				.getAllEmails(sourceUrlString);
		for (Email email : emailsList) {
			System.out.println(email.toString());
		}
	}
}
