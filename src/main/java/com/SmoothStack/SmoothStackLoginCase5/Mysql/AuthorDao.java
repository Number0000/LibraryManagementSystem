package com.SmoothStack.SmoothStackLoginCase5.Mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.SmoothStack.SmoothStackLoginCase5.Dao.IAuthorDao;
import com.SmoothStack.SmoothStackLoginCase5.Entity.Author;

public class AuthorDao implements IAuthorDao {
	
	public List<Author> findAll(Connection conn){
		List<Author> authorList = new ArrayList<>();
		
		PreparedStatement prepareStatement = null;
		ResultSet resultSet = null;
		Author author;
		String sqlQuery = "SELECT * FROM `lms`.`tbl_author`;";
		try {
			prepareStatement = conn.prepareStatement(sqlQuery);
			resultSet = prepareStatement.executeQuery();
			while(resultSet.next()){
				author = new Author();
				author.setAuthorId(resultSet.getInt("authorId"));
				author.setAuthorName(resultSet.getString("authorName"));
				authorList.add(author);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return authorList;
	}

	@Override
	public boolean addAuthor(Connection conn, Author author) {
		// TODO Auto-generated method stub
		PreparedStatement prepareStatement = null;
		boolean reply = false; int replyInt = 0;
		String sqlQuery = "INSERT INTO `lms`.`tbl_author` (`authorId`, `authorName`) VALUES (?, ?);";
		try {
			prepareStatement = conn.prepareStatement(sqlQuery);
			prepareStatement.setInt(1, author.getAuthorId());
			prepareStatement.setString(2, author.getAuthorName());
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
	public List<Author> getAuthorById(Connection conn, int authorId){
		PreparedStatement prepareStatement = null;
		ResultSet resultSet = null;
		String sqlQuery = "SELECT * FROM `lms`.`tbl_author` WHERE `lms`.`tbl_author`.`authorId` = ?;";
		Author author;
		List<Author> authorList = null;
		try {
			prepareStatement = conn.prepareStatement(sqlQuery);
			prepareStatement.setInt(1, authorId);
			System.out.println(prepareStatement.executeUpdate());
			while(resultSet.next()){
				author = new Author();
				author.setAuthorId(resultSet.getInt("authorId"));
				author.setAuthorName(resultSet.getString("authorName"));
				authorList.add(author);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return authorList;
	}
	
	@SuppressWarnings("null")
	public Author getAuthorByIdReturn(Connection conn, int authorId){
		PreparedStatement prepareStatement = null;
		ResultSet resultSet = null;
		String sqlQuery = "SELECT * FROM `lms`.`tbl_author` WHERE `lms`.`tbl_author`.`authorId` = ?;";
		Author author = null;
		try {
			prepareStatement = conn.prepareStatement(sqlQuery);
			prepareStatement.setInt(1, authorId);
			System.out.println(prepareStatement.executeUpdate());
			while(resultSet.next()){
				author = new Author();
				author.setAuthorId(resultSet.getInt("authorId"));
				author.setAuthorName(resultSet.getString("authorName"));
				return author;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return author;
	}
		
	public boolean updateAuthor(Connection conn, Author author){
		PreparedStatement prepareStatement = null;
		boolean reply = false; int replyInt = 0;
		String sqlQuery = "UPDATE `lms`.`tbl_author` SET `authorName` = ? WHERE `lms`.`tbl_author`.`authorId` = ?;";
		try {
			prepareStatement = conn.prepareStatement(sqlQuery);
			
			prepareStatement.setString(1, author.getAuthorName());
			prepareStatement.setInt(2, author.getAuthorId());

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

	public boolean deleteAuthorById(Connection conn, Author author){
		PreparedStatement prepareStatement = null;
		boolean reply = false; int replyInt = 0;
		String sqlQuery = "DELETE FROM `lms`.`tbl_author` WHERE `lms`.`tbl_author`.`authorId` = ?";
		try {
			prepareStatement = conn.prepareStatement(sqlQuery);
			prepareStatement.setInt(1, author.getAuthorId());
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
	public List<Author> getSingleAuthor(Connection conn, int count){
		PreparedStatement prepareStatement = null;
		ResultSet resultSet = null;
		Author author = null;
		List<Author> authorList = null;
		String sqlQuery = "SELECT * FROM `lms`.`tbl_author` LIMIT ?, 1;";
		try{
			prepareStatement = conn.prepareStatement(sqlQuery);
			prepareStatement.setInt(1, count-1);
			resultSet = prepareStatement.executeQuery();
			while(resultSet.next()){
				author = new Author();
				author.setAuthorId(resultSet.getInt("authorId"));
				author.setAuthorName(resultSet.getString("authorName"));
				authorList.add(author);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return authorList;
	}

}
