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
  <script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>
  
	<SCRIPT LANGUAGE="JavaScript">  
  
var ob;  
var obLeft;  
var obRight;  
var over = false;  
var iEdgeThreshold = 10;  
   
function findPos(obj) {  
  var curleft = curtop = 0;  
  if (obj.offsetParent) {  
      curleft = obj.offsetLeft;  
      curtop = obj.offsetTop;  
      while (obj = obj.offsetParent) {  
         curleft += obj.offsetLeft;  
         curtop += obj.offsetTop;  
      }  
   }  
   return [curleft,curtop];  
}  
   
/* Function that tells me if on the border or not */  
function isOnBorderRight(objTable,objTh,event){  
  var width = objTh.offsetWidth;  
  var left = objTh.offsetLeft;  
  var pos = findPos(objTable);  
  var absRight = pos[0] + left + width;  
   
  if( event.clientX > (absRight - iEdgeThreshold) ){  
      return true;  
  }  
   
  return false;  
}  
   
function getNodeName(objReference,nodeName){  
   var oElement = objReference;  
   while (oElement != null && oElement.tagName != null && oElement.tagName != "BODY") {  
      if (oElement.tagName.toUpperCase() == nodeName) {  
         return oElement;  
      }  
      oElement = oElement.parentNode;  
   }  
   return null;  
}  
   
function doResize(objTh,event){  
    if(!event) event = window.event;  
    var objTable = getNodeName(objTh,"TABLE");  
    if( isOnBorderRight(objTable,objTh,event)){   
       over=true;  
       objTh.style.cursor="e-resize";  
    }  
    else{  
       over=false;  
       objTh.style.cursor="";  
    }  
    return over;  
}  
   
function doneResizing(){  
   over=false;  
}  
   
function MD(event) {  
   if(!event) event = window.event;  
   
   MOUSTSTART_X=event.clientX;  
   MOUSTSTART_Y=event.clientY;  
   
   if (over){        
       if(event.srcElement)ob = event.srcElement;  
       else if(event.target)ob = event.target;  
       else return;  
   
       ob = getNodeName(ob,"TH");  
       if(ob == null) return;  
       //ob2 = getNodeName(ob,"TABLE");  
       //obLeft = ob.previousSibling;  
       obRight = ob.nextSibling;  
       //obLeft = ob.previousElementSibling;   
       //obRight = ob.nextElementSibling;  // Uncomment For FF  
       obwidth=parseInt(ob.style.width);  
       if (obLeft != null)  
       obLeftWidth=parseInt(obLeft.style.width);  
       if (obRight != null)  
       obRightWidth=parseInt(obRight.style.width);  
       //obwidth2=parseInt(ob2.offsetWidth);  
   }          
}  
   
function MM(event) {  
    if(!event) event = window.event;  
   
    if (ob) {  
        st=event.clientX-MOUSTSTART_X+obwidth;  
        //st2=event.clientX-MOUSTSTART_X+obwidth2;  
        document.getElementById("infoDiv").innerHTML = "st=" + st + " clientX=" + event.clientX + " moustart_x=" + MOUSTSTART_X + " obwidth=" + obwidth;  
        //document.getElementById("infoDiv").innerHTML += ;  
        //document.getElementById("infoDiv").innerHTML += ;  
        //document.getElementById("infoDiv").innerHTML += obwidth;  
   
        if(st>=10){  
            ob.style.width=st;  
            //ob2.style.width=st2;  
            //obLeft.style.width=st-obLeftWidth;  
            obRight.style.width=(parseInt(obwidth - st + obRightWidth) > 10 ? (obwidth - st + obRightWidth) : iEdgeThreshold + "px") ;  
        }  
        if(document.selection) document.selection.empty();  
        else if(window.getSelection)window.getSelection().removeAllRanges();  
    }  
}  
   
function MU(event) {  
    if(!event) event = window.event;  
    if(ob){  
        if(document.selection) document.selection.empty();  
        else if(window.getSelection)window.getSelection().removeAllRanges();  
        ob = null;  
    }  
}  
   
document.onmousedown = MD;  
document.onmousemove = MM;  
document.onmouseup = MU;  
</script>  
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
	<td width="20%" align="center">
	<img src="/rise/resources/images/indian-flag.gif" height="85px" width="195px"></img></td>
	<td align="center" width="70%">
			<span style='font: bold 30px "Trebuchet MS", Verdana, Arial, Helvetica, sans-serif;'>Rural integration Sustainable Employment</span>
	</td>
	
	<td align="center">
	<span style="align:right;"><input type="button" value="Setup" class="styled-button-3" onclick="openSetupWindow()"></span>
	</td>
	</tr>
	</table>
		</div>
		
		<div id="menubar" style="width: auto">
			<ul id="menu">
				<li><a name="home" href="/rise/home"class="activesection"><span style="color: blue;">Home</span></a></li>
				<li><a name="person" href="/rise/person/list">Person
				</a></li>
				<li><a  name="Trainer" href="/rise/trainer">Trainer</a></li>
				<li><a href="#">Agents</a></li>
				<li><a href="#">Overview</a></li>
				<li><a href="#">Contact Us</a></li>
			</ul>
		</div>
		<div id="divcontainer" class="content">
		ESI Number : <input type="text" id="esi">
		<input type="hidden" id="hidesi">
		<input type="button" onclick="openwindow();" value="go">
		<div id="dialog-confirm" title="Empty the recycle bin?">
 <div id="innerdialog"></div>
</div>
<div class="datatablestyle">
  <table cellspacing="0" id="myTable">
  <thead>
	         <tr>
	         <th onmousemove="doResize(this,event)"  onmouseover="doResize(this,event)" onmouseout='doneResizing()'>First Name</th>
	         <th onmousemove="doResize(this,event)"  onmouseover="doResize(this,event)" onmouseout='doneResizing()'>Aadhar Number</th>
	          <th onmousemove="doResize(this,event)"  onmouseover="doResize(this,event)" onmouseout='doneResizing()'>Phone</th>
	         </tr>
	         </thead>
	         <tbody>
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
	         </tbody>
	         </table>
	         </div>
</div>
		
	</div>
</body>
</html>