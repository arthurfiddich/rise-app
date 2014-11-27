package www.christuniversity.in;

import java.util.List;

import com.data.generator.extractor.HtmlExtractor;

public class Test {
	public static void main(String[] args) {
		System.setProperty("webdriver.chrome.driver", "C:\\devhome\\tools\\chromedriver_win32\\chromedriver.exe");
		HtmlExtractor htmlExtractor = new HtmlExtractor();
		CollectUrls collectUrls = new CollectUrls(htmlExtractor);
		List<String> urlsList = collectUrls
				.extract("http://www.christuniversity.in/Psychology/deptfaculty.php?division=Humanities%20and%20Social%20Sciences&&dept=33#fa");
		CollectUserInfo collectUserInfo = new CollectUserInfo(htmlExtractor);
		List<User> usersList = collectUserInfo.getUserInfoList(urlsList);
		ListWriter listWriter = new ListWriter(
				"./output/christuniversity/user.csv");
		listWriter.write(usersList);
	}
}
