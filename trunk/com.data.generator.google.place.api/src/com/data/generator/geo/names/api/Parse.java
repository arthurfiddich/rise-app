package com.data.generator.geo.names.api;

import java.io.InputStream;

import javax.xml.bind.JAXBException;
import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

public interface Parse<T> {

	public T unmarshal(T argName, InputStream argInputStream,
			String argPackageName) throws JAXBException, SAXException,
			ParserConfigurationException;

	public T unmarshal(T argName, InputStream argInputStream,
			String argNameSpaceElement, String argPackageName)
			throws JAXBException, SAXException, ParserConfigurationException;

}
