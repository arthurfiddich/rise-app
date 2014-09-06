package georgetown;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.data.generator.extractor.HtmlExtractor;
import com.data.generator.file.impl.CsvWriter;
import com.data.generator.japan.university.MemberInfo;
import com.data.generator.util.Precondition;

public class GeorgeTownTest {

	private HtmlExtractor htmlExtractor = new HtmlExtractor();
	private CollectUrls collectUrls = new CollectUrls(htmlExtractor);
	// private List<String> urlsList = new ArrayList<String>();
	private CollectUserInfo collectUserInfo = new CollectUserInfo(htmlExtractor);

	public GeorgeTownTest() {

	}

	public void extract(String argOutputFileName, String argUrl) {
		String url = Precondition.ensureNotEmpty(argUrl, "URL");
		Map<String, Map<String, String>> nameVsEmailUrlMap = this.collectUrls
				.collectNameVsUrl(url);
		if (Precondition.checkNotEmpty(nameVsEmailUrlMap)) {
			Iterator<Entry<String, Map<String, String>>> iterator = nameVsEmailUrlMap
					.entrySet().iterator();
			Map<String, List<User>> nameVsUserMap = new LinkedHashMap<String, List<User>>();
			while (iterator.hasNext()) {
				Entry<String, Map<String, String>> entry = iterator.next();
				Map<String, String> nameVsUrlMap = entry.getValue();
				if (Precondition.checkNotEmpty(nameVsUrlMap)) {
					Iterator<Entry<String, String>> inneriterator = nameVsUrlMap
							.entrySet().iterator();
					while (inneriterator.hasNext()) {
						Entry<String, String> innerEntry = inneriterator.next();
						String name = innerEntry.getKey();
						String emailUrl = innerEntry.getValue();
						List<User> usersList = this.collectUserInfo
								.getMemberInfo(emailUrl);
						if (Precondition.checkNotEmpty(usersList)
								&& Precondition.checkNotEmpty(name)) {
							nameVsUserMap.put(name, usersList);
						}
					}
				}
			}

			Iterator<Entry<String, List<User>>> emailIterator = nameVsUserMap
					.entrySet().iterator();
			Map<String[], List<List<String>>> map = new LinkedHashMap<String[], List<List<String>>>();
			MemberInfo memberInfo = getMemeberInfo(emailIterator);
			String[] headersArray = getHeaders(memberInfo.getEmailCount());
			List<List<String>> list = getTokensList(memberInfo.getTokensList(),
					headersArray.length);
			map.put(headersArray, list);
			CsvWriter csvWriter = new CsvWriter("./output/georgetown/"
					+ argOutputFileName + ".csv");
			csvWriter.write(map);
		}
	}

	private List<List<String>> getTokensList(List<List<String>> argTokensList,
			int argEmailCount) {
		List<List<String>> list = new ArrayList<List<String>>();
		for (List<String> tokensList : argTokensList) {
			List<String> l = new ArrayList<String>();
			l.addAll(tokensList);
			if (tokensList.size() < argEmailCount) {
				int balance = argEmailCount - tokensList.size();
				for (int i = 0; i < balance; i++) {
					l.add("");
				}
			}
			list.add(l);
		}
		return list;
	}

	private static String[] getHeaders(int argEmailsCount) {
		String[] headersArray = new String[argEmailsCount + 2];
		headersArray[0] = "Name";
		headersArray[1] = "Phone";
		for (int i = 1; i <= argEmailsCount; i++) {
			headersArray[i + 1] = "Email-" + (i);
		}
		return headersArray;
	}

	private static MemberInfo getMemeberInfo(
			Iterator<Entry<String, List<User>>> argEmailIterator) {
		List<List<String>> tokensList = new ArrayList<List<String>>();
		int emailCount = 0;
		while (argEmailIterator.hasNext()) {
			Entry<String, List<User>> entry = argEmailIterator.next();
			List<User> value = entry.getValue();
			for (User user : value) {
				List<String> emailsList = user.getEmailsList();
				int size = emailsList.size();
				if (emailCount < size) {
					emailCount = size;
				}
				if (Precondition.checkNotEmpty(value)
						&& Precondition.checkNotEmpty(emailsList)) {
					List<String> tokens = new ArrayList<String>();
					tokens.add(entry.getKey());
					tokens.add(user.getPhoneNumber());
					tokens.addAll(emailsList);
					tokensList.add(tokens);
				}
			}
		}
		MemberInfo memberInfo = new MemberInfo();
		memberInfo.setEmailCount(emailCount);
		memberInfo.setTokensList(tokensList);
		return memberInfo;
	}

	public static void main(String[] args) {
		GeorgeTownTest georgeTownTest = new GeorgeTownTest();
		for (int i = 67; i <= 90; i++) {
			String homePageUrl = "http://explore.georgetown.edu/faculty/index.cfm?Action=List&Letter=";
			char ch = (char) i;
			homePageUrl += ch;
			georgeTownTest.extract(String.valueOf(ch), homePageUrl);
		}
		System.exit(0);
	}
}
