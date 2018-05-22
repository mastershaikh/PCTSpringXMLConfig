<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<%@ page import="java.io.*,java.util.*,java.sql.*"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script src="${pageContext.request.contextPath}/resources/js/jquery.min.js" type="text/javascript"></script>
  <script src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js" type="text/javascript"></script>

<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap-theme.min.css">

<title>Return cylinders</title>
</head>
<body>
${msg}
	<form:form action="CylinderLists" method="post" commandName="ret"
		id="commentForm">
		<center>
				<h1>Buy Cylinders</h1>
				
<form:hidden path="dealerId" value='${sessionScope.dealerId}'></form:hidden>
			<table>
				<tr>
					<td>Dealer Name:</td>
					<td><form:input path="dealerName" maxlength="15" value='${sessionScope.dealerName}' readonly="true"/></td>
				</tr>
           <tr><td>Here is the total list</td>
            <td><form:select path="transactionId">
    			<c:forEach items="${list}" var="tx">
        		<form:option value="${tx.key}">${tx.value}</</form:option>
    			</c:forEach>
				</form:select></td>
				<tr>
					<td></td>
					<td><input type="submit" name="Show" value="Show"
						class="btn btn-success" /></td>
				</tr>
				</table>
				
				<!-- <div id='tab1' class="tab_content" style="display: block; width: 100%"  align="center">
            <h3>:::::::::::::List of all Transactions::::::::::::: </h3>
            <display:table name="list" pagesize="5" 
                           export="true" sort="list" uid="one" requestURI="/PioneerCylinderTracker/printCylinderList"  >
               <display:column property="cylinderId" title=""cylinderId"" 
                                sortable="true" headerClass="sortable"  />
       
                <display:column property="capacity" title=""capacity""
                                sortable="true" headerClass="sortable" />
                <display:column property="cylinderType" title="cylinderType  "
                                sortable="true" headerClass="sortable" />
                <display:column property="usageStatus" title="DealerID"
                                sortable="true" headerClass="sortable" />
                                
            <display:setProperty name="export.excel.filename" value="CylinderDetails.xls"/>
            <display:setProperty name="export.pdf.filename" value="CylinderDetails.pdf"/>
            <display:setProperty name="export.pdf" value="true" />             
            </display:table>        
        </div>  -->
			
		</center>
		<center><b><a href="Index.jsp">Home</a></b></center>
	</form:form>
</body>
</html>