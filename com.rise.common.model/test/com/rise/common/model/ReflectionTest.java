package com.rise.common.model;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;

public class ReflectionTest {
	public static void main(String[] args) throws ClassNotFoundException, IntrospectionException {
		String name = Address.class.getName();
//		System.out.println(name);
		Class cls = Class.forName(name);
		System.out.println(cls.isPrimitive());
//		// Field[] fields = cls.getFields();
//		Field[] fields = cls.getDeclaredFields();
//		for (Field field : fields) {
//			if(cls.isAssignableFrom(field.getType())){
//			System.out.println(field.getName() + "\t" + field.getType());
//			}
//		}
		BeanInfo beanInfo = Introspector.getBeanInfo(cls, Object.class);
		PropertyDescriptor[] pds = beanInfo.getPropertyDescriptors();
		for (PropertyDescriptor propertyDescriptor : pds) {
			System.out.println(propertyDescriptor.getName());
			System.out.println(propertyDescriptor.getDisplayName());
			System.out.println(propertyDescriptor.getShortDescription());
			System.out.println(propertyDescriptor.getPropertyType().getSimpleName());
		}
	}
}
