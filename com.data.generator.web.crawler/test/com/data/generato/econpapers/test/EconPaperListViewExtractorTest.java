package com.data.generato.econpapers.test;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import com.data.generator.extractor.EconPaperListViewExtractor;
import com.data.generator.extractor.HtmlExtractor;

public class EconPaperListViewExtractorTest {
	public static void main(String[] args) {
		String sourceUrlString = "./output/input.html";
		HtmlExtractor htmlExtractor = new HtmlExtractor();
		EconPaperListViewExtractor econPaperListViewExtractor = new EconPaperListViewExtractor(
				htmlExtractor);
		Map<String, String> emailsList = econPaperListViewExtractor
				.getListViewLinks(sourceUrlString);
		Iterator<Entry<String, String>> iterator = emailsList.entrySet()
				.iterator();
		while (iterator.hasNext()) {
			Entry<String, String> entry = iterator.next();
			System.out.println(entry.getValue());
		}
	}
}
