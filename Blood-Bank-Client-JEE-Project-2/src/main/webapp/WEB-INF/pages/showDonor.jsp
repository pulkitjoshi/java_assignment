<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
  <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<style>
table{
		
		text-align:center;
		
		width : 100%;
		border: 1px solid black;

	}
th,td{
		
		text-align:center;
		
	

	}
table tr:nth-child(odd) {
background-color: rgb(255, 0, 0);
color: #fff; }


</style>

</head>
<body>
<header>
<%@include file="menu.html" %>
</header>


<p>
  <a style="float: right;position:relative;right:10%" class="btn btn-primary" data-toggle="collapse" href="#collapseExample" role="button" aria-expanded="false" aria-controls="collapseExample">
    Filter
  </a>
 

<div class="container" class="collapse show" id="collapseExample">
 <div align="center" class="card card-body">
        <h2>User Registration</h2>
        
        <form:form action="filter" method="post" class="form-horizontal" >
        
         
           
            <div class="form-group row">
		<label for="" class="col-sm-2 col-form-label">Donor Type</label>
	
	        <div class="col-sm-7">
 			<form:select path="eligibilty" Class="form-control">
 			<form:option value="" disabled="true" selected="true" hidden="true">Choose here</form:option>
 			<form:option value="all">All</form:option>
 			<form:option value="eligible">Eligible</form:option>
 			
 			</form:select>
 			 </div>
                    
               </div>  
            
            <div class="form-group row">
             <label class="col-sm-2 col-form-label">City:</label>
             <div class="col-sm-7">
            <form:input value="" path="donorCity" class="form-control"/>
            </div>
             </div>
            
            
         
            
            
            <div class="form-group row">
		<label for="" class="col-sm-2 col-form-label">Blood Group</label>
	
	        <div class="col-sm-7">
 			<form:select path="donorBloodGroup" Class="form-control">
 			<form:option value="" disabled="false" selected="true" hidden="true">Choose here</form:option>
 			<form:option value="">All Group</form:option>
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
            <input type="submit" value="Filter">
        </form:form>
    </div>

</div>





<h1 style="text-align:center;text-transform: uppercase;">Donors List</h1>
<table style="text-align: center;text-transform: uppercase;">
   <tr>
      <th style="text-align:center">Name</th>
      <th style="text-align:center">Address</th>
      <th style="text-align:center">City</th>
      <th style="text-align:center">Mobile Number</th>
      <th style="text-align:center">Date of Birth</th>
      <th style="text-align:center">Blood Group</th>
      <th style="text-align:center">Last Donate Date</th>
    </tr>
<c:forEach var="eachDonor" items="${donor}" >
          
           <tr>
        <td><c:out value="${eachDonor.donorName}"/></td>
        <td><c:out value="${eachDonor.donorAddress}"/></td>
         <td><c:out value="${eachDonor.donorCity}"/></td>
          <td><c:out value="${eachDonor.donorMobileNumber}"/></td>
           <td><c:out value="${eachDonor.donorDateofBirth}"/></td>
           <td><c:out value="${eachDonor.donorBloodGroup}"/></td>
           <td><c:out value="${eachDonor.lastDonateDate}"/></td>     
          </tr>
      </c:forEach>
</table>

</body>
</html>