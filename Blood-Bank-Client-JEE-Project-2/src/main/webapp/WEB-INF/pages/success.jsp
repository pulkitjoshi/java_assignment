<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<style type="text/css">

p{
   color:rgb(255, 0, 0);
   font-size:50px;
	text-align:center;
    position:relative;
    top:80px;
}

</style>

</head>
<body>
<header>
<%@include file="menu.html" %>
</header>
<p><c:out value="${added} "></c:out></p>
</body>
</html>