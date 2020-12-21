<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="java.util.ArrayList,com.example.demo.model.Student"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<header>
<%@ include file="menu.html" %>
</header>
<p>Following Details Are Added</p>
<c:out value="${param.rollNumber }" var="rollNumber"></c:out>
<c:out value="${param.studentName }" var="studentName"></c:out>
<c:out value="${param.markScored }" var="markScored"></c:out>

<jsp:forward page="showStudent.jsp">
<jsp:param value="${rollNumber}" name="number"/>
</jsp:forward>

<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>