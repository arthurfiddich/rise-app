package com.data.generator.web.crawler;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
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
		driver.get(baseUrl + "/science/article/pii/S1568494612005741");
		List<WebElement> ele = driver.findElements(By
				.xpath(".//*[@class='authorName']"));
		System.out.println(ele.size());
		driver.findElement(By.xpath("(.//*[@class='authorName'])[1]")).click();
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
		System.out.println(driver.getPageSource());
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
