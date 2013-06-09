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
<title>Address: ${educationqualification.getId()} View Page</title>
</head>
<body bgcolor="skyblue">
	<h1 align="left">
		<b>${educationqualification.getId() }</b>
	</h1>
	<div id="viewAddress">
		<form:form modelAttribute="educationQualification" action="edit"
			method="get">
			<input type="hidden" name="id"
				value="${educationqualification.getId()}"></input>
			<div>
				<fieldset>
					<legend>Education Qualification Information</legend>
					<div id="eqHeader">
						<table>
							<tr></tr>
							<tr></tr>
							<tr>
								<td bgcolor="white" width="10%" align="left"><b>Education
										Qualification Information</b>
								</td>
							</tr>
						</table>
						<table>
							<tr>
								<td><form:label path="educationQualification.name"
										for="educationQualification.name">Name:</form:label>
								</td>
								<td><c:out value="${educationqualification.getName()}"></c:out>
								</td>
							</tr>
							<tr>
								<td><form:label path="educationQualification.university"
										for="educationQualification.university">University:</form:label>
								</td>
								<td width="60%"><c:out
										value="${educationqualification.getUniversity()}"></c:out>
								</td>
								<!-- </tr>
						<tr> -->
								<td><form:label path="educationQualification.yearCompleted"
										for="educationQualification.yearCompleted">Year Completed:</form:label>
								</td>
								<td><c:out
										value="${educationqualification.getYearCompleted()}"></c:out>
								</td>
							</tr>
							<tr>
								<td><form:label
										path="educationQualification.monthCompleted"
										for="educationQualification.monthCompleted">Last Name:</form:label>
								</td>
								<td width="60%"><c:out
										value="${educationqualification.getMonthCompleted()}"></c:out>
								</td>
								<!-- </tr>
						<tr> -->
								<td><form:label path="educationQualification.percentage"
										for="educationQualification.percentage">Suffix:</form:label>
								</td>
								<td><c:out
										value="${educationqualification.getPercentage()}"></c:out>
								</td>
							</tr>
							<tr>
								<td><form:label path="educationQualification.gpa"
										for="educationQualification.gpa">Last Name:</form:label>
								</td>
								<td width="60%"><c:out
										value="${educationqualification.getGpa()}"></c:out>
								</td>
							<tr align="center">
								<td align="center"><input type="submit" value="Delete"
									formaction="<%=request.getContextPath()%>/eq/delete/${educationqualification.getId()}" />
									<input type="submit" value="Edit" />
								</td>
							</tr>
						</table>
					</div>
				</fieldset>
			</div>
		</form:form>
	</div>
</body>
</html>