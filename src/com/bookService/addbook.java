package com.bookService;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;

public class addbook extends HttpServlet{
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();

		Entity en = new Entity("BookDetails");
		
		en.setProperty("author", request.getParameter("author"));
		en.setProperty("title", request.getParameter("title"));
		en.setProperty("isbn", request.getParameter("isbn"));
		
		datastore.put(en);
		
		response.sendRedirect("/mybook.jsp");
	}

}
