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
   <script type="text/javascript" language="javascript" src="http://www.technicalkeeda.com/js/javascripts/plugin/jquery.js"></script>
  <link rel="stylesheet" type="text/css"
	href="/rise/resources/css/jquery-ui.css" />
  <script src="http://code.jquery.com/jquery-1.9.1.js"></script>
  <script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>
  <link rel="stylesheet" href="/resources/demos/style.css" />
  <link rel="stylesheet" type="text/css"
	href="/rise/resources/css/demo_page.css" />
<link rel="stylesheet" type="text/css"
	href="/rise/resources/css/jquery.dataTables.css" />
<script type="text/javascript" language="javascript"
	src="/rise/resources/js/jquery.dataTables.js"></script>
<script type="text/javascript" charset="utf-8">
function createTable(){
	$('#example').dataTable();	
}
</script>
	<link rel="stylesheet" type="text/css"
	href="/rise/resources/css/menu.css" />
<!-- <script type="text/javascript">
	function changeContent(){
		document.getElementById('divcontainer').load('/rise/candidate');
	}
	</script> -->
<script>
	function openwindow() {
		//alert('Entered...');
		var str = "";
		$
				.ajax({
					type : "POST",

					url : "/rise/person/ajaxList",
					dataType : "json",
					contentType : "application/json; charset=utf-8",
					data : "name='hello'",

					success : function(response) {
						str += "<div class='datatablestyle'>"
						str += "<table id='example'>";
						str += "<thead>";
						str += "<tr>";
						$.each(response.fieldsList, function(index, value) {
							str += "<th>";
							str += value;
							str += "</th>";
						});
						str += "</tr>";
						str += "</thead>";
						str += "<tbody>";
						$
								.each(
										response.resultsList,
										function(index, value) {
											str += "<tr>";
											str += "<td><span value='"
													+ value.personName.firstName
													+ "' id='"
													+ index
													+ "' onclick='fun(this.id)'' style='color:blue;'>"
													+ value.id
													+ "</span></td><td><span style='color:#000'>"
													+ value.personName.title
													+ "</span></td>";
											str += "<td><span style='color:#000'>"
													+ value.personName.firstName
													+ "</span></td>";
											str += "<td><span style='color:#000'>"
													+ value.personName.middleName
													+ "</span></td>";
											str += "<td><span style='color:#000'>"
													+ value.personName.lastName
													+ "</span></td>";
											str += "<td><span style='color:#000'>"
													+ value.personName.suffix
													+ "</span></td>";
											str += "<td><span style='color:#000'>"
													+ value.dateOfBirth
													+ "</span></td>";
											str += "<td><span style='color:#000'>"
													+ value.aadhaarNumber
													+ "</span></td>";
											str += "<input type='hidden' value='"+value.id+"' id='hid"+index+"'>";
											str += "</tr>";
										});
						str += "</tbody>";
						str += "</table>";
						str += "</div>";
						$("#innerdialog").html(str);
						createTable();
						$(function() {
							$("#dialog-confirm").dialog({
								resizable : true,
								width : 1000,
								height : 500,
								modal : true
							});
						});

					},
					error : function(e) {
						str += "error";
						alert('Error: ' + e);
						$("#innerdialog").html(str);
						$(function() {
							$("#dialog-confirm").dialog({
								resizable : true,
								height : 200,
								modal : true
							});
						});

					}
				});
	}
	function fun(index) {
		//alert(document.getElementById(index).value);
		//alert(document.getElementById('hid' + index).value);
		$("#id").val(document.getElementById('hid' + index).value);
		//document.getElementById("esi").value = document.getElementById(index).value;
		$("#dialog-confirm").dialog("close");
	}
</script>
</head>
<body>
	<div class="container">
		<header>
			<h1>Rural integration Sustainable Employment</h1>
		</header>
		<div id="menubar" style="width: auto">
			<ul id="menu">
				<li><a name="home" href="/rise/home">Home</a>
				</li>
				<li><a name="person" href="/rise/educationqualification/list"
					class="activesection"><span style="color: blue;">Education
							Qualification</span> </a>
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
		<form:form modelAttribute="educationQualification"
			action="/educationqualification/save" method="post">
			<div id="divcontainer" class="content">
				<input type="submit" value="Save" class="styled-button-3"> <input
					type="button" value="Cancel" onClick="history.go(-1);"
					class="styled-button-3" />
				<h1 align="left" class="headersection">
					<b>Education Qualification Information</b>
				</h1>
				<div class="maintablestyle">
				<table>
					<tr>
						<td>
							<table>
								<tr>
									<td><form:label path="person.id" for="person.id">
											<spring:message code="label.personId" />
										</form:label></td>
									<td><form:input path="person.id" id="id"
											value="${educationqualification.getPerson().getId() }" /> <input
										type="button" onclick="openwindow();" value="go">
									</td>
								</tr>
								<tr>
									<td align="right"><form:label path="name" for="name">
											<spring:message code="label.name" />
										</form:label>
									</td>
									<td><form:input path="name" />
									</td>
								</tr>
								<tr>
									<td align="right"><form:label path="university"
											for="university">
											<spring:message code="label.university" />
										</form:label>
									</td>
									<td><form:input path="university" />
									</td>
								</tr>
								<tr>
									<td align="right"><form:label path="yearCompleted"
											for="yearCompleted">
											<spring:message code="label.yearCompleted" />
										</form:label>
									</td>
									<td><form:input path="yearCompleted" />
									</td>
								</tr>
								<tr>
									<td align="right"><form:label path="monthCompleted"
											for="monthCompleted">
											<spring:message code="label.monthCompleted" />
										</form:label>
									</td>
									<td><form:input path="monthCompleted" />
									</td>
								</tr>
								<tr>
									<td align="right"><form:label path="percentage"
											for="percentage">
											<spring:message code="label.percentage" />
										</form:label>
									</td>
									<td><form:input path="percentage" />
									</td>
								</tr>
								<tr>
									<td align="right"><form:label path="gpa" for="gpa">
											<spring:message code="label.gpa" />
										</form:label>
									</td>
									<td><form:input path="gpa" />
									</td>
								</tr>
							</table>
						</td>
					</tr>
				</table>
				</div>
				<input type="submit" value="Save" class="styled-button-3"> <input
					type="button" value="Cancel" onClick="history.go(-1);"
					class="styled-button-3" />
			</div>
<div id="dialog-confirm" title="Persons List">
 <div id="innerdialog"></div>
</div>
		</form:form>
	</div>
</body>
</html>