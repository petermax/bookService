package com.bookService;

import javax.persistence.*;

public final class EMF {
	
	private static final EntityManagerFactory emfInstance=Persistence.createEntityManagerFactory("Books");
	
	private EMF() {}
	
	public static EntityManagerFactory get(){
		return emfInstance;
	}

}
