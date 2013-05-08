<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Address</title>
<style type="text/css">
body {
	font-family: sans-serif;
}

.data,.data td {
	border-collapse: collapse;
	width: 50%;
	border: 1px solid #aaa;
	margin: 2px;
	padding: 2px;
}

.data th {
	font-weight: bold;
	background-color: #5C82FF;
	color: white;
}
</style>
</head>
<body>
	<h2>Address Form</h2>

	<form:form method="post" modelAttribute="address" action="create">
		<table>
			<tr>
				<td><form:label path="id">
						<spring:message code="label.id" />
					</form:label></td>
				<td><form:input path="id" />
				</td>
			</tr>
			<tr>
				<td><form:label path="streetAddress">
						<spring:message code="label.streetAddress" />
					</form:label></td>
				<td><form:input path="streetAddress" />
				</td>
			</tr>
			<tr>
				<td><form:label path="city">
						<spring:message code="label.city" />
					</form:label></td>
				<td><form:input path="city" />
				</td>
			</tr>
			<tr>
				<td><form:label path="state">
						<spring:message code="label.state" />
					</form:label></td>
				<td><form:input path="state" />
				</td>
			</tr>
			<tr>
				<td><form:label path="postalCode">
						<spring:message code="label.postalCode" />
					</form:label></td>
				<td><form:input path="postalCode" />
				</td>
			</tr>
			<tr>
				<td><form:label path="country">
						<spring:message code="label.country" />
					</form:label></td>
				<td><form:input path="country" />
				</td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit"
					value="<spring:message code="label.addcontact"/>" />
				</td>
			</tr>
		</table>
	</form:form>

	<h3>Address List</h3>
	<c:if test="${!empty addresses}">
		<table class="data">
			<tr>
				<td>ID</td>
				<td>Street Address</td>
				<td>City</td>
				<td>State</td>
				<td>Postal Code</td>
				<td>Country</td>
			</tr>
			<c:forEach items="${addresses}" var="address">
				<tr>
					<td><a href="findById/${address.id}">${address.id }</a></td>
					<td>${address.streetAddress }</td>
					<td>${address.city }</td>
					<td>${address.state }</td>
					<td>${address.postalCode }</td>
					<td>${address.country }</td>
					<td><a href="delete/${address.id}">delete</a></td>
				</tr>
			</c:forEach>
		</table>
	</c:if>
</body>
</html>