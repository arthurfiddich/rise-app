package com.rise.common.util.writer;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.rise.common.util.checker.Precondition;
import com.rise.common.util.constants.HibernateHelperConstants;

public class FileSystemConfigWriter extends
		AbstractFileWriter<String, List<String>> {

	@Override
	public void writePropertyFile(String argFileName,
			Map<String, List<String>> argMap, boolean argTenantOrgScope) {
		String fileName = Precondition.ensureNotEmpty(argFileName,
				HibernateHelperConstants.OUTPUT_FILE_NAME);
		Map<String, List<String>> classNameVsInHouseFieldsList = Precondition
				.ensureNotNull(argMap, HibernateHelperConstants.MAP);
		Set<Entry<String, List<String>>> entrySet = classNameVsInHouseFieldsList
				.entrySet();
		Iterator<Entry<String, List<String>>> iterator = entrySet.iterator();
		List<String> tokens = new ArrayList<String>();
		while (iterator.hasNext()) {
			Entry<String, List<String>> entry = iterator.next();
			String value = buildEntry(entry);
			if (Precondition.checkNotNull(value)) {
				tokens.add(value);
			}
		}
		writeTextFile(fileName, tokens);
	}

	private String buildEntry(Entry<String, List<String>> argEntry) {
		if (Precondition.checkNotNull(argEntry)) {
			List<String> entries = argEntry.getValue();
			StringBuilder tokenBuilder = new StringBuilder();
			tokenBuilder.append(argEntry.getKey());
			tokenBuilder.append(HibernateHelperConstants.EQUALS);
			if (Precondition.checkNotEmpty(entries)
					&& Precondition.checkNotEmpty(argEntry.getKey())) {
				for (int i = 0; i < entries.size(); i++) {
					String fullyQualifiedClassName = entries.get(i);
					tokenBuilder.append(fullyQualifiedClassName);
					if (i < entries.size() - 1) {
						tokenBuilder.append(HibernateHelperConstants.COMMA);
					}
				}
			}
			return tokenBuilder.toString();
		}
		return "";
	}
}
