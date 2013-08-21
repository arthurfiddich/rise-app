package com.rise.common.util.annotation;

public class FieldType {

	public static enum Type {
		LIST("list"), SET("set"), DEFAULT("default"), MAP("map");

		public String type;

		private Type(String argType) {
			this.type = argType;
		}

		public static Object getInstance(String argType) {
			Type type = Type.valueOf(argType);
			switch (type) {
			case LIST:
				break;

			default:
				break;
			}
			return "";
		}
	}
}
