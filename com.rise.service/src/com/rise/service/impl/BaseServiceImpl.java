package com.rise.service.impl;

import java.beans.Introspector;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rise.common.model.Model;
import com.rise.common.util.Helper.TenantConfigHelper;
import com.rise.common.util.checker.Precondition;
import com.rise.common.util.checker.PreconditionException;
import com.rise.dao.common.BaseDao;
import com.rise.service.BaseService;

@Service
public abstract class BaseServiceImpl implements BaseService {

	// @Autowired
	// private BaseDao baseDao;

	@Transactional
	public void delete(Model argModel) {
		this.getBaseDao().delete(argModel);
	}

	@Transactional
	public List<Model> findAll() {
		return this.getBaseDao().findAll();
	}

	@Transactional
	public Model findById(Integer argId) {
		return this.getBaseDao().findById(argId);
	}

	@Transactional
	public void deleteById(Integer argId) {
		this.getBaseDao().deleteById(argId);
	}

	@Override
	public String getSimpleName() {
		return this.getPersistentClass().getSimpleName();
	}

	@Override
	public String getFullyQualifiedName() {
		return this.getPersistentClass().getName();
	}

	public abstract BaseDao getBaseDao();

	protected List<Object> assignProperties(List<Object[]> argResults,
			List<Class> argComponentClassList, Class argParentClass) {
		List<Object[]> results = (List<Object[]>) Precondition.ensureNotEmpty(
				argResults, "Output List");
		List<Object> classObjectsList = new ArrayList<Object>();
		try {
			for (Object[] recordValues : results) {
				Class parentClass = Class.forName(this.getPersistentClass()
						.getName());
				String paramName = Introspector.decapitalize(parentClass
						.getSimpleName());
				Object parentClassInstance = parentClass.newInstance();
				int count = 0;
				count = assignValues(recordValues, parentClassInstance, count,
						paramName);
				for (Class clazz : argComponentClassList) {
					Class componentClass = Class.forName(clazz.getName());
					Object componentClassInstance = componentClass
							.newInstance();
					String decapitalize = Introspector.decapitalize(clazz
							.getSimpleName());
					Field field = parentClass.getDeclaredField(decapitalize);
					field.setAccessible(true);
					count = assignValues(recordValues, componentClassInstance,
							count, decapitalize);
					field.set(parentClassInstance, componentClassInstance);
				}
				Class superClass = parentClass.getSuperclass();
				count = assignValues(recordValues, parentClassInstance, count,
						Introspector.decapitalize(superClass.getSimpleName()));
				classObjectsList.add(parentClassInstance);
			}
		} catch (Exception e) {
			throw new PreconditionException(
					"Exception while assigning the values to corresponding objects dynamically: ",
					e);
		}
		return classObjectsList;
	}

	private int assignValues(Object[] argRecordValues,
			Object argParentClassInstance, int argCount, String argParamName) {
		if (Precondition.checkNotEmpty(argParamName)) {
			List<Field> fieldNames = TenantConfigHelper.getInstance()
					.getModelNameVsFieldsMap().get(argParamName);
			try {
				for (Field field : fieldNames) {
					BeanUtils.setProperty(argParentClassInstance,
							field.getName(), argRecordValues[argCount]);
					++argCount;
				}
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
		}
		return argCount;
	}
}
