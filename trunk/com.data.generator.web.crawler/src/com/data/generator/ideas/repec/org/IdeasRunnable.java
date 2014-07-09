package com.data.generator.ideas.repec.org;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import net.htmlparser.jericho.HTMLElementName;

import com.data.generator.extractor.HtmlExtractor;
import com.data.generator.file.impl.CsvWriter;
import com.data.generator.util.Precondition;

public class IdeasRunnable implements Runnable {

	private final Map<String, String> linkNameVsLinkMap;
	// private final String outputFolder = "./output/Ideas";
	private HtmlExtractor htmlExtractor;
	private IdeasSiteExtractor ideasSiteExtractor;
	private CsvWriter csvWriter;

	public IdeasRunnable(Map<String, String> argValue,
			HtmlExtractor argHtmlExtractor, String argOutputFilePath) {
		this.linkNameVsLinkMap = argValue;
		this.htmlExtractor = argHtmlExtractor;
		this.ideasSiteExtractor = new IdeasSiteExtractor(
				this.getHtmlExtractor(), "idea.txt");
		this.csvWriter = new CsvWriter(argOutputFilePath);
	}

	@Override
	public void run() {
		Precondition.ensureNotEmpty(this.getLinkNameVsLinkMap(),
				"LinkName Vs LinkMap");
		Set<Entry<String, String>> entrySet = this.getLinkNameVsLinkMap()
				.entrySet();
		Iterator<Entry<String, String>> iterator = entrySet.iterator();
		List<Ideas> ideasList = new ArrayList<Ideas>();
		while (iterator.hasNext()) {
			Entry<String, String> entry = iterator.next();
			Ideas ideas = this.ideasSiteExtractor.extract(entry.getKey(),
					HTMLElementName.DL);
			if (Precondition.checkNotNull(ideas)) {
				ideasList.add(ideas);
			}
		}

		Map<String[], List<List<String>>> map = populateMap(ideasList);
		this.csvWriter.write(map);
	}

	private Map<String[], List<List<String>>> populateMap(
			List<Ideas> argIdeasList) {
		Map<String[], List<List<String>>> map = new LinkedHashMap<String[], List<List<String>>>();
		int emailCount = getEmailCount(argIdeasList);
		List<String> headersList = new ArrayList<String>();
		headersList.add("FirstName");
		headersList.add("LastName");
		for (int i = 0; i < emailCount; i++) {
			headersList.add("Email-" + (i + 1));
		}
		String[] headersArray = headersList.toArray(new String[headersList
				.size()]);
		List<List<String>> tokensList = new ArrayList<List<String>>();
		for (Ideas ideas : argIdeasList) {
			List<String> tokens = new ArrayList<String>();
			tokens.add(ideas.getFirstName());
			tokens.add(ideas.getLastName());
			List<String> emailsList = ideas.getEmailList();
			if (Precondition.checkNotEmpty(emailsList)) {
				int size = emailsList.size();
				if (emailCount > size) {
					for (int i = 0; i < emailCount - size; i++) {
						emailsList.add("");
					}
				}
				tokens.addAll(emailsList);
				// } else {
				// List<String> emails = new ArrayList<String>();
				// for (int i = 0; i < emailCount; i++) {
				// emails.add("");
				// }
				// tokens.addAll(emails);
				// }
				tokensList.add(tokens);
			}
		}
		map.put(headersArray, tokensList);
		return map;
	}

	private int getEmailCount(List<Ideas> argIdeasList) {
		int emailCount = 0;
		for (Ideas ideas : argIdeasList) {
			List<String> emailList = ideas.getEmailList();
			if (Precondition.checkNotEmpty(emailList)) {
				if (emailCount < emailList.size()) {
					emailCount = emailList.size();
				}
			}
		}
		return emailCount;
	}

	public Map<String, String> getLinkNameVsLinkMap() {
		return this.linkNameVsLinkMap;
	}

	// public String getOutputFolder() {
	// return this.outputFolder;
	// }

	public HtmlExtractor getHtmlExtractor() {
		return this.htmlExtractor;
	}

}
