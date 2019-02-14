package com.SmoothStack.SmoothStackLoginCase5.Mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.SmoothStack.SmoothStackLoginCase5.Dao.IBookCopiesDao;
import com.SmoothStack.SmoothStackLoginCase5.Entity.Book;
import com.SmoothStack.SmoothStackLoginCase5.Entity.BookCopies;
import com.SmoothStack.SmoothStackLoginCase5.Entity.LibraryBranch;

public class BookCopiesDao implements IBookCopiesDao {
	BookDao bookDao = new BookDao();
	LibraryBranchDao libraryBranchDao = new LibraryBranchDao();
	
	public List<BookCopies> findAll(Connection conn){
		List<BookCopies> bookCopiesList = new ArrayList<>();
		
		PreparedStatement prepareStatement = null;
		ResultSet resultSet = null;
		BookCopies bookCopies;
		Book book; LibraryBranch libraryBranch;
		String sqlQuery = "SELECT * FROM `lms`.`tbl_book_copies`;";
		try {
			prepareStatement = conn.prepareStatement(sqlQuery);
			resultSet = prepareStatement.executeQuery();
			while(resultSet.next()){
				bookCopies = new BookCopies();
				book = bookDao.getBookByIdReturn(conn, resultSet.getInt("bookId"));
				libraryBranch = libraryBranchDao.getLibraryBranchByIdReturn(conn, resultSet.getInt("branchId"));
				bookCopies.setBookId(book);
				bookCopies.setBranchId(libraryBranch);
				bookCopies.setNoOfCopies(resultSet.getInt("noOfCopies"));
				bookCopiesList.add(bookCopies);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bookCopiesList;
	}

	@Override
	public boolean addBookCopies(Connection conn, BookCopies bookCopies) {
		// TODO Auto-generated method stub
		PreparedStatement prepareStatement = null;
		Book book; LibraryBranch libraryBranch;
		boolean reply = false; int replyInt = 0;
		String sqlQuery = "INSERT INTO `lms`.`tbl_book_copies` (`bookId`, `branchId`, `noOfCopies`) VALUES (?, ?, ?);";
		try {
			book = bookCopies.getBookId();
			libraryBranch = bookCopies.getBranchId();
			prepareStatement = conn.prepareStatement(sqlQuery);
			
			prepareStatement.setInt(1, book.getBookId());
			prepareStatement.setInt(2, libraryBranch.getBranchId());
			prepareStatement.setInt(3, bookCopies.getNoOfCopies());
			replyInt = prepareStatement.executeUpdate();
			if(replyInt == 1) {
				reply = true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return reply;
	}
	
	@SuppressWarnings("null")
	public List<BookCopies> getBookCopiesById(Connection conn,int bookId, int branchId){
		PreparedStatement prepareStatement = null;
		ResultSet resultSet = null;
		String sqlQuery = "SELECT * FROM `lms`.`tbl_book_copies` WHERE (`lms`.`tbl_book_copies`.`bookId` = ? AND `lms`.`tbl_book_copies`.`branchId` = ?);";
		BookCopies bookCopies;
		Book book; LibraryBranch libraryBranch;
		List<BookCopies> bookCopiesList = null;
		try {
			prepareStatement = conn.prepareStatement(sqlQuery);
			prepareStatement.setInt(1, bookId);
			prepareStatement.setInt(2, branchId);
			System.out.println(prepareStatement.executeUpdate());
			while(resultSet.next()){
				bookCopies = new BookCopies();
				book = bookDao.getBookByIdReturn(conn, resultSet.getInt("bookId"));
				libraryBranch = libraryBranchDao.getLibraryBranchByIdReturn(conn, resultSet.getInt("branchId"));
				bookCopies.setBookId(book);
				bookCopies.setBranchId(libraryBranch);
				bookCopies.setNoOfCopies(resultSet.getInt("noOfCopies"));
				bookCopiesList.add(bookCopies);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bookCopiesList;
	}
	
	@SuppressWarnings("null")
	public BookCopies getBookCopiesByIdReturn(Connection conn, int bookId, int branchId){
		PreparedStatement prepareStatement = null;
		ResultSet resultSet = null;
		String sqlQuery = "SELECT * FROM `lms`.`tbl_book_copies` WHERE (`lms`.`tbl_book_copies`.`bookId` = ? AND `lms`.`tbl_book_copies`.`branchId` = ?);";
		BookCopies bookCopies = null;
		Book book; LibraryBranch libraryBranch;
		try {
			prepareStatement = conn.prepareStatement(sqlQuery);
			prepareStatement.setInt(1, bookId);
			prepareStatement.setInt(2, branchId);
			System.out.println(prepareStatement.executeUpdate());
			while(resultSet.next()){
				bookCopies = new BookCopies();
				book = bookDao.getBookByIdReturn(conn, resultSet.getInt("bookId"));
				libraryBranch = libraryBranchDao.getLibraryBranchByIdReturn(conn, resultSet.getInt("branchId"));
				
				bookCopies.setBookId(book);
				bookCopies.setBranchId(libraryBranch);
				bookCopies.setNoOfCopies(resultSet.getInt("noOfCopies"));
				return bookCopies;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bookCopies;
	}
		
	public boolean updateBookCopies(Connection conn, BookCopies bookCopies){
		PreparedStatement prepareStatement = null;
		boolean reply = false; int replyInt = 0;
		Book book; LibraryBranch libraryBranch;
		String sqlQuery = "UPDATE `lms`.`tbl_book_copies` SET `noOfCopies` = ? WHERE ((`bookId` = ?) and (`branchId` = ?));";
		try {
			book = bookCopies.getBookId();
			libraryBranch = bookCopies.getBranchId();

			prepareStatement = conn.prepareStatement(sqlQuery);
			prepareStatement.setInt(1, bookCopies.getNoOfCopies());	
			prepareStatement.setInt(2, book.getBookId());
			prepareStatement.setInt(3, libraryBranch.getBranchId());
			
			replyInt = prepareStatement.executeUpdate();
			if(replyInt == 1) {
				reply = true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return reply;
	}

	public boolean deleteBookCopiesById(Connection conn, BookCopies bookCopies){
		PreparedStatement prepareStatement = null;
		boolean reply = false; int replyInt = 0;
		Book book; LibraryBranch libraryBranch;
		String sqlQuery = "DELETE FROM `lms`.`tbl_book_copies` WHERE `lms`.`tbl_book_copies`.`bookId` = ? AND `lms`.`tbl_book_copies`.`branchId` = ?;";
		try {
			book = bookCopies.getBookId();
			libraryBranch = bookCopies.getBranchId();
			
			prepareStatement = conn.prepareStatement(sqlQuery);
			prepareStatement.setInt(1, book.getBookId());
			prepareStatement.setInt(2, libraryBranch.getBranchId());
			replyInt = prepareStatement.executeUpdate();
			if(replyInt == 1) {
				reply = true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return reply;
	}
	
	@SuppressWarnings("null")
	public List<BookCopies> getSingleBookCopies(Connection conn, int count){
		PreparedStatement prepareStatement = null;
		ResultSet resultSet = null;
		BookCopies bookCopies = null;
		List<BookCopies> bookCopiesList = null;
		Book book; LibraryBranch libraryBranch;
		String sqlQuery = "SELECT * FROM `lms`.`tbl_book_copies` LIMIT ?, 1;";
		try{
			prepareStatement = conn.prepareStatement(sqlQuery);
			prepareStatement.setInt(1, count-1);
			resultSet = prepareStatement.executeQuery();
			while(resultSet.next()){
				bookCopies = new BookCopies();
				book = bookDao.getBookByIdReturn(conn, resultSet.getInt("bookId"));
				libraryBranch = libraryBranchDao.getLibraryBranchByIdReturn(conn, resultSet.getInt("branchId"));
				
				bookCopies.setBookId(book);
				bookCopies.setBranchId(libraryBranch);
				bookCopies.setNoOfCopies(resultSet.getInt("noOfCopies"));
				bookCopiesList.add(bookCopies);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bookCopiesList;
	}

}
