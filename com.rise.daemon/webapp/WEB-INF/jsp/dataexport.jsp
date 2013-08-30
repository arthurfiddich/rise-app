<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.List"%>
<%@ page
	import="com.rise.common.model.Person,com.rise.common.util.controller.components.Export,com.rise.common.util.controller.components.Field,java.util.*"%>

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
<link type="text/css" href="/rise/resources/css/jquery.treeview.css"
	rel="stylesheet" />
<script type="text/javascript"
	src="/rise/resources/js/jquery.treeview.js"></script>
<script type="text/javascript" src="/rise/resources/js/jquery-cookie.js"></script>
<script type="text/javascript" charset="utf-8">
	
<%Map<String, List<Field>> entityNameVsFieldListMap = new LinkedHashMap<String, List<Field>>();%>
	$(document).ready(function() {
		$("#navigation").treeview({
			persist : "location",
			Expandable : false,
			unique : true
		});
	});
	function openDataImport() {
		window.location.href = "/rise/export/create";
	}
	function openDataExport() {
		window.location.href = "/rise/export/create";
	}
	function colletFields(checkid, checkvalue) {
		alert(checkid);
		alert(checkvalue);
		alert($("#" + checkid).val());
		alert('Element......'+document.getElementById(""+checkid+"").checked);
		alert($("#"+checkid).prop('checked'));

	}
	function exportValues(entity, length) {
		alert(length);
		var fieldarray = "";
		if (length > 1) {
			for ( var i = 0; i < length - 1; i++) {
					alert('Helloooooooooooooooooooooo'+fieldarray);
				if ($("#" + i).prop('checked') == true) {
					fieldarray += $("#" + i).val() + ",";
				}
			}
		}
		$.ajax({
			type : "POST",

			url : "/rise/export/exportajaxList?entity=" + entity
					+ "&fieldarray=" + fieldarray,
			dataType : "json",
			contentType : "application/json; charset=utf-8",
			data : {
				entity : entity,
				fieldarray : fieldarray
			},

			success : function(response) {
				alert("success");
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
		// alert(resultstr);
	}
	function openDetails(data1, fieldList, idcount, totalcount) {
		for ( var k = 1; k < totalcount; k++) {
			document.getElementById(k).style.color = "white";
		}
		document.getElementById(idcount).style.color = "blue";
		var str = "";

		var str_array = fieldList.split(',');
		if (str_array.length > 1) {
			str += "<div class='headertablestyle'>";
			str += "<table border='0'>";
			for ( var i = 0; i < str_array.length; i++) {
				if(str_array[i]!=null && str_array[i]!=""){
					  str += "<tr>";
						str += "<td align='right'>";
						str += "<input type='checkbox' value='"+str_array[i]+"' id='"+i+"' onclick='colletFields(this.id,this.value)'/>";
						str += "</td>";
						str += "<td align='left'>";
						str += "<span>"+str_array[i]+"</span>";
						str += "</td>";
						str += "</tr>";
					  //alert(str_array[i]);
				  }
			}
			str += "</table>";
			str += "<input type='button' class='styled-button-3' value='Export' onclick=exportValues('"
					+ data1 + "','" + str_array.length + "')>";
		} else {
			str += "No Data Found";
		}
		$("#subrightcontent").html(str);
		/*
		$
		.ajax({
			type : "POST",

			url : "/rise/export/exportajaxList",
			dataType : "json",
			contentType : "application/json; charset=utf-8",
			data : {name:data1},

			success : function(response) {
				//str += "<div class='datatablestyle'>";
				str += "<table border='0'>";
				
				$.each(response, function(index, value) {
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
		});*/
		document.getElementById("subrightcontent").style.display = "block";
	}
	function openSetupWindow() {
		window.location.href = "/rise/setUp";
	}
</script>


</head>
<body>
	<div class="container">
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
								onclick="openSetupWindow()"> </span></td>
					</tr>
				</table>
			</div>
			<div id="menubar" style="width: auto">
				<ul id="menu">
					<li><a name="home" href="/rise/home">Home</a></li>
					<li><a name="person" href="/rise/person/list">Person </a></li>
					<li><a name="Trainer" href="/rise/trainer"
						class="activesection"><span style="color: blue;">Trainer</span>
					</a></li>
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
								<li><a href="javascript:openDataExport();"><span
										style="color: blue">Data Export</span> </a></li>
								<li><a href="javascript:openDataImport();">Data Import</a>
								</li>
							</ul>
						</li>
					</ul>

					    
				</div>
				 
				<div id="right" class="rightcontainer" style="display: block;">
					         
					<div class="maincontentcontainer" style="display: block;">
						<div id="subleftcontent" class="subleftcontainer">
							<table>
								<%
									Export export = (Export) request.getAttribute("export");
									entityNameVsFieldListMap = export.getEntityNameVsFieldListMap();
									Set s = entityNameVsFieldListMap.entrySet();
									Iterator itr = s.iterator();
									int count = 0;
									int length = entityNameVsFieldListMap.size();
									while (itr.hasNext()) {
										Map.Entry<String, List<Field>> me = (Map.Entry<String, List<Field>>) itr
												.next();
										String entity = me.getKey();
										List<Field> fieldList = me.getValue();
										String fieldStr = "";
										count++;
										for (Field field : fieldList) {
											fieldStr += field.getFieldName() + ",";
											System.out.println("value :" + field.getFieldName());
										}
								%>
								<tr>
									<td align="left"><div class="hrefstyles">
											<li><a
												href="javascript:onclick=openDetails('<%=entity%>','<%=fieldStr%>','<%=count%>','<%=length%>')"><span
													id="<%=count%>"><%=entity%></span> </a></li>
										</div></td>
								</tr>
								<%%>
								<%
									}
								%>
							</table>
						</div>
						<div id="subrightcontent" class="subrightcontainer"
							style="display: none;"></div>
					</div>
					     
				</div>
			</div>
		</div>
</body>
</html>