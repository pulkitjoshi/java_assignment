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
<a href="Book">BOOK</a>

</body>
</html>