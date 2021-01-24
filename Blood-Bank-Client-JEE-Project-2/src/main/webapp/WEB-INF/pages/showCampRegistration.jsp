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
	
button {
    background-color: Transparent;
    background-repeat:no-repeat;
    border: none;
    cursor:pointer;
    overflow: hidden;
    outline:none;
    
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

<h1 style="text-align:center;text-transform: uppercase;">Camp List</h1>
<table style="text-align: center;text-transform: uppercase;">
   <tr>
   
      <th style="text-align:center">S.No.</th>
     
      <th style="text-align:center">Donor Name</th>
      <th style="text-align:center">Mobile Number</th>
      <th style="text-align:center">Camp Id</th>
     
    </tr>
<c:forEach var="eachCamp" items="${campRegistration}" varStatus="status">
          
         <tr>
                    
        <td><c:out value="${status.count}"/></td>
        <td><c:out value="${eachCamp.donorName}"/></td>
         <td><c:out value="${eachCamp.donorMobileNumber}"/></td>
          <td><c:out value="${eachCamp.donationCampId}"/></td>
          
             <td>
        
            
          </tr>
      </c:forEach>
</table>



</body>
</html>