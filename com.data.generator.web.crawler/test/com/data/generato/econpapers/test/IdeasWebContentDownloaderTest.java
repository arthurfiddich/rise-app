package com.data.generato.econpapers.test;

import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import net.htmlparser.jericho.Element;
import net.htmlparser.jericho.HTMLElementName;
import net.htmlparser.jericho.Source;

import org.apache.commons.lang3.text.WordUtils;

import com.data.generator.extractor.HtmlExtractor;
import com.data.generator.ideas.repec.org.Affiliation;
import com.data.generator.ideas.repec.org.Ideas;
import com.data.generator.ideas.repec.org.IdeasSiteExtractor;
import com.data.generator.util.Precondition;
import com.data.generator.web.crawler.WebContentDownloader;

public class IdeasWebContentDownloaderTest {
	private static Pattern splitDetector = null;

	public static void main(String[] args) throws IllegalAccessException,
			InvocationTargetException, NoSuchMethodException,
			IntrospectionException {
		HtmlExtractor htmlExtractor = new HtmlExtractor();
		// extract1();
		IdeasSiteExtractor ideasSiteExtractor = new IdeasSiteExtractor(
				htmlExtractor, "idea.txt");
		Ideas ideas = ideasSiteExtractor.extract(
				"http://ideas.repec.org/e/paa6.html", HTMLElementName.DL);
		Precondition.ensureNotNull(ideas, "Ideas Object");
		System.out.println(ideas.toString());
		ideas.getEmailList().add("a@b.com");
		ideas.getEmailList().add("b@c.com");
		ideas.getEmailList().add("c@d.com");

		List<String> emailsList = new ArrayList<String>();
		emailsList.add("athondapu@ciphercloud.com");
		emailsList.add("amarshiva93@gmail.com");
		emailsList.add("shivaprasad.amar@gmail.com");
		Ideas i = new Ideas.IdeasBuilder("Amar", "ShivaPrasad", "Thondapu",
				"Jr.").setEmailList(emailsList).build();
		List<Ideas> ideasList = new ArrayList<Ideas>();
		ideasList.add(ideas);
		ideasList.add(i);
		populateMap(ideasList);
		System.exit(0);
	}

	private static void extract1() {
		HtmlExtractor htmlExtractor = new HtmlExtractor();
		WebContentDownloader webContentDownloader = new WebContentDownloader();
		Source source = htmlExtractor
				.getSourceBasedOnHtmlString(webContentDownloader
						.getHtmlContent("http://ideas.repec.org/e/paa6.html"));
		// IdeasSiteExtractor ideasSiteExtractor = new IdeasSiteExtractor(
		// htmlExtractor, "idea.txt");
		// Ideas ideas = ideasSiteExtractor.extract("", HTMLElementName.DL);
		// System.out.println(ideas.toString());
		List<Element> elementsList = source.getAllElements(HTMLElementName.P);
		for (Element element : elementsList) {
			List<Element> e = element.getAllElements(HTMLElementName.SCRIPT);
			if (Precondition.checkNotEmpty(e)) {
				for (Element element2 : e) {
					System.out.println(element2.getContent().getTextExtractor()
							.toString());
				}
			}
			System.out.println(element.getContent().getTextExtractor()
					.toString());
		}
	}

	private static int getEmailCount(List<Ideas> argIdeasList) {
		int emailCount = 0;
		for (Ideas ideas : argIdeasList) {
			if (emailCount < ideas.getEmailList().size()) {
				emailCount = ideas.getEmailList().size();
			}
		}
		return emailCount;
	}

	private static Map<String[], List<List<String>>> populateMap(
			List<Ideas> argIdeasList) {
		Map<String[], List<List<String>>> map = new LinkedHashMap<String[], List<List<String>>>();
		int emailCount = getEmailCount(argIdeasList);
		List<String> headersList = new ArrayList<String>();
		headersList.add("FirstName");
		headersList.add("LastName");
		for (int i = 0; i < emailCount; i++) {
			headersList.add("Email" + (i + 1));
		}
		String[] headersArray = headersList.toArray(new String[headersList
				.size()]);
		List<List<String>> tokensList = new ArrayList<List<String>>();
		for (Ideas ideas : argIdeasList) {
			List<String> tokens = new ArrayList<String>();
			tokens.add(ideas.getFirstName());
			tokens.add(ideas.getLastName());
			List<String> emailsList = ideas.getEmailList();
			if (emailCount > emailsList.size()) {
				emailsList.add("");
			}
			tokens.addAll(emailsList);
			tokensList.add(tokens);
		}
		map.put(headersArray, tokensList);
		return map;
	}

	public static void extract() throws IllegalAccessException,
			InvocationTargetException, NoSuchMethodException,
			IntrospectionException {
		// Affiliation affiliation = new Affiliation();
		// Method[] methodArray = affiliation.getClass().getDeclaredMethods();
		// for (Method method : methodArray) {
		// System.out.println(method.getName());
		// }

		// Map<String, Object> map = BeanUtils.describe(affiliation);
		// Iterator<Entry<String, Object>> iterator = map.entrySet().iterator();
		// while (iterator.hasNext()) {
		// Entry<String, Object> entry = iterator.next();
		// System.out.println(entry.getKey() + "\t" + entry.getValue());
		// }

		PropertyDescriptor[] propertyDescriptor = Introspector.getBeanInfo(
				Affiliation.class).getPropertyDescriptors();
		for (PropertyDescriptor propertyDescriptor2 : propertyDescriptor) {
			System.out.println(propertyDescriptor2.getReadMethod().getName());
		}

		System.out.println(WordUtils.capitalize("classname"));
		System.out.println(WordUtils.capitalizeFully("classname"));
		System.out.println(WordUtils.uncapitalize("ClassName"));
		System.out.println(WordUtils.uncapitalize("CLASSNAME"));
	}

	public static Pattern getSplitDetectorPattern() {
		if (splitDetector == null) {
			// update the wrapper detector.
			splitDetector = Pattern.compile("([\\r\\n]" + ")+");
		}
		return splitDetector;
	}

}
