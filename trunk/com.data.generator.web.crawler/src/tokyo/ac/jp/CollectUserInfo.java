package tokyo.ac.jp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.data.generator.extractor.HtmlExtractor;
import com.data.generator.file.impl.CsvWriter;
import com.data.generator.japan.university.MemberInfo;
import com.data.generator.util.Precondition;

public class CollectUserInfo {

	private HtmlExtractor htmlExtractor = new HtmlExtractor();
	private CollectEmails collectEmails = new CollectEmails(this.htmlExtractor);

	public Map<String, List<String>> collectNameVsEmailsListMap(
			Map<String, Map<String, String>> argTargetNameVsNameVsEmailUrlMap) {
		@SuppressWarnings("unchecked")
		Map<String, Map<String, String>> targetNameVsNameVsEmailUrlMap = (Map<String, Map<String, String>>) Precondition
				.ensureNotEmpty(argTargetNameVsNameVsEmailUrlMap,
						"TargetNameVsNameVsEmailUrlMap");
		Iterator<Entry<String, Map<String, String>>> outerIterator = targetNameVsNameVsEmailUrlMap
				.entrySet().iterator();
		Map<String, List<String>> nameVsEmailsListMap = new HashMap<String, List<String>>();
		while (outerIterator.hasNext()) {
			Entry<String, Map<String, String>> outerEntry = outerIterator
					.next();
			String target = outerEntry.getKey();
			if (target.equals("_self")) {
				Map<String, String> innerMap = outerEntry.getValue();
				Precondition.ensureNotEmpty(innerMap, "Inner Map");
				Iterator<Entry<String, String>> innerIterator = innerMap
						.entrySet().iterator();
				while (innerIterator.hasNext()) {
					Entry<String, String> innerEntry = innerIterator.next();
					String name = innerEntry.getKey();
					List<String> emailsList = this.collectEmails
							.collectEmails(innerEntry.getValue());
					if (Precondition.checkNotEmpty(name)
							&& Precondition.checkNotEmpty(emailsList)) {
						nameVsEmailsListMap.put(name, emailsList);
					}
				}
			}
		}
		Iterator<Entry<String, List<String>>> iterator = nameVsEmailsListMap
				.entrySet().iterator();
		Map<String[], List<List<String>>> map = new LinkedHashMap<String[], List<List<String>>>();
		MemberInfo memberInfo = getMemeberInfo(iterator);
		String[] headersArray = getHeaders(memberInfo.getEmailCount());
		map.put(headersArray, memberInfo.getTokensList());
		CsvWriter csvWriter = new CsvWriter("./output/tokyo.csv");
		csvWriter.write(map);
		System.exit(0);
		return null;
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

	public static void main(String[] args) {
		HtmlExtractor htmlExtractor = new HtmlExtractor();
		CollectUrls collectUrls = new CollectUrls(htmlExtractor);
		CollectUserInfo collectUserInfo = new CollectUserInfo();
		Map<String, Map<String, String>> map = collectUrls
				.collectNameVsUrl("http://www.e.u-tokyo.ac.jp/fservice/faculty/view.e.html");
		collectUserInfo.collectNameVsEmailsListMap(map);
		System.exit(0);
	}
}
