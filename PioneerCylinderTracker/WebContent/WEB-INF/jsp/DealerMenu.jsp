<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
  <script src="${pageContext.request.contextPath}/resources/js/jquery.min.js" type="text/javascript"></script>
  <script src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js" type="text/javascript"></script> 

<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap-theme.min.css" type="text/css">

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Dealer Menu</title>

</head>
<body>
<div class="container" style="background-image: url(resources/images/home.jpg); height: 600px; width: 150px; border: 1px solid black; ">
<div class="dropdown">
  <button type="button" class="btn btn-warning dropdown-toggle" data-toggle="dropdown" style="margin-top:80px;width:130px;">
   Dealer Details  <span class="caret"></span>
   
  </button>
<ul class="dropdown-menu" role="menu">
    <li><a href="${pageContext.request.contextPath}/CylinderBuy">Buy Cylinders</a></li>
  </ul>
  
</div>

<div class="dropdown">
  <button type="button" class="btn btn-warning dropdown-toggle" data-toggle="dropdown" style="margin-top:80px;width:130px;">
   Cylinder Details  <span class="caret"></span>
  </button>
<ul class="dropdown-menu" role="menu">
	<li><a href="${pageContext.request.contextPath}/CylinderLists">Transaction List</a></li>
    <li><a href="${pageContext.request.contextPath}/CylinderReturn">Return Cylinders</a></li>
  </ul>
  
</div>
</div>
</body>
</html>