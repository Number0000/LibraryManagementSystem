package com.SmoothStack.SmoothStackLoginCase5.Entity;

import java.io.Serializable;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import javax.persistence.*;

@Embeddable
public class BookLoanId implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@ManyToOne
	@JoinColumn(name = "bookId", referencedColumnName = "bookId", insertable = true, updatable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Book book;
	
	@ManyToOne
	@JoinColumn(name = "branchId", referencedColumnName = "branchId", insertable = true, updatable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private LibraryBranch libraryBranch;
	
	@ManyToOne
	@JoinColumn(name = "cardNo", referencedColumnName = "cardNo", insertable = true, updatable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Borrower borrower;
	
	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public LibraryBranch getLibraryBranch() {
		return libraryBranch;
	}

	public void setLibraryBranch(LibraryBranch libraryBranch) {
		this.libraryBranch = libraryBranch;
	}

	public Borrower getBorrower() {
		return borrower;
	}

	public void setBorrower(Borrower borrower) {
		this.borrower = borrower;
	}
	
	
}

