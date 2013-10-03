package com.rise.webapp.validators;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.text.WordUtils;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.rise.common.model.Address;
import com.rise.common.model.Person;
import com.rise.common.util.Helper.TenantConfigHelper;
import com.rise.common.util.annotation.InHouse;
import com.rise.common.util.annotation.Validation;
import com.rise.common.util.annotation.processor.AnnotationProcessor;
import com.rise.common.util.checker.Precondition;
import com.rise.common.util.constants.HibernateHelperConstants;
import com.rise.validation.impl.AddressValidation;

@Component
public class PersonValidator implements Validator {

	@Override
	public boolean supports(Class<?> argClazz) {
		return argClazz.isAssignableFrom(Person.class);
	}

	@Override
	public void validate(Object argTarget, Errors argErrors) {
		Map<String, List<String>> modelNameVsUiComponentMap = TenantConfigHelper
				.getInstance().getModelNameVsUiComponentListMap();
		if (Precondition.checkNotNull(modelNameVsUiComponentMap)) {
			List<String> tokens = modelNameVsUiComponentMap.get(argTarget
					.getClass().getName());
			if (Precondition.checkNotEmpty(tokens)) {
				for (String token : tokens) {
					try {
						validate(argTarget, argErrors, token);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}
	}

	private void validate(Object argTarget, Errors argErrors, String argToken)
			throws Exception {
		if (Precondition.checkNotEmpty(argToken)) {
			String fieldTree = argToken;
			int dotIndex = fieldTree.indexOf(HibernateHelperConstants.DOT);
			if (dotIndex != -1) {
				List<String> tokens = getTokens(fieldTree);
				if (Precondition.checkNotEmpty(tokens)) {
					Object obj = argTarget;
					for (String token : tokens) {
						if (!argTarget.getClass().getSimpleName().equals(token)) {
							token = getFieldName(token);
							obj = getValue(obj, token);
						}
					}
					String fieldName = getFieldName(tokens);
					fieldTree = removeParentClassName(fieldTree, argTarget
							.getClass().getSimpleName());
					validate(obj, argErrors, fieldName, fieldTree);
				}
			}
		}
	}

	private String removeParentClassName(String argFieldTree,
			String argParentClassName) {
		String token = argFieldTree;
		if (Precondition.checkNotEmpty(token)
				&& Precondition.checkNotEmpty(argParentClassName)) {
			if (token.startsWith(argParentClassName)) {
				if (token.length() > argParentClassName.length() + 1) {
					return token.substring(argParentClassName.length() + 1);
				}
			}
		}
		return token;
	}

	@SuppressWarnings("unchecked")
	protected void validate(Object argObject, Errors argErrors,
			String fieldName, String argTreeForField) throws Exception {
		if (Precondition.checkNotEmpty(fieldName)) {
			Object validationInstance = getValidation(argObject,
					Validation.class);
			if (Precondition.checkNotNull(validationInstance)) {
				com.rise.validation.Validation<Object, Object> validation = (com.rise.validation.Validation<Object, Object>) validationInstance;
				boolean valid = validation.validate(argObject);
				if (!valid) {
					argErrors.rejectValue(argTreeForField, fieldName,
							validation.getClass().getSimpleName() + " Failed");
				}
			} else {
				validate(argObject.getClass(), argErrors, argObject);
			}
		}
	}

	private Object getValidation(Object argObject,
			Class<? extends Annotation> argAnnotationType) {
		if (Precondition.checkNotNull(argObject)) {
			Validation validation = (Validation) argObject.getClass()
					.getAnnotation(argAnnotationType);
			if (Precondition.checkNotNull(validation)) {
				return getValidatorInstance(validation);
			}
		}
		return null;
	}

	private String getFieldName(List<String> argTokens) {
		String fieldName = argTokens.get(argTokens.size() - 1);
		return WordUtils.uncapitalize(fieldName);
	}

	private String getFieldName(String argToken) {
		return WordUtils.uncapitalize(argToken);
	}

	protected List<String> getTokens(String argToken) {
		String[] tokensArray = StringUtils.split(argToken,
				HibernateHelperConstants.DOT);
		return Arrays.asList(tokensArray);
	}

	// @Override
	public void validate1(Object argTarget, Errors argErrors) {
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
		Address address = person.getContactInformation().getPrimaryAddress();
		AddressValidation addressValidation = new AddressValidation();
		try {
			boolean valid = addressValidation.validate(address);
			if (!valid) {
				argErrors.rejectValue(Address.class.getSimpleName(), "Address",
						"Adress is not valid...");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@SuppressWarnings("unchecked")
	protected void validate(Class<?> argClass, Errors argErrors,
			Object argObject) {
		List<Field> ValidationFieldsList = AnnotationProcessor
				.getAnnotatedFields(argClass, Validation.class);
		if (Precondition.checkNotEmpty(ValidationFieldsList)) {
			for (Field field : ValidationFieldsList) {
				try {
					field.setAccessible(true);
					Object result = field.get(argObject);

					Object instance = getValidatorInstance(argClass,
							field.getName());
					if (Precondition.checkNotNull(instance)
							&& Precondition.checkNotNull(result)) {
						com.rise.validation.Validation<String, String> validation = (com.rise.validation.Validation<String, String>) instance;
						boolean valid = validation.validate(result.toString());
						if (!valid) {
							argErrors.rejectValue(argClass.getSimpleName()
									+ "." + field.getName(), field.getName(),
									field.getName() + " Validation Failed");
						}
					}
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (Exception e) {
					argErrors.rejectValue(argClass.getSimpleName() + "."
							+ field.getName(), field.getName(), field.getName()
							+ " Validation Failed");
				}
			}
		}
	}

	private Object getValidatorInstance(Class<?> argClass, String argFieldName) {
		if (Precondition.checkNotNull(argFieldName)
				&& Precondition.checkNotNull(argClass)) {
			Validation validation = (Validation) AnnotationProcessor
					.checkAnnotation(argClass.getName(), argFieldName,
							Validation.class);
			if (Precondition.checkNotNull(validation)) {
				return getValidatorInstance(validation);
			}
		}
		return null;
	}

	protected Object getValidatorInstance(Validation validation) {
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
