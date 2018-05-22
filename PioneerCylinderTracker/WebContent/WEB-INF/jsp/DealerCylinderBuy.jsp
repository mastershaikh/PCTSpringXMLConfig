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
<title>Add Dealer cylinders</title>
</head>
<body>
<center><div class="red"><h1>${fail}</h1></div>
<div class="available"><h1>${success}</h1></div>
	<form action="DealerCylinderBuy" method="post">
				<h1>Add Dealer Cylinders</h1>
			<table>
				<tr>
					<td>Dealer Name:
					<td><input name="dealerName" value='${sessionScope.dealerName}' readonly="readonly"/></td>
				</tr>
				<tr>
					<td>Dealer Id:
					<td><input name="dealerId" value='${sessionScope.dealerId}' readonly="readonly"/></td>
				</tr>
				
				<tr>
					<td>Lorry Number:
					<td><input type="text" name="lorryNo" value="${sessionScope.lorryNo}" maxlength="10"/></td>
				</tr>
				
				<tr>
				<td><b>Cylinder Type</b>
				<td><select name="cylinderType">
				<option value="INDUSTRIAL OXYGEN [HSN:28044090]" label="INDUSTRIAL OXYGEN"></option>
				<option value="INDUSTRIAL NITROGEN [HSN:28043000]" label="INDUSTRIAL NITROGEN"></option>
				<option value="Air" label="Air"></option>
				</select></td>
			</tr>
			<tr>
				<td><b>Capacity</b></td>
				<td><select name="capacity">
				<option value="1.00" label="1.00 Cu.m"></option>
				<option value="1.50" label="1.50 Cu.m"></option>
				<option value="3.00" label="3.00 Cu.m"></option>
				<option value="5.00" label="5.00 Cu.m"></option>
				<option value="6.00" label="6.00 Cu.m"></option>
				<option value="7.00" label="7.00 Cu.m"></option>
				</select></td>
			</tr>
				<tr>
					<td>Total Cylinders:
					<td><input type="text" name="cylinderCount" /></td>
				</tr>			
				
				<tr>
					<td></td>
					<td><input type="submit" name="Add" value="Add"
						class="btn btn-default" /></td>
				</tr>

			</table>
		
	</form><br>
	
	
	<form action="/PioneerCylinderTracker/PreviewInvoice" method="post">
				<input type="hidden" name="dealerId" value="${sessionScope.dealerId}"/>
				<input type="hidden" name="vehicleNo" value="${sessionScope.lorryNo}"/>
				<input type="hidden" name="company" value="N"/>
				
	<table>
        <tr align="center">
				<td><input type="submit" name="PreviewInvoice" value="PreviewInvoice"
					class="btn btn-success" /></td>
			</tr>
			</table>
        </form>
	</center>
</body>
</html>