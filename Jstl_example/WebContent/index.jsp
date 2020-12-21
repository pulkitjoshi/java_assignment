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
<header>
<%@ include file="menu.html" %>
</header>

<c:set var="heading" value="Horizon College" scope="page"></c:set>
<h1 style="text-align:center;"><c:out value="${heading } "></c:out></h1>
<form action="student" method="get">
  <div>
  		<lable for="">Branch</lable>
  		<select name="deptName">
  				<option value="csc">Computer Science</option>
  				<option value="ece">Electronics</option>
  		</select>
  
  </div>
  <div>
  	<input type="submit" value="Search"/>
  </div>

</form>

<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>