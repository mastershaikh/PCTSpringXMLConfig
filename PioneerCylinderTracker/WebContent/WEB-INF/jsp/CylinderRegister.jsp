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

<script type="text/javascript">

$(document).ready(function()
{
    var damage=document.getElementById('damy');
    damage.onclick=function(){
        dm =prompt('Enter cause for damage');
        if(dm)
		{document.getElementById('dam').value = dm;
		delete dm;
        }
       
    }
});

</script>
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
	<form:form action="CylinderRegister" method="post" commandName="cylinder">
		<h1 align="center">
		<b><i>Cylinder Registration Form</i></b>
		</h1>
		<table align="center" border=10px>
			<tr>
				<td><b>Cylinder Number</b></td>
				<td><form:input path="cylinderId" minlength="1" maxlength="5"
						/></td>
				<td><form:errors path="cylinderId" cssClass="error" /></td>
			</tr>
			
			<tr>
				<td><b>Cylinder Type</b>
				<td><form:select path="cylinderType" multiple="false">
				<form:option value="INDUSTRIAL OXYGEN [HSN:28044090]" label="INDUSTRIAL OXYGEN"></form:option>
				<form:option value="INDUSTRIAL NITROGEN [HSN:28043000]" label="INDUSTRIAL NITROGEN"></form:option>
				<form:option value="Air" label="Air"></form:option>
				</form:select></td>
				<td><form:errors path="cylinderType" cssClass="error" /></td>
			</tr>
			<tr>
				<td><b>Capacity</b></td>
				<td><form:select path="capacity" multiple="false">
				<%-- <form:option value="1.00" label="1.00 Cu.m"></form:option>
				<form:option value="1.50" label="1.50 Cu.m"></form:option>
				<form:option value="3.00" label="3.00 Cu.m"></form:option>
				<form:option value="5.00" label="5.00 Cu.m"></form:option> --%>
				<form:option value="6.00" label="6.00 Cu.m"></form:option>
				<form:option value="7.00" label="7.00 Cu.m"></form:option>
				</form:select></td>
				<td><form:errors path="capacity" cssClass="error" /></td>
			</tr>
			
			<tr>
				<td><b>Cylinder Damage status</b></td>
				<td><input type="radio" name="damage" value = "N" checked="checked">NO<br>
				<input type ="radio" name ="damage" value="Y" id="damy" >YES<br>				
			</tr>
			
			<form:hidden path="remark" value ="" id="dam"/>
			
			<tr align="center">
				<td></td>
				<td><input type="submit" name="Proceed" value="Proceed"
					class="btn btn-success" /></td>
			</tr>
		</table>
	</form:form>
</body>
</html>