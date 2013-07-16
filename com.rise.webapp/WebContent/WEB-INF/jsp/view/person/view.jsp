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
<title>Rural Integration Sustainable Employment</title>
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
	<%-- function createEducationQualification(){
		window.location.href = "<%=request.getContextPath()%>/educationqualification/create/${person.getId()}";
	}
	function editEducationQualification(){
		window.location.href = "<%=request.getContextPath()%>/educationqualification/edit/${person.getId()}";
	} --%>
	function educationQualificationOperation(keyword,eqId){
		alert(eqId);
		window.location.href = "<%=request.getContextPath()%>/educationqualification/"+keyword+"/"+eqId;
	}
	
	function awardOperation(keyword,eqId){
		alert(eqId);
		window.location.href = "<%=request.getContextPath()%>/award/"+keyword+"/"+eqId;
	}

	function employmentexperienceOperation(keyword,eqId){
		alert(eqId);
		window.location.href = "<%=request.getContextPath()%>/employmentexperience/"+keyword+"/"+eqId;
	}
</script>
</head>
<body>
	<div class="container">
		<header>
			<h1>Rural Integration Sustainable Employment</h1>
		</header>
		<div id="menubar" style="width: auto">
			<ul id="menu">
				<li><a name="home" href="/rise/home">Home</a></li>
				<li><a name="person" href="/rise/person/list"
					class="activesection"><span style="color: blue;">Person</span>
				</a></li>
				<li><a name="trainer" href="/rise/trainer">Trainer</a></li>
				<li><a name="agents" href="/rise/agents">Agents</a></li>
				<li><a name="overview" href="/rise/overview">Overview</a></li>
				<li><a name="contactus" href="/rise/contactus">Contact Us</a></li>
			</ul>
		</div>
		<form:form modelAttribute="person" action="edit" method="get">
			<input type="hidden" name="id" value="${person.getId()}"></input>
			<div id="divcontainer" class="content">
				<input type="submit" value="Edit" class="styled-button-3" /> <input
					type="submit" value="Delete" class="styled-button-3"
					formaction="<%=request.getContextPath()%>/person/delete/${person.getId()}">
				<h1 align="left" class="headersection">
					<b>Personal Information</b>
				</h1>
				<table>
					<tr>
						<td>
							<table>
								<tr>
									<td align="right"><form:label path="id" for="id">
										</form:label> <spring:message code="label.id" /></td>
									<td><c:out value="${person.getId()}"></c:out></td>
								</tr>

								<tr>
									<td align="right"><form:label path="personName.title"
											for="personName.title">
											<spring:message code="label.title" />
										</form:label></td>
									<td><c:out value="${person.getPersonName().getTitle()}"></c:out>
									</td>
								</tr>
								<tr>
									<td align="right"><form:label path="personName.firstName"
											for="personName.firstName">
											<spring:message code="label.firstName" />
										</form:label></td>
									<td><c:out
											value="${person.getPersonName().getFirstName()}"></c:out></td>
								</tr>
								<tr>
									<td align="right"><form:label path="personName.middleName"
											for="personName.middleName">
											<spring:message code="label.middleName" />
										</form:label></td>
									<td><c:out
											value="${person.getPersonName().getMiddleName()}"></c:out></td>
								</tr>
								<tr>
									<td align="right"><form:label path="personName.lastName"
											for="personName.lastName">
											<spring:message code="label.lastName" />
										</form:label></td>
									<td><c:out value="${person.getPersonName().getLastName()}"></c:out>
									</td>
								</tr>
								<tr>
									<td align="right"><form:label path="personName.suffix"
											for="personName.suffix">
											<spring:message code="label.suffix" />
										</form:label></td>
									<td><c:out value="${person.getPersonName().getSuffix()}"></c:out>
									</td>
								</tr>
							</table></td>
						<td colspan="2">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
						<td>
							<table>
								<tr>
									<td align="right"><form:label path="dateOfBirth"
											for="dateOfBirth">
											<spring:message code="label.dateOfBirth" />
										</form:label></td>
									<td><c:out value="${person.getDateOfBirth()}"></c:out></td>
								</tr>
								<tr>
									<td align="right"><form:label path="aadhaarNumber"
											for="aadhaarNumber">
											<spring:message code="label.aadhaarNumber" />
										</form:label></td>
									<td><c:out value="${person.getAadhaarNumber()}"></c:out></td>
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
									<td align="right"><form:label
											path="contactInformation.primaryAddress.streetAddress"
											for="contactInformation.primaryAddress.streetAddress">
											<spring:message code="label.streetAddress" />
										</form:label></td>
									<td><c:out
											value="${person.getContactInformation().getPrimaryAddress().getStreetAddress()}"></c:out>
									</td>
								</tr>
								<tr>
									<td align="right"><form:label
											path="contactInformation.primaryAddress.state"
											for="contactInformation.primaryAddress.state">
											<spring:message code="label.state" />
										</form:label></td>
									<td><c:out
											value="${person.getContactInformation().getPrimaryAddress().getState()}"></c:out>
									</td>
								</tr>
								<tr>
									<td align="right"><form:label
											path="contactInformation.primaryAddress.city"
											for="contactInformation.primaryAddress.city">
											<spring:message code="label.city" />
										</form:label></td>
									<td><c:out
											value="${person.getContactInformation().getPrimaryAddress().getCity()}"></c:out>
									</td>
								</tr>
							</table></td>
						<td colspan="2">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
						<td>
							<table>
								<tr>
									<td align="right"><form:label
											path="contactInformation.primaryAddress.postalCode"
											for="contactInformation.primaryAddress.postalCode">
											<spring:message code="label.postalCode" />
										</form:label></td>
									<td><c:out
											value="${person.getContactInformation().getPrimaryAddress().getPostalCode()}"></c:out>
									</td>
								</tr>
								<tr>
									<td align="right"><form:label
											path="contactInformation.primaryAddress.country"
											for="contactInformation.primaryAddress.country">
											<spring:message code="label.country" />
										</form:label></td>
									<td><c:out
											value="${person.getContactInformation().getPrimaryAddress().getCountry()}"></c:out>
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
									<td align="right"><form:label
											path="contactInformation.email1"
											for="contactInformation.email1">
											<spring:message code="label.email1" />
										</form:label></td>
									<td><c:out
											value="${person.getContactInformation().getEmail1()}"></c:out>
									</td>
								</tr>
								<tr>
									<td align="right"><form:label
											path="contactInformation.phone1"
											for="contactInformation.phone1">
											<spring:message code="label.phone1" />
										</form:label></td>
									<td><c:out
											value="${person.getContactInformation().getPhone1()}"></c:out>
									</td>
								</tr>
								<tr>
									<td align="right"><form:label
											path="contactInformation.mobile1"
											for="contactInformation.mobile1">
											<spring:message code="label.mobile1" />
										</form:label></td>
									<td><c:out
											value="${person.getContactInformation().getMobile1()}"></c:out>
									</td>
								</tr>
							</table>
						</td>
						<td>
							<table>
								<tr>
									<td align="right"><form:label
											path="contactInformation.email2"
											for="contactInformation.email2">
											<spring:message code="label.email2" />
										</form:label></td>
									<td><c:out
											value="${person.getContactInformation().getEmail2()}"></c:out>
									</td>
								</tr>
								<tr>
									<td align="right"><form:label
											path="contactInformation.phone2"
											for="contactInformation.phone2">
											<spring:message code="label.phone2" />
										</form:label></td>
									<td><c:out
											value="${person.getContactInformation().getPhone2()}"></c:out>
									</td>
								</tr>
								<tr>
									<td align="right"><form:label
											path="contactInformation.mobile2"
											for="contactInformation.mobile2">
											<spring:message code="label.mobile2" />
										</form:label></td>
									<td><c:out
											value="${person.getContactInformation().getMobile2()}"></c:out>
									</td>
								</tr>
							</table>
						</td>
						<td>
							<table>
								<tr>
									<td align="right"><form:label
											path="contactInformation.email3"
											for="contactInformation.email3">
											<spring:message code="label.email3" />
										</form:label></td>
									<td><c:out
											value="${person.getContactInformation().getEmail3()}"></c:out>
									</td>
								</tr>
								<tr>
									<td align="right"><form:label
											path="contactInformation.phone3"
											for="contactInformation.phone3">
											<spring:message code="label.phone3" />
										</form:label></td>
									<td><c:out
											value="${person.getContactInformation().getPhone3()}"></c:out>
									</td>
								</tr>
								<tr>
									<td align="right"><form:label
											path="contactInformation.mobile3"
											for="contactInformation.mobile3">
											<spring:message code="label.mobile3" />
										</form:label></td>
									<td><c:out
											value="${person.getContactInformation().getMobile3()}"></c:out>
									</td>
								</tr>
							</table>
						</td>
					</tr>
				</table>
				<input type="submit" value="Edit" class="styled-button-3" /> <input
					type="submit" value="Delete" class="styled-button-3"
					formaction="<%=request.getContextPath()%>/person/delete/${person.getId()}">
			</div>
		</form:form>
		<div id="divcontainer" class="content">
			<h1 align="left" class="headersection">
				<b>Education Qualifications Information</b>

			</h1>
			<!-- <div>
				<input type="button" value="New Education Qualification"
					onclick=createFunction()>
			</div> -->
			<div id="demo">
				<center>
					<input type="button" value="New Education Qualification"
						class="styled-button-3" onclick=educationQualificationOperation('create',${person.getId()})>
				</center>
				<c:if test="${!empty person.getEducationQualifications()}">
					<div class="tablestyle">
						<table>
							<thead>
								<tr>
									<th>Action</th>
									<th>ID</th>
									<th>Name</th>
									<th>University</th>
									<th>Year Completed</th>
									<th>Month Completed</th>
									<th>Percentage</th>
									<th>GPA</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${person.getEducationQualifications()}"
									var="equalification">
									<tr>
										<td><input type="submit" value="Edit"
											class="styled-button-3"
											onclick=educationQualificationOperation('edit',${equalification.id})>&nbsp;&nbsp;<input
											type="submit" value="Delete" class="styled-button-3"
											onclick=educationQualificationOperation('deleteEq',${equalification.id})>
										</td>
										<td><a
											href="<%=request.getContextPath()%>/educationqualification/${equalification.id}"><span
												style="color: white;"><u>${equalification.id}</u> </span> </a></td>
										<td>${equalification.name}</td>
										<td>${equalification.university}</td>
										<td>${equalification.yearCompleted}</td>
										<td>${equalification.monthCompleted}</td>
										<td>${equalification.percentage}</td>
										<td>${equalification.gpa}</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
				</c:if>
			</div>
		</div>

		<div id="divcontainer" class="content">
			<h1 align="left" class="headersection">
				<b>Awards</b>

			</h1>
			<div id="demo">
				<center>
					<input type="button" value="New Award" class="styled-button-3"
						onclick=awardOperation('create',${person.getId()})>
				</center>
				<c:if test="${!empty person.getAwards()}">
					<div class="tablestyle">
						<table>
							<thead>
								<tr>
									<th>Action</th>
									<th>ID</th>
									<th>Name</th>
									<th>Issued By</th>
									<th>Date Issued</th>
									<th>Description</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${person.getAwards()}" var="award">
									<tr>
										<td><input type="submit" value="Edit"
											class="styled-button-3" onclick=awardOperation('edit',${award.id})>&nbsp;&nbsp;<input
											type="submit" value="Delete" class="styled-button-3"
											onclick=awardOperation('delete',${award.id})>
										</td>
										<td><a
											href="<%=request.getContextPath()%>/award/${award.id}"><span
												style="color: white;"><u>${award.id}</u> </span> </a></td>
										<td>${award.name}</td>
										<td>${award.issuedBy}</td>
										<td>${award.dateIssued}</td>
										<td>${award.description}</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
				</c:if>
			</div>
		</div>
		
		<div id="divcontainer" class="content">
			<h1 align="left" class="headersection">
				<b>Employment Experiences</b>

			</h1>
			<div id="demo">
				<center>
					<input type="button" value="New Employment Experience" class="styled-button-3"
						onclick=employmentexperienceOperation('create',${person.getId()})>
				</center>
				<c:if test="${!empty person.getEmploymentExperiences()}">
					<div class="tablestyle">
						<table>
							<thead>
								<tr>
									<th>Action</th>
									<th>ID</th>
									<th>JobTitle</th>
									<th>From Date</th>
									<th>To Date</th>
									<th>Description</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${person.getEmploymentExperiences()}" var="employmentExperience">
									<tr>
										<td><input type="submit" value="Edit"
											class="styled-button-3" onclick=employmentexperienceOperation('edit',${employmentExperience.id})>&nbsp;&nbsp;<input
											type="submit" value="Delete" class="styled-button-3"
											onclick=employmentexperienceOperation('delete',${employmentExperience.id})>
										</td>
										<td><a
											href="<%=request.getContextPath()%>/employmentexperience/${employmentExperience.id}"><span
												style="color: white;"><u>${employmentExperience.id}</u> </span> </a></td>
										<td>${employmentExperience.jobTitle}</td>
										<td>${employmentExperience.fromDate}</td>
										<td>${employmentExperience.toDate}</td>
										<td>${employmentExperience.description}</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
				</c:if>
			</div>
		</div>

	</div>
</body>
</html>