<%@ page language="java" contentType="text/html charset=US-ASCII"%> 
<%@ page import = "java.io.*,java.util.*" %>
<%@page import="java.util.Date" %> 
<%@page import="com.revature.webstore.models.User"%>
<%@page import="com.revature.webstore.services.InventoryService" %>
<%@page import="com.revature.webstore.dao.InventoryDao" %>
<%@page import="java.util.List" %>
<%@page import="com.revature.webstore.models.Inventory" %>
<%@page import="com.fasterxml.jackson.databind.ObjectMapper" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "https://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
    <meta charset="UTF-8" />
    <meta http-equiv="Content-Type" content="text/html; charset=US-ASCII" />
    <META HTTP-EQUIV="Expires" CONTENT="now" />
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <% response.setHeader("Cache-Control", "no-cache");
    response.setHeader("Cache-Control", "no-store");
    response.setHeader("Pragma", "no-cache"); response.setDateHeader("Expires",
    0); %> 
    <title>Web Store</title>
    <link
      rel="stylesheet"
      href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css"
    />
    <link rel="stylesheet" type="text/css" href="css/styles.css" />
  </head>
  
    <% InventoryService inventoryService = new InventoryService(new InventoryDao()); %>
    <% List<Inventory> inventories = inventoryService.getAllInventories(); %>
    <% ObjectMapper objmap = new ObjectMapper(); %>
    <% String jsonString = jsonString = objmap.writeValueAsString(inventories); %>
  
  <%
   // Get session creation time.
   Date createTime = new Date(session.getCreationTime());
   
   // Get last access time of this Webpage.
   Date lastAccessTime = new Date(session.getLastAccessedTime());

   String title = "Welcome Back to my website";
   Integer visitCount = new Integer(0);
   String visitCountKey = new String("visitCount");
   String userIDKey = new String("userID");
   String userID = new String("ABCD");

   // Check if this is new comer on your Webpage.
   if (session.isNew() ){
      title = "Welcome to my website";
      session.setAttribute(userIDKey, userID);
      session.setAttribute(visitCountKey,  visitCount);
   } 
   visitCount = (Integer)session.getAttribute(visitCountKey);
   visitCount = visitCount + 1;
   userID = (String)session.getAttribute(userIDKey);
   session.setAttribute(visitCountKey,  visitCount);
%>

  <body>
  
  <!--<p><%= inventories.toArray().length %></p>-->
  <!--<p>Message: <%= jsonString %></p>-->  
  <!--<p>The current date is: <%= new Date() %></p>-->
    
    <!-- Header -->
    <div class="header">
      <% User user = (User) session.getAttribute("webstore_current_user"); %> <%
      if (user == null) { %>
      <h1>Welcome to WebStore!</h1>
      <% } else {%>
      <h1>Welcome to WebStore!</h1>
      <h6>[ <%= user.getfName() %> <%= user.getlName() %> ]</h6>
      <% } %>
      <p><b>JSP</b></p>
    </div>

    <!-- Navigation Bar -->
    <div class="topnav">
      <a class="active topnav__link" href="#home">Home</a>
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

    <!-- The flexible grid (content) -->
    <div class="main-container">
      <div class="side">
        <!-- Left Side menu Categories -->
        <div class="vertical-menu">
          <a id=0 href="#" class="vertical-menu__link vertical-menu__link_active">All Products</a>
          <a id=1 href="#" class="vertical-menu__link ">Music</a>
          <a id=2 href="#" class="vertical-menu__link">Soft</a>
          <a id=3 href="#" class="vertical-menu__link">Games</a>
        </div>
      </div>
      <section class="content">
      
      
       <%!
          public void  sendMail() {    
          
         }
       %>
      
      <% for(Inventory inventory : inventories) { %>
       <div class="card">
          <img 
            class="card__img"
            src=""
            alt="<%= inventory.getCategory() %>" />          
          <h3 class="card__title"><%= inventory.getProduct() %></h3>
          <h5 class="card__title"><%= inventory.getManufacturer() %></h5>
          <p class="card__price">$<%= inventory.getPrice() %></p>
          <p class="card__desc">
            Some text about the jeans. Super slim and comfy lorem ipsum lorem
            jeansum. Lorem jeamsun denim lorem jeansum.
          </p>
          <p><button class="card__button" onclick="<% sendMail(); %>">Add to Cart</button></p>
           <p hidden><%= inventory.getProductId() %></p>
          <p hidden><%= inventory.getCategoryId() %></p>
        </div>
     <% } %>
      
       
        
        

      </section>
    </div>

    <!-- Footer -->
    <div class="footer">
      <p>Copyright Revature Java-React © 2022. All rights reserved.</p>
    </div>
    <script src="js/main.js"></script>
  </body>
</html>
