<%-- //[START all]--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.google.appengine.api.users.User" %>
<%@ page import="com.google.appengine.api.users.UserService" %>
<%@ page import="com.google.appengine.api.users.UserServiceFactory" %>
<%-- //[START imports]--%>
<%@ page import="com.google.appengine.api.datastore.DatastoreService" %>
<%@ page import="com.google.appengine.api.datastore.DatastoreServiceFactory" %>
<%@ page import="com.google.appengine.api.datastore.Entity" %>
<%@ page import="com.google.appengine.api.datastore.FetchOptions" %>
<%@ page import="com.google.appengine.api.datastore.Key" %>
<%@ page import="com.google.appengine.api.datastore.KeyFactory" %>
<%@ page import="com.google.appengine.api.datastore.Query" %>
<%-- //[END imports]--%>
<%@ page import="java.util.List" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Book Service</title>
		<script src="./scripts/ajax-books.js" type="text/javascript"></script>
	</head>
	<body>
		<fieldset>
			<legend>Show All books</legend>
			<input type="button" value="Show All books"
			       onclick='ajaxResult("All", "book-result")'/>
		  <div id="book-result"></div>
		</fieldset>
	  
	  
	  
	  
	  <h1>Hello App Engine!</h1>
	
	  <table>
	    <tr>
	      <td colspan="2" style="font-weight:bold;">Available Servlets:</td>        
	    </tr>
	    <tr>
	      <td><a href="bookService">Book service</a></td>
	    </tr>
	  </table>
	</body>
</html>

<%-- //[END all]--%>