package science.direct;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class science {
	private WebDriver driver;
	private String baseUrl;
	private boolean acceptNextAlert = true;
	private StringBuffer verificationErrors = new StringBuffer();

//	@Before
	public void setUp() throws Exception {
		// driver = new HtmlUnitDriver();
		driver = new FirefoxDriver();
		baseUrl = "http://www.sciencedirect.com/";
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

//	@Test
	public void testScience() throws Exception {
//		driver.get(baseUrl + "science?_ob=ArticleListURL&_method=tag&searchtype=a&refSource=search&_st=13&count=1000&sort=r&filterType=&_chunk=0&hitCount=471948&NEXT_LIST=1&view=c&md5=c7260aeaed2592a41d41c43f0599ecee&_ArticleListID=-611362988&chunkSize=25&sisr_search=&TOTAL_PAGES=18878&zone=exportDropDown&citation-type=RIS&format=cite-abs&bottomPaginationBoxChanged=&displayPerPageFlag=t&resultsPerPage=200");
		driver.get(baseUrl + "/science?_ob=ArticleListURL&_method=list&_ArticleListID=-614816387&_sort=r&_st=13&view=c&md5=93725cc194a0054549e460fd4c3a62da&searchtype=a");
		System.out.println(driver.getPageSource());
		List<WebElement> elements = driver.findElements(By.className("more"));
		for (WebElement webElement : elements) {
			String attribute = webElement.getAttribute("alt");
			System.out.println(attribute);
			if(attribute.equals("View more Year filters")){
				webElement.click();
				System.out.println(driver.getPageSource());
			}
		}
		//		List<WebElement> ele = driver.findElements(By
//				.xpath(".//*[@class='authorName']"));
//		System.out.println(ele.size());
//		driver.findElement(By.xpath("(.//*[@class='authorName'])[1]")).click();
		/*
		 * System.out.println(driver.findElement(
		 * By.xpath("(.//*[@class='authorName'])[2]")).getText());
		 * System.out.println(driver.findElement(
		 * By.xpath("(.//*[@class='authorName'])[3]")).getText());
		 * driver.findElement
		 * (By.xpath("(.//*[@class='authorName'])[1]")).click();
		 * Thread.sleep(1000);
		 */
		/*
		 * System.out.println(driver.findElement(
		 * By.xpath(".//*[@id='rightInner']/div[2]/div")).getText());
		 */
//		System.out.println(driver.getPageSource());
//		List<WebElement> findElements = driver.findElements(By.xpath("//a"));
//		System.out.println(findElements.size());
//		for (WebElement webElement : findElements) {
//			System.out.println(webElement.getText());
//		}

	}

//	@After
//	public void tearDown() throws Exception {
//		driver.quit();
//		String verificationErrorString = verificationErrors.toString();
//		if (!"".equals(verificationErrorString)) {
//			fail(verificationErrorString);
//		}
//	}
//
//	private boolean isElementPresent(By by) {
//		try {
//			driver.findElement(by);
//			return true;
//		} catch (NoSuchElementException e) {
//			return false;
//		}
//	}
//
//	private boolean isAlertPresent() {
//		try {
//			driver.switchTo().alert();
//			return true;
//		} catch (NoAlertPresentException e) {
//			return false;
//		}
//	}
//
//	private String closeAlertAndGetItsText() {
//		try {
//			Alert alert = driver.switchTo().alert();
//			String alertText = alert.getText();
//			if (acceptNextAlert) {
//				alert.accept();
//			} else {
//				alert.dismiss();
//			}
//			return alertText;
//		} finally {
//			acceptNextAlert = true;
//		}
//	}
	
	public static void main(String[] args) throws Exception {
		science sc = new science();
		sc.setUp();
		sc.testScience();
	}
}
