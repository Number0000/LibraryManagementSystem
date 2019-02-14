package com.SmoothStack.SmoothStackLoginCase5.Dao;

import java.sql.Connection;
import java.util.List;

import org.springframework.stereotype.Component;

import com.SmoothStack.SmoothStackLoginCase5.Entity.Author;

@Component
public interface IAuthorDao {
	public List<Author> findAll(Connection conn);
	public boolean addAuthor(Connection conn, Author author);
	public List<Author> getAuthorById(Connection conn, int authorId);
	public Author getAuthorByIdReturn(Connection conn, int authorId);
	public boolean updateAuthor(Connection conn, Author author);
	public boolean deleteAuthorById(Connection conn, Author author);
	public List<Author> getSingleAuthor(Connection conn, int count);
		
}
