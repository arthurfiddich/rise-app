package com.data.generator.google.place.api.impl;

import java.io.InputStream;

import javax.xml.bind.JAXBException;
import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import com.generator.data.xmlns.geo.names.api.v1.GeoNames;

public class GeoNamesParser extends ParserImpl<GeoNames> {

	@Override
	public GeoNames unmarshal(GeoNames argName, InputStream argInputStream,
			String argNameSpaceElement) throws JAXBException, SAXException,
			ParserConfigurationException {
		return super.unmarshal(argName, argInputStream, argNameSpaceElement);
	}
}
