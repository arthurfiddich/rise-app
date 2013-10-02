package com.rise.common.util.writer;

import java.util.List;
import java.util.Map;

public interface FileWriter<T, S> {

	public void writePropertyFile(String argFileName, Map<T, S> argMap,
			boolean tenantOrgScope);

	public void writeTextFile(String argFileName, List<T> argTokensList);

}
