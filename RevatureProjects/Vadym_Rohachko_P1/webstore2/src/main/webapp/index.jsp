<%@ page language="java" contentType="text/html; charset=US-ASCII" pageEncoding="US-ASCII"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "https://www.w3.org/TR/html4/loose.dtd">

<%@ page 
  import="com.revature.webstore2.models.User,
  com.revature.webstore2.services.UserService,
  com.revature.webstore2.dao.UserDao"
%>

<html>
<head>

<%! 
UserService userService = new UserService(new UserDao());
%>

<% if(userService.isLoggedin()){%> 
<meta http-equiv="refresh" content="0;URL='/webstore2/shopping'" /> 
<%}%>

<% response.setHeader("Cache-Control", "no-cache");
    response.setHeader("Cache-Control", "no-store");
    response.setHeader("Pragma", "no-cache"); 
    response.setDateHeader("Expires",0); 
%> 
    
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
<title>Login Success Page</title>
<link rel="stylesheet" type="text/css" href="css/styles.css" />
</head>
<body> 
<%--
	<div style="margin-left:100px">
    <p>SessionId = <%= session.getId()%></p>
    <p>userService.isLoggedin() = <%=  userService.isLoggedin()%></p>
   </div>
--%>
   
    <!-- Header -->
    <header class="header">
    <h1>Welcome to Webstore!</h1>
    </header>

    <!-- Navigation Bar -->
    <div class="topnav">
      <a class="active topnav__link" >Home</a>
      <a class="topnav__link" href="#news">News</a>
      <a class="topnav__link" href="#contact">Contact</a>
      <div class="topnav topnav_position_right">       
        <a class="topnav__link" href="/webstore2/register">Register</a>
        <a class="topnav__link" href="/webstore2/login">Login</a>
      </div>
    </div>
    
    <div class="container_1">
    	<p>Login or Register, please</p>
    </div>    

    <!-- Footer -->
    <footer class="footer">
      <p>Copyright Minimalist Design Studio &copy 2022. All rights reserved.</p>
    </footer>
    
    <script src="js/main.js"></script>
  </body>
</html>
