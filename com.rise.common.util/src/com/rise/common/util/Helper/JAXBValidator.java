/**
 * JAXBValidator
 *
 * Copyright (C) 2010 CipherCloud, Inc. All Rights Reserved.
 *
 * This software is the confidential and proprietary information of
 * CipherCloud, Inc. ("Confidential Information"). 
 * 
 * For more information, visit http://www.ciphercloud.com
 */
package com.rise.common.util.Helper;

import javax.xml.bind.ValidationEvent;
import javax.xml.bind.ValidationEventHandler;
import javax.xml.bind.ValidationEventLocator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The Class JAXBValidator.
 */
public class JAXBValidator implements ValidationEventHandler {

	/** The logger for logging the messages into log file. */
	private static final Logger logger = LoggerFactory
			.getLogger(JAXBValidator.class);

	private String configurationFile;

	/*
	 * Handles the xsd validation with custom messages (non-Javadoc)
	 * 
	 * @see javax.xml.bind.ValidationEventHandler#handleEvent(javax.xml.bind.
	 * ValidationEvent)
	 */
	@Override
	public boolean handleEvent(ValidationEvent argEvent) {
		if (argEvent.getSeverity() == argEvent.ERROR
				|| argEvent.getSeverity() == argEvent.FATAL_ERROR) {
			ValidationEventLocator validationEventLocator = argEvent
					.getLocator();
			logger.error("XML Validation failed for xml file : "
					+ this.getConfigurationFile() + "\n with Exception :"
					+ argEvent.getMessage() + " at row: "
					+ validationEventLocator.getLineNumber() + " column: "
					+ validationEventLocator.getColumnNumber());
			System.err.println("XML Validation failed for xml file : "
					+ this.getConfigurationFile() + "\n with Exception :"
					+ argEvent.getMessage() + " at row: "
					+ validationEventLocator.getLineNumber() + " column: "
					+ validationEventLocator.getColumnNumber());
			throw new RuntimeException("XML Validation Exception:  "
					+ argEvent.getMessage() + " at row: "
					+ validationEventLocator.getLineNumber() + " column: "
					+ validationEventLocator.getColumnNumber());
		}
		return true;
	}

	/**
	 * @return the configurationFile
	 */
	public String getConfigurationFile() {
		return this.configurationFile;
	}

	/**
	 * @param argConfigurationFile
	 *            the configurationFile to set
	 */
	public void setConfigurationFile(String argConfigurationFile) {
		this.configurationFile = argConfigurationFile;
	}

}
