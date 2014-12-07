package com.bookService;

import java.io.IOException;
import java.util.List;
import javax.servlet.http.*;
/*
 * Import google libraries
 */
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.QueryResultList;

@SuppressWarnings("serial")
public class bookServiceServlet extends HttpServlet {
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		
		// Use class Query to assemble a query
		Query q = new Query("BookDetails");
		
		// Use PreparedQuery interface to retrieve results
		PreparedQuery pq = datastore.prepare(q);
		
		resp.setContentType("text/plain");
		
		
		
		for (Entity result : pq.asIterable()) {
			  String author = (String) result.getProperty("author");
			  String title = (String) result.getProperty("title");
			  //String ISBN = (String) result.getProperty("ISBN");
			  resp.getWriter().println("Author: " + author + ", Title: " + title); // + ", ISBN: " + ISBN);
		}
		resp.getWriter().println("OK");

	}
}