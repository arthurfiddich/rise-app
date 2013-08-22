<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.List"%>
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
<script type="text/javascript" language="javascript"
	src="/rise/resources/js/jquery.js"></script>
	<link type="text/css" href="/rise/resources/css/jquery.treeview.css" rel="stylesheet" />
     <script type="text/javascript" src="/rise/resources/js/jquery.treeview.js"></script>
    <script type="text/javascript" src="/rise/resources/js/jquery-cookie.js"></script>
<script type="text/javascript" charset="utf-8">
	$(document).ready(function() {
		$("#navigation").treeview({
			persist: "location",
			Expandable: false,
			unique: true
		});
	});
	function openDataImport(){
		window.location.href="/rise/export/create";
	}
	function openDataExport(){
		window.location.href="/rise/export/create";
	}
	function openSetupWindow(){
		  window.location.href="/rise/setUp";
	  }
</script>
	
    
</head>
<body>
	<div class="container">
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
							onclick="openSetupWindow()">
					</span></td>
				</tr>
			</table>
		</div>
		<div id="menubar" style="width: auto">
			<ul id="menu">
				<li><a name="home" href="/rise/home">Home</a></li>
				<li><a name="person" href="/rise/person/list">Person
				</a></li>
				<li><a  name="Trainer" href="/rise/trainer"
					class="activesection"><span style="color: blue;">Trainer</span></a></li>
				<li><a href="#">Agents</a></li>
				<li><a href="#">Overview</a></li>
				<li><a href="#">Contact Us</a></li>
			</ul>
		</div>
		<div id="content" class="maincontentcontainer"> 
    <div id="left" class="leftcontainer">   
   <ul id="navigation">
		<li><a href="?1"><span style="color:white">Data Management</span></a>
			<ul>
			<li><a href="javascript:openDataExport();">Data Export</a></li>
				<li><a href="javascript:openDataImport();">Data Import</a>
				</li>
	</ul>
	</li>
	</ul>

    </div>  <div id="right" class="rightcontainer" style="display:block;">    
 </div>
     </div></div>
	</div>
</body>
</html>