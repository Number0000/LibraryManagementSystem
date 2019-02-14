package com.SmoothStack.SmoothStackLoginCase5.ServiceHelper;

import java.sql.Connection;
import java.util.List;

import com.SmoothStack.SmoothStackLoginCase5.Connection.Connections;
import com.SmoothStack.SmoothStackLoginCase5.Dao.IBookDao;
import com.SmoothStack.SmoothStackLoginCase5.Entity.Book;
import com.SmoothStack.SmoothStackLoginCase5.Mysql.BookDao;
import com.SmoothStack.SmoothStackLoginCase5.Service.IBookService;

public class BookService implements IBookService{
	IBookDao bookDao;
	
	public BookService() {
		bookDao = new BookDao();
	}
	
	public List<Book> getBook(){
		Connection conn = Connections.connection();
		return bookDao.findAll(conn);
	}
	
	public boolean addBook(Book book) {
		Connection conn = Connections.connection();
		return bookDao.addBook(conn, book);
	}
	
	public List<Book> getBookById(int bookId){
		Connection conn = Connections.connection();
		return bookDao.getBookById(conn, bookId);
	}
	
	public Book getBookByIdReturn(int bookId){
		Connection conn = Connections.connection();
		return bookDao.getBookByIdReturn(conn, bookId);
	}
	
	public boolean updateBook(Book book){
		Connection conn = Connections.connection();
		return bookDao.updateBook(conn, book);
	}
	
	public boolean deleteBook(Book book){
		Connection conn = Connections.connection();
		return bookDao.deleteBookById(conn, book);
	}
	
	public List<Book> getSingleBook(int count){
		Connection conn = Connections.connection();
		return bookDao.getSingleBook(conn, count);
	}
	
	
}
