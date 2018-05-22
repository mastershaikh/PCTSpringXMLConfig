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
<title>Preview Dealer Invoice</title>
</head>
<body>
<center>
	<form action="/PioneerCylinderTracker/GenerateInvoice" method="post">

			<input type="text" name="dealerId" value="${sessionScope.dealerId}" readonly="readonly"/>
				<input type="hidden" name="company" value ="N">
				<input type="text" name="vehicleNo" value="${sessionScope.lorryNo}"/>
				
<input type="submit" name="GenerateInvoice" value="GenerateInvoice"	class="btn btn-success" />
			
        </form><br>
        <h2 style="color:blue">Preview Dealer Invoice</h2>		
<table>
<tr><th>Dealer Id</th>
<th>Lorry No</th>
<th>Type of Cylinder</th>
<th>List of Cylinder Count</th>
<th>Total Cylinders</th>
<th>Quantity in Cu.m</th>
<th>Total Quantity in Cu.m</th>
<th>Bill Without Tax</th>
<th>CGST @9%</th>
<th>SGST @9%</th>
<th>Invoice Amount</th>
</tr>

<tr><td><c:out value="${sessionScope.dealerId}" ></c:out></td>
<td><c:out value="${sessionScope.vehicleNo}" ></c:out></td>
<td><c:forEach items="${sessionScope.cylinderType}" var="item"  varStatus="loop">
  <c:out value="${loop.index+1})"/> <c:out value="${item}" ></c:out><br>
</c:forEach>
</td>
<td><c:forEach items="${sessionScope.cylCount}" var="item">
   <c:out value="${item}" ></c:out><br>
</c:forEach>
</td>
<td><c:out value="${sessionScope.totalCCount}" ></c:out></td>
<td><c:forEach items="${sessionScope.quantity}" var="item">
  <c:out value="${item}" ></c:out><br>
</c:forEach></td>

<td><c:out value="${sessionScope.totalQuantity}" ></c:out></td>
<td><c:out value="${sessionScope.billwithoutTax}" ></c:out></td>
<td><c:out value="${sessionScope.CGST}" ></c:out></td>
<td><c:out value="${sessionScope.SGST}" ></c:out></td>
<td><c:out value="${sessionScope.bill}" ></c:out></td></tr>
</table><br>



</center>
</body>
</html>