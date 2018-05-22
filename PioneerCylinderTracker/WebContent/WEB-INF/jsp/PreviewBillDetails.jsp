<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page session="true" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dth">
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
table, th, td {
    border: 1px solid black;
    border-collapse: collapse;
}
th, td {
    padding: 5px;
    text-align: center;
}
th{
	background-color: orange;
}
.blue{
	color:blue;
}
.highlight {background-color: red;}
</style>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Preview Bill Details</title>
</head>
<body>
<c:set var="dealerId" value="${sessionScope.dealerId}"></c:set>
<center>
	<form action="/PioneerCylinderTracker/GenerateInvoice" method="post">

			<table>
			<tr><td><input type="text" name="dealerId" value="${dealerId}"/><td></tr>
			<tr><td><input type="text" name="vehicleNo" value="${sessionScope.lorryNo}"/></td></tr>
				
			<tr><td><input type="submit" name="GenerateInvoice" value="Generate Invoice" class="btn btn-success" /></td></tr>
			</table>
			<input type="hidden" name="company" value = "${sessionScope.CorD}">
        </form>		<br>
        ${dealerId}
<table>
<tr><th>Dealer Id</th>
<th>Lorry No</th>
<th>Type of Cylinder</th>
<th>List of Cylinder Number</th>
<th>Total Cylinders</th>
<th>Total Quantity in Cu.m</th>
<th>CGST @9%</th>
<th>SGST @9%</th>
<th>Invoice Amount + 18% GST</th>
</tr>

<tr><td><c:out value="${sessionScope.dealerId}" ></c:out></td>
<td><c:out value="${sessionScope.lorryNo}" ></c:out></td>
<td><c:out value="${sessionScope.GasType}" ></c:out></td>
<td><c:forEach items="${sessionScope.cylinderList}" var="item"  varStatus="loop">
  <c:out value="${loop.index+1}) "/> <c:out value="${item}" ></c:out><br>
</c:forEach>
<td><c:out value="${sessionScope.NoCyl}" ></c:out></td>
<td><c:out value="${sessionScope.totalQuantity}" ></c:out></td>
<td><c:out value="${sessionScope.CGST}" ></c:out></td>
<td><c:out value="${sessionScope.SGST}" ></c:out></td>
<td><c:out value="${sessionScope.bill}" ></c:out></td></tr>
</table><br>

</center>
</body>
</html>