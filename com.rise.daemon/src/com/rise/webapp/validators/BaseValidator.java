package com.rise.webapp.validators;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class BaseValidator implements Validator {

	private Class<?> clazz;

	@Override
	public boolean supports(Class<?> argClazz) {
		return argClazz.isAssignableFrom(this.getClazz());
	}

	@Override
	public void validate(Object argTarget, Errors argErrors) {
		Method[] methodsArray = argTarget.getClass().getMethods();
		try {
			Field[] fields = argTarget.getClass().getDeclaredFields();
			for (Field field : fields) {
				System.out.println("Field Name: " + field.getName());
			}
		} catch (SecurityException e) {
			e.printStackTrace();
		}
	}

	public Class<?> getClazz() {
		return this.clazz;
	}

	public void setClazz(Class<?> argClazz) {
		this.clazz = argClazz;
	}

}
