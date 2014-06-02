package com.data.generator.web.crawler;

import org.apache.http.HttpStatus;

import com.data.generator.geo.names.api.impl.GeoLocationBuilder;
import com.data.generator.jericho.parser.ExtractLinks;
import com.data.generator.jericho.parser.ExtractTd;
import com.generator.data.xmlns.geo.names.api.v1.GeoName;
import com.generator.data.xmlns.geo.names.api.v1.GeoNames;

import edu.uci.ics.crawler4j.crawler.CrawlConfig;
import edu.uci.ics.crawler4j.crawler.Page;
import edu.uci.ics.crawler4j.fetcher.PageFetchResult;
import edu.uci.ics.crawler4j.fetcher.PageFetcher;
import edu.uci.ics.crawler4j.parser.HtmlParseData;
import edu.uci.ics.crawler4j.parser.ParseData;
import edu.uci.ics.crawler4j.parser.Parser;
import edu.uci.ics.crawler4j.url.WebURL;

public class Downloader {

	private Parser parser;
	private PageFetcher pageFetcher;

	public Downloader() {
		CrawlConfig config = new CrawlConfig();
		parser = new Parser(config);
		pageFetcher = new PageFetcher(config);
	}

	private Page download(String url) {
		WebURL curURL = new WebURL();
		curURL.setURL(url);
		PageFetchResult fetchResult = null;
		try {
			fetchResult = pageFetcher.fetchHeader(curURL);
			if (fetchResult.getStatusCode() == HttpStatus.SC_OK) {
				try {
					Page page = new Page(curURL);
					fetchResult.fetchContent(page);
					if (parser.parse(page, curURL.getURL())) {
						return page;
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		} finally {
			if (fetchResult != null) {
				fetchResult.discardContentIfNotConsumed();
			}
		}
		return null;
	}

	public void processUrl(String url) {
		System.out.println("Processing: " + url);
		Page page = download(url);
		if (page != null) {
			ParseData parseData = page.getParseData();
			if (parseData != null) {
				if (parseData instanceof HtmlParseData) {
					HtmlParseData htmlParseData = (HtmlParseData) parseData;
					// System.out.println("Title: " + htmlParseData.getTitle());
					// System.out.println("Text length: " +
					// htmlParseData.getText());
					String html = htmlParseData.getHtml();
					System.out.println("Html length: " + html);
					try {
						ExtractLinks.main(html);
					} catch (Exception e) {
						e.printStackTrace();
					}

				}
			} else {
				System.out.println("Couldn't parse the content of the page.");
			}
		} else {
			System.out.println("Couldn't fetch the content of the page.");
		}
		System.out.println("==============");
	}

	public static void main(String[] args) {
		Downloader downloader = new Downloader();
		// GeoLocationBuilder geo = new GeoLocationBuilder();
		// GeoNames geoNames = geo
		// .buildGeoNamesList("http://api.geonames.org/children?geonameId=2508807&username=shivaprasad.amar");
		// for (GeoName geoName : geoNames.getGeoname()) {
		// System.out.println(geoName.getToponymName());
		// }
//		downloader.processUrl("http://econpapers.repec.org/RAS/paa6.htm");
		downloader.processUrl("http://econpapers.repec.org/RAS/par395.htm");
		// downloader
		// .processUrl("http://api.geonames.org/children?geonameId=2508807&username=shivaprasad.amar");
		// downloader
		// .processUrl("http://dbpedia.org/sparql?default-graph-uri=http%3A%2F%2Fdbpedia.org&query=%0D%0ASELECT+DISTINCT+%3Fcategory+WHERE+%7B%3Fsubject+dcterms%3Asubject+%3Fcategory%7D+LIMIT+50000+Offset+0&format=text%2Fhtml&timeout=30000&debug=on");
		// downloader.processUrl("http://www.yahoo.com/");
	}
}
