<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<head>
    
    <link href="${pageContext.request.contextPath}/resources/css/js-image-slider.css" rel="stylesheet" type="text/css" />
    <script src="${pageContext.request.contextPath}/resources/js/js-image-slider.js" type="text/javascript"></script>
</head>
<body>
    
    <div id="sliderFrame">
        <div id="slider">           
            <img src="${pageContext.request.contextPath}/resources/image/1.jpg" alt="" />
            <img src="${pageContext.request.contextPath}/resources/image/2.jpg" alt="" />
             <img src="${pageContext.request.contextPath}/resources/image/3.jpg" alt="" />
             <img src="${pageContext.request.contextPath}/resources/image/4.jpg" alt="" />
             <img src="${pageContext.request.contextPath}/resources/image/5.png" alt="" />
        </div>
    </div>  
</body>
</html>
