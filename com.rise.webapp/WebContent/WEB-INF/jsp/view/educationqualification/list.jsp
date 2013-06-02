<%@page import="com.rise.common.util.constants.HibernateConstants"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.List"%>
<%@ page import="com.rise.common.model.EducationQualification"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Education Qualification List Page</title>
</head>
<body>
	<h1 align="left">
		<b>Education Qualification Home</b>
	</h1>
	<div id="list">
		<fieldset>
			<legend>Education Qualification Records</legend>
			<table bgcolor="skyblue" cellpadding="0.5" cellspacing="0.5"
				border="1" bordercolor="lightblue">
				<tr height="10">
					<td bgcolor="skyblue" width="15%" align="center"><b>Education
							Qualification ID</b>
					</td>
					<td bgcolor="skyblue" width="15%" align="center"><b>Name</b>
					</td>
					<td bgcolor="skyblue" width="15%" align="center"><b>University</b>
					</td>
					<td bgcolor="skyblue" width="15%" align="center"><b>Year</b>
					</td>
					<td bgcolor="skyblue" width="15%" align="center"><b>Month</b>
					</td>
					<td bgcolor="skyblue" width="15%" align="center"><b>Percentage</b>
					</td>
					<td bgcolor="skyblue" width="15%" align="center"><b>GPA</b>
					</td>
				</tr>
				<%
					String fullyQualifiedClassName = EducationQualification.class
							.getName().toLowerCase();
					List<EducationQualification> educationQualifications = (List<EducationQualification>) request
							.getAttribute(fullyQualifiedClassName);
					if (educationQualifications != null
							&& educationQualifications.size() > 0) {
						for (EducationQualification educationQualification : educationQualifications) {
				%>
				<tr>
					<th><a
						href="<%=request.getContextPath()%>/eq/<%=educationQualification.getId()%>"><%=educationQualification.getId()%></a>
					</th>
					<th><%=educationQualification.getName()%></th>
					<th><%=educationQualification.getUniversity()%></th>
					<th><%=educationQualification.getYearCompleted()%></th>
					<th><%=educationQualification.getMonthCompleted()%></th>
					<th><%=educationQualification.getPercentage()%></th>
					<th><%=educationQualification.getGpa()%></th>
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