package com.data.generator.geo.names.api.impl;

import java.io.InputStream;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import com.data.generator.geo.names.api.Parse;
import com.data.generator.util.GenericJaxbHelper;

public abstract class ParserImpl<T> implements Parse<T> {

	@Override
	public T unmarshal(T argName, InputStream argInputStream,
			String argPackageName) throws JAXBException, SAXException,
			ParserConfigurationException {
		GenericJaxbHelper<T> jaxbHelper = new GenericJaxbHelper<T>(
				argPackageName);

		JAXBElement<T> dataSourceConfigurationElement = jaxbHelper
				.unmarshal(argInputStream);
		return dataSourceConfigurationElement.getValue();
	}

	@Override
	public T unmarshal(T argName, InputStream argInputStream,
			String argNameSpaceElement, String argPackageName)
			throws JAXBException, SAXException, ParserConfigurationException {
		GenericJaxbHelper<T> jaxbHelper = new GenericJaxbHelper<T>(
				argPackageName);

		JAXBElement<T> dataSourceConfigurationElement = jaxbHelper
				.customUnmarshller(argInputStream, argNameSpaceElement);
		return dataSourceConfigurationElement.getValue();
	}

}
