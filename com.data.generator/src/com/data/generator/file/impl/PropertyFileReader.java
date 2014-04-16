package com.data.generator.file.impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.springframework.core.io.Resource;

import com.data.generator.file.AutoFileCloser;
import com.data.generator.file.Reader;
import com.data.generator.util.Precondition;
import com.data.generator.util.ResourceUtil;

public class PropertyFileReader implements Reader<String, Properties> {

	@Override
	public Properties read(String argFileName) {
		if (Precondition.checkNotEmpty(argFileName)) {
			final Resource[] resourcesArray = ResourceUtil.getResources(argFileName);
			Precondition.ensureNotNull(resourcesArray, "Resources Not Found");
			final Properties properties = new Properties();
			new AutoFileCloser() {

				@Override
				protected void doWork() throws Throwable {
					for (Resource resource : resourcesArray) {
						properties.putAll(getProperties(properties, resource));
					}
				}

				protected Properties getProperties(final Properties properties,
						Resource resource) throws IOException {
					Properties fileProperties = new Properties();
					InputStream inputStream = resource.getInputStream();
					fileProperties.load(inputStream);
					return fileProperties;
				}
			};
			return properties;
		}
		return null;
	}

}
