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
<title>Edit Person: ${editAddress.getId()}</title>
</head>
<body bgcolor="skyblue">
	<h1 align="left">
		<b>${editAddress.getId()}</b>
	</h1>

	<div id="newForm">
		<form:form modelAttribute="address" action="update" method="post">
			<form:input path="id" value="${editAddress.getId()}" type="hidden" />
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
										Information</b></td>
							</tr>
						</table>
					</div>
					<table>
						<tr></tr>
						<tr></tr>
						<tr>
							<td><form:label path="streetAddress" for="streetAddress">Street Address:</form:label></td>
							<td><form:input path="streetAddress"
									value="${editAddress.getStreetAddress() }" /></td>
						</tr>
						<tr>
							<td><form:label path="city" for="city">City:</form:label></td>
							<td width="60%"><form:input path="city"
									value="${editAddress.getCity() }" /></td>
							<!-- </tr>
						<tr> -->
							<td><form:label path="state" for="state">State</form:label></td>
							<td><form:input path="state"
									value="${editAddress.getState() }" /></td>
						</tr>
						<tr>
							<td><form:label path="postalCode" for="postalCode">Postal Code:</form:label></td>
							<td width="60%"><form:input path="postalCode"
									value="${editAddress.getPostalCode() }" /></td>
							<!-- </tr>
						<tr> -->
							<td><form:label path="country" for="country">Country:</form:label></td>
							<td><form:input path="country"
									value="${editAddress.getCountry()}" /></td>
						</tr>
						<tr align="center">
							<td align="center"><input type="submit" value="Cancel" /> <input
								type="submit" value="Save" autofocus /></td>
						</tr>
					</table>
				</fieldset>
			</div>
		</form:form>
	</div>
</body>
</html>