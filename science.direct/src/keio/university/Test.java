package keio.university;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.data.generator.extractor.HtmlExtractor;
import com.data.generator.file.impl.CsvWriter;
import com.data.generator.util.Precondition;

public class Test {

	private static List<String> list = new ArrayList<String>();
	private static Map<String, String> map = new HashMap<String, String>();
	static {
		map.put("Mechanical Engineering",
				"http://yagamix.st.keio.ac.jp/tprofile/e_index.html?shozoku=Department%20of%20Mechanical%20Engineering&cond=OR&search=%E6%A4%9C%E7%B4%A2");
		map.put("Electronics Electrical Engineering",
				"http://yagamix.st.keio.ac.jp/tprofile/e_index.html?shozoku=Department%20of%20Electronics%20and%20Electrical%20Engineering&cond=OR&search=%E6%A4%9C%E7%B4%A2");
		map.put("Applied Chemistry",
				"http://yagamix.st.keio.ac.jp/tprofile/e_index.html?shozoku=Department%20of%20Applied%20Chemistry&cond=OR&search=%E6%A4%9C%E7%B4%A2");
		map.put("Applied Physics",
				"http://yagamix.st.keio.ac.jp/tprofile/e_index.html?shozoku=Department%20of%20Applied%20Physics%20and%20Physico-Informatics&cond=OR&search=%E6%A4%9C%E7%B4%A2");
		map.put("Administration Engineering",
				"http://yagamix.st.keio.ac.jp/tprofile/e_index.html?shozoku=Department%20of%20Administration%20Engineering&cond=OR&search=%E6%A4%9C%E7%B4%A2");
		map.put("Information Computer Science",
				"http://yagamix.st.keio.ac.jp/tprofile/e_index.html?shozoku=Department%20of%20Information%20and%20Computer%20Science&cond=OR&search=%E6%A4%9C%E7%B4%A2");
		map.put("Biosciences Informatics",
				"http://yagamix.st.keio.ac.jp/tprofile/e_index.html?shozoku=Department%20of%20Biosciences%20and%20Informatics&cond=OR&search=%E6%A4%9C%E7%B4%A2");
	}

	public static void main(String[] args) {
		HtmlExtractor htmlExtractor = new HtmlExtractor();
		Iterator<Entry<String, String>> iterator = map.entrySet().iterator();
		while (iterator.hasNext()) {
			Entry<String, String> entry = iterator.next();
			String name = entry.getKey();
			String value = entry.getValue();
			CollectUrls collectUrls = new CollectUrls(htmlExtractor);
			Map<String, String> map = collectUrls.extract(value);
			CollectUserInfo collectUsernfo = new CollectUserInfo(htmlExtractor);
			List<User> usersList = collectUsernfo.getUserInfoList(map);
			Map<String[], List<List<String>>> mapTokens = new HashMap<String[], List<List<String>>>();
			mapTokens = prepareMap(usersList);
			CsvWriter writer = new CsvWriter("./output/" + name + ".csv");
			writer.write(mapTokens);
		}
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

	private static int getHighestNumber(List<User> argUsersList) {
		for (User user : argUsersList) {
			int size = user.getEmailsList().size();
		}
		return 0;
	}
}
