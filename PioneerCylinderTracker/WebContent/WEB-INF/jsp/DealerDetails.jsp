<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
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
<title>Customer Home</title>
</head>
<body>
<center>
<div><c:set var="dealerId" value="${sessionScope.dealerId}"></c:set>
<c:set var="dealerName" value="${sessionScope.dealerName}"></c:set>
			<h2>Dealer Name</h2><h1 class="blue"><c:out value="${sessionScope.dealerName}"></c:out></h1>
</div><br>
						
<table>
<tr><th>Dealer Id</th>
<th>Dealer Name</th>
<th>Address</th>
<th>Contact No</th>
<th>GSTIN</th>
<th>Rate of Oxygen</th>
<th>Rate of Nitrogen</th>
<th>PIO Cylinders possessed by Dealer</th>
<!-- <th>Date Of Registration</th> -->
<th>Bill Till date</th>
</tr>

<tr><td><c:out value="${sessionScope.dealerId}" ></c:out></td>
<td><c:out value="${sessionScope.dealerName}" ></c:out></td>
<td><c:out value="${sessionScope.address}" ></c:out></td>
<td><c:out value="${sessionScope.contactNo}" ></c:out></td>
<td><c:out value="${sessionScope.gstin}" ></c:out></td>
<td><c:out value="${sessionScope.rateo2}" ></c:out></td>
<td><c:out value="${sessionScope.raten2}" ></c:out></td>
<td><c:out value="${sessionScope.totalCylindersTaken}" ></c:out></td>
<td><c:out value="${sessionScope.bill}" ></c:out></td></tr>
</table><br>

<c:choose>
    <c:when test="${empty sessionScope.dealerId}">
    No Dealer exist
    </c:when>
    <c:otherwise>
    <div id="navigate">
        
        <a href="/PioneerCylinderTracker/CylinderBuy"><button class="btn-success">Company Cylinders</button></a>
        <a href="/PioneerCylinderTracker/DealerCylinderBuy"><button class="btn-default">Dealer Cylinders</button></a>
        <a href="/PioneerCylinderTracker/CylinderReturn"><button class="btn-danger">Return Cylinders</button></a>
</div><br>
    </c:otherwise>
</c:choose>
</center>
</body>
</html>