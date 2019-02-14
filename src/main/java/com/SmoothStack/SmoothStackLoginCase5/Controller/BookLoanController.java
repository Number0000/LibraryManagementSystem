package com.SmoothStack.SmoothStackLoginCase5.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.SmoothStack.SmoothStackLoginCase5.ControllerHelper.JsonConverter;
import com.SmoothStack.SmoothStackLoginCase5.Entity.BookLoan;
import com.SmoothStack.SmoothStackLoginCase5.ServiceHelper.BookLoanService;

@RestController
public class BookLoanController {
    
	@Autowired
	BookLoanService bookLoanService;
	
	@RequestMapping(path="/lms/bookLoan", method=RequestMethod.GET)
	public String getBookLoan(){
		List<BookLoan> bookLoan = new BookLoanService().getBookLoan();
		JsonConverter converter = new JsonConverter();
		String output = converter.convertBookLoanListToJson(bookLoan);
		return output;
	}
	
	@RequestMapping(path="/lms/bookLoan/{cardNo}", method=RequestMethod.GET)
	public String getBookLoanByCardNo(@PathVariable(value = "cardNo") int cardNo){
		List<BookLoan> bookLoan = new BookLoanService().getBookLoanByCardNo(cardNo);
		JsonConverter converter = new JsonConverter();
		String output = converter.convertBookLoanListToJson(bookLoan);
		return output;
	}
	
	@RequestMapping(path="/lms/bookLoan/{bookLoan}", method=RequestMethod.POST)
	public boolean addBookLoan(@RequestBody BookLoan bookLoan) {
		return bookLoanService.addBookLoan(bookLoan);
	}
	
//	@RequestMapping(path="/lms/bookLoan/returnList/{bookId}/{branchId}/{cardNo}", method=RequestMethod.GET)
//	public String getBookLoanById(@PathVariable(value = "bookId") int bookId, @PathVariable(value = "branchId") int branchId, @PathVariable(value = "cardNo") int cardNo){
//		List<BookLoan> bookLoan = new BookLoanService().getBookLoanById(bookId, branchId, cardNo);
//		JsonConverter converter = new JsonConverter();
//		String output = converter.convertBookLoanListToJson(bookLoan);
//		return output;
//	}
	
	@RequestMapping(path="/lms/bookLoan/{bookId}/{branchId}/{cardNo}", method=RequestMethod.GET)
	public BookLoan getBookLoanByIdReturn(@PathVariable(value = "bookId") int bookId, @PathVariable(value = "branchId") int branchId, @PathVariable(value = "cardNo") int cardNo) {
		return bookLoanService.getBookLoanByIdReturn(bookId, branchId, cardNo);
	}
	
	@RequestMapping(path="/lms/bookLoan/{bookLoan}", method=RequestMethod.PUT)
	public boolean updateBookLoan(@RequestBody BookLoan bookLoan) {
		return bookLoanService.updateBookLoan(bookLoan);
	}
	
	@RequestMapping(path="/lms/bookLoan/{bookLoan}", method=RequestMethod.DELETE)
	public boolean deleteBookLoan(@RequestBody BookLoan bookLoan) {
		return bookLoanService.deleteBookLoan(bookLoan);
	}
	
	@RequestMapping(path="/lms/bookLoan/line/{lineNo}", method=RequestMethod.GET)
	public String getSingleBookLoan(@PathVariable(value = "lineNo") int count) {
		List<BookLoan> bookLoan = new BookLoanService().getSingleBookLoan(count);
		JsonConverter converter = new JsonConverter();
		String output = converter.convertBookLoanListToJson(bookLoan);
		return output;
	}

}
