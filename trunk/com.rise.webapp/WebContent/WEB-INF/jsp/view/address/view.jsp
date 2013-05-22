<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="com.rise.common.model.Address"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt"%>
<%@ page session="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Address: ${address.getId()} View Page</title>
</head>
<body bgcolor="skyblue">
	<h1 align="left">
		<b>${address.getId() }</b>
	</h1>
	<div id="viewAddress">
		<form:form modelAttribute="address" action="edit" method="get">
			<input type="hidden" name="id" value="${address.getId()}"></input>
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
						<table>
							<tr>
								<td><form:label path="streetAddress" for="streetAddress">Street Address:</form:label></td>
								<td><c:out value="${address.getStreetAddress()}"></c:out></td>
							</tr>
							<tr>
								<td><form:label path="city" for="city">City:</form:label></td>
								<td width="60%"><c:out value="${address.getCity()}"></c:out></td>
								<!-- </tr>
						<tr> -->
								<td><form:label path="state" for="state">State</form:label></td>
								<td><c:out value="${address.getState()}"></c:out></td>
							</tr>
							<tr>
								<td><form:label path="postalCode" for="postalCode">Postal Code:</form:label></td>
								<td width="60%"><c:out value="${address.getPostalCode()}"></c:out></td>
								<!-- </tr>
						<tr> -->
								<td><form:label path="country" for="country">Country:</form:label></td>
								<td><c:out value="${address.getCountry()}"></c:out></td>
							</tr>
							<tr align="center">
								<td align="center"><input type="submit" value="Delete"
									formaction="<%=request.getContextPath()%>/address/delete/${address.getId()}" /> <input
									type="submit" value="Edit" /></td>
							</tr>
						</table>
					</div>
				</fieldset>
			</div>
		</form:form>
	</div>
</body>
</html>