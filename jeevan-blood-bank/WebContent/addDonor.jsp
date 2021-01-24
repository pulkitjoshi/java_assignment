<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous"> 
</head>
<body>
<header>
<%@include file="menu.jsp" %>
</header>
<div class="container">
<%
        String isUser=(String)session.getAttribute("user");
       
        //redirect user to home page if already logged in
        if(isUser==null){
        	response.sendRedirect("Login.jsp");
        }
       
        
        %>
<h2 style="text-align:center;color:green;"><c:out value="${message }"></c:out></h2>

<form action="donor" method="post">
 	<div>
		<label for="">Name</label>
		<input type="text" name="name" required>
	</div>
	<div>
		<label for="">Age</label>
		<input type="number" name="age" required>
	</div>
	<div>
	     <label for="gender">Select Gender:</label>
		  <select name="gender" id="gender">
		    <option value="male">male</option>
		    <option value="female">female</option>
		    <option value="other">other</option>
		  
		  </select>
	</div>
	
	<div>
		
		 <label for="bloodGroup">Blood Group</label>
		  <select name="bloodGroup" id="bloodGroup" required>
		    <option value="A+">A+</option>
		    <option value="A-">A-</option>
		    <option value="B+">B+</option>
		    <option value="B-">B-</option>
		    <option value="O+">O+</option>
		    <option value="O-">O-</option>
		    <option value="AB+">AB+</option>
		    <option value="AB-">AB-</option>
		  
		  </select>
	</div>
	
	<div>
		<label for="">Mobile Number</label>
		<input type="tel" name="number" required>
	</div>
	
	<div>
		<label for="">email</label>
		<input type="email" name="email" required>
	</div>
	<div>
		<label for="">Date of Birth</label>
		<input type="date" name="dob" required>
	</div>
	<div>
		<input type="submit" value="add" required>
	</div>
</form>
<h2 style="text-align:center;color:green;"><c:out value="${rowAdded }"></c:out></h2>
</div>
</body>
</html>