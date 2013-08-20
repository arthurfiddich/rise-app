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
<title>New Address</title>
</head>
<body bgcolor="skyblue">
	<h1 align="left">
		<b>New Address</b>
	</h1>

	<div id="newForm">
		<form:form modelAttribute="address" action="save" method="post">
			<div>
				<fieldset>
					<legend>Address Information</legend>
					<div id="addressHeader">
						<table>
							<tr></tr>
							<tr></tr>
							<tr>
								<td bgcolor="white" width="10%" align="left"><b>Address
										Information</b></td>
							</tr>
						</table>
					</div>
					<table>
						<tr></tr>
						<tr></tr>
						<tr>
							<td><form:label path="id" for="id">
									<spring:message code="label.id" />
								</form:label>
							</td>
							<td><form:input path="id" /></td>
						</tr>
						<tr>
							<td><form:label path="streetAddress" for="streetAddress">
									<spring:message code="label.streetAddress" />
								</form:label>
							</td>
							<td width="60%"><form:input path="streetAddress" /></td>
							<!-- </tr>
						<tr> -->
							<td><form:label path="city" for="city">
									<spring:message code="label.city" />
								</form:label></td>
							<td><form:input path="city" /></td>
						</tr>
						<tr>
							<td><form:label path="state" for="state">
									<spring:message code="label.state" />
								</form:label>
							</td>
							<td width="60%"><form:input path="state" /></td>
							<!-- </tr>
						<tr> -->
							<td><form:label path="postalCode" for="postalCode">
									<spring:message code="label.postalCode" />
								</form:label>
							</td>
							<td><form:input path="postalCode" /></td>
						</tr>
						<tr>
							<td><form:label path="country" for="country">
									<spring:message code="label.country" />
								</form:label>
							</td>
							<td width="60%"><form:input path="country" /></td>
						<tr align="center">
							<td align="center"><input type="reset" value="Reset" /> <input
								type="submit" value="<spring:message code="label.addcontact"/>" autofocus /></td>
						</tr>
					</table>

				</fieldset>
			</div>
		</form:form>
	</div>
</body>
</html>