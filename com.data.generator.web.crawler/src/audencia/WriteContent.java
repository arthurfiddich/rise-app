package audencia;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.data.generator.extractor.HtmlExtractor;
import com.data.generator.file.impl.CsvWriter;
import com.data.generator.util.Precondition;

public class WriteContent {

	public void write(List<User> argUsersList) {
		List<User> usersList = (List<User>) Precondition.ensureNotEmpty(
				argUsersList, "Users List");
		int emailCount = getEmailCount(usersList);
		int phoneNumberCount = getPhoneNumberCount(usersList);
		Map<String[], List<List<String>>> headerArrayVsTokensListMap = new HashMap<String[], List<List<String>>>();
		List<List<String>> tokensList = new ArrayList<List<String>>();
		for (User user : usersList) {
			List<String> tokens = new ArrayList<String>();
			tokens.add(user.getName());
			List<String> emailsList = user.getEmailsList();
			if (Precondition.checkNonNegative(emailCount)) {
				for (int i = 0; i < emailCount - emailsList.size(); i++) {
					emailsList.add("");
				}
			}
			tokens.addAll(emailsList);
			List<String> phoneNumbersList = user.getPhonesList();
			if (Precondition.checkNonNegative(phoneNumberCount)) {
				for (int i = 0; i < phoneNumberCount - phoneNumbersList.size(); i++) {
					phoneNumbersList.add("");
				}
			}
			tokens.addAll(phoneNumbersList);
			tokensList.add(tokens);
		}
		List<String> headersList = new ArrayList<String>();
		headersList.add("Name");
		for (int i = 0; i < emailCount; i++) {
			headersList.add("Email-" + (i + 1));
		}
		for (int i = 0; i < phoneNumberCount; i++) {
			headersList.add("Phone-" + (i + 1));
		}
		String[] headerArray = headersList.toArray(new String[headersList
				.size()]);
		headerArrayVsTokensListMap.put(headerArray, tokensList);
		CsvWriter csvWriter = new CsvWriter("./output/audencia/output.csv");
		csvWriter.write(headerArrayVsTokensListMap);
		System.exit(0);
	}

	private int getPhoneNumberCount(List<User> argUsersList) {
		int phoneNumberCount = 0;
		for (User user : argUsersList) {
			int phoneNumbersSize = user.getPhonesList().size();
			if (phoneNumberCount < phoneNumbersSize) {
				phoneNumberCount = phoneNumbersSize;
			}
		}
		return phoneNumberCount;
	}

	private int getEmailCount(List<User> argUsersList) {
		int emailCount = 0;
		for (User user : argUsersList) {
			int emailsSize = user.getEmailsList().size();
			if (emailCount < emailsSize) {
				emailCount = emailsSize;
			}
		}
		return emailCount;
	}
	
	public static void main(String[] args) {
		WriteContent writeContent = new WriteContent();
		HtmlExtractor htmlExtractor = new HtmlExtractor();
		CollectUrls collectUrls = new CollectUrls(htmlExtractor);
		Map<String, String> nameVsEmailUrlMap = collectUrls.getNameVSEmailUrlMap("http://www.audencia.com/en/faculty-research/faculty/");
		CollectUserInfo collectUserInfo = new CollectUserInfo(htmlExtractor);
		List<User> userInfosList = collectUserInfo.getUserInfoList(nameVsEmailUrlMap);
		writeContent.write(userInfosList);
	}
}
