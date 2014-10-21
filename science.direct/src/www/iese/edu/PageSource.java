package www.iese.edu;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class PageSource {

	private WebDriver driver;

	public PageSource() {
		super();
		this.driver = new FirefoxDriver();
		this.driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	public String getHtmlContent(String argUrl) {
		this.getDriver().get(argUrl);
		return this.getDriver().getPageSource();
	}

	public WebDriver getDriver() {
		return this.driver;
	}

	public static void main(String[] args) {
		PageSource pageSource = new PageSource();
		System.out
				.println(pageSource
						.getHtmlContent("http://www.iese.edu/en/faculty-research/professors/?paginaActual=1&initialLetter=&language=en&name=&lastname=&department="));
		System.exit(0);
	}
}
