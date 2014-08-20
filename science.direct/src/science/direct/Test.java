package science.direct;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Test {
	private WebDriver driver;
	private String baseUrl;

	public void setUp() throws Exception {
		driver = new FirefoxDriver();
		baseUrl = "http://fkee.ump.edu.my/";
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	public void testScience() throws Exception {
		driver.get(baseUrl
				+ "index.php/en/component/comprofiler/userslist/People?Itemid=105");
		System.out.println(driver.getPageSource());
		List<WebElement> elements = driver.findElements(By.className("more"));
		for (WebElement webElement : elements) {
			String attribute = webElement.getAttribute("alt");
			System.out.println(attribute);
			if (attribute.equals("View more Year filters")) {
				webElement.click();
				System.out.println(driver.getPageSource());
			}
		}
	}

	public static void main(String[] args) throws Exception {
		Test sc = new Test();
		sc.setUp();
		sc.testScience();
	}
}
