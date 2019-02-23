package com.SmoothStack.SmoothStackLoginCase5.Entity;

import java.io.Serializable;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import javax.persistence.*;
import javax.validation.constraints.AssertFalse;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.PositiveOrZero;

@Entity(name = "tbl_book_loans")
@Table(name = "tbl_book_loans")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"createdAt", "updatedAt"}, 
	allowGetters = true)
public class BookLoan implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
	private BookLoanId bookLoanId;
		
	@PastOrPresent
	@Column(name = "dateOut", nullable = false)
	private LocalDate dateOut;
	
	@FutureOrPresent
	@Column(name = "dueDate", nullable = false)
	private LocalDate dueDate;
	
	@NotNull
	@Column(name = "returned", nullable = false)
	private Boolean returned;
	
	@PositiveOrZero
	@Column(name = "extended", nullable = false)
	private int extended;
	
	@Column(name = "bookId")
	public Book getBook() {
		return bookLoanId.getBook();
	}

	public void setBook(Book book) {
		this.bookLoanId.setBook(book);
	}
	
	@Column(name = "branchId")
	public LibraryBranch getLibraryBranch() {
		return bookLoanId.getLibraryBranch();
	}

	public void setLibraryBranch(LibraryBranch libraryBranch) {
		this.bookLoanId.setLibraryBranch(libraryBranch);;
	}
	
	@Column(name = "cardNo")
	public Borrower getBorrower() {
		return bookLoanId.getBorrower();
	}

	public void setBorrower(Borrower borrower) {
		this.bookLoanId.setBorrower(borrower);;
	}
	
	public LocalDate getDateOut() {
		return dateOut;
	}

	public void setDateOut(LocalDate dateOut) {
		this.dateOut = dateOut;
	}
	
	public void setDateOutToDueDate(LocalDate dueDate) {
		this.dateOut = dueDate;
	}

	public LocalDate getDueDate() {
		return dueDate;
	}

	public void setDueDate(LocalDate dueDate) {
		this.dueDate = dueDate;
	}
	
	public void setDueDateExtend7Day(LocalDate dueDate) {
		LocalDate weekLater = dueDate.plusDays(7);
		this.dueDate = weekLater;
	}

	public boolean isReturned() {
		return returned;
	}

	public void setReturned(boolean returned) {
		this.returned = returned;
	}

	public int getExtended() {
		return extended;
	}

	public void setExtended(int extended) {
		this.extended = extended;
	}
	
	public void setExtended1Time(int extended) {
		this.extended = extended++;
	}

	@Override
	public String toString() {
		return "BookLoan [dateOut=" + dateOut + ", dueDate=" + dueDate + ", returned=" + returned + ", extended="
				+ extended + "]";
	}
	
}

