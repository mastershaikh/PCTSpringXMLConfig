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
<title>Dealer Sale</title>
</head>
<body>
<center>
<div><h2 style="color:green">Ongoing current month Sale Value</h2>
</div><br>
						
<table>
<tr><th>Dealer Id</th>
<th>Dealer Name</th>
<th>GSTIN</th>
<th>O2 cylinders</th>
<th>N2 cylinders</th>
<th>Total Cylinders</th>
<th>Bill Till date</th>
</tr>

<tr><td><c:out value="${sessionScope.dealerId}" ></c:out></td>
<td><c:out value="${sessionScope.dealerName}" ></c:out></td>
<td><c:out value="${sessionScope.gstin}" ></c:out></td>
<td><c:out value="${sessionScope.noO2}" ></c:out></td>
<td><c:out value="${sessionScope.noN2}" ></c:out></td>
<td><c:out value="${sessionScope.totalCylindersTaken}" ></c:out></td>
<td><c:out value="${sessionScope.bill}" ></c:out></td></tr>
</table><br>

</center>
</body>
</html>