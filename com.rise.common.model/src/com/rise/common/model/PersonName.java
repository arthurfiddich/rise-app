package com.rise.common.model;

import com.rise.common.util.annotation.DesiredField;

/**
 * We are not extending with BaseModel because its a unique class and I am using
 * this POJO class to make it "component" into another class. In Hibernate terms
 * the PersonName entity is called the component and it cannot have its own
 * primary key, it uses the primary key of the enclosing entity.
 * 
 * See this link:
 * http://www.dzone.com/tutorials/java/hibernate/hibernate-example
 * /hibernate-mapping-component-1.html
 * 
 * @author Administrator
 * 
 */
public class PersonName extends BaseModel {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	@DesiredField
	private String title;
	@DesiredField
	private String firstName;
	@DesiredField
	private String middleName;
	@DesiredField
	private String lastName;
	@DesiredField
	private String suffix;

	public PersonName() {
		super();
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String argTitle) {
		this.title = argTitle;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String argFirstName) {
		this.firstName = argFirstName;
	}

	public String getMiddleName() {
		return this.middleName;
	}

	public void setMiddleName(String argMiddleName) {
		this.middleName = argMiddleName;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String argLastName) {
		this.lastName = argLastName;
	}

	public String getSuffix() {
		return this.suffix;
	}

	public void setSuffix(String argSuffix) {
		this.suffix = argSuffix;
	}

}
