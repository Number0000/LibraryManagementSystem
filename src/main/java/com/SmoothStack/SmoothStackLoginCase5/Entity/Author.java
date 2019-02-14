package com.SmoothStack.SmoothStackLoginCase5.Entity;
//db to support jdbctest.java
public class Author {
	private Integer authorId;
	private String authorName;
	public Integer getAuthorId() {
		return authorId;
	}
	public void setAuthorId(Integer authorId) {
		this.authorId = authorId;
	}
	public String getAuthorName() {
		return authorName;
	}
	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}
	
	@Override
	public String toString() {
		return "Author [authorId= " + authorId 
				+ ", authorName= " + authorName + "]";
	}
	
	
}
