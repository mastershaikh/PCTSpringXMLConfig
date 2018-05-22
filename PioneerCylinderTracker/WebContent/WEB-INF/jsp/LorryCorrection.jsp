<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
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
<title>Lorry Correction</title>
</head>
<body><center><div><h2 class="green">${done}</h2></div>
<div><h2 class="red">${fail}</h2></div></center>
	<form action="RectifyLorry" method="post">

		<table align="center" border=10px>
			<tr>
				<td><b>Invoice Number</b></td>
				<td><input type="text" name="billNo"/></td>
			</tr>
			
			<tr>
				<td><b>New Lorry Number</b></td>
				<td><input type="text" name="lorryNo"/></td>
			</tr>
			
			<tr align="center">
				<td></td>
				<td><input type="submit" class="btn-success" name="proceed" value="proceed"/></td>
			</tr>
		</table>
	</form>
</body>
</html>