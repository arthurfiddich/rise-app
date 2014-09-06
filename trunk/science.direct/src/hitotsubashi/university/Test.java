package hitotsubashi.university;

import java.io.IOException;
import java.net.MalformedURLException;

import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.Page;
import com.gargoylesoftware.htmlunit.WebClient;

public class Test {
	public static void main(String[] args)
			throws FailingHttpStatusCodeException, MalformedURLException,
			IOException {
		WebClient webClient = new WebClient();
		Page page = webClient
				.getPage("http://www.e.u-tokyo.ac.jp/finance-g/english/teacher/18.html");
		String content = page.getWebResponse().getContentAsString();
		System.out.println(content);
	}
}
