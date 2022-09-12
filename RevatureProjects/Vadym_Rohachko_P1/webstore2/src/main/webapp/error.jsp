<%@ page language="java" contentType="text/html; charset=US-ASCII"
    pageEncoding="US-ASCII" isErrorPage="true"%>

<%@page import="com.revature.webstore.models.User"%>
<%@page import="java.util.List" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "https://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
<link rel="stylesheet" type="text/css" href="css/styles.css" />
<title>Error Page</title>
    
</head>
<body>
    <!-- Header 
    <div class="header">
      <h1 style="text-align:center"><b>Page 404</b></h1>
      <p><b>JSP</b></p>
    </div>-->
    
        <!-- Header -->
    <div class="header">
    	<h1 ><b>Webstore</b></h1>
    </div>
    
    <!-- Navigation Bar -->
    <div class="topnav">
      <a class="active topnav__link" href="/webstore2/">Home</a>
      <a class="topnav__link" href="#news">News</a>
      <a class="topnav__link" href="#contact">Contact</a>
      <div class="dropdown">
        <button class="dropdown__dropbtn">
          Categories
          <i class="fa fa-caret-down"></i>
        </button>
        <div class="dropdown__content">
          <a class="topnav__link dropdown__link" href="#">Music</a>
          <a class="topnav__link dropdown__link" href="#">Soft</a>
          <a class="topnav__link dropdown__link" href="#">Games</a>
        </div>
      </div>
      <div class="topnav topnav_position_right">
        <a class="topnav__link" href="/webstore/cart">Cart</a>
        <a class="topnav__link" href="#"> </a>
        <% if (user == null) { %>
        <a class="topnav__link" href="/webstore/reg">Register</a>
        <a class="topnav__link" href="/webstore/log">Login</a>
        <% } else {%>      
        <a class="topnav__link" href="/webstore/logout">Logout</a>
        <% } %>
      </div>
    </div>
    
    <div class="error-img-container">    	
            <img class="error-img-container__img" alt="404"
            src='<%= request.getContextPath() + "/img/404.jpg" %>'>
    </div>
    
    <!-- Footer -->
    <div class="footer">
      <p>Inspired by w3school.</p>
    </div>
    <script src="js/main.js"></script>
    
</body>
</html>