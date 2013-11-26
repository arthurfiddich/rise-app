package com.data.generator.db.operations;

/**
 * 
 * @author Amar, This interface will help to authenticate to particular cloud.
 *         You just need to provide an authentication details(UserName and
 *         Password).
 * 
 * @param <T>, its nothing but the return type. You can use this instance for
 *        other operations like CRUD.
 */
public interface Authentication<T> {

	public T authenticate(String argUserName, String argPassword,
			String argSecurityToken);
}
