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
				<li><a name="person" href="/rise/person/personcreate"
					class="activesection">Person</a></li>
				<li><a name="trainer" href="/rise/trainer">Trainer</a></li>
				<li><a name="agents" href="/rise/agents">Agents</a></li>
				<li><a name="overview" href="/rise/overview">Overview</a></li>
				<li><a name="contactus" href="/rise/contactus">Contact Us</a></li>
			</ul>
		</div>
		<form action="">
			<div class="buttondiv">
				<input type="submit" value="save" class="buttonstyle"> <input
					type="button" value="Cancel" onClick="history.go(-1);return true;"
					class="buttonstyle" />
			</div>
			<div id="divcontainer" class="content">
				<h1 align="left" class="headersection">
					<b>Personal Information</b>
				</h1>
				<table>
					<tr>
						<td>
							<table>
								<tr>
									<td align="right">Title</td>
									<td><input type="text">
									</td>
								</tr>
								<tr>
									<td align="right">First Name</td>
									<td><input type="text">
									</td>
								</tr>
								<tr>
									<td align="right">Middle Name</td>
									<td><input type="text">
									</td>
								</tr>
								<tr>
									<td align="right">Last Name</td>
									<td><input type="text">
									</td>
								</tr>
							</table></td>
						<td colspan="2">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
						<td>
							<table>
								<tr>
									<td align="right">Suffix</td>
									<td><input type="text">
									</td>
								</tr>
								<tr>
									<td align="right">Date Of Birth</td>
									<td><input type="text">
									</td>
								</tr>
								<tr>
									<td align="right">Aadhar Number</td>
									<td><input type="text">
									</td>
								</tr>
								<tr>
									<td align="right">Last Name</td>
									<td><input type="text">
									</td>
								</tr>
							</table></td>
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
									<td align="right">Street</td>
									<td><input type="text">
									</td>
								</tr>
								<tr>
									<td align="right">City</td>
									<td><input type="text">
									</td>
								</tr>
								<tr>
									<td align="right">State</td>
									<td><input type="text">
									</td>
								</tr>
							</table></td>
						<td colspan="2">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
						<td>
							<table>
								<tr>
									<td align="right">Postal Code</td>
									<td><input type="text">
									</td>
								</tr>
								<tr>
									<td align="right">Country</td>
									<td><input type="text">
									</td>
								</tr>
							</table></td>
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
									<td align="right">Street</td>
									<td><input type="text">
									</td>
								</tr>
								<tr>
									<td align="right">City</td>
									<td><input type="text">
									</td>
								</tr>
								<tr>
									<td align="right">State</td>
									<td><input type="text">
									</td>
								</tr>
							</table></td>
						<td colspan="2">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
						<td>
							<table>
								<tr>
									<td align="right">Postal Code</td>
									<td><input type="text">
									</td>
								</tr>
								<tr>
									<td align="right">Country</td>
									<td><input type="text">
									</td>
								</tr>
							</table></td>
					</tr>
				</table>
			</div>
			<div class="buttondiv">
				<input type="submit" value="save" class="buttonstyle">
			</div>
		</form>
	</div>
</body>
</html>