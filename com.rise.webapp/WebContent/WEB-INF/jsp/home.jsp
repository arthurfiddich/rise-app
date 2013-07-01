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
<script type="text/javascript" src="http://www.technicalkeeda.com/js/javascripts/plugin/json2.js"></script>
  <link rel="stylesheet" href="http://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css" />
  <script src="http://code.jquery.com/jquery-1.9.1.js"></script>
  <script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>
  <link rel="stylesheet" type="text/css"
	href="/rise/resources/css/table.css" />
  <script>
 
  function openwindow(){
	  var str = "";
	  $.ajax({
          type: "POST",
 
          url: "/rise/states",
          dataType: "json",
          contentType : "application/json; charset=utf-8",
          data: "name='hello'",
  
          success: function(response){
          // we have the response
        str+="<table>";  
        //alert(response);
        /* var ary = response.split(",");
        alert(ary.length);
        for(var k=0;k<ary.length;k++){
        	 str+="<tr>";  
	        	str+="<td><input type='checkbox'></td>";  
	        	str+="<td>"+ary[k]+"</td>";  
	        	str+="</tr>"; 	
        } */
           $.each(response, function(index, value) {
        	  str+="<tr>";  
	        	str+="<td><input type='radio' value='"+value.name+"' id='"+index+"' onclick='fun(this.id)''>"+value.name+"</td>"; 
	        	str+="<td>"+value.id+"</td>"; 
	        	str+="<input type='hidden' value='"+value.id+"' id='hid"+index+"'>";
	        	//str+="<td><a href='#' onclick=fun(this.href)>"+value.name+"</a></td>";
	        	//str+="<td>"+value+"</td>"; 
	        	str+="</tr>";  
          }); 
          str+="</table>"; 
          $("#innerdialog").html(str);
    	  $(function() {
    	    $( "#dialog-confirm" ).dialog({
    	      resizable: true,
    	      width:400,
    	      height:300,
    	      modal: true
    	    });
    	  });  

          },
         error: function(e){
        	 str+="error";
          alert('Error: ' + e);
          $("#innerdialog").html(str);
    	  $(function() {
    	    $( "#dialog-confirm" ).dialog({
    	      resizable: true,
    	      height:200,
    	      modal: true
    	    });
    	  });  

          }
          });
	  }
  function fun(index){
	alert(document.getElementById(index).value);
	alert(document.getElementById('hid'+index).value);
	//alert(value); 
	//alert(code);
	$("#esi").val(document.getElementById(index).value);
	//document.getElementById("esi").value = document.getElementById(index).value;
	$( "#dialog-confirm" ).dialog("close");
  }
  </script>
</head>
<body>
	<div class="container">
		<header>
			<h1>Rural integration Sustainable Employment</h1>
		</header>
		<div id="menubar" style="width:auto">
			<ul id="menu">
				<li><a name="home" href="/rise/home" class="activesection">Home</a>
				</li>
				<li><a name="person" href="/rise/person/personcreate">Person</a>
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
		ESI Number : <input type="text" id="esi">
		<input type="hidden" id="hidesi">
		<input type="button" onclick="openwindow();" value="go">
		<div id="dialog-confirm" title="Empty the recycle bin?">
 <div id="innerdialog"></div>
</div>
  <table cellspacing="0">
	         <tr>
	         <th>First Name</th>
	         <th>Aadhar Number</th>
	          <th>Phone</th>
	         </tr>
	          <tr>
	         <td>sowjanya</td>
	         <td>APSFRFDTF</td>
	          <td>9875676786768</td>
	         </tr>
	          <tr>
	         <td>sousheel</td>
	         <td>APS4324FF</td>
	          <td>987876786768</td>
	         </tr>
	         </table>
</div>
		
	</div>
</body>
</html>