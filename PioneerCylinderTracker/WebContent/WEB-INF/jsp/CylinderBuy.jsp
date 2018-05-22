<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<%@ page import="java.io.*,java.util.*,java.sql.*"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page session="true" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script src="${pageContext.request.contextPath}/resources/js/jquery.min.js" type="text/javascript"></script>
  <script src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js" type="text/javascript"></script>

<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap-theme.min.css">

<script type="text/javascript">
function countCylinders(){
$('#result').text($.trim($('#cid').val())
		.split(' ').filter(function(a){return a!==''}).length);
};
</script>

<style>
.error {
	color: #ff0000;
	font-weight: bold;
}
.available{
	color:green;
}
.red{
	color:red;
}
.blue{
	color:blue;
}
</style>
<script>
var num;
var cyltype;
/* $(document).ready(function() {
	$('#cylinderType').change(function() {
		$.ajax({
			url : '/PioneerCylinderTracker/CylinderCount',
			data : {
				cylinderType : $('#cylinderType').val()
			},
			success : function(responseText) {
				$('#count').text(responseText);
				num = responseText;
				var comp = $('#cylNum').val();
				$('#cylNum').click(function(){
				if(comp>num){
					alert("You cannot buy cylinders more than availability");
					}
				})
			}
		});
	});
}); */

/* $(document).ready(function() {
	$('#cylinderId').change(function() {
		$.ajax({
			type:"GET",
			url : '/PioneerCylinderTracker/CylinderType',
			data : {
				cylinderId : $('#cylinderId').val()
			},
			success : function(responseText) {
				$('#cylType').text(responseText);				
				document.getElementById('cylTyp').value = responseText;
			}
		});
	});
}); */


function countCylinders(){
$('#result').text($.trim($('#cid').val())
		.split(' ').filter(function(a){return a!==''}).length);
};
</script>
<title>Purchase cylinders</title>
</head>
<body>
<center><div><h2 style="color:red">${stats}${nodeal}</h2>
</div>
<div>
<h2 style="color:green">${MSG}</h2>
</div>
</center>
	<form action="CylinderBuy" method="post">
		<center>
				<h1>Buy Cylinders</h1>
			<table>
				<tr>
					<td>Dealer Name:
					<td><input type="text" name="dealerName" value='${sessionScope.dealerName}' readonly="readonly"/></td>
				</tr>
				<tr>
					<td>Dealer Id:
					<td><input type="text" name="dealerId" value='${sessionScope.dealerId}' readonly="readonly"/></td>
				</tr>
				
				<tr>
					<td>Lorry Number:
					<td><input type="text" name="lorryNo"/></td>
				</tr>
				<tr>
					<td>Cylinder Number:
					<td><input type="text" id = "cid" name="cylinderId" onkeyup="countCylinders()"/></td>
				</tr>
				
				<tr>
					<td></td>
					<td>
					<input type="submit" name="Proceed" value="Proceed"
						class="btn btn-success" /></td>
				</tr>

			</table>
			<h1 id="result"> - </h1>
		</center>
	</form>
</body>
</html>