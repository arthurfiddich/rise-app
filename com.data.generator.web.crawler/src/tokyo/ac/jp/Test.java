package tokyo.ac.jp;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Test {
	private static WebDriver driver = new FirefoxDriver();

	public static void main(String[] args) {
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		// WebContentDownloader webContentDownloader = new
		// WebContentDownloader();
		// String content = webContentDownloader
		// .getHtmlContent("http://www.e.u-tokyo.ac.jp/finance-g/english/teacher/18.html");
		driver.get("http://www.e.u-tokyo.ac.jp/finance-g/english/teacher/18.html");
		String content = driver.getPageSource();
		System.out.println(content);
	}
}
