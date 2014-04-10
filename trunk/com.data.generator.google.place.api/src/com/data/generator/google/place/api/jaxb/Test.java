package com.data.generator.google.place.api.jaxb;

import java.io.File;
import java.io.FileInputStream;

import javax.xml.bind.JAXBElement;

import com.data.generator.util.GenericJaxbHelper;
import com.generator.data.xmlns.gplace.api.v1.PlaceDetailsResponse;

public class Test {
	public void main(String argConfigurationXmlFile) {

		GenericJaxbHelper<PlaceDetailsResponse> jaxbHelper = new GenericJaxbHelper<PlaceDetailsResponse>(
				com.generator.data.xmlns.gplace.api.v1.ObjectFactory.class.getPackage().getName());
		try {
			FileInputStream fileInputStream = new FileInputStream(new File(
					argConfigurationXmlFile));
			JAXBElement<PlaceDetailsResponse> dataSourceConfigurationElement = jaxbHelper
					.customUnmarshller(fileInputStream);
			PlaceDetailsResponse placeDetailsResponse = dataSourceConfigurationElement
					.getValue();
			String status = placeDetailsResponse.getStatus();
			System.out.println(status);
		} catch (Exception e) {
			throw new RuntimeException(
					"error while unmarshalling configuration xml file: "
							+ argConfigurationXmlFile, e);
		}
	}

	public static void main(String[] args) {
		Test test = new Test();
		test.main("./input/place.xml");
	}
}
