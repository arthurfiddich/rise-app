package www.essec.edu;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import www.iese.edu.PageSource;
import www.iese.edu.User;

import com.data.generator.extractor.HtmlExtractor;
import com.data.generator.file.impl.CsvWriter;
import com.data.generator.util.Precondition;

public class Extract {

	public static void main(String[] args) {
		HtmlExtractor htmlExtractor = new HtmlExtractor();
		PageSource pageSource = new PageSource();
		CollectUrls collectUrls = new CollectUrls(htmlExtractor, pageSource);
		CollectUserInfo collectUserInfo = new CollectUserInfo(htmlExtractor);
		Extract extract = new Extract();
		Map<String, Map<String, String>> departmentNameVsNameVsEmailUrlMap = collectUrls
				.getDepartmentVsNameVsEmailrlMap("http://www.essec.edu/professorsCV/showProfsByDpt.do?keyUrl=7");
		Precondition.ensureNotEmpty(departmentNameVsNameVsEmailUrlMap,
				"Name Vs Email URL Map");
		Iterator<Entry<String, Map<String, String>>> iterator = departmentNameVsNameVsEmailUrlMap
				.entrySet().iterator();
		while (iterator.hasNext()) {
			Entry<String, Map<String, String>> entry = iterator.next();
			String departmentName = entry.getKey();
			Map<String, String> nameVsEmailUrlMap = entry.getValue();
			List<User> userInfoList = collectUserInfo
					.getUserInfoList(nameVsEmailUrlMap);
			extract.write(userInfoList, departmentName);
		}
		System.exit(0);
	}

	public void write(List<User> argUsersList, String argDepartmentName) {
		List<User> usersList = (List<User>) Precondition.ensureNotEmpty(
				argUsersList, "Users List");
		int emailCount = getEmailCount(usersList);
		Map<String[], List<List<String>>> headerArrayVsTokensListMap = new HashMap<String[], List<List<String>>>();
		List<List<String>> tokensList = new ArrayList<List<String>>();
		for (User user : usersList) {
			List<String> tokens = new ArrayList<String>();
			tokens.add(user.getName());
			List<String> emailsList = user.getEmailsList();
			if (Precondition.checkNonNegative(emailCount)) {
				int size = emailsList.size();
				for (int i = 0; i < emailCount - size; i++) {
					emailsList.add("");
				}
			}
			tokens.addAll(emailsList);
			tokensList.add(tokens);
		}
		List<String> headersList = new ArrayList<String>();
		headersList.add("Name");
		for (int i = 0; i < emailCount; i++) {
			headersList.add("Email-" + (i + 1));
		}
		String[] headerArray = headersList.toArray(new String[headersList
				.size()]);
		headerArrayVsTokensListMap.put(headerArray, tokensList);
		try {
			CsvWriter csvWriter = new CsvWriter("./output/essec/"
					+ argDepartmentName + ".csv");
			csvWriter.write(headerArrayVsTokensListMap);
		} catch (Exception e) {
			throw new RuntimeException(
					"Exception while writing the file content into a file: ", e);
		}
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
}
