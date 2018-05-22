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
<title>Invoice Preview</title>
</head>
<body>
<center><div style="color:blue"><h1><i>Preview Invoice</i></h1></div><br><div style="color:green">${success}</div>
        <div style="color:red">${exist}${inv}</div>
         <form action="/PioneerCylinderTracker/PreviewInvoice" method="post">
        <table>       
       	<tr>
				<td><b>Dealer ID </b></td><td><input type="text" name="dealerId" value="${sessionScope.dealerId}" readonly="readonly"/></td>
				</tr>
       <tr>
       <tr>
				<td><b>vehicle No.</b></td>
				<td><input type="text" name="vehicleNo" value="${sessionScope.lorryNo}"/></td>
			</tr>
        <tr>				

        <tr align="center">
				<td><input type="submit" name="PreviewInvoice" value="PreviewInvoice"
					class="btn btn-success" /></td>
			</tr>
			</table>
			<input type="hidden" name="company" value="${sessionScope.CorD}"/>
        </form>
        
        </center>
</body>
</html>