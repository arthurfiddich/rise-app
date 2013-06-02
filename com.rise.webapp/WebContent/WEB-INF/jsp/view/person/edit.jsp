<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt"%>
<%@ page session="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="com.rise.common.model.Address"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Edit Person: ${editPerson.getId()}</title>
</head>
<body bgcolor="skyblue">
	<h1 align="left">
		<b>${editPerson.getId()}</b>
	</h1>

	<div id="newForm">
		<form:form modelAttribute="person" action="update" method="post">
			<form:input path="id" value="${editPerson.getId()}" type="hidden" />
			<%-- <form:input type="hidden" name="id" value="${editPerson.getId()}"></input> --%>
			<div>
				<fieldset>
					<legend>Address Information</legend>
					<div id="personHeader">
						<table>
							<tr></tr>
							<tr></tr>
							<tr>
								<td bgcolor="white" width="10%" align="left"><b>Address
										Information</b>
								</td>
							</tr>
						</table>
					</div>
					<table>
						<tr></tr>
						<tr></tr>
						<%-- <tr>
							<td><form:label path="id" for="id">
									<spring:message code="label.id" />
								</form:label>
							</td>
							<td><form:input path="id"
									value="${editPerson.getId()}" /></td>
						</tr> --%>
						<tr>
							<td><form:label path="personName.title"
									for="personName.title">
									<spring:message code="label.title" />
								</form:label></td>
							<td width="60%"><form:input path="personName.title"
									value="${editPerson.getPersonName().getTitle() }" />
							</td>
						</tr>
						<tr>
							<td><form:label path="personName.firstName"
									for="personName.firstName">
									<spring:message code="label.firstName" />
								</form:label>
							</td>
							<td><form:input path="personName.firstName"
									value="${editPerson.getPersonName().getFirstName() }" />
							</td>
						</tr>
						<tr>
							<td><form:label path="personName.middleName"
									for="personName.middleName">
									<spring:message code="label.middleName" />
								</form:label></td>
							<td width="60%"><form:input path="personName.middleName"
									value="${editPerson.getPersonName().getMiddleName() }" /></td>
						</tr>
						<tr>
							<td><form:label path="personName.lastName"
									for="personName.lastName">
									<spring:message code="label.lastName" />
								</form:label></td>
							<td><form:input path="personName.lastName"
									value="${editPerson.getPersonName().getLastName() }" />
							</td>
						</tr>
						<tr>
							<td><form:label path="personName.suffix"
									for="personName.suffix">
									<spring:message code="label.suffix" />
								</form:label></td>
							<td width="60%"><form:input path="personName.suffix"
									value="${editPerson.getPersonName().getSuffix() }" />
							</td>
						</tr>
						<tr>
							<td><form:label path="dateOfBirth" for="dateOfBirth">
									<spring:message code="label.dateOfBirth" />
								</form:label></td>
							<td width="60%"><form:input path="dateOfBirth"
									value="${editPerson.getDateOfBirth() }" />
							</td>
						</tr>
						<tr>
							<td><form:label path="aadhaarNumber" for="aadhaarNumber">
									<spring:message code="label.aadhaarNumber" />
								</form:label></td>
							<td width="60%"><form:input path="aadhaarNumber"
									value="${editPerson.getAadhaarNumber() }" />
							</td>
						</tr>
						<tr align="center">
							<td align="center"><input type="button" value="Cancel"
								onClick="history.go(-1);return true;" /> <input type="submit"
								value="Save" autofocus />
							</td>
						</tr>
					</table>
				</fieldset>
			</div>
		</form:form>
	</div>
</body>
</html>