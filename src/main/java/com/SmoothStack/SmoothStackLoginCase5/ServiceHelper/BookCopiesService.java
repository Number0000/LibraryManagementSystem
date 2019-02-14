package com.SmoothStack.SmoothStackLoginCase5.ServiceHelper;

import java.sql.Connection;
import java.util.List;

import com.SmoothStack.SmoothStackLoginCase5.Connection.Connections;
import com.SmoothStack.SmoothStackLoginCase5.Dao.IBookCopiesDao;
import com.SmoothStack.SmoothStackLoginCase5.Entity.BookCopies;
import com.SmoothStack.SmoothStackLoginCase5.Mysql.BookCopiesDao;
import com.SmoothStack.SmoothStackLoginCase5.Service.IBookCopiesService;

public class BookCopiesService implements IBookCopiesService{
	IBookCopiesDao bookCopiesDao;
	
	public BookCopiesService() {
		bookCopiesDao = new BookCopiesDao();
	}
	
	public List<BookCopies> getBookCopies(){
		Connection conn = Connections.connection();
		return bookCopiesDao.findAll(conn);
	}
	
	public boolean addBookCopies(BookCopies bookCopies) {
		Connection conn = Connections.connection();
		return bookCopiesDao.addBookCopies(conn, bookCopies);
	}
	
	public List<BookCopies> getBookCopiesById(int bookId, int branchId){
		Connection conn = Connections.connection();
		return bookCopiesDao.getBookCopiesById(conn, bookId, branchId);
	}
	
	public BookCopies getBookCopiesByIdReturn(int bookId, int branchId){
		Connection conn = Connections.connection();
		return bookCopiesDao.getBookCopiesByIdReturn(conn, bookId, branchId);
	}
	
	public boolean updateBookCopies(BookCopies bookCopies){
		Connection conn = Connections.connection();
		return bookCopiesDao.updateBookCopies(conn, bookCopies);
	}
	
	public boolean deleteBookCopies(BookCopies bookCopies){
		Connection conn = Connections.connection();
		return bookCopiesDao.deleteBookCopiesById(conn, bookCopies);
	}
	
	public List<BookCopies> getSingleBookCopies(int count){
		Connection conn = Connections.connection();
		return bookCopiesDao.getSingleBookCopies(conn, count);
	}	
	
}
