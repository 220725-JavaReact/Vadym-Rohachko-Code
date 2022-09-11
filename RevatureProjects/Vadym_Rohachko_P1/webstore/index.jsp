<%@ page language="java" contentType="text/html charset=US-ASCII" %> 

<%-- @ page language="java" contentType="text/html charset=US-ASCII" session="false" --%> 

<%@ page import = "java.io.*,java.util.*" %>
<%@ page import="java.util.Date" %> 

<%@ page import="com.revature.webstore.models.*"%>
<%@ page import="com.revature.webstore.models.Inventory" %>
<%@ page import="com.revature.webstore.services.InventoryService" %>
<%@ page import="com.revature.webstore.dao.InventoryDao" %>
<%@ page import="com.fasterxml.jackson.databind.ObjectMapper" %>
<%@ page import="com.revature.webstore.utils.Logger" %>
<%@ page import="java.util.Enumeration" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "https://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
  <%--session.invalidate(); --%>  
 
  <%-- IF CUSTOMER LOGGED IN, THEN REDIRECT --%>
  <% try{  
   if(String.valueOf(session.getAttribute("currCust")) != null && String.valueOf(session.getAttribute("currCust")) != ""){%> 
   <meta http-equiv="refresh" content="2;URL='/webstore/store'" /> 
   <% }}catch(Exception e){e.printStackTrace();} %>
      
    <meta charset="UTF-8" />
    <meta http-equiv="Content-Type" content="text/html; charset=US-ASCII" />
    <META HTTP-EQUIV="Expires" CONTENT="now" />
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <% response.setHeader("Cache-Control", "no-cache");
    response.setHeader("Cache-Control", "no-store");
    response.setHeader("Pragma", "no-cache"); 
    response.setDateHeader("Expires",0); %> 
    <title>Web Store</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css" />
    <link rel="stylesheet" type="text/css" href="css/styles.css" />
    <script src='js/jquery_ajax.js'></script>
  </head>  	
    
    <%-- data for displaying cards --%>
    <%! InventoryService inventoryService = new InventoryService(new InventoryDao()); %>
    <%! List<Inventory> inventories = inventoryService.getAllInventories(); %>
            
   <%-- getting contents of the unreg cart 
   <% List<Inventory> uInventory = (List<Inventory>) session.getAttribute("unregInventoryKey"); %>
   <% if(uInventory == null) {   
					List<Inventory> unregInventory = new ArrayList<Inventory>();
					session.setAttribute("unregInventoryKey", unregInventory);
					uInventory = (List<Inventory>) session.getAttribute("unregInventoryKey"); } %>--%> 
					
  <body>
  
   <h4>session.getId() = <%= session.getId()%></h4>
   <h4>currCust = <%= session.getAttribute("currCust") %></h4>
   
  <%  Enumeration keys = session.getAttributeNames();
					   while (keys.hasMoreElements())
					   {
					     String key = (String)keys.nextElement(); %>
					     <p>item = <%= session.getValue(key)%> <br>
					 <%  } %>
 
   
    <!-- Header -->
    <header class="header">
    <h1>Welcome to Webstore!</h1>
    <h4>[ unregistered user ]</h4>
    </header>

    <!-- Navigation Bar -->
    <div class="topnav">
      <a class="active topnav__link" >Home</a>
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
        <a class="topnav__link" href="/webstore/reg">Register</a>
        <a class="topnav__link" href="/webstore/log">Login</a>
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
            
      <% for(Inventory inventory : inventories) { %>
       <form class="card">
          <img class="card__img" src="" alt="<%= inventory.getCategory() %>" />          
          <h3 class="card__title"><%= inventory.getProduct() %></h3>
          <h5 class="card__title"><%= inventory.getManufacturer() %></h5>
          <p class="card__price">$<%= inventory.getPrice() %></p>
          <p class="card__desc">
            Some text about the jeans. Super slim and comfy lorem ipsum lorem
            jeansum. Lorem jeamsun denim lorem jeansum.
          </p>
          <p><button name="unreg" type="submit" class="card__button card__button_type_submit">Add to Cart</button></p>
          <input class="card__product" type="text"  name="productId" value= <%= inventory.getProductId() %> hidden>
	      <input class="card__category" type="text"  name="categoryId" value= <%= inventory.getCategoryId() %>  hidden>           
          <p hidden><%= inventory.getCategoryId() %></p>
        </form>
     <% } %> 
     

      </section>
    </div>

    <!-- Footer -->
    <footer class="footer">
      <p>Copyright Minimalist Design Studio &copy 2022. All rights reserved.</p>
    </footer>
    <script src="js/main.js"></script>
  </body>
</html>
