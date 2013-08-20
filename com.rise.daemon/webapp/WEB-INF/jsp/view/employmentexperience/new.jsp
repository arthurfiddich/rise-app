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
<%@ page import="com.rise.common.model.EmploymentExperience"%>
<head>
<meta charset="UTF-8" />
<!-- <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">  -->
<title>Rural integration Sustainable Employment</title>
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
<!-- <script type="text/javascript">
	function changeContent(){
		document.getElementById('divcontainer').load('/rise/candidate');
	}
	</script> -->
</head>
<body>
	<div class="container">
		<header>
			<h1>Rural integration Sustainable Employment</h1>
		</header>
		<div id="menubar" style="width: auto">
			<ul id="menu">
				<li><a name="home" href="/rise/home">Home</a></li>
				<li><a name="home" href="/rise/person/list">Person</a></li>
				<li><a name="educationQualification"
					href="/rise/educationqualification/list">Education
						Qualification</a></li>
				<li><a name="award" href="/rise/award/list">Award</a>
				</li>
				<li><a name="award" href="/rise/employmentexperience/list"
					class="activesection"><span style="color: blue;">EmploymentExperience</span>
				</a>
				</li>
				<li><a name="trainer" href="/rise/trainer">Trainer</a></li>
				<li><a name="agents" href="/rise/agents">Agents</a></li>
				<li><a name="overview" href="/rise/overview">Overview</a></li>
				<li><a name="contactus" href="/rise/contactus">Contact Us</a></li>
			</ul>
		</div>
		<form:form modelAttribute="employmentExperience" action="/employmentexperience/save"
			method="post">
			<div id="divcontainer" class="content">
				<input type="submit" value="Save" class="styled-button-3"> <input
					type="button" value="Cancel" onClick="history.go(-1);return true;"
					class="styled-button-3" />
				<h1 align="left" class="headersection">
					<b>EmploymentExperience Information</b>
				</h1>
				<table>
					<tr>
						<td>
							<table>
								<tr>
									<td><form:label path="person.id" for="person.id">
											<spring:message code="label.personId" />
										</form:label>
									</td>
									<td><form:input path="person.id"
											value="${employmentExperience.getPerson().getId() }" /></td>
								</tr>
								<tr>
									<td align="right"><form:label path="jobTitle"
											for="jobTitle">
											<spring:message code="label.jobTitle" />
										</form:label></td>
									<td><form:input path="jobTitle" /></td>
								</tr>
								<tr>
									<td align="right"><form:label path="fromDate"
											for="fromDate">
											<spring:message code="label.fromDate" />
										</form:label></td>
									<td><form:input path="fromDate" /></td>
								</tr>
								<tr>
									<td align="right"><form:label path="toDate" for="toDate">
											<spring:message code="label.toDate" />
										</form:label></td>
									<td><form:input path="toDate" /></td>
								</tr>
								<tr>
									<td align="right"><form:label path="description"
											for="description">
											<spring:message code="label.description" />
										</form:label></td>
									<td><form:input path="description" /></td>
								</tr>
								<tr>
									<td align="right"><form:label path="reasonForLeaving"
											for="reasonForLeaving">
											<spring:message code="label.reasonForLeaving" />
										</form:label></td>
									<td><form:input path="reasonForLeaving" /></td>
								</tr>
							</table></td>
					</tr>
				</table>
				<input type="submit" value="Save" class="styled-button-3"> <input
					type="button" value="Cancel" onClick="history.go(-1);return true;"
					class="styled-button-3" />
			</div>

		</form:form>
	</div>
</body>
</html>