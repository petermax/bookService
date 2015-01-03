package com.bookService;

import org.json.*;
import org.json.simple.JSONValue;
import org.json.simple.parser.*;
import org.mortbay.util.ajax.JSON.Generator;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;



public class BookList extends HttpServlet {
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setHeader("Cache-Control", "no-cache");
	    response.setHeader("Pragma", "no-cache");
	    String format = getServletName();
	    
	    DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
	    // Use class Query to assemble a query
	 	Query q = new Query("BookDetails");

	 	// Use PreparedQuery interface to retrieve results
	 	PreparedQuery pq = datastore.prepare(q);
	 	
	 	Collection <Book123> Books = new ArrayList<Book123>();
	 	
	 	
	    
	    for (Entity result : pq.asIterable()) {
	    	String ID = (String) result.getProperty("name");
	    	String author = (String) result.getProperty("author");
	    	String title = (String) result.getProperty("title");
			String ISBN = (String) result.getProperty("isbn");
			Books.add(new Book123(ID,author,title,ISBN));
		}
	    
	    if ("xml".equals(format)) {
	 		response.setContentType("text/xml");
	      } else if ("json".equals(format)) {
	    	  response.setContentType("text/javascript");
	    	  Gson gson = new Gson();
	    	  String json = gson.toJson(Books);
	    	  response.getWriter().println(json);
	      } else {
	    	  response.setContentType("text/plain");
	    	  response.getWriter().println(Books);
	      }

	    /*for (int j = 0 ; j < Books.size() ; j++){
	    	Book123 v = ((ArrayList<Book123>) Books).get(j);
	    	response.getWriter().println(v.getAuthor());
	    	response.getWriter().println(v.getTitle());
	    	response.getWriter().println(v.getIsbn());
	    	response.getWriter().println("<BR>");
	    }*/
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
}
