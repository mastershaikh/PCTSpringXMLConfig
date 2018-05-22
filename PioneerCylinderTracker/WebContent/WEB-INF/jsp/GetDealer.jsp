<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page session="true" %>
     <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/font-awesome.min.css">
<style>
table, th, td {
    border: 1px solid black;
    border-collapse: collapse;
}
th, td {
    padding: 5px;
    text-align: left;
}

</style>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Dealer Search</title>
</head>
<body>
<center>
<div style="color:red"><h1>${fail}</h1></div>
<form action="/PioneerCylinderTracker/dealerDetails" method="POST">
<input type="text" name="dealerId"/>
<button type="submit" value ="Search" name="Search">
<i class="fa fa-truck" style="font-size:30px;"></i></button>
</form>

	<h1>Dealer List</h1>
	<table>
<tr><th>Dealer Id</th>
<th>Dealer Name</th></tr>
<c:forEach items="${dealerList}" var="idname">
<tr><td>${idname.key}</td>
<td>${idname.value}</td>
</tr>
</c:forEach>
</table>
</center>
</body>
</html>