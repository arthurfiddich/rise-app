<%@page import="java.beans.Introspector"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.List"%>
<%@ page import="java.beans.Introspector"%>
<%@ page import="com.rise.common.model.Person;"%>
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
<title>Person Details</title>
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
	function openPersonCreate() {
		window.location.href = "/rise/person/personcreate";
		//alert("hello");
	}
	function openSetupWindow() {
		window.location.href = "/rise/setUp";
	}
</script>
<link rel="stylesheet" type="text/css"
	href="/rise/resources/css/table.css" />
</head>
<body>
	<div class="container">
		<!-- Header Style start -->
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
		<!-- Header Style end -->
		<div id="menubar" style="width: auto">
			<ul id="menu">
				<li><a name="home" href="/rise/home">Home</a>
				</li>
				<li><a name="person" href="/rise/person/list"
					class="activesection"><span style="color: blue;">Person</span>
				</a>
				</li>
				<li><a href="#">Trainer</a>
				</li>
				<li><a href="#">Agents</a>
				</li>
				<li><a href="#">Overview</a>
				</li>
				<li><a href="#">Contact Us</a>
				</li>
			</ul>
		</div>
		<div id="divcontainer" class="content">
			<h1 align="left">
				<img alt="Home" src="/rise/resources/images/person.png"
					height="32px" width="45px">
			</h1>
			<fieldset>
				<legend>
					<input type="button" class="styled-button-3" value="New"
						onclick="openPersonCreate();" />
				</legend>
				<table id="example">
					<thead>
						<tr height="10">
							<th align="center"><b>Person ID</b>
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
						<%
							String fullyQualifiedClassName = Person.class.getName().toLowerCase();
							List<Person> persons = (List<Person>) request
									.getAttribute(fullyQualifiedClassName);
							if (persons != null && persons.size() > 0) {
								for (Person person : persons) {
						%>

						<tr>
							<td><a
								href="<%=request.getContextPath()%>/person/<%=person.getId()%>"><%=person.getId()%></a>
							</td>
							<td><%=person.getPersonName().getTitle()%></td>
							<td><%=person.getPersonName().getFirstName()%></td>
							<td><%=person.getPersonName().getMiddleName()%></td>
							<td><%=person.getPersonName().getLastName()%></td>
							<td><%=person.getPersonName().getSuffix()%></td>
							<td><%=person.getDateOfBirth()%></td>
							<td><%=person.getAadhaarNumber()%></td>
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