<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Person Names</title>
</head>
<body>
	<h2>Person Names List</h2>

	<form:form method="post" modelAttribute="personName" action="create">
		<table>
			<tr>
				<td><form:label path="id">
						<spring:message code="label.id" />
					</form:label></td>
				<td><form:input path="id" />
				</td>
			</tr>
			<tr>
				<td><form:label path="title">
						<spring:message code="label.title" />
					</form:label></td>
				<td><form:input path="title" />
				</td>
			</tr>
			<tr>
				<td><form:label path="firstName">
						<spring:message code="label.firstName" />
					</form:label></td>
				<td><form:input path="firstName" />
				</td>
			</tr>
			<tr>
				<td><form:label path="middleName">
						<spring:message code="label.middleName" />
					</form:label></td>
				<td><form:input path="middleName" />
				</td>
			</tr>
			<tr>
				<td><form:label path="lastName">
						<spring:message code="label.lastName" />
					</form:label></td>
				<td><form:input path="lastName" />
				</td>
			</tr>
			<tr>
				<td><form:label path="suffix">
						<spring:message code="label.suffix" />
					</form:label></td>
				<td><form:input path="suffix" />
				</td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit"
					value="<spring:message code="label.addcontact"/>" />
				</td>
			</tr>
		</table>
	</form:form>

	<h3>Person Names List</h3>
	<c:if test="${!empty personNames}">
		<table class="data">
			<tr>
				<td>Title</td>
				<td>FirstName</td>
				<td>MiddleName</td>
				<td>LastName</td>
				<td>Suffix</td>
			</tr>
			<c:forEach items="${personNames}" var="personName">
				<tr>
					<td>${personName.title }</td>
					<td>${personName.firstName }</td>
					<td>${personName.middleName }</td>
					<td>${personName.lastName }</td>
					<td>${personName.suffix }</td>
				</tr>
			</c:forEach>
		</table>
	</c:if>
</body>
</html>