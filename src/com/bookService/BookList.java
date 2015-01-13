package com.bookService;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.Filter;
import com.google.appengine.api.datastore.Query.FilterOperator;
import com.google.appengine.api.datastore.Query.CompositeFilterOperator;
import com.google.gson.Gson;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

public class BookList extends HttpServlet {
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Query q;
		response.setHeader("Cache-Control", "no-cache");
	    response.setHeader("Pragma", "no-cache");
	    String format = request.getParameter("format");
	    String SearchPhrase = request.getParameter("book");
	    DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
	    
	    if (SearchPhrase.equals("BookDetails")){
	    	q = new Query("BookDetails");
	    } else {
	    	Filter SearchString = CompositeFilterOperator.or(FilterOperator.EQUAL.of("author", SearchPhrase),FilterOperator.EQUAL.of("title", SearchPhrase),FilterOperator.EQUAL.of("isbn", SearchPhrase));
		    q = new Query("BookDetails").setFilter(SearchString);
	    }
		
		// Use PreparedQuery interface to retrieve results
	 	PreparedQuery pq = datastore.prepare(q);
	 	
	 	Collection <Book123> Books = new ArrayList<Book123>();
	 	   
	    for (Entity result : pq.asIterable()) {
	    	String ID = (String) result.getProperty("ID/Name");
	    	String author = (String) result.getProperty("author");
	    	String title = (String) result.getProperty("title");
			String ISBN = (String) result.getProperty("isbn");
			Books.add(new Book123(ID,author,title,ISBN));
		}
	    
	    
	    if ("xml".equals(format)) {
	 		response.setContentType("text/xml");
	 		XStream xstream = new XStream(new DomDriver());
	 		xstream.alias("Book", Book123.class);
	 		String xml = xstream.toXML(Books);
	 		response.getWriter().println(xml);
	      } else if ("json".equals(format)) {
	    	  response.setContentType("text/javascript");
	    	  Gson gson = new Gson();
	    	  String json = gson.toJson(Books);
	    	  response.getWriter().println(json);
	      } else {
	    	  response.setContentType("text/plain");
	    	  response.getWriter().println(Books);
	      }

	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	
	
}
