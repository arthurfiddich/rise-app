<!DOCTYPE html>
<!--[if lt IE 7 ]> <html lang="en" class="no-js ie6 lt8"> <![endif]-->
<!--[if IE 7 ]>    <html lang="en" class="no-js ie7 lt8"> <![endif]-->
<!--[if IE 8 ]>    <html lang="en" class="no-js ie8 lt8"> <![endif]-->
<!--[if IE 9 ]>    <html lang="en" class="no-js ie9"> <![endif]-->
<!--[if (gt IE 9)|!(IE)]><!-->
<%@page import="com.rise.common.util.constants.HibernateHelperConstants"%>
<%@page
	import="com.rise.common.util.annotation.processor.AnnotationProcessor"%>
<%@page import="com.rise.common.util.checker.Precondition"%>
<%@page import="com.rise.common.util.Helper.TenantConfigHelper"%>
<html lang="en" class="no-js">
<!--<![endif]-->
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt"%>
<%@ page session="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page import="com.rise.common.model.Address"%>
<%@ page import="com.rise.common.model.Person"%>
<%@ page import="java.beans.Introspector"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.Map"%>
<%@ page import="java.lang.reflect.Field"%>
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
	function openSetupWindow() {
		window.location.href = "/rise/setUp";
	}
</script>

<style>
.error {
	color: #ff0000;
}

.errorblock {
	color: #000;
	background-color: #ffEEEE;
	border: 3px solid #ff0000;
	padding: 8px;
	margin: 16px;
}
</style>
</head>
<body>
	<div class="container">
		<div class="headertablestyle">
			<table width="100%">
				<tr>
					<td width="20%" align="center"><img
						src="/rise/resources/images/indian-flag.gif" height="85px"
						width="195px"></img></td>
					<td align="center" width="70%"><span
						style='font: bold 30px "Trebuchet MS", Verdana, Arial, Helvetica, sans-serif;'>Rural
							integration Sustainable Employment</span>
					</td>

					<td align="center"><span style="align: right;"><input
							type="button" value="Setup" class="styled-button-3"
							onclick="openSetupWindow()"> </span>
					</td>
				</tr>
			</table>
		</div>
		<div id="menubar" style="width: auto">
			<ul id="menu">
				<%
					String className = (String) request
							.getAttribute(HibernateHelperConstants.CLASS_NAME);
				%>
				<c:if test="${not empty activeTabsList}">
					<c:forEach items="${activeTabsList}" var="tabName"
						varStatus="index">
						<li><a name="${tabName}"
							href="/rise/${fn:toLowerCase(tabName) }/list"
							class="activesection"><span style="color: blue;">${tabName}</span>
						</a></li>
					</c:forEach>
				</c:if>
			</ul>
		</div>
		<form:form modelAttribute="<%=className %>" action="save"
			method="post">
			<form:errors path="*" cssClass="errorblock" element="div" />
			<div id="divcontainer" class="content">
				<input type="submit" value="Save" class="styled-button-3"> <input
					type="button" value="Cancel" onClick="history.go(-1);return true;"
					class="styled-button-3" />
				<%
					Map<String, List<Field>> modelNameVsFieldsMap = TenantConfigHelper
								.getInstance().getModelNameVsFieldsMap();
						if (Precondition.checkNotNull(modelNameVsFieldsMap)
								&& modelNameVsFieldsMap.size() > 0) {
							List<Field> fieldsList = modelNameVsFieldsMap
									.get(className);
							String align = "";
				%>

				<h1 align="left" class="headersection">
					<b>Personal Information</b>
				</h1>
				<table>
					<tr>
						<%
							for (int i = 0; i < fieldsList.size(); i++) {
										Field field = fieldsList.get(i);

										if (i % 2 == 0) {
											align = "right";
										} else {
											align = "left";
										}
										String fieldName = field.getName();
										String label = "label." + fieldName;
						%>
						<td>
							<table>
								<tr>
									<td align="<%=align%>"><form:label path="<%=fieldName %>"
											for="<%=fieldName %>">
											<spring:message code="<%=label %>" />
										</form:label></td>
									<td><form:errors path="<%=fieldName %>" cssClass="error" />
									</td>
									<td><form:input path="<%=fieldName %>" /></td>
								</tr>
							</table></td>
						<%
							if (i % 2 == 0) {
						%>
						<td colspan="2">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
						<%
							}
									}
						%>
						<%
							}
						%>
					</tr>
				</table>

				<c:if test="${not empty uiComponentList}">
					<c:forEach items="${uiComponentList}" var="uiComponent"
						varStatus="index">
						<%
							String co = (String) pageContext
												.getAttribute("uiComponent");
						%>
						<h1 align="left" class="headersection">
							<b><%=co %> Information</b>
						</h1>
					</c:forEach>
				</c:if>
				<input type="submit" value="Save" class="styled-button-3"> <input
					type="button" value="Cancel" onClick="history.go(-1);return true;"
					class="styled-button-3" />
			</div>
		</form:form>
	</div>
</body>
</html>