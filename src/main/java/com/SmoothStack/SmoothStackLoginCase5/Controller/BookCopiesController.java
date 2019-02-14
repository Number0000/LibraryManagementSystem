package com.SmoothStack.SmoothStackLoginCase5.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.SmoothStack.SmoothStackLoginCase5.ControllerHelper.JsonConverter;
import com.SmoothStack.SmoothStackLoginCase5.Entity.BookCopies;
import com.SmoothStack.SmoothStackLoginCase5.ServiceHelper.BookCopiesService;

@RestController
public class BookCopiesController {
    
	@Autowired
	BookCopiesService bookCopiesService;
	
	@RequestMapping(path="/lms/bookCopies", method=RequestMethod.GET)
	public String getBookCopies(){
		List<BookCopies> bookCopies = new BookCopiesService().getBookCopies();
		JsonConverter converter = new JsonConverter();
		String output = converter.convertToJson(bookCopies);
		return output;
	}
	
	@RequestMapping(path="/lms/bookCopies/{bookCopies}", method=RequestMethod.POST)
	public boolean addBookCopies(@RequestBody BookCopies bookCopies) {
		return bookCopiesService.addBookCopies(bookCopies);
	}
	
//	@RequestMapping(path="/lms/bookCopies/list/{bookId}/{branchId}", method=RequestMethod.GET)
//	public String getBookCopiesById(@PathVariable(value = "bookId") int bookId, @PathVariable(value = "branchId") int branchId){
//		List<BookCopies> bookCopies = new BookCopiesService().getBookCopiesById(bookId, branchId);
//		JsonConverter converter = new JsonConverter();
//		String output = converter.convertToJson(bookCopies);
//		return output;
//	}
	
	@RequestMapping(path="/lms/bookCopies/{bookId}/{branchId}", method=RequestMethod.GET)
	public BookCopies getBookCopiesByIdReturn(@PathVariable(value = "bookId") int bookId, @PathVariable(value = "branchId") int branchId) {
		return bookCopiesService.getBookCopiesByIdReturn(bookId, branchId);
	}
	
	@RequestMapping(path="/lms/bookCopies/{bookCopies}", method=RequestMethod.PUT)
	public boolean updateBookCopies(@RequestBody BookCopies bookCopies) {
		return bookCopiesService.updateBookCopies(bookCopies);
	}
	
	@RequestMapping(path="/lms/bookCopies/{bookCopies}", method=RequestMethod.DELETE)
	public boolean deleteBookCopies(@RequestBody BookCopies bookCopies) {
		return bookCopiesService.deleteBookCopies(bookCopies);
	}
	
	@RequestMapping(path="/lms/bookCopies/line/{lineNo}", method=RequestMethod.GET)
	public String getSingleBookCopies(@PathVariable(value = "lineNo") int count) {
		List<BookCopies> bookCopies = new BookCopiesService().getSingleBookCopies(count);
		JsonConverter converter = new JsonConverter();
		String output = converter.convertToJson(bookCopies);
		return output;
	}

}
