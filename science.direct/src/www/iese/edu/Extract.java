package www.iese.edu;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.data.generator.extractor.HtmlExtractor;
import com.data.generator.file.impl.CsvWriter;
import com.data.generator.util.Precondition;

public class Extract {
	public static void main(String[] args) {
		HtmlExtractor htmlExtractor = new HtmlExtractor();
		CollectUrls collectUrls = new CollectUrls(htmlExtractor);
		List<String> urlsList = collectUrls.collectUrls(11,20);
		Map<String, String> nameVsEmailUrlMap = collectUrls
				.getNameVsEmailUrlMap(urlsList);
		CollectUserInfo collectUserInfo = new CollectUserInfo(htmlExtractor);
		List<www.iese.edu.User> userInfoList = collectUserInfo
				.getUserInfoList(nameVsEmailUrlMap);
		Map<String[], List<List<String>>> mapTokens = new HashMap<String[], List<List<String>>>();
		mapTokens = prepareMap(userInfoList);
		CsvWriter writer = new CsvWriter("./output/iese/" + "11-20" + ".csv");
		writer.write(mapTokens);
		System.exit(0);
	}

	private static Map<String[], List<List<String>>> prepareMap(
			List<User> argUsersList) {
		List<User> usersList = (List<User>) Precondition.ensureNotEmpty(
				argUsersList, "Users List");
		Map<String[], List<List<String>>> map = new HashMap<String[], List<List<String>>>();
		List<List<String>> list = new ArrayList<List<String>>();
		// int number = getHighestNumber(usersList);
		for (User user : usersList) {
			List<String> li = new ArrayList<String>();
			li.add(user.getName());
			List<String> emailsList = user.getEmailsList();
			if (emailsList.size() == 1) {
				emailsList.add("");
			}
			li.addAll(emailsList);
			list.add(li);
		}
		String[] tokensArray = new String[3];
		tokensArray[0] = "Name";
		for (int i = 1; i <= 2; i++) {
			tokensArray[i] = "Email" + i;
		}
		map.put(tokensArray, list);
		return map;
	}
}
