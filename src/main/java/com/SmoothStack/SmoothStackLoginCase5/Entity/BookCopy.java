package com.SmoothStack.SmoothStackLoginCase5.Entity;

import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity(name = "tbl_book_copies")
@Table(name = "tbl_book_copies")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"createdAt", "updatedAt"}, 
	allowGetters = true)
public class BookCopy implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
	private BookCopyId bookCopyId;
		
	@NotBlank
	@Column(name = "noOfCopies", nullable = false)
	private int noOfCopies;
	
	@Column(name = "bookId")
	public Book getBook() {
		return bookCopyId.getBook();
	}

	public void setBook(Book book) {
		this.bookCopyId.setBook(book);
	}
	
	@Column(name = "branchId")
	public LibraryBranch getLibraryBranch() {
		return bookCopyId.getLibraryBranch();
	}

	public void setLibraryBranch(LibraryBranch libraryBranch) {
		this.bookCopyId.setLibraryBranch(libraryBranch);;
	}

	public int getNoOfCopies() {
		return noOfCopies;
	}

	public void setNoOfCopies(int noOfCopies) {
		this.noOfCopies = noOfCopies;
	}
	
	public void decrementNumberOfCopies() {
		if(noOfCopies > 0) {
			noOfCopies--;
		}
	}
	
	public void incrementNumberOfCopies() {
		noOfCopies++;
	}

	@Override
	public String toString() {
		return "BookCopy [book=" + bookCopyId.getBook() + ", libraryBranch=" + bookCopyId.getLibraryBranch() + ", noOfCopies=" + noOfCopies + "]";
	}
	
	
}

