<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
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
<form action="handleStudent.jsp">

	<div>
		<label for="">Roll Number</label>
		<input type="text" name="rollNumber">
	</div>
	<div>
		<label for="">Student Name</label>
		<input type="text" name="studentName">
	</div>
	<div>
		<label for="">Mark Scored</label>
		<input type="text" name="markScored">
	</div>
	<div>
		<input type="submit" value="add">
	</div>
</form>
<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>