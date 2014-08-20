package science.direct;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class SsrnTest {
	private WebDriver driver;

	public void setUp() throws Exception {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	public void testScience() throws Exception {
		driver.get("http://papers.ssrn.com/sol3/cf_dev/AbsByAuth.cfm?per_id=9#");
		System.out.println(driver.getPageSource());
	}

	public static void main(String[] args) throws Exception {
		SsrnTest sc = new SsrnTest();
		sc.setUp();
		sc.testScience();
	}
}
