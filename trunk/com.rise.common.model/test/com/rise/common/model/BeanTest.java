package com.rise.common.model;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

public class BeanTest {
	public static void main(String[] args) throws IntrospectionException {
		Map<String, Object> map = getNonNullProperties(new Person());
		Iterator<Entry<String, Object>> iter = map.entrySet().iterator();
		while (iter.hasNext()) {
			Entry<String, Object> entry = iter.next();
			System.out.println(entry.getKey());
			System.out.println(entry.getValue());
		}
		final BeanInfo beanInfo = Introspector.getBeanInfo(new Person()
				.getClass());
		BeanInfo[] beanInfos = beanInfo.getAdditionalBeanInfo();
		for (BeanInfo beanInfo1 : beanInfos) {
			System.out.println(beanInfo1.getClass().getName());
			// beanInfo1.getPropertyDescriptors();
		}

	}

	public static Map<String, Object> getNonNullProperties(final Object thingy) {
		final Map<String, Object> nonNullProperties = new TreeMap<String, Object>();
		try {
			final BeanInfo beanInfo = Introspector.getBeanInfo(thingy
					.getClass());
			for (final PropertyDescriptor descriptor : beanInfo
					.getPropertyDescriptors()) {
				try {
					System.out.println("\n");
					Method readMethod = descriptor.getReadMethod();
					System.out.println(readMethod.getName() + "\t"
							+ readMethod.getReturnType().getSimpleName());
					Type genericReturnType = readMethod.getGenericReturnType();
					if (genericReturnType instanceof ParameterizedType) {
						ParameterizedType pt = (ParameterizedType) genericReturnType;
						for (Type typeOfReferance : pt.getActualTypeArguments()) {
							if (!descriptor.getName().equals("class")) {
								Class cls = (Class) typeOfReferance;
								if (cls.isPrimitive()) {
									System.out.println("Yes Primitive :"
											+ readMethod.getName());
								} else if (cls.isSynthetic()) {
									System.out.println("Yes Synthetic :"
											+ readMethod.getName());
								} else if (cls.isMemberClass()) {
									System.out.println("Yes MemberClass :"
											+ readMethod.getName());
								} else if (cls.isLocalClass()) {
									System.out.println("Yes LocalClass :"
											+ readMethod.getName());
								}
							}
						}
					}
					System.out.println(genericReturnType.toString());
					final Object propertyValue = readMethod.invoke(thingy);
					if (propertyValue != null) {
						nonNullProperties.put(descriptor.getName(),
								propertyValue);
					}
				} catch (final IllegalArgumentException e) {
					// handle this please
				} catch (final IllegalAccessException e) {
					// and this also
				} catch (final InvocationTargetException e) {
					// and this, too
				}
			}
		} catch (final IntrospectionException e) {
			// do something sensible here
		}
		return nonNullProperties;
	}
}
