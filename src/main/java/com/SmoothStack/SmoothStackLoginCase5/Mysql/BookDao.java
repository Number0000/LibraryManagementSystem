package com.SmoothStack.SmoothStackLoginCase5.Mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.SmoothStack.SmoothStackLoginCase5.Dao.IBookDao;
import com.SmoothStack.SmoothStackLoginCase5.Entity.Author;
import com.SmoothStack.SmoothStackLoginCase5.Entity.Book;
import com.SmoothStack.SmoothStackLoginCase5.Entity.Publisher;

public class BookDao implements IBookDao {
	private AuthorDao authorDao = new AuthorDao();
	private PublisherDao publisherDao = new PublisherDao();

	public List<Book> findAll(Connection conn) {
		List<Book> bookList = new ArrayList<>();

		PreparedStatement prepareStatement = null;
		ResultSet resultSet = null;
		Book book;
		Author author;
		Publisher publisher;
		String sqlQuery = "SELECT * FROM `lms`.`tbl_book` ";
		try {
			prepareStatement = conn.prepareStatement(sqlQuery);
			resultSet = prepareStatement.executeQuery();
			while (resultSet.next()) {
				book = new Book();
				author = authorDao.getAuthorByIdReturn(conn, resultSet.getInt("authorId"));
				publisher = publisherDao.getPublisherByIdReturn(conn, resultSet.getInt("publisherId"));

				book.setBookId(resultSet.getInt("bookId"));
				book.setTitle(resultSet.getString("title"));
				book.setPublisher(publisher);
				book.setAuthor(author);
				bookList.add(book);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bookList;
	}

	@Override
	public boolean addBook(Connection conn, Book book) {
		// TODO Auto-generated method stub
		PreparedStatement prepareStatement = null;
		boolean reply = false;
		int replyInt = 0;
		Author author;
		Publisher publisher;
		String sqlQuery = "INSERT INTO `lms`.`tbl_book` (`bookId`, `title`, `authorId`, `publisherId`) VALUES (?, ?, ?, ?);";
		try {
			author = book.getAuthor();
			publisher = book.getPublisher();
			prepareStatement = conn.prepareStatement(sqlQuery);
			prepareStatement.setInt(1, book.getBookId());
			prepareStatement.setString(2, book.getTitle());
			prepareStatement.setInt(3, author.getAuthorId());
			prepareStatement.setInt(4, publisher.getPublisherId());
			replyInt = prepareStatement.executeUpdate();
			if (replyInt == 1) {
				reply = true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return reply;
	}

	@SuppressWarnings("null")
	public List<Book> getBookById(Connection conn, int branchId) {
		PreparedStatement prepareStatement = null;
		ResultSet resultSet = null;
		String sqlQuery = "SELECT * FROM `lms`.`tbl_book` WHERE `lms`.`tbl_book`.`bookId` = ?";
		Book book;
		Author author;
		Publisher publisher;
		List<Book> bookList = null;
		try {
			prepareStatement = conn.prepareStatement(sqlQuery);
			prepareStatement.setInt(1, branchId);
			System.out.println(prepareStatement.executeUpdate());
			while (resultSet.next()) {
				book = new Book();
				author = authorDao.getAuthorByIdReturn(conn, resultSet.getInt("authorId"));
				publisher = publisherDao.getPublisherByIdReturn(conn, resultSet.getShort("publisherId"));
				book.setBookId(resultSet.getInt("bookId"));
				book.setTitle(resultSet.getString("title"));
				book.setAuthor(author);
				book.setPublisher(publisher);
				bookList.add(book);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bookList;
	}

	@SuppressWarnings("null")
	public Book getBookByIdReturn(Connection conn, int branchId) {
		PreparedStatement prepareStatement = null;
		ResultSet resultSet = null;
		String sqlQuery = "SELECT * FROM `lms`.`tbl_book` WHERE `lms`.`tbl_book`.`bookId` = ?";
		Book book = null;
		Author author;
		Publisher publisher;
		try {
			prepareStatement = conn.prepareStatement(sqlQuery);
			prepareStatement.setInt(1, branchId);
			System.out.println(prepareStatement.executeUpdate());
			while (resultSet.next()) {
				book = new Book();
				author = authorDao.getAuthorByIdReturn(conn, resultSet.getInt("authorId"));
				publisher = publisherDao.getPublisherByIdReturn(conn, resultSet.getShort("publisherId"));

				book.setBookId(resultSet.getInt("bookId"));
				book.setTitle(resultSet.getString("title"));
				book.setAuthor(author);
				book.setPublisher(publisher);
				return book;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return book;
	}

	public boolean updateBook(Connection conn, Book book) {
		PreparedStatement prepareStatement = null;
		boolean reply = false;
		int replyInt = 0;
		String sqlQuery = "UPDATE `lms`.`tbl_book` SET `title` = ? WHERE (`bookId` = ?);";
		try {
			prepareStatement = conn.prepareStatement(sqlQuery);

			prepareStatement.setString(1, book.getTitle());
			prepareStatement.setInt(2, book.getBookId());

			replyInt = prepareStatement.executeUpdate();
			if (replyInt == 1) {
				reply = true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return reply;
	}

	public boolean deleteBookById(Connection conn, Book book) {
		PreparedStatement prepareStatement = null;
		boolean reply = false;
		int replyInt = 0;
		String sqlQuery = "DELETE FROM `lms`.`tbl_book` WHERE `lms`.`tbl_book`.`bookId` = ?";
		try {
			prepareStatement = conn.prepareStatement(sqlQuery);
			prepareStatement.setInt(1, book.getBookId());
			replyInt = prepareStatement.executeUpdate();
			if (replyInt == 1) {
				reply = true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return reply;
	}

	@SuppressWarnings("null")
	public List<Book> getSingleBook(Connection conn, int count) {
		PreparedStatement prepareStatement = null;
		ResultSet resultSet = null;
		Book book = null;
		Publisher publisher;
		Author author;
		List<Book> bookList = null;
		String sqlQuery = "SELECT * FROM `lms`.`tbl_book` LIMIT ?, 1";
		try {
			prepareStatement = conn.prepareStatement(sqlQuery);
			prepareStatement.setInt(1, count - 1);
			resultSet = prepareStatement.executeQuery();
			while (resultSet.next()) {
				book = new Book();
				publisher = publisherDao.getPublisherByIdReturn(conn, resultSet.getInt("publisherId"));
				author = authorDao.getAuthorByIdReturn(conn, resultSet.getInt("authorId"));

				book.setBookId(resultSet.getInt("bookId"));
				book.setTitle(resultSet.getString("title"));
				book.setPublisher(publisher);
				book.setAuthor(author);
				bookList.add(book);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bookList;
	}

}
