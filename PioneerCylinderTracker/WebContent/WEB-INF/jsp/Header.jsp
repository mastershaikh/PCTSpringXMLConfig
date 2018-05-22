<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap-theme.min.css">
<style>
    .black_overlay{
        display: none;
        position: absolute;
        top: 0%;
        left: 0%;
        width: 100%;
        height: 100%;
        background-color: black;
        z-index:1001;
        -moz-opacity: 0.8;
        opacity:.80;
        filter: alpha(opacity=80);
    }
    .white_content {
        display: none;
        position: absolute;
        top: 25%;
        left: 25%;
        width: 50%;
        height: 50%;
        padding: 16px;
        border: 16px solid orange;
        background-color: white;
        z-index:1002;
        overflow: auto;
    }
.dropbtn {
    background-color: #4CAF50;
    color: white;
    padding: 16px;
    font-size: 16px;
    border: none;
}

.dropdown {
    position: relative;
    display: inline-block;
}

.dropdown-content {
    display: none;
    position: absolute;
    background-color: #f1f1f1;
    min-width: 160px;
    box-shadow: 0px 8px 16px 0px rgba(0,0,0,0.2);
    z-index: 1;
}

.dropdown-content a {
    color: black;
    padding: 12px 16px;
    text-decoration: none;
    display: block;
}

.dropdown-content a:hover {background-color: #ddd}

.dropdown:hover .dropdown-content {
    display: block;
}

.dropdown:hover .dropbtn {
    background-color: #3e8e41;
}
</style>
</head>
<body>
<div class="tnav">
<div class="navbar navbar-fixed-top" style="background-color:purple; height:50px;">

      <ul class="nav navbar-nav pull-left">
      <li><div class="dropdown">
  <button class="dropbtn">Register</button>
  <div class="dropdown-content">
  <a href="${pageContext.request.contextPath}/CylinderRegister">Cylinder Register</a>
    <a href="${pageContext.request.contextPath}/dealerRegistration" >Dealer Register</a>
    
  </div>
</div>
        </li>
              
        </ul>

  
  <ul class="nav navbar-nav pull-right">
      <li><div class="dropdown">
  <button class="dropbtn">Dealer Transaction</button>
  <div class="dropdown-content">
    <a href="${pageContext.request.contextPath}/dealerDetails">Dealer Txn</a>
    <a href="${pageContext.request.contextPath}/dealerSales">Dealer Total Sales</a>
  </div>
</div>
        </li>
        
        <li><div class="dropdown">
  <button class="dropbtn">Track Cylinder</button>
  <div class="dropdown-content">
    <a href="${pageContext.request.contextPath}/FindCylinder">Find Cylinder</a>
  </div>
</div>
        </li>
        <li><div class="dropdown">
  <button class="dropbtn">Outstanding</button>
  <div class="dropdown-content">
    <a href="${pageContext.request.contextPath}/GenerateOutstanding" >Dealer Outstanding</a>
  <a href="${pageContext.request.contextPath}/GenerateCylinderOutstanding" >Cylinder Outstanding</a>
  </div>
</div>
        </li>
        
        <li><div class="dropdown">
  <button class="dropbtn">Re-Print Invoice</button>
  <div class="dropdown-content">
    <a href="${pageContext.request.contextPath}/ReprintInvoice" >Print Invoice</a>
  </div>
</div>
        </li>
        
        <li><div class="dropdown">
  <button class="dropbtn">ECR</button>
  <div class="dropdown-content">
    <a href="${pageContext.request.contextPath}/GenerateECR" >Generate ECR</a>
    <%-- <a href="${pageContext.request.contextPath}/RectifyECR" >ECR Correction</a> --%>
  </div>
</div>
        </li>
        
         <li><div class="dropdown" style="color: red" >
  <button class="dropbtn">Invoice Correction</button>
  <div class="dropdown-content">
    <a href="${pageContext.request.contextPath}/RectifyCylinder">Company Cylinder</a>
    <a href="${pageContext.request.contextPath}/RectifyLorry" >Lorry</a>
    <%-- <a href="${pageContext.request.contextPath}/RectifyCylinder" >Dealer Invoice Cylinder Correction</a> --%>
   </div>
</div></li>

        </ul>
  </div>
    </div>
    <div id="fade" class="black_overlay"></div>
</body>
</html>