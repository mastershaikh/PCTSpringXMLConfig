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
<title>Dealer Sales</title>
</head>
<body>
<center>
<div><h3><c:out value="${sessionScope.dealerId}"></c:out></h3>
</div><br>
						
<table>
<tr>
<th>Sl No </th>
<th>Invoice No</th>
<th>Type of Cylinder</th>
<th> Number of Cylinders Taken</th>
<th>Quantity in Cu.m</th>
<th>Sale Value</th>
<th>CGST @9%</th>
<th>SGST @9%</th>
<th>Invoice Amount with GST</th>
</tr>

<tr><td><c:forEach items="1" var="item"  varStatus="loop">
  <c:out value="${loop.index+1})"/><br>
</c:forEach>

<td><c:forEach items="${sessionScope.billNo}" var="item">
 <c:out value="${item}" ></c:out><br>
</c:forEach></td>
<td><c:forEach items="${sessionScope.cylinderType}" var="item">
<c:out value="${item}" ></c:out><br>
</c:forEach></td>
<td><c:forEach items="${sessionScope.cylinders}" var="item">
<c:out value="${item}" ></c:out><br>
</c:forEach></td>
<td><c:forEach items="${sessionScope.quantity}" var="item">
<c:out value="${item}" ></c:out><br>
</c:forEach></td>
<td><c:forEach items="${sessionScope.saleValue}" var="item">
<c:out value="${item}" ></c:out><br>
</c:forEach></td>
<td><c:forEach items="${sessionScope.CGST}" var="item">
<c:out value="${item}" ></c:out><br>
</c:forEach></td>
<td><c:forEach items="${sessionScope.SGST}" var="item">
<c:out value="${item}" ></c:out><br>
</c:forEach></td>
<td><c:forEach items="${sessionScope.billValue}" var="item">
<c:out value="${item}" ></c:out><br>
</c:forEach></td>

</tr>
</table><br>

Total Bill value<c:out value="${sessionScope.totalBillValue}"></c:out>


</center>
</body>
</html>