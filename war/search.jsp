<%@ page import="com.google.appengine.api.datastore.DatastoreService" %>
<%@ page import="com.google.appengine.api.datastore.DatastoreServiceFactory" %>
<%@ page import="com.google.appengine.api.datastore.Entity" %>
<%@ page import="com.google.appengine.api.datastore.FetchOptions" %>
<%@ page import="com.google.appengine.api.datastore.Key" %>
<%@ page import="com.google.appengine.api.datastore.KeyFactory" %>
<%@ page import="com.google.appengine.api.datastore.Query" %>
<%@ page language="java" import="java.util.*,java.lang.*" %> 
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<h3>It is now <%= new java.util.Date() %></h3>

<form action="addbook" method="post">
	Search: <input type="text" name="frase">
	<input type="button" value="Search books" onclick='ajaxResult("BookDetails", "book-result")'/>
</form>
<div id="book-result"></div>