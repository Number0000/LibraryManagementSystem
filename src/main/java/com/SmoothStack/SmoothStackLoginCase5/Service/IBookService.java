package com.SmoothStack.SmoothStackLoginCase5.Service;

import java.util.List;

import com.SmoothStack.SmoothStackLoginCase5.Entity.Book;

public interface IBookService {
	
	public List<Book> getBook();
	public boolean addBook(Book book);
	public List<Book> getBookById(int bookId);
	public Book getBookByIdReturn(int bookId);
	public boolean updateBook(Book book);
	public boolean deleteBook(Book book);
	public List<Book> getSingleBook(int count);

}
