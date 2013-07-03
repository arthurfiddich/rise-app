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
	<style>
	.buttons a, .buttons button{
    display:block;
    float:left;
    margin:0 7px 0 0;
    background-color:#f5f5f5;
    border:1px solid #dedede;
    border-top:1px solid #eee;
    border-left:1px solid #eee;

    font-family:"Lucida Grande", Tahoma, Arial, Verdana, sans-serif;
    font-size:12px;
    line-height:130%;
    text-decoration:none;
    font-weight:bold;
    color:#565656;
    cursor:pointer;
    padding:5px 10px 6px 7px; /* Links */
}
.buttons button{
    width:auto;
    overflow:visible;
    padding:4px 10px 3px 7px; /* IE6 */
}
.buttons button[type]{
    padding:5px 10px 5px 7px; /* Firefox */
    line-height:17px; /* Safari */
}
*:first-child+html button[type]{
    padding:4px 10px 3px 7px; /* IE7 */
}
.buttons button img, .buttons a img{
    margin:0 3px -3px 0 !important;
    padding:0;
    border:none;
    width:16px;
    height:16px;
}

/* STANDARD */

button:hover, .buttons a:hover{
    background-color:#dff4ff;
    border:1px solid #c2e1ef;
    color:#336699;
}
.buttons a:active{
    background-color:#6299c5;
    border:1px solid #6299c5;
    color:#fff;
}

/* POSITIVE */

button.positive, .buttons a.positive{
    color:#529214;
}
.buttons a.positive:hover, button.positive:hover{
    background-color:#E6EFC2;
    border:1px solid #C6D880;
    color:#529214;
}
.buttons a.positive:active{
    background-color:#529214;
    border:1px solid #529214;
    color:#fff;
}

/* NEGATIVE */

.buttons a.negative, button.negative{
    color:#d12f19;
}
.buttons a.negative:hover, button.negative:hover{
    background:#fbe3e4;
    border:1px solid #fbc2c4;
    color:#d12f19;
}
.buttons a.negative:active{
    background-color:#d12f19;
    border:1px solid #d12f19;
    color:#fff;
}

/* REGULAR */

button.regular, .buttons a.regular{
    color:#336699;
}
.buttons a.regular:hover, button.regular:hover{
    background-color:#dff4ff;
    border:1px solid #c2e1ef;
    color:#336699;
}
.buttons a.regular:active{
    background-color:#6299c5;
    border:1px solid #6299c5;
    color:#fff;
}
	</style>
	<style type="text/css">
.styled-button-2 {
	-webkit-box-shadow:rgba(0,0,0,0.0.1) 0 1px 0 0;
	-moz-box-shadow:rgba(0,0,0,0.0.1) 0 1px 0 0;
	box-shadow:rgba(0,0,0,0.0.1) 0 1px 0 0;
	background-color:#5B74A8;
	border:1px solid #29447E;
	font-family:'Lucida Grande',Tahoma,Verdana,Arial,sans-serif;
	font-size:12px;
	font-weight:700;
	padding:2px 6px;
	height:28px;
	color:#fff;
	border-radius:5px;
	-moz-border-radius:5px;
	-webkit-border-radius:5px
}
</style>
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
	         Person Details <div class="buttons">
    <input type="button" class="styled-button-2" value="New" /> 
    </div>
	         <table cellpadding="0" cellspacing="0" border="0" class="display" id="example" width="100%">
	         <thead>
	       <tr height="10">
					<th align="center"><b>Person
							ID</b>
					</th>
					<th align="center"><b>Title</b>
					</th>
					<th align="center"><b>First Name</b>
					</th>
					<th align="center"><b>Middle Name</b>
					</th>
					<th align="center"><b>Last Name</b>
					</th>
					<th align="center"><b>Suffix</b>
					</th>
					<th align="center"><b>Date Of Birth</b>
					</th>
					<th align="center"><b>Aadhaar Number</b>
					</th>
				</tr>
		        </thead>
	         <tbody>
	          <tr>
	         <td>sowjanya</td>
	         <td>APSFRFDTF</td>
	          <td>9875676786768</td>
	          <td>sowjanya</td>
	         <td>APSFRFDTF</td>
	          <td>9875676786768</td>
	            <td>APSFRFDTF</td>
	          <td>9875676786768</td>
	         </tr>
	          <tr>
	         <td>sousheel</td>
	         <td>APS4324FF</td>
	          <td>987876786768</td>
	          <td>ergewhgr</td>
	          <td>tertert</td>
	          <td>rtretertgrdyt</td>
	          <td>treytryutyu</td>
	          <td>treytryutyu</td>
	         </tr>
	          <tr>
	         <td>padma</td>
	         <td>ASD5634756</td>
	          <td>8142054431</td>
	          <td>ergewhgr</td>
	          <td>tertert</td>
	          <td>treywrrtretertgrdyt</td>
	          <td>ljkjhtreytryutyu</td>
	          <td>mbmnvtreytryutyu</td>
	          
	         </tr>
	         <tr>
	         <td>Malla Reddy</td>
	         <td>ADASF4756</td>
	          <td>7342054431</td>
	          <td>434ergewhgr</td>
	          <td>opipitertert</td>
	          <td>qewertretertgrdyt</td>
	          <td>rytruieyttreytryutyu</td>
	          <td>piooutreytryutyu</td>
	          
	         </tr>
	         </tbody>
	         </table>
	         <br>
				<input type="submit" value="save" class="buttonstyle">
			</div>
	</div>
</body>
</html>