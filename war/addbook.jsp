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
<%
    DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
    //Key BookDetailsKey = KeyFactory.createKey("BookDetails", 415);
    Query q = new Query("BookDetails");
    List <Entity> bookStored = datastore.prepare(q).asList(FetchOptions.Builder.withLimit(5));
    if (bookStored.isEmpty()){
    %>
    <p>No books in database</p>
    <% 	
    } else {
	%>
	<p>Some books in database</p>
	<%
    }
%>
Time to add some book :)<br/>
<form action="addbook" method="post">
<ul>
	<li>Author: <input type="text" name="author">
	<li>Title: <input type="text" name="title">
	<li>ISBN: <input type="text" name="isbn" maxlength="13">
	<li><input type="submit">
</ul>
</form>