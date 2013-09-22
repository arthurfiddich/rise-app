package com.rise.webapp.validators;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.rise.common.model.ContactInformation;
import com.rise.common.model.Person;
import com.rise.common.util.Helper.TenantConfigHelper;
import com.rise.common.util.annotation.InHouse;
import com.rise.common.util.annotation.Validation;
import com.rise.common.util.annotation.processor.AnnotationProcessor;
import com.rise.common.util.checker.Precondition;
import com.rise.common.util.constants.HibernateHelperConstants;

@Component
public class PersonValidator implements Validator {

	@Override
	public boolean supports(Class<?> argClazz) {
		return argClazz.isAssignableFrom(Person.class);
	}

	@Override
	public void validate(Object argTarget, Errors argErrors) {
		Person person = (Person) argTarget;
		validate(argTarget.getClass(), argErrors, person);
		List<Field> inHouseAnnotationFields = AnnotationProcessor
				.getAnnotatedFields(argTarget.getClass(), InHouse.class);
		if (Precondition.checkNotEmpty(inHouseAnnotationFields)) {
			for (Field field : inHouseAnnotationFields) {
				System.out.println(field.getName());
				Object obj = getValue(person, field.getName());
				validate(field.getType(), argErrors, obj);
			}
		}
		// argErrors.rejectValue("Errors", "All Errors");
		// ContactInformation contactInformation =
		// person.getContactInformation();
		// if (Precondition.checkNotNull(contactInformation)) {
		// validate(argTarget, argErrors, person);
		// }
	}

	@SuppressWarnings("unchecked")
	protected void validate(Class<?> argClass, Errors argErrors,
			Object argObject) {
		List<Field> calidationFieldsList = AnnotationProcessor
				.getAnnotatedFields(argClass, Validation.class);
		if (Precondition.checkNotEmpty(calidationFieldsList)) {
			for (Field field : calidationFieldsList) {
				try {
					field.setAccessible(true);
					Object result;
					result = field.get(argObject);

					Object instance = getValidatorInstance(argClass, field);
					if (Precondition.checkNotNull(instance)
							&& Precondition.checkNotNull(result)) {
						com.rise.validation.Validation<String, String> validation = (com.rise.validation.Validation<String, String>) instance;
						boolean valid = validation.validate(result.toString());
						if (!valid) {
							argErrors.rejectValue(field.getName(),
									field.getName() + " Validation Failed");
						}
					}
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

	private Object getValidatorInstance(Class<?> argClass, Field argField) {
		if (Precondition.checkNotNull(argField)
				&& Precondition.checkNotNull(argClass)) {
			Validation validation = (Validation) AnnotationProcessor
					.checkAnnotation(argClass.getName(), argField.getName(),
							Validation.class);
			if (Precondition.checkNotNull(validation)) {
				String annotationPackage = validation.annotationPackage();
				String validationType = validation.validationType().validationType;
				if (Precondition.checkNotEmpty(annotationPackage)
						&& Precondition.checkNotEmpty(validationType)) {
					validationType += HibernateHelperConstants.VALIDATION_KEY_WORD;
					String fullyQualifiedValidatorClassName = new StringBuilder()
							.append(annotationPackage)
							.append(HibernateHelperConstants.DOT)
							.append(validationType).toString();
					Map<String, Object> fullyQualifiedClassNameVsValidatorInstanceMap = TenantConfigHelper
							.getInstance()
							.getFullyQualifiedClassNameVsValidatorInstance();
					if (Precondition
							.checkNotNull(fullyQualifiedClassNameVsValidatorInstanceMap)) {
						return fullyQualifiedClassNameVsValidatorInstanceMap
								.get(fullyQualifiedValidatorClassName);
					}
				}
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
