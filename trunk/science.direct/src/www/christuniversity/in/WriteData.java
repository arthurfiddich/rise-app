package www.christuniversity.in;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.data.generator.extractor.HtmlExtractor;
import com.data.generator.file.impl.CsvListWriter;

public class WriteData {

	private HtmlExtractor htmlExtractor;
	private WebDriver driver;

	public WriteData(HtmlExtractor argHtmlExtractor) {
		super();
		this.htmlExtractor = argHtmlExtractor;
		this.driver = new FirefoxDriver();
		this.driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	public void write(Map<List<String>, List<List<String>>> argTokens,
			String argOutputFileName) {
		CsvListWriter csvListWriter = new CsvListWriter(argOutputFileName);
		csvListWriter.write(argTokens);
	}

	public HtmlExtractor getHtmlExtractor() {
		return this.htmlExtractor;
	}

	public WebDriver getDriver() {
		return this.driver;
	}

}
