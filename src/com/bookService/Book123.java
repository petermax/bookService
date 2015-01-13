package com.bookService;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Book123 {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String bookID;
	
	String author, title, isbn;
	
	public Book123 (String iD, String author, String title, String isbn){
		setBookID(iD);
		setAuthor(author);
		setTitle(title);
		setIsbn(isbn);
	}
	
	public void setBookID(String bookID){
		this.bookID = bookID;
	}
	
	public void setAuthor(String author){
		this.author = author;
	}
	
	public void setTitle(String title){
		this.title = title;
	}
	
	public void setIsbn(String isbn){
		this.isbn = isbn;
	}
	
	public String getBookID(){
		return (bookID);
	}
	
	public String getAuthor(){
		return(author);
	}
	
	public String getTitle(){
		return(title);
	}
	
	public String getIsbn(){
		return(isbn);
	}
	
	public String toString(){
		return "id: " + this.bookID + ", Author: " + this.author + ", Title: " + this.title + ", ISBN: " + this.isbn + "#";
	}
}
