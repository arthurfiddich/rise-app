package com.data.generator.util;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLFilterImpl;

public class XmlNameSpaceFilter extends XMLFilterImpl {

	public final String startElement;

	public XmlNameSpaceFilter(XMLReader argParent, String argStartElement) {
		super(argParent);
		this.startElement = argStartElement;
	}

	@Override
	public void startElement(String argUri, String argLocalName,
			String argQName, Attributes argAtts) throws SAXException {
		super.startElement(startElement, argLocalName, argQName, argAtts);
	}

	public String getStartElement() {
		return this.startElement;
	}

}
