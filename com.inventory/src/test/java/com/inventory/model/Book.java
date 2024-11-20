package com.inventory.model;

public class Book {
//Structure of the book object is defined in this class along with the getter and setter methods.
	   	public int EntryID;
	    public String Title;
	    public String Author;
	    public String Genre;
	    public String PublicationDate;
	    public String ISBN;

	    public Book(String title, String author, String genre, String publicationDate, String isbn) {
	       
	        this.Title = title;
	        this.Author = author;
	        this.Genre = genre;
	        this.PublicationDate = publicationDate;
	        this.ISBN = isbn;
	    }
	    //constructor including entryid
	    public Book(int EntryID, String title, String author, String genre, String publicationDate, String isbn) {
		    this.EntryID = EntryID;   
	        this.Title = title;
	        this.Author = author;
	        this.Genre = genre;
	        this.PublicationDate = publicationDate;
	        this.ISBN = isbn;
	    }
		public Book() {
		}

		public int getEntryID() 
	    { 
	    	return EntryID; }
	    
	    public void setEntryID(int entryID) { 
	    	this.EntryID = entryID;
	    	}

	    public String getTitle() { 
	    	return Title; 
	    	}
	    public void setTitle(String title) {
	    	this.Title = title; 
	    	}

	    public String getAuthor() {
	    	return Author; 
	    	}
	    public void setAuthor(String author) {
	    	this.Author = author;
	    	}

	    public String getGenre() {
	    	return Genre; 
	    	}
	    public void setGenre(String genre) { 
	    	this.Genre = genre;
	    	}

	    public String getPublicationDate() { 
	    	return PublicationDate; 
	    	}
	    
	    public void setPublicationDate(String publicationDate) {
	    	this.PublicationDate = publicationDate;
	    	}
	    public String getIsbn() {
	    	return ISBN; }
	    
	    public void setIsbn(String isbn) {
	    	this.ISBN = isbn;
	    	}
	}