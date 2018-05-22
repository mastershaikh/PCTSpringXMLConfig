<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page session="true" %>
     <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
     <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<style>
.confirm_selection {
    -webkit-transition: text-shadow 0.2s linear;
    -moz-transition: text-shadow 0.2s linear;
    -ms-transition: text-shadow 0.2s linear;
    -o-transition: text-shadow 0.2s linear;
    transition: text-shadow 0.2s linear;
}
.confirm_selection:hover {
    text-shadow: 0 0 10px green; /* replace with whatever color you want */
}
</style>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Dealer Search</title>
</head>
<body>
<center>
<div style='color:red'><h1>${invalid}${dam}</h1></div>
<div style="color:green"><h1>${company}</h1></div>
<div style="color:blue"><h1>${dealer}</h1></div><br>
<form action="/PioneerCylinderTracker/FindCylinder" method="POST">
<input type="text" name="cid"/>
<button type="submit" value ="Search" name="Search">
<i class="fa fa-search" style="font-size:30px;"></i></button>
</form>
</center>
</body>
</html>