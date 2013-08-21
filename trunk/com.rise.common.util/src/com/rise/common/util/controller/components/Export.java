package com.rise.common.util.controller.components;

import java.util.List;
import java.util.Map;

public class Export {

	private Map<String, List<Field>> entityNameVsFieldListMap;

	public Export() {
	}

	/**
	 * @return the entityNameVsFieldListMap
	 */
	public Map<String, List<Field>> getEntityNameVsFieldListMap() {
		return this.entityNameVsFieldListMap;
	}

	/**
	 * @param argEntityNameVsFieldListMap
	 *            the entityNameVsFieldListMap to set
	 */
	public void setEntityNameVsFieldListMap(
			Map<String, List<Field>> argEntityNameVsFieldListMap) {
		this.entityNameVsFieldListMap = argEntityNameVsFieldListMap;
	}

}
