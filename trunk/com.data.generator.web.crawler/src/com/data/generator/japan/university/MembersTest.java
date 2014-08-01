package com.data.generator.japan.university;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.data.generator.extractor.HtmlExtractor;
import com.data.generator.file.impl.CsvWriter;
import com.data.generator.util.Precondition;

public class MembersTest {
	public static void main(String[] args) {
		HtmlExtractor htmlExtractor = new HtmlExtractor();
		Members members = new Members(htmlExtractor);
		Map<String, String> linkVsNameMap = members
				.getMemeberNameVsLinkMap("http://www.econ.tohoku.ac.jp/econ/english/staff/index.html");
		Precondition.ensureNotEmpty(linkVsNameMap, "Link Vs Name Map");
		Iterator<Entry<String, String>> iterator = linkVsNameMap.entrySet()
				.iterator();
		Map<String, List<String>> nameVsEmailsList = new LinkedHashMap<String, List<String>>();
		while (iterator.hasNext()) {
			Entry<String, String> entry = iterator.next();
			CollectMetaData collectMetaData = new CollectMetaData(htmlExtractor);
			List<String> emailsList = collectMetaData.getEmailsList(entry
					.getKey());
			if (Precondition.checkNotEmpty(emailsList)) {
				nameVsEmailsList.put(entry.getValue(), emailsList);
			}
		}
		Iterator<Entry<String, List<String>>> emailIterator = nameVsEmailsList
				.entrySet().iterator();
		Map<String[], List<List<String>>> map = new LinkedHashMap<String[], List<List<String>>>();
		MemberInfo memberInfo = getMemeberInfo(emailIterator);
		String[] headersArray = getHeaders(memberInfo.getEmailCount());
		map.put(headersArray, memberInfo.getTokensList());
		CsvWriter csvWriter = new CsvWriter("./output/japanese.csv");
		csvWriter.write(map);
		System.exit(0);
	}

	private static String[] getHeaders(int argEmailsCount) {
		String[] headersArray = new String[argEmailsCount + 1];
		headersArray[0] = "Name";
		for (int i = 0; i < argEmailsCount; i++) {
			headersArray[i + 1] = "Email-" + (i + 1);
		}
		return headersArray;
	}

	private static MemberInfo getMemeberInfo(
			Iterator<Entry<String, List<String>>> argEmailIterator) {
		List<List<String>> tokensList = new ArrayList<List<String>>();
		int emailCount = 0;
		while (argEmailIterator.hasNext()) {
			Entry<String, List<String>> entry = argEmailIterator.next();
			List<String> value = entry.getValue();
			int size = value.size();
			if (emailCount < size) {
				emailCount = size;
			}
			if (Precondition.checkNotEmpty(value)) {
				List<String> tokens = new ArrayList<String>();
				tokens.add(entry.getKey());
				tokens.addAll(value);
				tokensList.add(tokens);
			}
		}
		MemberInfo memberInfo = new MemberInfo();
		memberInfo.setEmailCount(emailCount);
		memberInfo.setTokensList(tokensList);
		return memberInfo;
	}

}
