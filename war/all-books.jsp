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
<input type="button" value="Show All books" onclick='ajaxResult("BookDetails", "book-result")'/>
<div id="book-result"></div>
<input type="button" value="Show All books JSON" onclick='jsonBookTable("BookDetails", "book-result-json")'/>
<div id="book-result-json"></div>
<input type="button" value="Show All books XML" onclick='ajaxResult("BookDetails", "book-result-xml")'/>
<div id="book-result-xml"></div>