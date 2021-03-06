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
	function openPersonCreate() {
		window.location.href = "/rise/person/personcreate";
		//alert("hello");
	}
	function openDataImport(){
		var str ="";
		str+="<li>Person</li>";
		str+="<li>Candidate</li>";
		str+="<li>Agent</li>";
		$("#subleftcontent").html(str);
		$("#subleftcontent").style.display="block";
	}
	function openDataExport(){
		var value ='person';
		var str ="";
		str+="<ul>";
		str+="<li style='list-style:none;'><a href=javascript:openDetails('"+value+"');>Person</a></li>";
		str+="<li style='list-style:none;'><a href='javascript:openDetails();'>Candidate</a></li>";
		str+="<li style='list-style:none;'><a href='javascript:openDetails();'>Agent</a></li>";
		str+="</ul>";
		$("#subleftcontent").html(str);
	}
	function openDetails(data){
		alert(data);
		var str ="";
		$
		.ajax({
			type : "POST",

			url : "/rise/person/ajaxList",
			dataType : "json",
			contentType : "application/json; charset=utf-8",
			data : "name='hello'",

			success : function(response) {
				//str += "<div class='datatablestyle'>";
				str += "<table border='0'>";
				
				$.each(response.fieldsList, function(index, value) {
					str += "<tr>";
					str += "<td align='right'>";
					str += "<input type='checkbox'/>";
					str += "</td>";
					str += "<td align='left'>";
					str += "<span>"+value+"</span>";
					str += "</td>";
					str += "</tr>";
				});
				
				str += "</table>";
				//str += "</div>";
				$("#subrightcontent").html(str);	
			},
			error : function(e) {
				str += "error";
				alert('Error: ' + e);
				$("#subrightcontent").html("No Data Found");	
			}
		});
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
		<li><a href="?1">Data Management</a>
			<ul>
			<li><a href="javascript:openDataExport();">Data Export</a></li>
				<li><a href="javascript:openDataImport();">Data Import</a>
				</li>
	</ul>
	</li>
	</ul>

    </div>  <div id="right" class="rightcontainer" style="display:block;">    
     <div class="maincontentcontainer" style="display:block;">
    <div id="subleftcontent" class="leftcontainer" style="display:block;"></div>
   <div id="subrightcontent" class="rightcontainer" style="display:block;"></div>
 </div>
     </div></div>
	</div>
</body>
</html>