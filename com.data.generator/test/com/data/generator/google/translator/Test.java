package com.data.generator.google.translator;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import com.gtranslate.Language;
import com.gtranslate.Translator;

public class Test {

	public static void main(String[] args) throws UnsupportedEncodingException {
		Translator translate = Translator.getInstance();
		String plainInput = "Dominic Joseph Fontana (born March 15, 1931, in Shreveport, Louisiana), is an American musician best known as the drummer for Elvis Presley for 14 years. He played on over 460 RCA cuts with Elvis. Nicknamed \"\"D. J. \"\", he was employed by the Louisiana Hayride to be an in-house drummer on its Saturday night radio broadcast.";
		String encodedToken = URLEncoder.encode(plainInput, "UTF-8");
		String text = translate.translate(encodedToken, Language.ENGLISH,
				Language.HEBREW);
		System.out.println(text);
	}
}
