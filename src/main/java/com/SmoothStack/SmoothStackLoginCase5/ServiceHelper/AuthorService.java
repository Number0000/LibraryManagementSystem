package com.SmoothStack.SmoothStackLoginCase5.ServiceHelper;

import java.sql.Connection;
import java.util.List;

import com.SmoothStack.SmoothStackLoginCase5.Connection.Connections;
import com.SmoothStack.SmoothStackLoginCase5.Dao.IAuthorDao;
import com.SmoothStack.SmoothStackLoginCase5.Entity.Author;
import com.SmoothStack.SmoothStackLoginCase5.Mysql.AuthorDao;
import com.SmoothStack.SmoothStackLoginCase5.Service.IAuthorService;

public class AuthorService implements IAuthorService{
	IAuthorDao authorDao;
	
	public AuthorService() {
		authorDao = new AuthorDao();
	}
	
	public List<Author> getAuthor(){
		Connection conn = Connections.connection();
		return authorDao.findAll(conn);
	}
	
	public boolean addAuthor(Author author) {
		Connection conn = Connections.connection();
		return authorDao.addAuthor(conn, author);
	}
	
	public List<Author> getAuthorById(int authorId){
		Connection conn = Connections.connection();
		return authorDao.getAuthorById(conn, authorId);
	}
	
	public Author getAuthorByIdReturn(int authorId){
		Connection conn = Connections.connection();
		return authorDao.getAuthorByIdReturn(conn, authorId);
	}
	
	public boolean updateAuthor(Author author){
		Connection conn = Connections.connection();
		return authorDao.updateAuthor(conn, author);
	}
	
	public boolean deleteAuthor(Author author){
		Connection conn = Connections.connection();
		return authorDao.deleteAuthorById(conn, author);
	}
	
	public List<Author> getSingleAuthor(int count){
		Connection conn = Connections.connection();
		return authorDao.getSingleAuthor(conn, count);
	}
	
}
