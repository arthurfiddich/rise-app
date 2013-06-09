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
<title>New Education Qualification Page</title>
</head>
<body bgcolor="skyblue">
	<h1 align="left">
		<b>New Education Qualification</b>
	</h1>

	<div id="newForm">
		<form:form modelAttribute="educationqualification" action="save"
			method="post">
			<div>
				<fieldset>
					<legend>Education Qualification Information</legend>
					<div id="personHeader">
						<table>
							<tr></tr>
							<tr></tr>
							<tr>
								<td bgcolor="white" width="10%" align="left"><b>Education
										Qualification Information</b></td>
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
							<td><form:label path="name"
									for="name">
									<spring:message code="label.name" />
								</form:label>
							</td>
							<td width="60%"><form:input path="name" /></td>
						</tr>
						<tr>
							<td><form:label path="university"
									for="university">
									<spring:message code="label.university" />
								</form:label></td>
							<td><form:input path="university" /></td>
						</tr>
						<tr>
							<td><form:label path="yearCompleted"
									for="yearCompleted">
									<spring:message code="label.yearCompleted" />
								</form:label>
							</td>
							<td width="60%"><form:input path="yearCompleted" />
							</td>
						</tr>
						<tr>
							<td><form:label path="monthCompleted"
									for="monthCompleted">
									<spring:message code="label.monthCompleted" />
								</form:label>
							</td>
							<td><form:input path="monthCompleted" /></td>
						</tr>
						<tr>
							<td><form:label path="percentage"
									for="percentage">
									<spring:message code="label.percentage" />
								</form:label>
							</td>
							<td width="60%"><form:input path="percentage" /></td>
						</tr>
						<tr>
							<td><form:label path="gpa" for="gpa">
									<spring:message code="label.gpa" />
								</form:label>
							</td>
							<td width="60%"><form:input path="gpa" /></td>
						</tr>
						<tr align="center">
							<td align="center"><input type="reset" value="Reset" /> <input
								type="submit" value="<spring:message code="label.educationqualification"/>"
								autofocus /></td>
						</tr>
					</table>
				</fieldset>
			</div>
		</form:form>
	</div>
</body>
</html>