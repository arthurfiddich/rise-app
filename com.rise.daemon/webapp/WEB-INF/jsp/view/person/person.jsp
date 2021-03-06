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
<script type="text/javascript">
	function openSetupWindow() {
		window.location.href = "/rise/setUp";
	}
</script>

<style>
.error {
	color: #ff0000;
}
 
.errorblock {
	color: #000;
	background-color: #ffEEEE;
	border: 3px solid #ff0000;
	padding: 8px;
	margin: 16px;
}
</style>
</head>
<body>
	<div class="container">
		<div class="headertablestyle">
			<table width="100%">
				<tr>
					<td width="20%" align="center"><img
						src="/rise/resources/images/indian-flag.gif" height="85px"
						width="195px"></img>
					</td>
					<td align="center" width="70%"><span
						style='font: bold 30px "Trebuchet MS", Verdana, Arial, Helvetica, sans-serif;'>Rural
							integration Sustainable Employment</span></td>

					<td align="center"><span style="align: right;"><input
							type="button" value="Setup" class="styled-button-3"
							onclick="openSetupWindow()"> </span></td>
				</tr>
			</table>
		</div>
		<div id="menubar" style="width: auto">
			<ul id="menu">
				<li><a name="home" href="/rise/home">Home</a>
				</li>
				<li><a name="person" href="/rise/person/list"
					class="activesection"><span style="color: blue;">Person</span>
				</a>
				</li>
				<li><a name="trainer" href="/rise/trainer">Trainer</a>
				</li>
				<li><a name="agents" href="/rise/agents">Agents</a>
				</li>
				<li><a name="overview" href="/rise/overview">Overview</a>
				</li>
				<li><a name="contactus" href="/rise/contactus">Contact Us</a>
				</li>
			</ul>
		</div>
		<form:form modelAttribute="person" action="save" method="post">
			<form:errors path="*" cssClass="errorblock" element="div" />
				<div id="divcontainer" class="content">
					<input type="submit" value="Save" class="styled-button-3">
					<input type="button" value="Cancel"
						onClick="history.go(-1);return true;" class="styled-button-3" />
					<h1 align="left" class="headersection">
						<b>Personal Information</b>
					</h1>
					<table>
						<tr>
							<td>
								<table>
									<%-- <tr>
									<td align="right"><form:label path="id" for="id">
											<spring:message code="label.id" />
										</form:label></td>
									<td><form:input path="id" /></td>
								</tr> --%>
									<tr>
										<td align="right"><form:label path="personName.title"
												for="personName.title">
												<spring:message code="label.title" />
											</form:label>
										</td>
										<td><form:errors path="personName.title" cssClass="error" />
										</td>
										<td><form:input path="personName.title" />
										</td>
									</tr>
									<tr>
										<td align="right"><form:label path="personName.firstName"
												for="personName.firstName">
												<spring:message code="label.firstName" />
											</form:label>
										</td>
										<td><form:errors path="personName.firstName"
												cssClass="error" /></td>
										<td><form:input path="personName.firstName" />
										</td>
									</tr>
									<tr>
										<td align="right"><form:label
												path="personName.middleName" for="personName.middleName">
												<spring:message code="label.middleName" />
											</form:label>
										</td>
										<td><form:errors path="personName.middleName"
												cssClass="error" /></td>
										<td><form:input path="personName.middleName" />
										</td>
									</tr>
									<tr>
										<td align="right"><form:label path="personName.lastName"
												for="personName.lastName">
												<spring:message code="label.lastName" />
											</form:label>
										</td>
										<td><form:errors path="personName.lastName"
												cssClass="error" /></td>
										<td><form:input path="personName.lastName" />
										</td>
									</tr>
									<tr>
										<td align="right"><form:label path="personName.suffix"
												for="personName.suffix">
												<spring:message code="label.suffix" />
											</form:label>
										</td>
										<td><form:errors path="personName.suffix"
												cssClass="error" /></td>
										<td><form:input path="personName.suffix" />
										</td>
									</tr>
								</table>
							</td>
							<td colspan="2">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
							<td>
								<table>
									<tr>
										<td align="right"><form:label path="dateOfBirth"
												for="dateOfBirth">
												<spring:message code="label.dateOfBirth" />
											</form:label>
										</td>
										<td><form:errors path="dateOfBirth" cssClass="red" /></td>
										<td><form:input path="dateOfBirth" />
										</td>
									</tr>
									<tr>
										<td align="right"><form:label path="aadhaarNumber"
												for="aadhaarNumber">
												<spring:message code="label.aadhaarNumber" />
											</form:label>
										</td>
										<td><form:errors path="aadhaarNumber" cssClass="error" />
										</td>
										<td><form:input path="aadhaarNumber" />
										</td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
					<h1 align="left" class="headersection">
						<b>Address Information</b>
					</h1>
					<table>
						<tr>
							<td>
								<table>
									<tr>
										<td align="right"><form:label
												path="contactInformation.primaryAddress.streetAddress"
												for="contactInformation.primaryAddress.streetAddress">
												<spring:message code="label.streetAddress" />
											</form:label>
										</td>
										<td><form:errors
												path="contactInformation.primaryAddress.streetAddress"
												cssClass="error" /></td>
										<td><form:input
												path="contactInformation.primaryAddress.streetAddress" />
										</td>
									</tr>
									<tr>
										<td align="right"><form:label
												path="contactInformation.primaryAddress.state"
												for="contactInformation.primaryAddress.state">
												<spring:message code="label.state" />
											</form:label>
										</td>
										<td><form:errors
												path="contactInformation.primaryAddress.state"
												cssClass="error" /></td>
										<td><form:input
												path="contactInformation.primaryAddress.state" />
										</td>
									</tr>
									<tr>
										<td align="right"><form:label
												path="contactInformation.primaryAddress.city"
												for="contactInformation.primaryAddress.city">
												<spring:message code="label.city" />
											</form:label>
										</td>
										<td><form:errors
												path="contactInformation.primaryAddress.city"
												cssClass="error" /></td>
										<td><form:input
												path="contactInformation.primaryAddress.city" />
										</td>
									</tr>
								</table>
							</td>
							<td colspan="2">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
							<td>
								<table>
									<tr>
										<td align="right"><form:label
												path="contactInformation.primaryAddress.postalCode"
												for="contactInformation.primaryAddress.postalCode">
												<spring:message code="label.postalCode" />
											</form:label>
										</td>
										<td><form:errors
												path="contactInformation.primaryAddress.postalCode"
												cssClass="error" /></td>
										<td><form:input
												path="contactInformation.primaryAddress.postalCode" />
										</td>
									</tr>
									<tr>
										<td align="right"><form:label
												path="contactInformation.primaryAddress.country"
												for="contactInformation.primaryAddress.country">
												<spring:message code="label.country" />
											</form:label>
										</td>
										<td><form:errors
												path="contactInformation.primaryAddress.country"
												cssClass="error" /></td>
										<td><form:input
												path="contactInformation.primaryAddress.country" />
										</td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
					<h1 align="left" class="headersection">
						<b>Contact Information</b>
					</h1>
					<table>
						<tr>
							<td>
								<table>
									<tr>
										<td align="right"><form:label
												path="contactInformation.email1"
												for="contactInformation.email1">
												<spring:message code="label.email1" />
											</form:label>
										</td>
										<td><form:errors path="contactInformation.email1"
												cssClass="error" /></td>
										<td><form:input path="contactInformation.email1" />
										</td>
									</tr>
									<tr>
										<td align="right"><form:label
												path="contactInformation.phone1"
												for="contactInformation.phone1">
												<spring:message code="label.phone1" />
											</form:label>
										</td>
										<td><form:errors path="contactInformation.phone1"
												cssClass="error" /></td>
										<td><form:input path="contactInformation.phone1" />
										</td>
									</tr>
									<tr>
										<td align="right"><form:label
												path="contactInformation.mobile1"
												for="contactInformation.mobile1">
												<spring:message code="label.mobile1" />
											</form:label>
										</td>
										<td><form:errors path="contactInformation.mobile1"
												cssClass="red">Required</form:errors></td>
										<td><form:input path="contactInformation.mobile1" />
										</td>
									</tr>
								</table></td>
							<td>
								<table>
									<tr>
										<td align="right"><form:label
												path="contactInformation.email2"
												for="contactInformation.email2">
												<spring:message code="label.email2" />
											</form:label>
										</td>
										<td><form:errors path="contactInformation.email2"
												cssClass="error" /></td>
										<td><form:input path="contactInformation.email2" />
										</td>
									</tr>
									<tr>
										<td align="right"><form:label
												path="contactInformation.phone2"
												for="contactInformation.phone2">
												<spring:message code="label.phone2" />
											</form:label>
										</td>
										<td><form:errors path="contactInformation.phone2"
												cssClass="error" /></td>
										<td><form:input path="contactInformation.phone2" />
										</td>
									</tr>
									<tr>
										<td align="right"><form:label
												path="contactInformation.mobile2"
												for="contactInformation.mobile2">
												<spring:message code="label.mobile2" />
											</form:label>
										</td>
										<td><form:errors path="contactInformation.mobile2"
												cssClass="error" /></td>
										<td><form:input path="contactInformation.mobile2" />
										</td>
									</tr>
								</table></td>
							<td>
								<table>
									<tr>
										<td align="right"><form:label
												path="contactInformation.email3"
												for="contactInformation.email3">
												<spring:message code="label.email3" />
											</form:label>
										</td>
										<td><form:errors path="contactInformation.email3"
												cssClass="error" /></td>
										<td><form:input path="contactInformation.email3" />
										</td>
									</tr>
									<tr>
										<td align="right"><form:label
												path="contactInformation.phone3"
												for="contactInformation.phone3">
												<spring:message code="label.phone3" />
											</form:label>
										</td>
										<td><form:errors path="contactInformation.phone3"
												cssClass="error" /></td>
										<td><form:input path="contactInformation.phone3" />
										</td>
									</tr>
									<tr>
										<td align="right"><form:label
												path="contactInformation.mobile3"
												for="contactInformation.mobile3">
												<spring:message code="label.mobile3" />
											</form:label>
										</td>
										<td><form:errors path="contactInformation.mobile3"
												cssClass="error" /></td>
										<td><form:input path="contactInformation.mobile3" />
										</td>
									</tr>
								</table></td>
						</tr>
					</table>
					<input type="submit" value="Save" class="styled-button-3">
					<input type="button" value="Cancel"
						onClick="history.go(-1);return true;" class="styled-button-3" />
				</div>
		</form:form>
	</div>
</body>
</html>