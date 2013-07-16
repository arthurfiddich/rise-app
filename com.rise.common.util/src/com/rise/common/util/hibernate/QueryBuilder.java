package com.rise.common.util.hibernate;

import java.beans.Introspector;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.List;

import com.rise.common.util.Helper.QueryInfo;
import com.rise.common.util.Helper.TenantConfigHelper;
import com.rise.common.util.checker.Precondition;
import com.rise.common.util.checker.PreconditionException;
import com.rise.common.util.constants.HibernateHelperConstants;

public class QueryBuilder {

	public static String buildQuery(Class argClass) {
		Class clazz = argClass;
		StringBuilder queryBuilder = new StringBuilder();
		Field[] fields = clazz.getDeclaredFields();
		for (int i = 0; i < fields.length; i++) {
			Field field = fields[i];
			if (!java.lang.reflect.Modifier.isStatic(field.getModifiers())) {
				if (field.getType().isAssignableFrom(String.class)) {
					System.out.println("String.....");
				}
				System.out.println(field.getType().getName() + "\t"
						+ field.getName());
				queryBuilder.append(field.getName());
				if (i < fields.length - 1) {
					queryBuilder.append(HibernateHelperConstants.COMMA);
				}
			}
		}
		return queryBuilder.toString();
	}

	private static String buildQuery(Class argClass, Class argComponentClass,
			boolean argSuperClass) {
		String simpleName = argComponentClass.getSimpleName();
		simpleName = Introspector.decapitalize(simpleName);
		boolean found = false;
		if (!argSuperClass) {
			found = TenantConfigHelper.getInstance().isComponent(
					argClass.getName(), simpleName);
		} else if (argSuperClass) {
			found = TenantConfigHelper.getInstance().isSuperClass(
					argClass.getName(), simpleName);
		}
		if (found) {
			Field[] fields = argComponentClass.getDeclaredFields();
			if(argSuperClass){
				return queryPartBuilder(argComponentClass, fields, true);
			}
			return queryPartBuilder(argComponentClass, fields, false);
		}
		return null;
	}

	private static String queryPartBuilder(Class argClass, Field[] fields,
			boolean argParentClass) {
		StringBuilder queryBuilder = new StringBuilder();
		if (fields != null && fields.length > 0) {
			for (int i = 0; i < fields.length; i++) {
				Field field = fields[i];
				System.out.println(Arrays.toString(fields));
				if (!Modifier.isStatic(field.getModifiers())) {
					boolean foundComponent = TenantConfigHelper.getInstance()
							.isComponent(argClass.getName(), field.getName());
					if (!foundComponent) {
						String param = buildParam(argClass, field,
								argParentClass);
						queryBuilder.append(param);
						if (i < fields.length - 1) {
							queryBuilder.append(HibernateHelperConstants.COMMA);
						}
					}
				}
			}
		}
		return queryBuilder.toString();
	}

	private static String buildParam(Class argClass, Field argField,
			boolean argParentClass) {
		if (!argParentClass) {
			StringBuilder paramBuilder = new StringBuilder();
			String componentClassName = argClass.getSimpleName();
			componentClassName = Introspector.decapitalize(componentClassName);
			paramBuilder.append(componentClassName);
			paramBuilder.append(HibernateHelperConstants.DOT);
			paramBuilder.append(argField.getName());
			return paramBuilder.toString();
		}
		return argField.getName();
	}

	public static QueryInfo buildQuery(Class argClass,
			List<Class> argComponentClassList, List<Class> argSuperClassList) {
		String query = buildQuery(argClass, argComponentClassList);
		QueryInfo queryInfo = new QueryInfo();
		StringBuilder queryBuilder = new StringBuilder();
		queryBuilder.append(query);
		List<Class> superClassList = (List<Class>) Precondition.ensureNotEmpty(
				argSuperClassList, HibernateHelperConstants.SUPER_CLASS_LIST);
		queryBuilder.append(HibernateHelperConstants.COMMA);
		for (int i = 0; i < superClassList.size(); i++) {
			Class clazz = superClassList.get(i);
			String queryPart = buildQuery(argClass, clazz, true);
			queryBuilder.append(queryPart);
			if (i < superClassList.size() - 1) {
				queryBuilder.append(HibernateHelperConstants.COMMA);
			}
		}
		queryInfo.setSelectClasue(queryBuilder.toString());
		queryBuilder.append(HibernateHelperConstants.SPACE);
		String fromClause = buildFromClause(argClass);
		queryInfo.setFromClause(fromClause);
		queryBuilder.append(fromClause);
		queryInfo.setQuery(queryBuilder.toString());
		return queryInfo;
	}

	public static String buildQuery(Class argClass,
			List<Class> argComponentClassList) {
		List<Class> componentList = (List<Class>) Precondition.ensureNotEmpty(
				argComponentClassList, HibernateHelperConstants.COMPONENT_LIST);
		StringBuilder queryBuilder = new StringBuilder();
		queryBuilder.append(HibernateHelperConstants.SELECT);
		queryBuilder.append(HibernateHelperConstants.SPACE);
		queryBuilder.append(queryPartBuilder(argClass,
				argClass.getDeclaredFields(), true));
		for (int i = 0; i < componentList.size(); i++) {
			Class clazz = componentList.get(i);
			String queryPart = buildQuery(argClass, clazz, false);
			queryBuilder.append(queryPart);
			if (i < componentList.size() - 1) {
				queryBuilder.append(HibernateHelperConstants.COMMA);
			}
		}
		return queryBuilder.toString();
	}

	private static String buildFromClause(Class argClass) {
		if (argClass != null) {
			StringBuilder fromClauseBuilder = new StringBuilder();
			fromClauseBuilder.append(HibernateHelperConstants.FROM);
			fromClauseBuilder.append(HibernateHelperConstants.SPACE);
			fromClauseBuilder.append(argClass.getSimpleName());
			return fromClauseBuilder.toString();
		}
		return HibernateHelperConstants.EMPTY;
	}

	public static String buildQuery(String argClassName) {
		try {
			Class clazz = Class.forName(argClassName);
			return buildQuery(clazz);
		} catch (ClassNotFoundException e) {
			throw new PreconditionException("Class not found: " + argClassName);
		}
	}

}
