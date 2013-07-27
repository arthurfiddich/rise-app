package com.rise.common.model;

import java.beans.Introspector;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class Test {

	// List<String> stringList = new ArrayList<String>();
	// List<Integer> integerList = new ArrayList<Integer>();
	//
	// public static void main(String... args) throws Exception {
	// Field stringListField = Test.class.getDeclaredField("stringList");
	// ParameterizedType stringListType = (ParameterizedType)
	// stringListField.getGenericType();
	// Class<?> stringListClass = (Class<?>)
	// stringListType.getActualTypeArguments()[0];
	// System.out.println(stringListClass); // class java.lang.String.
	//
	// Field integerListField = Test.class.getDeclaredField("integerList");
	// ParameterizedType integerListType = (ParameterizedType)
	// integerListField.getGenericType();
	// Class<?> integerListClass = (Class<?>)
	// integerListType.getActualTypeArguments()[0];
	// System.out.println(integerListClass); // class java.lang.Integer.
	// }
	java.util.List<? extends Foo> fooList = new ArrayList<Bar>();

	public static void main(String[] args) throws Exception {
		System.out.println(Test.class.getSimpleName());
		System.out.println(Introspector.decapitalize("TestTest"));
		Field field = Test.class.getDeclaredField("fooList");

		Type type = field.getGenericType();
		System.out.println("type: " + type);
		if (type instanceof ParameterizedType) {
			ParameterizedType pt = (ParameterizedType) type;
			System.out.println("raw type: " + pt.getRawType());
			System.out.println("owner type: " + pt.getOwnerType());
			System.out.println("actual type args:");
			for (Type t : pt.getActualTypeArguments()) {
				System.out.println("    " + t);
			}
		}

		System.out.println();

		Object obj = field.get(new Test());
		System.out.println("obj: " + obj);
		System.out.println("obj class: " + obj.getClass());
	}

	static class Foo {
	}

	static class Bar extends Foo {
	}
}
