package com.SmoothStack.SmoothStackLoginCase5.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.SmoothStack.SmoothStackLoginCase5.ControllerHelper.JsonConverter;
import com.SmoothStack.SmoothStackLoginCase5.Entity.Book;
import com.SmoothStack.SmoothStackLoginCase5.ServiceHelper.BookService;

@RestController
public class BookController {

	@Autowired
	BookService bookService;
	
	@RequestMapping(path="/lms/book", method=RequestMethod.GET)
	public String getBook(){
		List<Book> book = new BookService().getBook();
		JsonConverter converter = new JsonConverter();
		String output = converter.convertBookListToJson(book);
		return output;
	}
	
	@RequestMapping(path="/lms/book/{book}", method=RequestMethod.POST)
	public boolean addBook(@RequestBody Book book) {
		return bookService.addBook(book);
	}
	
//	@RequestMapping(path="/lms/book/returnList/{bookId}", method=RequestMethod.GET)
//	public String getBookById(@PathVariable(value = "bookId") int bookId){
//		List<Book> book = new BookService().getBookById(bookId);
//		JsonConverter converter = new JsonConverter();
//		String output = converter.convertBookListToJson(book);
//		return output;
//	}
	
	@RequestMapping(path="/lms/book/{bookId}", method=RequestMethod.GET)
	public Book getBookByIdReturn(@PathVariable(value = "bookId") int bookId) {
		return bookService.getBookByIdReturn(bookId);
	}
	
	@RequestMapping(path="/lms/book/{book}", method=RequestMethod.PUT)
	public boolean updateBook(@RequestBody Book book) {
		return bookService.updateBook(book);
	}
	
	@RequestMapping(path="/lms/book/{book}", method=RequestMethod.DELETE)
	public boolean deleteBook(@RequestBody Book book) {
		return bookService.deleteBook(book);
	}
	
	@RequestMapping(path="/lms/book/line/{lineNo}", method=RequestMethod.GET)
	public String getSingleBook(@PathVariable(value = "lineNo") int count) {
		List<Book> book = new BookService().getSingleBook(count);
		JsonConverter converter = new JsonConverter();
		String output = converter.convertBookListToJson(book);
		return output;
	}
	
}
