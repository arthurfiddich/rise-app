package com.rise.common.util.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.rise.common.util.annotation.FieldType.Type;

@Target(value = ElementType.FIELD)
@Retention(value = RetentionPolicy.RUNTIME)
public @interface Reference {

	public Type type();
	public String className();
	public String prefix();
	public String name();
	public String variableName();
}
