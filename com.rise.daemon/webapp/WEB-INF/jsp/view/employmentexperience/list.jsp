<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.List"%>
<%@ page import="com.rise.common.model.EmploymentExperience"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css"
	href="/rise/resources/css/demo.css" />
<link rel="stylesheet" type="text/css"
	href="/rise/resources/css/style.css" />
<link rel="stylesheet" type="text/css"
	href="/rise/resources/css/menu.css" />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>EmploymentExperience Details</title>
<link rel="stylesheet" type="text/css"
	href="/rise/resources/css/demo_page.css" />
<link rel="stylesheet" type="text/css"
	href="/rise/resources/css/jquery.dataTables.css" />
<script type="text/javascript" language="javascript"
	src="/rise/resources/js/jquery.js"></script>
<script type="text/javascript" language="javascript"
	src="/rise/resources/js/jquery.dataTables.js"></script>
<script type="text/javascript" charset="utf-8">
	$(document).ready(function() {
		$('#example').dataTable();
	});
	function createEmploymentExperience() {
		window.location.href = "/rise/employmentexperience/create";
		//alert("hello");
	}
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
				<li><a name="home" href="/rise/person/list">Person</a></li>
				<li><a name="educationQualification"
					href="/rise/educationqualification/list">Education
						Qualification</a></li>
				<li><a name="award" href="/rise/award/list">Award</a>
				</li>
				<li><a name="award" href="/rise/employmentexperience/list"
					class="activesection"><span style="color: blue;">EmploymentExperience</span> </a>
				</li>
				<li><a href="#">Trainer</a></li>
				<li><a href="#">Agents</a></li>
				<li><a href="#">Overview</a></li>
				<li><a href="#">Contact Us</a></li>
			</ul>
		</div>
		<div id="divcontainer" class="content">
			<h1 align="left">
				<img alt="Home"
					src="/rise/resources/images/11.free-education-icons.jpg"
					height="32px" width="45px">
			</h1>
			<fieldset>
				<legend>
					<input type="button" class="styled-button-3" value="New"
						onclick="createEmploymentExperience();" />
				</legend>
				<table id="example">
					<thead>
						<tr height="10">
							<th align="center"><b>ID</b></th>
							<th align="center"><b>jobTitle</b></th>
							<th align="center"><b>fromDate</b></th>
							<th align="center"><b>toDate</b></th>
							<th align="center"><b>description</b></th>
						</tr>
					</thead>
					<tbody>
						<%
							String fullyQualifiedClassName = EmploymentExperience.class
									.getName().toLowerCase();
							List<EmploymentExperience> employmentExperiences = (List<EmploymentExperience>) request
									.getAttribute(fullyQualifiedClassName);
							if (employmentExperiences != null
									&& employmentExperiences.size() > 0) {
								for (EmploymentExperience employmentExperience : employmentExperiences) {
						%>

						<tr>
							<td><a
								href="<%=request.getContextPath()%>/employmentexperience/<%=employmentExperience.getId()%>"><%=employmentExperience.getId()%></a>
							</td>
							<td><%=employmentExperience.getJobTitle()%></td>
							<td><%=employmentExperience.getFromDate()%></td>
							<td><%=employmentExperience.getToDate()%></td>
							<td><%=employmentExperience.getDescription()%></td>
						</tr>
						<%
							}
							}
						%>
					</tbody>
				</table>
			</fieldset>
		</div>
	</div>
</body>
</html>