<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.List"%>
<%@ page import="com.rise.common.model.Person;"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<h1 align="left">
		<b>Person Home</b>
	</h1>
	<div id="list">
		<fieldset>
			<legend>Person Records</legend>
			<table bgcolor="skyblue" cellpadding="0.5" cellspacing="0.5"
				border="1" bordercolor="lightblue">
				<tr height="10">
					<td bgcolor="skyblue" width="15%" align="center"><b>Person
							ID</b>
					</td>
					<td bgcolor="skyblue" width="15%" align="center"><b>Title</b>
					</td>
					<td bgcolor="skyblue" width="15%" align="center"><b>First Name</b>
					</td>
					<td bgcolor="skyblue" width="15%" align="center"><b>Middle Name</b>
					</td>
					<td bgcolor="skyblue" width="15%" align="center"><b>Last Name</b>
					</td>
					<td bgcolor="skyblue" width="15%" align="center"><b>Suffix</b>
					</td>
					<td bgcolor="skyblue" width="15%" align="center"><b>Date Of Birth</b>
					</td>
					<td bgcolor="skyblue" width="15%" align="center"><b>Aadhaar Number</b>
					</td>
				</tr>
				<%
					String fullyQualifiedClassName = Person.class.getName().toLowerCase();
					List<Person> persons = (List<Person>) request
							.getAttribute(fullyQualifiedClassName);
					if (persons != null && persons.size() > 0) {
						for (Person person : persons) {
				%>
				<tr>
					<th><a
						href="<%=request.getContextPath()%>/person/<%=person.getId()%>"><%=person.getId()%></a>
					</th>
					<th><%=person.getPersonName().getTitle()%></th>
					<th><%=person.getPersonName().getFirstName()%></th>
					<th><%=person.getPersonName().getMiddleName()%></th>
					<th><%=person.getPersonName().getLastName()%></th>
					<th><%=person.getPersonName().getSuffix()%></th>
					<th><%=person.getDateOfBirth()%></th>
					<th><%=person.getAadhaarNumber()%></th>
				</tr>
				<%
					}
					}
				%>

			</table>
		</fieldset>
	</div>
</body>
</html>