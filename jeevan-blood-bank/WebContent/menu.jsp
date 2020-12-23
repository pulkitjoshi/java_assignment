<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
  <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
 
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<style>
ul>li{

display:inline;

margin: 10px 10px;

border: 1px solid yellow;

border-radius :5px;
}
</style>
</head>
<body>

 <%
        String user=(String)session.getAttribute("user");
        String address=null;
        String tag =null;
        String logout=null;
        boolean login = false;
        //redirect user to home page if already logged in
        if(user!=null){
           address = "addDonor.jsp";
           tag = "Add Donor";
           logout ="Log Out";
           login=true;
        }
        else
        {
        	address = "Login.jsp";
            tag = "Login";
        }
        
        %>
 
 <ul>
	<li><a href=<%=address %>><%=tag %> </a></li> 
 	<li><a href="donor">View Donor </a></li>
 	<li ><a href="Logout.jsp"><c:if test="${user!=null}"><%=logout %></c:if></a></li> 
</ul>
</body>
</html>