<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>

</head>
<body>
<header>
<%@include file="menu.html" %>
</header>
<div class="container">
 <div align="center">
        <h2>User Registration</h2>
        
        <form:form action="campDonorRegistration" method="post" class="form-horizontal" >
        
          <div class="form-group row">
            <label class="col-sm-2 col-form-label">Full name:</label>
            <div class="col-sm-7">
            <form:input path="donorName"  class="form-control"/>
            </div>
             </div>
             
            
          
            
            
            <div class="form-group row">
            <label class="col-sm-2 col-form-label">MobileNumber:</label>
            <div class="col-sm-7">
            <form:input path="donorMobileNumber" class="form-control"/>
            </div>
             </div>
            
            
            
            
          
            
             
             
             
             
             
            <form:hidden path="donationCampId" value="${campId}" class="form-control"/>
           
            
            
                 
            <input type="submit" value="Registration">
        </form:form>
    </div>

</div>

</body>
</html>