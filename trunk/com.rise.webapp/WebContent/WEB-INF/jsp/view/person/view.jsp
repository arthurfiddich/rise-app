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
<title>Address: ${person.getId()} View Page</title>
</head>
<body bgcolor="skyblue">
	<h1 align="left">
		<b>${person.getId() }</b>
	</h1>
	<div id="viewAddress">
		<form:form modelAttribute="person" action="edit" method="get">
			<input type="hidden" name="id" value="${person.getId()}"></input>
			<div>
				<fieldset>
					<legend>Person Information</legend>
					<div id="personHeader">
						<table>
							<tr></tr>
							<tr></tr>
							<tr>
								<td bgcolor="white" width="10%" align="left"><b>Person
										Information</b></td>
							</tr>
						</table>
						<table>
							<tr>
								<td><form:label path="personName.title" for="personName.title">Title:</form:label></td>
								<td><c:out value="${person.getPersonName().getTitle()}"></c:out></td>
							</tr>
							<tr>
								<td><form:label path="personName.firstName" for="personName.firstName">First Name:</form:label></td>
								<td width="60%"><c:out value="${person.getPersonName().getFirstName()}"></c:out></td>
								<!-- </tr>
						<tr> -->
								<td><form:label path="personName.middleName" for="personName.middleName">Middle Name:</form:label></td>
								<td><c:out value="${person.getPersonName().getMiddleName()}"></c:out></td>
							</tr>
							<tr>
								<td><form:label path="personName.lastName" for="personName.lastName">Last Name:</form:label></td>
								<td width="60%"><c:out value="${person.getPersonName().getLastName()}"></c:out></td>
								<!-- </tr>
						<tr> -->
								<td><form:label path="personName.suffix" for="personName.suffix">Suffix:</form:label></td>
								<td><c:out value="${person.getPersonName().getSuffix()}"></c:out></td>
							</tr>
							<tr align="center">
								<td align="center"><input type="submit" value="Delete"
									formaction="<%=request.getContextPath()%>/person/delete/${person.getId()}" /> <input
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