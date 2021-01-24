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
   
      <th style="text-align:center">Camp Id</th>
      <th style="text-align:center">Camp Name</th>
      <th style="text-align:center">Location</th>
      <th style="text-align:center">Start Date</th>
      <th style="text-align:center">End Date</th>
      <th style="text-align:center">Register</th>
   <th style="text-align:center">View List</th>
     
    </tr>
<c:forEach var="eachCamp" items="${camps}" >
          
         <tr>
                    
        <td><c:out value="${eachCamp.campId}"/></td>
        <td><c:out value="${eachCamp.campName}"/></td>
         <td><c:out value="${eachCamp.campLocation}"/></td>
          <td><c:out value="${eachCamp.campStartDate}"/></td>
           <td><c:out value="${eachCamp.campEndDate}"/></td>
             <td>
         <form:form action="registerCampId" method="post" class="form-horizontal" >
          <form:hidden path="campId" value="${eachCamp.campId}" class="form-control"/>
         <button style="color:blue;font-style: italic;text-decoration:underline;" type="submit">Register</button>
         </form:form>
         </td> 
         <td>
         <form:form action="viewCampId" method="post" class="form-horizontal" >
          <form:hidden path="campId" value="${eachCamp.campId}" class="form-control"/>
         <button style="color:blue;font-style: italic;text-decoration:underline;"  type="submit">View</button>
         </form:form>
         </td>    
            
          </tr>
      </c:forEach>
</table>


</body>
</html>