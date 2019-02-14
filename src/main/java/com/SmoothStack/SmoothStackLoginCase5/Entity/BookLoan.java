package com.SmoothStack.SmoothStackLoginCase5.Entity;
import java.sql.Date;

//db to support jdbctest.java
public class BookLoan {
	private Book bookId;
	private LibraryBranch branchId;
	private Borrower cardNo ;
	private Date dateOut;
	private Date dueDate;
	private boolean returned;
	
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
	public Borrower getCardNo() {
		return cardNo;
	}
	public void setCardNo(Borrower cardNo) {
		this.cardNo = cardNo;
	}
	public Date getDateOut() {
		return dateOut;
	}
	public void setDateOut(Date dateOut) {
		this.dateOut = dateOut;
	}
	public Date getDueDate() {
		return dueDate;
	}
	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}
	public boolean getReturned() {
		return returned;
	}
	public void setReturned(boolean returned) {
		this.returned = returned;
	}
	
	
	@Override
	public String toString() {
		return "BookLoans [bookId= " + bookId 
				+ ", branchId= " + branchId 
				+ ", cardNo= " + cardNo 
				+ ", dateOut= " + dateOut
				+ ", dueDate= " + dueDate 
				+ ", returned= " + returned + "]";
	}
	
	
}
