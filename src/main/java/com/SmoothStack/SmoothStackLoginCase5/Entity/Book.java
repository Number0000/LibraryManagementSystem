package com.SmoothStack.SmoothStackLoginCase5.Entity;
//db to support jdbctest.java
public class Book {
	private int bookId;
	private String title;
	private Author authorId;
	private Publisher publisherId;
	public int getBookId() {
		return bookId;
	}
	public void setBookId(int bookId) {
		this.bookId = bookId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Author getAuthorId() {
		return authorId;
	}
	public void setAuthorId(Author authorId) {
		this.authorId = authorId;
	}
	
	public Publisher getPublisherId() {
		return publisherId;
	}
	public void setPublisherId(Publisher publisherId) {
		this.publisherId = publisherId;
	}
	
	@Override
	public String toString() {
		return "Book [bookId= " + bookId 
				+ ", title= " + title 
				+ ", authorId= " + authorId 
				+ ", publisherId= " + publisherId
				+ "]";
	}
	
}
