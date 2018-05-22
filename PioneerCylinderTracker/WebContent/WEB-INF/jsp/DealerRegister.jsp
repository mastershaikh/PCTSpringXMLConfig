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
</style>
<title>Registration Form</title>
</head>
<body bgcolor="green">${MSG}
	<form:form action="dealerRegistration" method="post" id="commentForm"
		commandName="dealer">
		<h1 align="center">
				<b><i>Pioneer Oxygen Dealer registration Form</i></b>
		</h1>
		<table align="center" border=10px>
			<tr>
				<td><b>Dealer Name</b></td>
				<td><form:input path="dealerName" minlength="2" maxlength="50"
						/></td>
				<td><form:errors path="dealerName" cssClass="error" /></td>
			</tr>
			
			<tr>
				<td><b>GSTIN</b></td>
				<td><form:input path="gstin" minlength="2" maxlength="50" /></td>
				<td><form:errors path="gstin" cssClass="error" /></td>
			</tr>
			
			<tr>
				<td><b>Company Oxygen rate per Cu.m</b></td>
				<td><form:input path="rateo2" minlength="1" maxlength="30" /></td>
				<td><form:errors path="rateo2" cssClass="error" /></td>
			</tr>
			<tr>
				<td><b>Company Nitrogen rate per Cu.m</b></td>
				<td><form:input path="raten2" minlength="1" maxlength="30"/></td>
				<td><form:errors path="raten2" cssClass="error" /></td>
			</tr>
			
			<tr>
				<td><b>Company Air rate per Cu.m</b></td>
				<td><form:input path="rateair" minlength="1" maxlength="30"/></td>
				<td><form:errors path="rateair" cssClass="error" /></td>
			</tr>
			
			<tr>
				<td class="red"><b>Dealer Oxygen rate per Cu.m</b></td>
				<td><form:input path="drateo2" minlength="1" maxlength="30" /></td>
				<td><form:errors path="drateo2" cssClass="error" /></td>
			</tr>
			
			<tr>
				<td class="red"><b>Dealer Nitrogen rate per Cu.m</b></td>
				<td><form:input path="draten2" minlength="1" maxlength="30"/></td>
				<td><form:errors path="draten2" cssClass="error" /></td>
			</tr>
			
			<tr>
				<td class="red"><b>Dealer Air rate per Cu.m</b></td>
				<td><form:input path="drateair" minlength="1" maxlength="30"/></td>
				<td><form:errors path="drateair" cssClass="error" /></td>
			</tr>
			
			<tr>
				<td><b>Address</b></td>
				<td><form:input path="address" maxlength="100"/></td>
				<td><form:errors path="address" cssClass="error" /></td>
			</tr>
			<tr>
				<td><b>Contact No</b></td>
				<td><form:input path="contactNo" maxlength="15" /></td>
				<td><form:errors path="contactNo" cssClass="error" /></td>
			</tr>
			<tr>
				<td><b>EmailID</b></td>
				<td><form:input path="emailId" maxlength="30"				
						placeholder="abc@xyz.com" /></td>
				<td><form:errors path="emailId" cssClass="error" /></td>
			</tr>
			<tr align="center">
				<td></td>
				<td><input type="submit" class="btn-success" name="register" value="Register"/></td>
			</tr>
		</table>
	</form:form>
</body>
</html>