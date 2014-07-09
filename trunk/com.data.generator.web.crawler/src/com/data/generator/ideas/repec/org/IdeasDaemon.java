package com.data.generator.ideas.repec.org;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.ExecutorService;

import com.data.generato.econpapers.ExecutorServiceImpl;
import com.data.generato.econpapers.HtmlExtractorConstants;
import com.data.generator.extractor.HtmlExtractor;
import com.data.generator.util.Precondition;
import com.data.generator.web.crawler.WebContentDownloader;

public class IdeasDaemon {

	private ExecutorServiceImpl executorServiceImpl;

	public IdeasDaemon() {
		this.executorServiceImpl = new ExecutorServiceImpl();
		this.executorServiceImpl.initialize();
	}

	public void execute() {
		ExecutorService executorService = this.executorServiceImpl
				.getExecutorService();
		Precondition.ensureNotNull(executorService, "Executor Service");

		HtmlExtractor htmlExtractor = new HtmlExtractor();
		IdeasDetailedExtractor ideasDetailedExtractor = new IdeasDetailedExtractor(
				htmlExtractor);
		WebContentDownloader webContentDownloader = new WebContentDownloader();
		String htmlToken = webContentDownloader
				.getHtmlContent(HtmlExtractorConstants.ALL_LINKS);
		Map<String, Map<String, String>> pageNameVsPageLinksMap = ideasDetailedExtractor
				.getPageNameVsLinksMap(htmlToken);
		Precondition.ensureNotEmpty(pageNameVsPageLinksMap,
				"PageNameVsPageLinksMap");
		Set<Entry<String, Map<String, String>>> entrySet = pageNameVsPageLinksMap
				.entrySet();
		Iterator<Entry<String, Map<String, String>>> iterator = entrySet
				.iterator();
		while (iterator.hasNext()) {
			Entry<String, Map<String, String>> entry = iterator.next();
			if (Precondition.checkNotNull(entry)
					&& Precondition.checkNotEmpty(entry.getValue())) {
				IdeasRunnable ideasRunnable = new IdeasRunnable(
						entry.getValue(), htmlExtractor, "./output/Ideas/"
								+ entry.getKey() + ".csv");
				executorService.execute(ideasRunnable);
			}
		}
	}
	
	public static void main(String[] args) {
		IdeasDaemon ideasDaemon = new IdeasDaemon();
		ideasDaemon.execute();
	}
}
