package com.data.generator.file.impl;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.core.io.Resource;

import com.data.generator.file.AutoFileCloser;
import com.data.generator.file.Reader;
import com.data.generator.util.Precondition;
import com.data.generator.util.ResourceUtil;

public class TextFileReader implements Reader<String, List<String>> {

	@Override
	public List<String> read(String argFileName) {
		if (Precondition.checkNotEmpty(argFileName)) {
			final Resource[] resourcesArray = ResourceUtil
					.getResources(argFileName);
			Precondition.ensureNotNull(resourcesArray, "Resources Not Found");
			final List<String> tokensist = new ArrayList<String>();
			new AutoFileCloser() {

				@Override
				protected void doWork() throws Throwable {
					for (Resource resource : resourcesArray) {
						tokensist.addAll(getProperties(resource));
					}
				}

				protected List<String> getProperties(Resource resource)
						throws IOException {
					BufferedReader bufferedReader = autoClose(new BufferedReader(
							new FileReader(resource.getFile())));
					List<String> tokensist = new ArrayList<String>();
					String token = null;
					while ((token = bufferedReader.readLine()) != null) {
						tokensist.add(token);
					}
					return tokensist;
				}
			};
			return tokensist;
		}
		return null;
	}
}
