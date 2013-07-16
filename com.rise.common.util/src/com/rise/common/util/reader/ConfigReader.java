package com.rise.common.util.reader;

import java.util.Properties;

public interface ConfigReader {

	public Object readXmlFile(String appKey, String filename, String xsd,
			String packageName, boolean tenantOrgScope, boolean skipValidation);

	public Properties readPropertyFile(String appKey, String filename,
			boolean tenantOrgScope);

	public Object readXmlFile(String filename, String xsd, String packageName,
			boolean skipValidation);

	public Properties readPropertyFile(String filename);

}
