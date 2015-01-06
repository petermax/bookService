package com.bookService;

public class Book123 {
	private String bookID, author, title, isbn;
	
	public Book123 (String bookID, String author, String title, String isbn){
		setBookID(bookID);
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
