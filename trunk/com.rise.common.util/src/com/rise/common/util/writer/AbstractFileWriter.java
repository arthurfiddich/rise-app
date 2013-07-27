package com.rise.common.util.writer;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.List;

import com.rise.common.util.checker.Precondition;
import com.rise.common.util.constants.HibernateHelperConstants;
import com.rise.common.util.file.AutoFileCloser;
import com.rise.common.util.hibernate.ResourceUtil;

public abstract class AbstractFileWriter implements FileWriter<String, String> {

	@Override
	public void writeTextFile(String argFileName, List<String> argTokensList) {
		Precondition.ensureNotEmpty(argFileName, "Output File Name");
		final List<String> tokens = (List<String>) Precondition.ensureNotEmpty(
				argTokensList, "Output Tokens");
		final String outputFileName = ResourceUtil
				.getWritableConfigFolderPath()
				+ File.separatorChar
				+ argFileName;
		new AutoFileCloser() {

			@Override
			protected void doWork() throws Throwable {
				FileOutputStream fileOutputStream = autoClose(fileOutputStream = new FileOutputStream(
						outputFileName));
				OutputStreamWriter outputStreamWriter = autoClose(outputStreamWriter = new OutputStreamWriter(
						fileOutputStream, HibernateHelperConstants.UTF_8));
				BufferedWriter bufferedWriter = autoClose(bufferedWriter = new BufferedWriter(
						outputStreamWriter));
				for (String token : tokens) {
					bufferedWriter.write(token);
					bufferedWriter.write("\n");
				}
				bufferedWriter.flush();
			}
		};
	}
}
