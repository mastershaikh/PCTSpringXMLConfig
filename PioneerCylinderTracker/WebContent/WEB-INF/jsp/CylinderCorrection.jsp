<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<style>
.error {
	color: #ff0000;
	font-weight: bold;
}
.red{
	color:red;
}
.green{
	color:green;
}
</style>
<title>Cylinder Correction</title>
</head>
<body><center><div><h2 class="green">${done}</h2></div></center>
	<form action="RectifyCylinder" method="post">

		<table align="center" border=10px>
			<tr>
				<td><b>Dealer Id</b></td>
				<td><input type="text" name="dealerId"/></td>
			</tr>
			
			<tr>
				<td><b>Wrong Cylinder Number</b></td>
				<td><input type="text" name="wrongCid"/></td>
			</tr>
			
			<tr>
				<td><b>Correct Cylinder Number</b></td>
				<td><input type="text" name="rightCid"/></td>
			</tr>
			<tr align="center">
				<td></td>
				<td><input type="submit" class="btn-success" name="proceed" value="proceed"/></td>
			</tr>
		</table>
	</form>
</body>
</html>