package com.rise.common.util.file;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.rise.common.util.checker.Precondition;
import com.rise.common.util.constants.HibernateHelperConstants;
import com.rise.common.util.reader.ConfigReader;
import com.rise.common.util.reader.FileSystemConfigReader;

public class UiComponentFileReader {

	private static final Logger logger = LoggerFactory
			.getLogger(UiComponentFileReader.class);

	private String tenantId;
	private String configurationFile;
	private Map<String, String> modelNameVsCommaSeperatedUiComponentsMap = new HashMap<String, String>();
	private Map<String, List<String>> modelNameVsUiComponentListMap = new HashMap<String, List<String>>();

	public static UiComponentFileReader createInstance(String argTenantId,
			String argConfigurationFile) {
		logger.debug("Creating/Initializing an instance for this class: "
				+ UiComponentFileReader.class.getSimpleName());
		UiComponentFileReader uiComponentFileReader = new UiComponentFileReader();
		uiComponentFileReader.setConfigurationFile(argConfigurationFile);
		uiComponentFileReader.setTenantId(argTenantId);
		uiComponentFileReader.initialize();
		return uiComponentFileReader;
	}

	private void initialize() {
		ConfigReader configReader = new FileSystemConfigReader();
		Properties properties = configReader.readPropertyFile(this
				.getConfigurationFile());
		buildModelNameVsCommaSeperatedUiComponentsMap(properties);
		buildModelNameVsUiComponentListMap();
	}

	private void buildModelNameVsUiComponentListMap() {
		Map<String, String> modelNameVsCommaSeperatedUiComponentsMap = this
				.getModelNameVsUiComponentsMap();
		if (Precondition.checkNotNull(modelNameVsCommaSeperatedUiComponentsMap)) {
			Set<Entry<String, String>> entrySet = modelNameVsCommaSeperatedUiComponentsMap
					.entrySet();
			Iterator<Entry<String, String>> iterator = entrySet.iterator();
			while (iterator.hasNext()) {
				Entry<String, String> entry = iterator.next();
				if (Precondition.checkNotNull(entry)) {
					List<String> tokens = prepareList(entry.getValue());
					if (Precondition.checkNotEmpty(tokens)) {
						this.getModelNameVsUiComponentListMap().put(
								entry.getKey(), tokens);
					}
				}
			}
		}
	}

	private List<String> prepareList(String argValue) {
		if (Precondition.checkNotEmpty(argValue)) {
			String[] tokens = org.apache.commons.lang3.StringUtils.split(
					argValue, HibernateHelperConstants.COMMA);
			if (Precondition.checkNotEmpty(tokens)) {
				return Arrays.asList(tokens);
			}
		}
		return null;
	}

	private void buildModelNameVsCommaSeperatedUiComponentsMap(
			Properties argProperties) {
		if (Precondition.checkNotNull(argProperties)) {
			Set<Entry<Object, Object>> entrySet = argProperties.entrySet();
			Iterator<Entry<Object, Object>> iterator = entrySet.iterator();
			while (iterator.hasNext()) {
				Entry<Object, Object> entry = iterator.next();
				if (Precondition.checkNotNull(entry)) {
					this.getModelNameVsUiComponentsMap().put(
							entry.getKey().toString(),
							entry.getValue().toString());
				}
			}
		}
	}

	public String getTenantId() {
		return this.tenantId;
	}

	public void setTenantId(String argTenantId) {
		this.tenantId = argTenantId;
	}

	public String getConfigurationFile() {
		return this.configurationFile;
	}

	public void setConfigurationFile(String argConfigurationFile) {
		this.configurationFile = argConfigurationFile;
	}

	public Map<String, String> getModelNameVsUiComponentsMap() {
		return this.modelNameVsCommaSeperatedUiComponentsMap;
	}

	public void setModelNameVsUiComponentsMap(
			Map<String, String> argModelNameVsUiComponentsMap) {
		this.modelNameVsCommaSeperatedUiComponentsMap = argModelNameVsUiComponentsMap;
	}

	public Map<String, List<String>> getModelNameVsUiComponentListMap() {
		return this.modelNameVsUiComponentListMap;
	}

	public void setModelNameVsUiComponentListMap(
			Map<String, List<String>> argModelNameVsUiComponentListMap) {
		this.modelNameVsUiComponentListMap = argModelNameVsUiComponentListMap;
	}

}
