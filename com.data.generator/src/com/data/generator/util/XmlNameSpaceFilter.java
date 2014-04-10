package com.data.generator.util;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLFilterImpl;

public class XmlNameSpaceFilter extends XMLFilterImpl {

	public XmlNameSpaceFilter(XMLReader argParent) {
		super(argParent);
	}

	@Override
	public void startElement(String argUri, String argLocalName,
			String argQName, Attributes argAtts) throws SAXException {
		super.startElement("http://xmlns.data.generator.com/gplace/api/v1", argLocalName, argQName, argAtts);
	}
}
