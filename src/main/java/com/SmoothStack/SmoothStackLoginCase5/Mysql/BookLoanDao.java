package com.SmoothStack.SmoothStackLoginCase5.Mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.SmoothStack.SmoothStackLoginCase5.Dao.IBookLoanDao;
import com.SmoothStack.SmoothStackLoginCase5.Entity.Book;
import com.SmoothStack.SmoothStackLoginCase5.Entity.BookLoan;
import com.SmoothStack.SmoothStackLoginCase5.Entity.Borrower;
import com.SmoothStack.SmoothStackLoginCase5.Entity.LibraryBranch;

public class BookLoanDao implements IBookLoanDao {
	private BookDao bookDao = new BookDao();
	private LibraryBranchDao libraryBranchDao = new LibraryBranchDao();
	private BorrowerDao borrowerDao = new BorrowerDao();
	
	public List<BookLoan> findAll(Connection conn){
		List<BookLoan> bookLoanList = new ArrayList<>();
		
		PreparedStatement prepareStatement = null;
		ResultSet resultSet = null;
		BookLoan bookLoan;
		Book book; LibraryBranch libraryBranch; Borrower borrower;
		String sqlQuery = "SELECT `a`.*, `b`.`title`, `b`.`authorId`, `b`.`publisherId`, `c`.`branchName`, `c`.`branchAddress`, `d`.`name`, `d`.`address`, `d`.`phone` FROM `lms`.`tbl_book_loans` AS `a` INNER JOIN `lms`.`tbl_book` AS `b` ON `a`.`bookId` = `b`.`bookId` INNER JOIN `lms`.`tbl_library_branch` AS `c` ON `a`.`branchId` = `c`.`branchId` INNER JOIN `lms`.`tbl_borrower` AS `d` ON `a`.`cardNo` = `d`.`cardNo`;";
		try {
			prepareStatement = conn.prepareStatement(sqlQuery);
			resultSet = prepareStatement.executeQuery();
			while(resultSet.next()){
				bookLoan = new BookLoan();
				book = bookDao.getBookByIdReturn(conn, resultSet.getInt("bookId"));
				libraryBranch = libraryBranchDao.getLibraryBranchByIdReturn(conn, resultSet.getInt("branchId"));
				borrower = borrowerDao.getBorrowerByIdReturn(conn, resultSet.getInt("cardNo"));
				bookLoan.setBookId(book);
				bookLoan.setBranchId(libraryBranch);
				bookLoan.setCardNo(borrower);
				bookLoan.setDateOut(resultSet.getDate("dateOut"));
				bookLoan.setDueDate(resultSet.getDate("dueDate"));
				bookLoan.setReturned(resultSet.getBoolean("returned"));
				bookLoanList.add(bookLoan);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bookLoanList;
	}
	
	public List<BookLoan> findAllByCardNo(Connection conn, int cardNo){
		List<BookLoan> bookLoanList = new ArrayList<>();
		
		PreparedStatement prepareStatement = null;
		ResultSet resultSet = null;
		BookLoan bookLoan;
		Book book; LibraryBranch libraryBranch; Borrower borrower;
		String sqlQuery = "SELECT `a`.*, `b`.`title`, `b`.`authorId`, `b`.`publisherId`, `c`.`branchName`, `c`.`branchAddress`, `d`.`name`, `d`.`address`, `d`.`phone` "
				+ "FROM `lms`.`tbl_book_loans` AS `a` INNER JOIN `lms`.`tbl_book` AS `b` ON `a`.`bookId` = `b`.`bookId` "
				+ "INNER JOIN `lms`.`tbl_library_branch` AS `c` ON `a`.`branchId` = `c`.`branchId` "
				+ "INNER JOIN `lms`.`tbl_borrower` AS `d` ON `a`.`cardNo` = `d`.`cardNo`"
				+ "WHERE `lms`.`tbl_borrower` = ?;";
		try {
			prepareStatement = conn.prepareStatement(sqlQuery);
			prepareStatement.setInt(1, cardNo);
			resultSet = prepareStatement.executeQuery();
			while(resultSet.next()){
				bookLoan = new BookLoan();
				book = bookDao.getBookByIdReturn(conn, resultSet.getInt("bookId"));
				libraryBranch = libraryBranchDao.getLibraryBranchByIdReturn(conn, resultSet.getInt("branchId"));
				borrower = borrowerDao.getBorrowerByIdReturn(conn, resultSet.getInt("cardNo"));
				bookLoan.setBookId(book);
				bookLoan.setBranchId(libraryBranch);
				bookLoan.setCardNo(borrower);
				bookLoan.setDateOut(resultSet.getDate("dateOut"));
				bookLoan.setDueDate(resultSet.getDate("dueDate"));
				bookLoan.setReturned(resultSet.getBoolean("returned"));
				bookLoanList.add(bookLoan);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bookLoanList;
	}

	@Override
	public boolean addBookLoan(Connection conn, BookLoan bookLoan) {
		// TODO Auto-generated method stub
		PreparedStatement prepareStatement = null;
		boolean reply = false; int replyInt = 0;
		Book book; LibraryBranch libraryBranch; Borrower borrower;
		String sqlQuery = "INSERT INTO `lms`.`tbl_book_loans` (`bookId`, `branchId`, `cardNo`, `dateOut`, `dueDate`, `returned`) VALUES (?, ?, ?, ?, ?, 0);";
		try {
			prepareStatement = conn.prepareStatement(sqlQuery);
			
			book = bookLoan.getBookId();
			libraryBranch = bookLoan.getBranchId();
			borrower = bookLoan.getCardNo();
			// parse_j_dateOut = bookLoan.getDateOut();
			// parse_s_dateOut = new java.sql.Date(parse_j_dateOut.getTime());
			prepareStatement = conn.prepareStatement(sqlQuery);
			prepareStatement.setInt(1, book.getBookId());
			prepareStatement.setInt(2, libraryBranch.getBranchId());
			prepareStatement.setInt(3, borrower.getCardNo());
			prepareStatement.setDate(4, bookLoan.getDateOut());
			
			Calendar in = Calendar.getInstance();
			in.add(Calendar.DATE, 7);
			java.sql.Date ui= new java.sql.Date (in.getTime().getTime());
			
			prepareStatement.setDate(5, ui);
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
	public List<BookLoan> getBookLoanById(Connection conn, int bookId, int branchId, int cardNo){
		PreparedStatement prepareStatement = null;
		ResultSet resultSet = null;
		String sqlQuery = "SELECT * FROM `lms`.`tbl_book_loans` AS `a` WHERE (`a`.`bookId` = ? AND `a`.`branchId` = ? AND `a`.`cardNo` = ?)";
		BookLoan bookLoan;
		Book book; LibraryBranch libraryBranch; Borrower borrower;
		List<BookLoan> bookLoanList = null;
		try {
			prepareStatement = conn.prepareStatement(sqlQuery);
			prepareStatement.setInt(1, bookId);
			prepareStatement.setInt(2, branchId);
			prepareStatement.setInt(3, cardNo);
			System.out.println(prepareStatement.executeUpdate());
			while(resultSet.next()){
				bookLoan = new BookLoan();
				book = bookDao.getBookByIdReturn(conn, resultSet.getInt("bookId"));
				libraryBranch = libraryBranchDao.getLibraryBranchByIdReturn(conn, resultSet.getInt("branchId"));
				borrower = borrowerDao.getBorrowerByIdReturn(conn, resultSet.getInt("cardNo"));
				bookLoan.setBookId(book);
				bookLoan.setBranchId(libraryBranch);
				bookLoan.setCardNo(borrower);
				bookLoan.setDateOut(resultSet.getDate("dateOut"));
				bookLoan.setDueDate(resultSet.getDate("dueDate"));
				bookLoan.setReturned(resultSet.getBoolean("returned"));
				bookLoanList.add(bookLoan);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bookLoanList;
	}
	
	@SuppressWarnings("null")
	public BookLoan getBookLoanByIdReturn(Connection conn, int bookId, int branchId, int cardNo){
		PreparedStatement prepareStatement = null;
		ResultSet resultSet = null;
		String sqlQuery = "SELECT * FROM `lms`.`tbl_book_loans` AS `a` WHERE (`a`.`bookId` = ? AND `a`.`branchId` = ? AND `a`.`cardNo` = ?)";
		BookLoan bookLoan = null;
		Book book; LibraryBranch libraryBranch; Borrower borrower;
		try {
			prepareStatement = conn.prepareStatement(sqlQuery);
			prepareStatement.setInt(1, bookId);
			prepareStatement.setInt(2, branchId);
			prepareStatement.setInt(3, cardNo);
			System.out.println(prepareStatement.executeUpdate());
			while(resultSet.next()){
				bookLoan = new BookLoan();
				book = bookDao.getBookByIdReturn(conn, resultSet.getInt("bookId"));
				libraryBranch = libraryBranchDao.getLibraryBranchByIdReturn(conn, resultSet.getInt("branchId"));
				borrower = borrowerDao.getBorrowerByIdReturn(conn, resultSet.getInt("cardNo"));
				bookLoan.setBookId(book);
				bookLoan.setBranchId(libraryBranch);
				bookLoan.setCardNo(borrower);
				bookLoan.setDateOut(resultSet.getDate("dateOut"));
				bookLoan.setDueDate(resultSet.getDate("dueDate"));
				bookLoan.setReturned(resultSet.getBoolean("returned"));
				return bookLoan;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bookLoan;
	}
		
	public boolean updateBookLoan(Connection conn, BookLoan bookLoan){
		PreparedStatement prepareStatement = null;
		boolean reply = false; int replyInt = 0;
		Book book; LibraryBranch libraryBranch; Borrower borrower;
		String sqlQuery = "UPDATE `lms`.`tbl_book_loans` AS `a` SET `a`.`dueDate` = date_add(now(), interval 7 day) WHERE ((`a`.`bookId` = ?) AND (`a`.`branchId` = ?) AND (`a`.`cardNo` = ?));";
		try {
			prepareStatement = conn.prepareStatement(sqlQuery);

			prepareStatement = conn.prepareStatement(sqlQuery);
			
			book = bookLoan.getBookId();
			libraryBranch = bookLoan.getBranchId();
			borrower = bookLoan.getCardNo();
			
			prepareStatement.setInt(1, book.getBookId());
			prepareStatement.setInt(2, libraryBranch.getBranchId());
			prepareStatement.setInt(3, borrower.getCardNo());

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

	public boolean deleteBookLoanById(Connection conn, BookLoan bookLoan){
		PreparedStatement prepareStatement = null;
		boolean reply = false; int replyInt = 0;
		Book book; LibraryBranch libraryBranch; Borrower borrower;
		String sqlQuery = "DELETE FROM `lms`.`tbl_book_loans` WHERE `lms`.`tbl_book_loans`.`bookId` = ? and `lms`.`tbl_book_loans`.`branchId` = ? and `lms`.`tbl_book_loans`.`cardNo` = ?";
		try {
			book = bookLoan.getBookId();
			libraryBranch = bookLoan.getBranchId();
			borrower = bookLoan.getCardNo();
			
			prepareStatement = conn.prepareStatement(sqlQuery);
			prepareStatement.setInt(1, book.getBookId());
			prepareStatement.setInt(2, libraryBranch.getBranchId());
			prepareStatement.setInt(3, borrower.getCardNo());
			
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
	public List<BookLoan> getSingleBookLoan(Connection conn, int count){
		PreparedStatement prepareStatement = null;
		ResultSet resultSet = null;
		BookLoan bookLoan = null;
		List<BookLoan> bookLoanList = null;
		Book book; LibraryBranch libraryBranch; Borrower borrower;
		String sqlQuery = "SELECT * FROM `lms`.`tbl_library_branch` LIMIT ?, 1;";
		try{
			prepareStatement = conn.prepareStatement(sqlQuery);
			prepareStatement.setInt(1, count-1);
			resultSet = prepareStatement.executeQuery();
			while(resultSet.next()){
				bookLoan = new BookLoan();
				book = bookDao.getBookByIdReturn(conn, resultSet.getInt("bookId"));
				libraryBranch = libraryBranchDao.getLibraryBranchByIdReturn(conn, resultSet.getInt("branchId"));
				borrower = borrowerDao.getBorrowerByIdReturn(conn, resultSet.getInt("cardNo"));
				bookLoan.setBookId(book);
				bookLoan.setBranchId(libraryBranch);
				bookLoan.setCardNo(borrower);
				bookLoan.setDateOut(resultSet.getDate("dateOut"));
				bookLoan.setDueDate(resultSet.getDate("dueDate"));
				bookLoan.setReturned(resultSet.getBoolean("returned"));
				bookLoanList.add(bookLoan);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bookLoanList;
	}

}
