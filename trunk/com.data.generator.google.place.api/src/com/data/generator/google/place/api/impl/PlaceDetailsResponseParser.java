package com.data.generator.google.place.api.impl;

import java.io.InputStream;

import javax.xml.bind.JAXBException;
import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import com.generator.data.xmlns.gplace.api.v1.PlaceDetailsResponse;

public class PlaceDetailsResponseParser extends
		ParserImpl<PlaceDetailsResponse> {

	@Override
	public PlaceDetailsResponse unmarshal(PlaceDetailsResponse argName,
			InputStream argInputStream, String argNameSpaceElement,
			String argPackageName) throws JAXBException, SAXException,
			ParserConfigurationException {
		return super.unmarshal(argName, argInputStream, argNameSpaceElement,
				argPackageName);
	}
}
