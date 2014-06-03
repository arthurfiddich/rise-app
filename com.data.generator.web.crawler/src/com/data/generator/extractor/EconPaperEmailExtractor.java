package com.data.generator.extractor;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import net.htmlparser.jericho.Element;
import net.htmlparser.jericho.HTMLElementName;
import net.htmlparser.jericho.Source;

import com.data.generato.econpapers.Email;
import com.data.generato.econpapers.HtmlExtractorConstants;
import com.data.generato.econpapers.HtmlExtractorUtil;
import com.data.generator.util.Precondition;
import com.data.generator.custom.annotation.EconPaper;

@EconPaper(priority = 2)
public class EconPaperEmailExtractor {

	private HtmlExtractor htmlExtractor;

	public EconPaperEmailExtractor(HtmlExtractor argHtmlExtractor) {
		super();
		this.htmlExtractor = argHtmlExtractor;
	}

	/**
	 * This method will return all the Email's in the form String(s). Before
	 * returning, this method will check local part, separator and domain part.
	 * 
	 * @param argEmailsList
	 * @return
	 */
	public List<String> getEmailList(List<Email> argEmailsList) {
		List<String> emailsList = new ArrayList<String>();
		if (Precondition.checkNotEmpty(argEmailsList)) {
			for (Email email : argEmailsList) {
				emailsList.add(email.toString());
			}
		}
		return emailsList;
	}

	public List<Email> getAllEmails(String argInputFileName) {
		String inputFileName = Precondition.ensureNotEmpty(argInputFileName,
				"Input File Name");
		return getAllEmails(new File(inputFileName));
	}

	public List<Email> getAllEmailsBasedOnHtml(String argHtmlToken) {
		String htmlToken = Precondition.ensureNotEmpty(argHtmlToken,
				"Html Token");
		Source source = this.htmlExtractor
				.getSourceBasedOnHtmlString(htmlToken);
		return getAllEmails(source);
	}

	public List<Email> getAllEmails(File argInputFile) {
		File inputFile = Precondition.ensureNotNull(argInputFile, "Input File");
		Source source = this.htmlExtractor.getSource(inputFile);
		return getAllEmails(source);
	}

	public List<Email> getAllEmails(Source argSource) {
		if (Precondition.checkNotNull(argSource)) {
			List<Element> linkElements = argSource
					.getAllElements(HTMLElementName.SCRIPT);
			return getAllEmails(linkElements);
		}
		return null;
	}

	public List<Email> getAllEmails(List<Element> argLinkElements) {
		if (Precondition.checkNotEmpty(argLinkElements)) {
			List<Email> emailsList = new ArrayList<Email>();
			for (Element linkElement : argLinkElements) {
				String label = linkElement.getContent().getTextExtractor()
						.toString();
				if (Precondition.checkNotEmpty(label)
						&& label.startsWith(HtmlExtractorConstants.OBFUSCATE)) {
					Email email = constructEmail(label);
					if (Precondition.checkNotNull(email)) {
						emailsList.add(email);
					}
				}
			}
			return emailsList;
		}
		return null;
	}

	/*
	 * In this econpapers website all the emails are in the form of
	 * "Obfuscate( '&#115;&#115;&#98;&#46;&#110;&#111;', '&#114;&#111;&#108;&#102;&#46;&#97;&#97;&#98;&#101;&#114;&#103;&#101;' )"
	 * If we use Jericho Parser it will automatically decoded and it will give
	 * an output like "Obfuscate( 'ssb.no', 'rolf.aaberge' )". From this we need
	 * construct a proper email.
	 */
	private Email constructEmail(String argLabel) {
		String token = argLabel;
		if (Precondition.checkNotEmpty(token)
				&& token.startsWith(HtmlExtractorConstants.OBFUSCATE)) {
			int index = token.indexOf(HtmlExtractorConstants.OBFUSCATE);
			token = token.substring(index
					+ HtmlExtractorConstants.OBFUSCATE.length());
			token = HtmlExtractorUtil.removeBrackets(token);
			if (Precondition.checkNotEmpty(token)) {
				return HtmlExtractorUtil.splitAndConstructEmail(token);
			}
		}
		return null;
	}

}
