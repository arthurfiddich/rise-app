package com.data.generator.geo.names.api.impl;

import java.util.List;

import org.apache.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.data.generator.exceptions.BaseUncheckedException;
import com.data.generator.util.Precondition;

import edu.uci.ics.crawler4j.crawler.CrawlConfig;
import edu.uci.ics.crawler4j.crawler.Page;
import edu.uci.ics.crawler4j.fetcher.PageFetchResult;
import edu.uci.ics.crawler4j.fetcher.PageFetcher;
import edu.uci.ics.crawler4j.parser.HtmlParseData;
import edu.uci.ics.crawler4j.parser.ParseData;
import edu.uci.ics.crawler4j.parser.Parser;
import edu.uci.ics.crawler4j.url.WebURL;

public class WebCrawlerGeoLocation {

	private static Logger logger = LoggerFactory
			.getLogger(WebCrawlerGeoLocation.class);
	private Parser parser;
	private PageFetcher pageFetcher;

	public WebCrawlerGeoLocation() {
		CrawlConfig crawlConfig = new CrawlConfig();
		this.parser = new Parser(crawlConfig);
		this.pageFetcher = new PageFetcher(crawlConfig);
	}

	public Page download(String argUrl) {
		WebURL curURL = new WebURL();
		curURL.setURL(argUrl);
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
					throw new BaseUncheckedException("", e);
				}
			}
		} finally {
			if (Precondition.checkNotNull(fetchResult)) {
				fetchResult.discardContentIfNotConsumed();
			}
		}
		return null;
	}

	public HtmlParseData processUrl(String argUrl) {
		logger.info("Processing: " + argUrl);
		Page page = download(argUrl);
		if (Precondition.checkNotNull(page)) {
			ParseData parseData = page.getParseData();
			if (Precondition.checkNotNull(parseData)) {
				if (parseData instanceof HtmlParseData) {
					return (HtmlParseData) parseData;
				}
			} else {
				logger.info("Couldn't parse the content of the page.");
			}
		} else {
			logger.info("Couldn't fetch the content of the page.");
		}
		return null;
	}

	public String getText(HtmlParseData argHtmlParseData) {
		Precondition.ensureNotNull(argHtmlParseData, "HtmlParseData");
		return argHtmlParseData.getText();
	}

	public String getHtmlContent(HtmlParseData argHtmlParseData) {
		Precondition.ensureNotNull(argHtmlParseData, "HtmlParseData");
		return argHtmlParseData.getHtml();
	}
	
	public String getTitle(HtmlParseData argHtmlParseData){
		Precondition.ensureNotNull(argHtmlParseData, "HtmlParseData");
		return argHtmlParseData.getTitle();
	}
	
	public List<WebURL> getOutGoingUrls(HtmlParseData argHtmlParseData){
		Precondition.ensureNotNull(argHtmlParseData, "HtmlParseData");
		return argHtmlParseData.getOutgoingUrls();
	}

}
