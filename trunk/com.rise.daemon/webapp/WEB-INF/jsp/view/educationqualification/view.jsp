<!DOCTYPE html>
<!--[if lt IE 7 ]> <html lang="en" class="no-js ie6 lt8"> <![endif]-->
<!--[if IE 7 ]>    <html lang="en" class="no-js ie7 lt8"> <![endif]-->
<!--[if IE 8 ]>    <html lang="en" class="no-js ie8 lt8"> <![endif]-->
<!--[if IE 9 ]>    <html lang="en" class="no-js ie9"> <![endif]-->
<!--[if (gt IE 9)|!(IE)]><!-->
<html lang="en" class="no-js">
<!--<![endif]-->
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt"%>
<%@ page session="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="com.rise.common.model.Address"%>
<head>
<meta charset="UTF-8" />
<!-- <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">  -->
<title>Rural Integration Sustainable Employment</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description"
	content="Login and Registration Form with HTML5 and CSS3" />
<meta name="keywords"
	content="html5, css3, form, switch, animation, :target, pseudo-class" />
<meta name="author" content="Codrops" />
<!--   <link rel="shortcut icon" href="../favicon.ico">  -->
<link rel="stylesheet" type="text/css"
	href="/rise/resources/css/demo.css" />
<link rel="stylesheet" type="text/css"
	href="/rise/resources/css/style.css" />
<link rel="stylesheet" type="text/css"
	href="/rise/resources/css/animate-custom.css" />
<link rel="stylesheet" type="text/css"
	href="/rise/resources/css/menu.css" />
</head>
<body>
	<div class="container">
		<header>
			<h1>Rural Integration Sustainable Employment</h1>
		</header>
		<div id="menubar" style="width: auto">
			<ul id="menu">
				<li><a name="home" href="/rise/home">Home</a></li>
				<li><a name="person" href="/rise/educationqualification/list"
					class="activesection"><span style="color: blue;">Education
							Qualification</span> </a></li>
				<li><a name="trainer" href="/rise/trainer">Trainer</a></li>
				<li><a name="agents" href="/rise/agents">Agents</a></li>
				<li><a name="overview" href="/rise/overview">Overview</a></li>
				<li><a name="contactus" href="/rise/contactus">Contact Us</a></li>
			</ul>
		</div>
		<form:form modelAttribute="educationQualification" action="edit"
			method="get">
			<input type="hidden" name="id"
				value="${educationQualification.getId()}"></input>
			<div id="divcontainer" class="content">
				<input type="submit" value="Edit" class="styled-button-3" /> <input
					type="submit" value="Delete" class="styled-button-3"
					formaction="<%=request.getContextPath()%>/educationqualification/deleteEq/${educationQualification.getId()}">
				<h1 align="left" class="headersection">
					<b>Personal Information</b>
				</h1>
				<table>
					<tr>
						<td>
							<table>
								<tr>
									<td align="right"><form:label path="person.id"
											for="person.id">
											<spring:message code="label.personId" />
										</form:label>
									</td>
									<td><a
										href="<%=request.getContextPath()%>/person/${educationQualification.getPerson().getId() }"><span
											style="color: white;"><u><c:out
													value="${educationQualification.getPerson().getId() }"></c:out></u>
										</span> </a></td>
								</tr>
								<tr>
									<td align="right"><form:label path="name" for="name">
											<spring:message code="label.name" />
										</form:label>
									</td>
									<td><c:out value="${educationQualification.getName() }"></c:out>
									</td>
								</tr>
								<tr>
									<td align="right"><form:label path="university"
											for="university">
											<spring:message code="label.university" />
										</form:label>
									</td>
									<td><c:out
											value="${educationQualification.getUniversity() }"></c:out></td>
								</tr>
								<tr>
									<td align="right"><form:label path="yearCompleted"
											for="yearCompleted">
											<spring:message code="label.yearCompleted" />
										</form:label>
									</td>
									<td><c:out
											value="${educationQualification.getYearCompleted() }"></c:out>
									</td>
								</tr>
								<tr>
									<td align="right"><form:label path="monthCompleted"
											for="monthCompleted">
											<spring:message code="label.monthCompleted" />
										</form:label>
									</td>
									<td><c:out
											value="${educationQualification.getMonthCompleted() }"></c:out>
									</td>
								</tr>
								<tr>
									<td align="right"><form:label path="percentage"
											for="percentage">
											<spring:message code="label.percentage" />
										</form:label>
									</td>
									<td><c:out
											value="${educationQualification.getPercentage() }"></c:out></td>
								</tr>
								<tr>
									<td align="right"><form:label path="gpa" for="gpa">
											<spring:message code="label.gpa" />
										</form:label>
									</td>
									<td><c:out value="${educationQualification.getGpa() }"></c:out>
									</td>
								</tr>
							</table>
						</td>
					</tr>
				</table>
				<input type="submit" value="Edit" class="styled-button-3" /> <input
					type="submit" value="Delete" class="styled-button-3"
					formaction="<%=request.getContextPath()%>/educationqualification/deleteEq/${educationQualification.getId()}">
			</div>
		</form:form>
	</div>
</body>
</html>