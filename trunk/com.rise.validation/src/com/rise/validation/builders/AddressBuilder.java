package com.rise.validation.builders;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.util.List;

import com.rise.common.util.Helper.TenantConfigHelper;
import com.rise.common.util.annotation.Validation;
import com.rise.common.util.annotation.processor.AnnotationProcessor;
import com.rise.common.util.checker.Precondition;
import com.rise.common.util.constants.HibernateHelperConstants;

public class AddressBuilder {

	private Object object;

	public AddressBuilder() {
		super();
	}

	public AddressBuilder(Object argObject) {
		super();
		this.object = argObject;
	}

	/*
	 * This method will return a string with the provided object from the
	 * constructor. If the object was null the method will return null.
	 */
	public String build() throws IllegalArgumentException,
			IllegalAccessException {
		if (Precondition.checkNotNull(object)) {
			List<Field> fieldsList = TenantConfigHelper
					.getInstance()
					.getModelNameVsFieldsMap()
					.get(Introspector.decapitalize(object.getClass()
							.getSimpleName()));
			// List<Field> fieldsList = AnnotationProcessor.getAnnoatatedFields(
			// object.getClass().getName(), Validation.class);
			if (Precondition.checkNotEmpty(fieldsList)) {
				StringBuilder addressBuilder = new StringBuilder();
				for (int i = 0; i < fieldsList.size(); i++) {
					Field field = fieldsList.get(i);
					field.setAccessible(true);
					Object result = field.get(object);
					addressBuilder.append(result.toString());
					if (i < fieldsList.size() - 1) {
						addressBuilder.append(HibernateHelperConstants.COMMA);
					}
				}
				return addressBuilder.toString();
			}
		}
		return null;
	}

	public Object getValue(final Object argObject, String argPropertyName) {
		try {
			final BeanInfo beanInfo = Introspector.getBeanInfo(argObject
					.getClass());
			PropertyDescriptor[] propertyDescriptorsArray = beanInfo
					.getPropertyDescriptors();
			if (Precondition.checkNotEmpty(propertyDescriptorsArray)) {
				for (PropertyDescriptor propertyDescriptor : propertyDescriptorsArray) {
					if (propertyDescriptor.getName().equals(argPropertyName)) {
						return propertyDescriptor.getReadMethod().invoke(
								argObject);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
