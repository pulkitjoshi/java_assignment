<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    

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
background-color: #777;
color: #fff; }


</style>
</head>
<body>
<header>
<%@ include file="menu.jsp" %>
</header>

<h1 style="text-align:center">Blood Donor List</h1>
<table>
   <tr>
      <th>Name</th>
      <th>Age</th>
      <th>Gender</th>
      <th>Blood group</th>
      <th>Mobile Number</th>
      <th>Email</th>
      <th>Date of Birth</th>
    </tr>
<c:forEach items="${donorList}" var="eachDonor">
      <tr>
        <td><c:out value="${eachDonor.name}"/></td>
        <td><c:out value="${eachDonor.age}"/></td>
         <td><c:out value="${eachDonor.gender}"/></td>
          <td><c:out value="${eachDonor.bloodGroup}"/></td>
           <td><c:out value="${eachDonor.number}"/></td>
           <td><c:out value="${eachDonor.email}"/></td>
           <td><c:out value="${eachDonor.dob}"/></td>     
          </tr>
</c:forEach>
</table>

</body>
</html>