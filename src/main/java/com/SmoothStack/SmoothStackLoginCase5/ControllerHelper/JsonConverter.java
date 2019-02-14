package com.SmoothStack.SmoothStackLoginCase5.ControllerHelper;

import java.util.HashMap;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.SmoothStack.SmoothStackLoginCase5.Entity.Author;
import com.SmoothStack.SmoothStackLoginCase5.Entity.Book;
import com.SmoothStack.SmoothStackLoginCase5.Entity.BookCopies;
import com.SmoothStack.SmoothStackLoginCase5.Entity.BookLoan;
import com.SmoothStack.SmoothStackLoginCase5.Entity.Borrower;
import com.SmoothStack.SmoothStackLoginCase5.Entity.LibraryBranch;
import com.SmoothStack.SmoothStackLoginCase5.Entity.Publisher;
import com.SmoothStack.SmoothStackLoginCase5.Entity.User;

public class JsonConverter {

	private final Gson gson;
	
	public JsonConverter () {
		gson= new GsonBuilder().create();
	}
	
	public String convertToJson(List<?> user) {
		JsonArray jarray = gson.toJsonTree(user).getAsJsonArray();
		JsonObject jsonObject = new JsonObject();
		jsonObject.add("Users", jarray);
		
		return jsonObject.toString();
	}
	
	public String convertAuthorListToJson(List<Author> author) {
		JsonArray jarray = gson.toJsonTree(author).getAsJsonArray();
		JsonObject jsonObject = new JsonObject();
		jsonObject.add("Author", jarray);
		
		return jsonObject.toString();
	}
	
	public String convertPublisherListToJson(List<Publisher> publisher) {
		JsonArray jarray = gson.toJsonTree(publisher).getAsJsonArray();
		JsonObject jsonObject = new JsonObject();
		jsonObject.add("Publisher", jarray);
		
		return jsonObject.toString();
	}
	
	public String convertLibraryBranchListToJson(List<LibraryBranch> libraryBranch) {
		JsonArray jarray = gson.toJsonTree(libraryBranch).getAsJsonArray();
		JsonObject jsonObject = new JsonObject();
		jsonObject.add("LibraryBranch", jarray);
		
		return jsonObject.toString();
	}
	
	public String convertBorrowerListToJson(List<Borrower> borrower) {
		JsonArray jarray = gson.toJsonTree(borrower).getAsJsonArray();
		JsonObject jsonObject = new JsonObject();
		jsonObject.add("Borrower", jarray);
		
		return jsonObject.toString();
	}
	
	public String convertBookListToJson(List<Book> book) {
		JsonArray jarray = gson.toJsonTree(book).getAsJsonArray();
		JsonObject jsonObject = new JsonObject();
		jsonObject.add("Book", jarray);
		
		return jsonObject.toString();
	}
	
	public String convertBookCopiesListToJson(List<BookCopies> bookCopies) {
		JsonArray jarray = gson.toJsonTree(bookCopies).getAsJsonArray();
		JsonObject jsonObject = new JsonObject();
		jsonObject.add("BookCopies", jarray);
		
		return jsonObject.toString();
	}
	
	public String convertBookLoanListToJson(List<BookLoan> bookLoan) {
		JsonArray jarray = gson.toJsonTree(bookLoan).getAsJsonArray();
		JsonObject jsonObject = new JsonObject();
		jsonObject.add("BookLoan", jarray);
		
		return jsonObject.toString();
	}
	
	public String getValuesForGivenKey(List<User> user, String key) {
		System.out.println("Looking for feedback");
	    HashMap<String, User> hashMap = new HashMap<String, User>();
	    for (int i = 0; i < user.size(); i++) {
	        hashMap.put(user.get(i).getUserName(), user.get(i));
	    }
        User info = hashMap.get(user.get(0).getUserName());
        String result = null;
        if(key.equals("userId")){
        	result = String.valueOf(info.getUserId());
        }
        else if (key.equals("userName")) {
        	result = info.getUserName();
        } else if (key.equals("userPassword")) {
        	result = info.getUserPassword();
        } else {
        	result = "empty";
        }
        System.out.println(key + " | " + user);
        System.out.println(result);
	    return result == null? "empty" : result;
	}

}
