package com.SmoothStack.SmoothStackLoginCase5.Controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.SmoothStack.SmoothStackLoginCase5.Entity.BookLoan;
import com.SmoothStack.SmoothStackLoginCase5.Exception.ResourceNotFoundException;
import com.SmoothStack.SmoothStackLoginCase5.Repository.BookLoanRepository;

@RestController
@RequestMapping("/lms")
public class BookLoanController {
	
	@Autowired
	BookLoanRepository bookLoanRepository;
	
	//Get All BookLoan
//	@RequestMapping(value="/notes", method=RequestMethod.GET)
	@GetMapping("/bookLoans")
	public List<BookLoan> getAllBookLoan(){
		return bookLoanRepository.findAll();
	}
	
	//Create a new bookLoan
	@PostMapping("/bookLoan")
	public BookLoan createBookLoan(@Valid @RequestBody BookLoan bookLoan) {
		return bookLoanRepository.save(bookLoan);
		
	}
	
	//Get a Single BookLoan
	@GetMapping("/bookLoan/{id}")
	public BookLoan getNoteById(@PathVariable(value = "id") int bookLoanId) {
		return bookLoanRepository.findById(bookLoanId)
				.orElseThrow(()-> new ResourceNotFoundException("BookLoan", "id", bookLoanId));
	}
	
	//Get a Single BookLoan by line within BookLoans
	@GetMapping("/bookLoans/{line}")
	public List<BookLoan> getBookLoanByLine(@PathVariable(value = "line") int line) {
		Page<BookLoan> bookLoanPage = bookLoanRepository.findAll(PageRequest.of(line-1, 1));
		List<BookLoan> bookLoan = bookLoanPage.getContent();
		return bookLoan;
	}
	
	//Update a BookLoan
	@PutMapping("/bookLoan/{bookId}/{libraryBranchId}/{cardNo}")
	public BookLoan updateBookLoan(@PathVariable(value = "bookId") int bookId,
							@PathVariable(value = "libraryBranchId") int libraryBranchId,
							@PathVariable(value = "cardNo") int cardNo,
							@Valid @RequestBody BookLoan bookLoanDetails) {
		BookLoan bookLoan = bookLoanRepository.getByBookIdAndBranchIdAndCardNo(bookId, libraryBranchId, cardNo);
//				.orElseThrow(()-> new ResourceNotFoundException("BookLoan", "id", bookLoanId));
		bookLoan.setDateOut(bookLoanDetails.getDateOut());
		bookLoan.setDueDate(bookLoanDetails.getDueDate());
		bookLoan.setReturned(bookLoanDetails.isReturned());
		bookLoan.setExtended(bookLoanDetails.getExtended());
			
		BookLoan updatedBookLoan = bookLoanRepository.save(bookLoan);
		return updatedBookLoan;
	}
	
	//Delete a BookLoan
	@DeleteMapping("/bookLoan/{id}")
	public ResponseEntity<?> deleteBookLoan(@PathVariable(value = "id") int bookLoanId){
		BookLoan bookLoan = bookLoanRepository.findById(bookLoanId)
				.orElseThrow(()-> new ResourceNotFoundException("BookLoan", "id", bookLoanId));
		bookLoanRepository.delete(bookLoan);
		
		return ResponseEntity.ok().build();
	}
}

