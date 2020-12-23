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
<form action="transection" method="post">
	<div>
		<label for="">Name</label>
		<input type="text" name="name" required>
	</div>
	<div>
	     <label for="plans">Select Gender:</label>
		  <select name="plans" id="plans" required>
		    <option value="500">ABC500</option>
		    <option value="1000">ABC1000</option>
		    <option value="1500">ABC1500</option>
		    <option value="2000">ABC2000</option>
		    <option value="3000">ABC3000</option>
		  
		  </select>
	</div>
	<input type="submit" value="BUY">
	

</form>

<p><c:out value="${message}"></c:out></p>
<p>Your Balance:= <c:out value="${amount}"></c:out></p>
</body>
</html>