package com.rise.common.util.converters;

public class FieldRoleType {

	public static enum RoleType {
		PHONE_NUMBER("phone"), EMAIL("email"), WEB_SITE("website"), INSTANT_MESSENGER(
				"im"), DATE("date");

		public final String type;

		private RoleType(String argType) {
			this.type = argType;
		}

		public static Object getValidInstance(String argFieldType) {
			RoleType roleType = RoleType.valueOf(argFieldType);
			switch (roleType) {
			case PHONE_NUMBER:
				return null;
			case EMAIL:
				return null;
			case WEB_SITE:
				return null;
			case INSTANT_MESSENGER:
				return null;
			case DATE:
				return null;
			default:
				return null;
			}
		}
	}
}
