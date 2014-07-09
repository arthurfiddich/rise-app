package com.data.generato.econpapers.test;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.data.generator.extractor.HtmlExtractor;
import com.data.generator.ideas.repec.org.IdeasDetailedExtractor;
import com.data.generator.web.crawler.WebContentDownloader;

public class IdeasDetailedExtractorTest {
	public static void main(String[] args) {
		HtmlExtractor htmlExtractor = new HtmlExtractor();
		IdeasDetailedExtractor ideasDetailedExtractor = new IdeasDetailedExtractor(
				htmlExtractor);
		WebContentDownloader webContentDownloader = new WebContentDownloader();
		String htmlToken = webContentDownloader
				.getHtmlContent("http://ideas.repec.org/i/eall.html");
		Map<String, Map<String, String>> map = ideasDetailedExtractor
				.getPageNameVsLinksMap(htmlToken);
		Set<Entry<String, Map<String, String>>> entrySet = map.entrySet();
		Iterator<Entry<String, Map<String, String>>> iterator = entrySet
				.iterator();
		while (iterator.hasNext()) {
			Entry<String, Map<String, String>> entry = iterator.next();
			String key = entry.getKey();
			System.out.println("########################### Key " + key
					+ "###########################");
			Map<String, String> valueMap = entry.getValue();
			Iterator<Entry<String, String>> innerIterator = valueMap.entrySet()
					.iterator();
			while (innerIterator.hasNext()) {
				Entry<String, String> innerEntry = innerIterator.next();
				String innerKey = innerEntry.getKey();
				String innerValue = innerEntry.getValue();
				System.out.println("Inner Key: " + innerKey
						+ "\t Inner Value: " + innerValue);
			}
			System.out
					.println("########################### End ###########################");
		}
	}
}
