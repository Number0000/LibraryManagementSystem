package com.SmoothStack.SmoothStackLoginCase5.Dao;

import java.sql.Connection;
import java.util.List;

import com.SmoothStack.SmoothStackLoginCase5.Entity.Book;

public interface IBookDao {
	public List<Book> findAll(Connection conn);
	public boolean addBook(Connection conn, Book book);
	public List<Book> getBookById(Connection conn, int bookId);
	public Book getBookByIdReturn(Connection conn, int bookId);
	public boolean updateBook(Connection conn, Book book);
	public boolean deleteBookById(Connection conn, Book book);
	public List<Book> getSingleBook(Connection conn, int count);
	
}
