<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %> 
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
        
        <form:form action="submit" method="post" class="form-horizontal" >
        
          <div class="form-group row">
            <label class="col-sm-2 col-form-label">Full name:</label>
            <div class="col-sm-7">
            <form:input path="donorName"  class="form-control"/>
            </div>
             </div>
             
             <div class="form-group row">
             <label class="col-sm-2 col-form-label">Address:</label>
              <div class="col-sm-7">
            <form:textarea path="donorAddress" cols="25" rows="5" class="form-control"/>
             </div>
             </div>
            
            <div class="form-group row">
             <label class="col-sm-2 col-form-label">City:</label>
             <div class="col-sm-7">
            <form:input path="donorCity" class="form-control"/>
            </div>
             </div>
            
            
            <div class="form-group row">
            <label class="col-sm-2 col-form-label">MobileNumber:</label>
            <div class="col-sm-7">
            <form:input path="donorMobileNumber" class="form-control"/>
            </div>
             </div>
            
            
            <div class="form-group row">
            <label class="col-sm-2 col-form-label">Birthday (yyyy-mm-dd):</label>
            <div class="col-sm-7">
            <form:input type="date" path="donorDateofBirth" class="form-control"/>
            </div>
             </div>
            
            
            <div class="form-group row">
		<label for="" class="col-sm-2 col-form-label">Blood Group</label>
	
	        <div class="col-sm-7">
 			<form:select path="donorBloodGroup" Class="form-control">
 			<form:option value="" disabled="true" selected="true" hidden="true">Choose here</form:option>
 			<form:option value="A+">A+</form:option>
 			<form:option value="A-">A-</form:option>
 			<form:option value="B+">B+</form:option>
 			<form:option value="B-">B-</form:option>
 			<form:option value="O+">O+</form:option>
 			<form:option value="O-">O-</form:option>
 			<form:option value="AB+">AB+</form:option>
 			<form:option value="AB-">AB-</form:option>
 			</form:select>
 			 </div>
             </div>
	
	
            
             
             
             
             
             
            <form:hidden path="lastDonateDate" value="1947-08-15" class="form-control"/>
           
            
            
                 
            <input type="submit" value="Registration">
        </form:form>
    </div>

</div>








</body>
</html>