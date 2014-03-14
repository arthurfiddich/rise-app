package com.data.geneator.metadata.extraction;

import java.util.GregorianCalendar;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.generator.data.xmlns.configuration.v1.BaseObject;

/**
 * The Class XmlConfigurationHelper helps in populating the time related values
 * in the xml files.
 */
public class XmlConfigurationHelper {

	/** The logger. */
	private static Logger logger = LoggerFactory
			.getLogger(XmlConfigurationHelper.class);

	/** The id counter. */
	private int idCounter;

	/**
	 * Gets the new xml id.
	 * 
	 * @return the new xml id
	 */
	public String getNewXmlId() {
		return "id" + String.valueOf(this.idCounter++);
	}

	/**
	 * Populate base object elements by setting the Date Created,Date
	 * Generated,Last Modified .
	 * 
	 * @param argBaseObject
	 *            the base object
	 */
	public void populateBaseObjectElements(BaseObject argBaseObject) {
		argBaseObject.setId(getNewXmlId());
		argBaseObject.setDateCreated(getXmlDate());
		argBaseObject.setDateGenerated(getXmlDate());
		argBaseObject.setLastModified(getXmlDate());
	}

	/**
	 * Gets the xml date based on GregorianCalendar.
	 * 
	 * @return the xml date
	 */
	public XMLGregorianCalendar getXmlDate() {
		try {
			GregorianCalendar cal = new GregorianCalendar();
			return DatatypeFactory.newInstance().newXMLGregorianCalendar(cal);
		} catch (DatatypeConfigurationException e) {
			if (logger.isErrorEnabled()) {
				logger.error("Unable to create XMLGregorianCalendar:", e);
			}
		}
		return null;
	}

}
