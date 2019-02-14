package com.SmoothStack.SmoothStackLoginCase5.Entity;
//db to support jdbctest.java
public class BookCopies {
	private Book bookId;
	private LibraryBranch branchId;
	private int noOfCopies;
	public Book getBookId() {
		return bookId;
	}
	public void setBookId(Book bookId) {
		this.bookId = bookId;
	}
	public LibraryBranch getBranchId() {
		return branchId;
	}
	public void setBranchId(LibraryBranch branchId) {
		this.branchId = branchId;
	}
	public int getNoOfCopies() {
		return noOfCopies;
	}
	public void setNoOfCopies(int noOfCopies) {
		this.noOfCopies = noOfCopies;
	}
	@Override
	public String toString() {
		return "BookCopies [bookId= " + bookId 
				+ ", branchId= " + branchId 
				+ ", noOfCopies= " + noOfCopies + "]";
	}
	
	
}
