<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Cache-Control" content="no-cache, no-store, must-revalidate" />
<meta http-equiv="Pragma" content="no-cache" />

<style>
.error {
	color: #ff0000;
	font-weight: bold;
}
</style>
<title>Cylinder Registration Form</title>
</head>
<body>

<center style="color:red">${fail}</center>
<center style="color:green">${success}</center>
	<form:form action="CylinderDamage" method="post" commandName="cylinder">
		<h1 align="center">
		<b><i>Cylinder Damage Entry form</i></b>
		</h1>
		<table align="center" border=10px>
			<tr>
				<td><b>Cylinder Number</b></td>
				<td><form:input path="cylinderId" minlength="1" maxlength="15"
						/></td>
				<td><form:errors path="cylinderId" cssClass="error" /></td>
			</tr>
			
			<tr>
				<td><b>Cylinder Damage remark</b></td>
				<td><form:input path="remark" minlength="1" maxlength="60" ></form:input>
			</tr>
						
			<tr align="center">
				<td></td>
				<td><input type="submit" name="submit" value="submit"
					class="btn btn-success" /></td>
			</tr>
		</table>
	</form:form>
</body>
</html>