package com.rise.common.util.annotation;

public enum MainTab {

	TRUE("true"), FALSE("false");

	public String flag;

	private MainTab(String argFlag) {
		this.flag = argFlag;
	}

	public static boolean isActive(String argActive) {
		MainTab mainTab = MainTab.valueOf(argActive);
		switch (mainTab) {
		case TRUE:
			return Boolean.parseBoolean(MainTab.TRUE.name());
		case FALSE:
			return Boolean.parseBoolean(MainTab.FALSE.name());
		default:
			return false;
		}
	}

}
