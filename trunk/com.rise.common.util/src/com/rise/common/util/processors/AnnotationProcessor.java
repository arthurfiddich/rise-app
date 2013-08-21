package com.rise.common.util.processors;

import java.util.List;

import com.rise.common.util.Helper.TenantConfigHelper;
import com.rise.common.util.checker.Precondition;
import com.rise.common.util.reader.ConfigReader;
import com.rise.common.util.reader.FileSystemConfigReader;

public class AnnotationProcessor extends AbstractProcessor {

	private ConfigReader configReader;

	private AnnotationProcessor() {
		initializeConfigReader();
	}

	private void initializeConfigReader() {
		this.configReader = new FileSystemConfigReader();
	}

	@Override
	public void process(String argFileName) {
		String fileName = Precondition.ensureNotEmpty(argFileName, "File Name");
	}

	public ConfigReader getConfigReader() {
		return this.configReader;
	}

	@Override
	public void process() {
//		List<String> modelClassesList = TenantConfigHelper.getInstance()
//				.getModelClassesList();
//		modelClassesList = (List<String>) Precondition.ensureNotEmpty(
//				modelClassesList, "Model Class List");
//		for (String modelClass : modelClassesList) {
//
//		}
	}
}
