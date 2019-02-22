package com.SmoothStack.SmoothStackLoginCase5.Entity;

import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity(name = "tbl_book")
@Table(name = "tbl_book")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"createdAt", "updatedAt"}, 
	allowGetters = true)
public class Book implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "bookId", unique = true, nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int bookId;
	
	@NotBlank
	@Column(name = "title", nullable = false)
	private String title;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "authId",  referencedColumnName = "authorId", insertable = true, updatable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Author authorId;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "pubId",  referencedColumnName = "publisherId", insertable = true, updatable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
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
		return "Book [bookId=" + bookId + ", title=" + title + ", authorId=" + authorId + ", publisherId=" + publisherId
				+ "]";
	}
	
	
	
}

