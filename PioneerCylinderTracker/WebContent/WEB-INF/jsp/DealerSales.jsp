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
<title>Dealer Sales</title>
</head>
<body>
<center><div style="color:blue"><h1><i>Dealer Sales Invoice</i></h1></div><br><div style="color:green">${success}</div>
        <div style="color:red">${fail}</div>
        <form action="/PioneerCylinderTracker/dealerSales" method="post">
        <table>
        
       <tr>
				<td><b>Dealer ID </b></td><td><input type="text" name="dealerId"/></td>
				</tr>
       <tr>
       <tr>
				<td><b>From Date</b></td>
				<td><input type="text" name="fromDate"/></td>
			</tr>
        <tr>
				<td><b>To Date</b></td>
				<td><input type="text" name="toDate"/></td>
			</tr>
        <tr align="center">
				<td><input type="submit" name="submit" value="submit"
					class="btn btn-success" /></td>
			</tr>
        </table>
        </form>
        </center>
</body>
</html>