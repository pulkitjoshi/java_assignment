<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<p>Session Id : </p><c:out value="${pageContext.session.id}"></c:out>
<p>Session is New : </p><%=session.isNew() %>
<hr>
<span>from Application </span><c:out value="${warning} "></c:out>
<span>from Session </span><c:out value="${message} "></c:out>
<span>from Request </span><c:out value="${info} "></c:out>

<a href="third.jsp">Next</a>

</body>
</html>