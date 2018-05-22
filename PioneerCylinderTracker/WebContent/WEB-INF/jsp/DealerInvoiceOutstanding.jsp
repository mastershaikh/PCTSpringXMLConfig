<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
     <%@ page session="true" %>
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
<center><div style="color:blue"><h1><i>Generate Dealer Invoice Outstanding</i></h1></div><br><div style="color:green">${suc}</div>
        <div style="color:red">${inv}${fail}</div>
        <table>
        <form:form action="/PioneerCylinderTracker/GenerateOutstanding" method="post" commandName="gout">
       <tr>
				<td><b>Dealer ID </b></td><td><form:input path="dealerId" id="dId"/></td>
				<td><form:errors path="dealerId" cssClass="error" /></td>
				</tr>
        
        <tr align="center">
				<td><input type="submit" name="GenerateOutstanding" value="GenerateOutstanding"
					class="btn btn-success" /></td>
			</tr>
        </form:form>
        </table>
        </center>
</body>
</html>