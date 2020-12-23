<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h1 style="text-align:center">Login</h1>
<form action="validation" method="post">

	<label for="">User Name: </label>
	<input type="text" name="name" required>
	<label for="">PassWord : </label>
	<input type="password" name="password" required>
	
    <input type="submit" value="Login">

</form>
</body>
</html>