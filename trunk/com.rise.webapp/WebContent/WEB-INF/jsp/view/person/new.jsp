<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt"%>
<%@ page session="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>New Person</title>
</head>
<body bgcolor="skyblue">
	<h1 align="left">
		<b>New Person</b>
	</h1>

	<div id="newForm">
		<form:form modelAttribute="person" action="save" method="post">
			<div>
				<fieldset>
					<legend>Person Information</legend>
					<div id="addressHeader">
						<table>
							<tr></tr>
							<tr></tr>
							<tr>
								<td bgcolor="white" width="10%" align="left"><b>Person
										Information</b></td>
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
							<td><form:input path="id" /></td>
						</tr> --%>
						<tr>
							<td><form:label path="personName.title" for="personName.title">
									<spring:message code="label.title" />
								</form:label>
							</td>
							<td width="60%"><form:input path="personName.title" /></td>
						</tr>
						<tr>
							<td><form:label path="personName.firstName" for="personName.firstName">
									<spring:message code="label.firstName" />
								</form:label></td>
							<td><form:input path="personName.firstName" /></td>
						</tr>
						<tr>
							<td><form:label path="personName.middleName" for="personName.middleName">
									<spring:message code="label.middleName" />
								</form:label>
							</td>
							<td width="60%"><form:input path="personName.middleName" /></td>
						</tr>
						<tr>
							<td><form:label path="personName.lastName" for="personName.lastName">
									<spring:message code="label.lastName" />
								</form:label>
							</td>
							<td><form:input path="personName.lastName" /></td>
						</tr>
						<tr>
							<td><form:label path="personName.suffix" for="personName.suffix">
									<spring:message code="label.suffix" />
								</form:label>
							</td>
							<td width="60%"><form:input path="personName.suffix" /></td>
						</tr>
						<tr>
							<td><form:label path="dateOfBirth" for="dateOfBirth">
									<spring:message code="label.dateOfBirth" />
								</form:label>
							</td>
							<td width="60%"><form:input path="dateOfBirth" /></td>
						</tr>
						<tr>
							<td><form:label path="aadhaarNumber" for="aadhaarNumber">
									<spring:message code="label.aadhaarNumber" />
								</form:label>
							</td>
							<td width="60%"><form:input path="aadhaarNumber" /></td>
						</tr>
						<tr align="center">
							<td align="center"><input type="reset" value="Reset" /> <input
								type="submit" value="<spring:message code="label.addPerson"/>"
								autofocus /></td>
						</tr>
					</table>
				</fieldset>
			</div>
		</form:form>
	</div>
</body>
</html>