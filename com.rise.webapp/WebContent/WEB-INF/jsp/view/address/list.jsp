<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.List"%>
<%@ page import="com.rise.common.model.Address;"%>
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
					<td bgcolor="skyblue" width="15%" align="center"><b>Address
							ID</b>
					</td>
					<td bgcolor="skyblue" width="15%" align="center"><b>Street Address</b>
					</td>
					<td bgcolor="skyblue" width="15%" align="center"><b>City</b>
					</td>
					<td bgcolor="skyblue" width="15%" align="center"><b>State</b>
					</td>
					<td bgcolor="skyblue" width="15%" align="center"><b>Postal Code</b>
					</td>
					<td bgcolor="skyblue" width="15%" align="center"><b>Country</b>
					</td>
				</tr>
				<%
					List<Address> addresses = (List<Address>) request
							.getAttribute("addresses");
					if (addresses != null && addresses.size() > 0) {
						for (Address address : addresses) {
				%>
				<tr>
					<th><a
						href="<%=request.getContextPath()%>/address/<%=address.getId()%>"><%=address.getId()%></a>
					</th>
					<th><%=address.getStreetAddress()%></th>
					<th><%=address.getCity()%></th>
					<th><%=address.getState()%></th>
					<th><%=address.getPostalCode()%></th>
					<th><%=address.getCountry()%></th>
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