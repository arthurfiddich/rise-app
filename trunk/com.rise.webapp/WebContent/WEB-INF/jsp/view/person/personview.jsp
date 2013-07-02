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
	function openWindow(){
		window.location.href="/rise/person/personcreate";
	}
	</script> 
	<link rel="stylesheet" type="text/css"
	href="/rise/resources/css/demo_page.css" />
		<link rel="stylesheet" type="text/css"
	href="/rise/resources/css/jquery.dataTables.css" />
		<script type="text/javascript" language="javascript" src="/rise/resources/js/jquery.js"></script>
		<script type="text/javascript" language="javascript" src="/rise/resources/js/jquery.dataTables.js"></script>
		<script type="text/javascript" charset="utf-8">
			$(document).ready(function() {
				$('#example').dataTable();
			} );
		</script>
		<link rel="stylesheet" type="text/css"
	href="/rise/resources/css/table.css" />
</head>
<body>
	<div class="container">
		<header>
			<h1>Rural integration Sustainable Employment</h1>
		</header>
		<div id="menubar" style="width: auto">
			<ul id="menu">
				<li><a name="home" href="/rise/home">Home</a></li>
				<li><a name="person" href="/rise/person/personview"
					 class="activesection" style="font-color:#000">Person</a></li>
				<li><a name="trainer" href="/rise/trainer">Trainer</a></li>
				<li><a name="agents" href="/rise/agents">Agents</a></li>
				<li><a name="overview" href="/rise/overview">Overview</a></li>
				<li><a name="contactus" href="/rise/contactus">Contact Us</a></li>
			</ul>
		</div>
			<div id="divcontainer" class="content">
	         Person Details <input type="button" value="new" class="buttonstyle">
	         <table cellpadding="0" cellspacing="0" border="0" class="display" id="example" width="100%">
	         <thead>
	         <tr>
	         <th>First Name</th>
	         <th>Aadhar Number</th>
	          <th>Phone</th>
	         </tr>
	         </thead>
	         <tbody>
	          <tr>
	         <td>sowjanya</td>
	         <td>APSFRFDTF</td>
	          <td>9875676786768</td>
	         </tr>
	          <tr>
	         <td>sousheel</td>
	         <td>APS4324FF</td>
	          <td>987876786768</td>
	         </tr>
	         </tbody>
	         </table>
	         <br>
				<input type="submit" value="save" class="buttonstyle">
			</div>
	</div>
</body>
</html>