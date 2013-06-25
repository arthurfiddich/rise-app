package test;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.beanutils.BeanUtils;

import com.rise.common.model.Person;

public class Bean {

	public static void main(String[] args) throws IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {
		// Map map = BeanUtils.describe(new Person());
		// Iterator iterator = map.entrySet().iterator();
		// while(iterator.hasNext()){
		// Entry entry = (Entry) iterator.next();
		// System.out.println(entry.getKey());
		// // System.out.println(entry.getValue());
		// }

//		Field[] f = Person.class.getclass().getDeclaredFields();
		Field[] f = Person.class.getSuperclass().getDeclaredFields();
//		System.out.println(f.getClass().getCanonicalName());
//		System.out.println(f.length > 0);
		for (Field field : f) {
			System.out.println(field.getName());
		}
	}
}
