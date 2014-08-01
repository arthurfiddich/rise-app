package com.data.generato.econpapers;

import java.util.ArrayList;
import java.util.List;

public class HtmlExtractorConstants {

	public static final String OBFUSCATE = "Obfuscate";
	public static final String OBFUSCATE_OPEN_BRACKET = "Obfuscate(";
	public static final String ISSUE_LINKS = "issuelinks";
	public static final String HREF = "HREF";

	// ECON PAPER Web Site: http://econpapers.repec.org/
	public static final String ECON_PAPERS = "EconPapers:";

	// IDEAS Web Site: http://ideas.repec.org/i/eacc.html
	public static final String[] IDEAS_LINK_CONSTANT = { "/e/p", "/f/p" };
	public static final String ALL_LINKS = "http://ideas.repec.org/i/eall.html";
	public static final String HOME_PAGE_LINK = "http://ideas.repec.org";

	public static final List<String> NAME_PARTS_LIST = prepareList();
	public static final String FIRST_NAME_COLON = "First Name:";
	public static final String MIDDLE_NAME_COLON = "Middle Name:";
	public static final String LAST_NAME_COLON = "Last Name:";
	public static final String SUFFIX_COLON = "Suffix:";
	public static final char SPACE_CHAR = ' ';
	public static final String EMAIL_KEYWORD = "liame2";
	public static final String IDEAS_AT = "m7i7";
	public static final String JAPANESE_HOME_PAGE_LINK = "http://www.econ.tohoku.ac.jp/econ/english/staff/";

	private static List<String> prepareList() {
		List<String> namePartsList = new ArrayList<String>();
		namePartsList.add(FIRST_NAME_COLON);
		namePartsList.add(MIDDLE_NAME_COLON);
		namePartsList.add(LAST_NAME_COLON);
		namePartsList.add(SUFFIX_COLON);
		return namePartsList;
	}
}
