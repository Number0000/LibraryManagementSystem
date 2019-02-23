package com.SmoothStack.SmoothStackLoginCase5.Service;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.SmoothStack.SmoothStackLoginCase5.Entity.Book;
import com.SmoothStack.SmoothStackLoginCase5.Entity.BookCopy;
import com.SmoothStack.SmoothStackLoginCase5.Entity.BookLoan;
import com.SmoothStack.SmoothStackLoginCase5.Entity.LibraryBranch;
import com.SmoothStack.SmoothStackLoginCase5.Repository.BookCopyRepository;
import com.SmoothStack.SmoothStackLoginCase5.Repository.BookLoanRepository;
import com.SmoothStack.SmoothStackLoginCase5.Repository.BookRepository;
import com.SmoothStack.SmoothStackLoginCase5.Repository.LibraryBranchRepository;

@RestController
@RequestMapping("/borrower")
public class BorrowerService {
	
	@Autowired
	BookRepository bookRepository;
	
	@Autowired
	BookCopyRepository bookCopyRepository;
	
	@Autowired
	BookLoanRepository bookLoanRepository;
	
	@Autowired
	LibraryBranchRepository libraryBranchRepository;
	
	//Display
	@GetMapping("/book")
	public ResponseEntity<List<Book>> displayBook() {
		List<Book> list = bookRepository.findAll();
		return new ResponseEntity<List<Book>>(list, HttpStatus.OK);
	}
	
	@GetMapping("/bookCopy")
	public ResponseEntity<List<BookCopy>> displayBookCopy() {
		List<BookCopy> list = bookCopyRepository.findAll();
		return new ResponseEntity<List<BookCopy>>(list, HttpStatus.OK);
	}
	
	@GetMapping("/bookLoan")
	public ResponseEntity<List<BookLoan>> displayBookLoan() {
		List<BookLoan> list = bookLoanRepository.findAll();
		return new ResponseEntity<List<BookLoan>>(list, HttpStatus.OK);
	}
	
	@GetMapping("/libraryBranch")
	public ResponseEntity<List<LibraryBranch>> displayLibraryBranch() {
		List<LibraryBranch> list = libraryBranchRepository.findAll();
		return new ResponseEntity<List<LibraryBranch>>(list, HttpStatus.OK);
	}
	
	//Add
	
	@PostMapping("/bookLoan")
	public ResponseEntity<BookLoan> addBookLoan(@Valid @RequestBody BookLoan bookLoanDetails) {
		BookCopy bookCopy = bookCopyRepository.getByBookIdAndBranchId(bookLoanDetails.getBook().getBookId(), 
				bookLoanDetails.getLibraryBranch().getLibraryBranchId());
		if(bookCopy.getNoOfCopies() < 0)
			return new ResponseEntity<BookLoan>(HttpStatus.ALREADY_REPORTED);
		else {
			bookLoanRepository.save(bookLoanDetails);
			bookCopy.decrementNumberOfCopies();
			bookCopyRepository.save(bookCopy);
		}
		return new ResponseEntity<BookLoan>(HttpStatus.CREATED);
	}
	
	@PutMapping("/returnBookLoan/{bookId}/{libraryBranchId}/{cardNo}")
	public ResponseEntity<BookLoan> returnBookLoan(@PathVariable(value = "bookId") int bookId,
			@PathVariable(value = "libraryBranchId") int libraryBranchId,
			@PathVariable(value = "cardNo") int cardNo){
		BookLoan bookLoan = bookLoanRepository.getByBookIdAndBranchIdAndCardNo(bookId, libraryBranchId, cardNo);
//				.orElseThrow(()-> new ResourceNotFoundException("Author", "id", authorId));
		bookLoan.setReturned(true);
		bookLoanRepository.saveAndFlush(bookLoan);
		BookCopy bookCopy = bookCopyRepository.getByBookIdAndBranchId(bookId, libraryBranchId);
		bookCopy.incrementNumberOfCopies();
		bookCopyRepository.saveAndFlush(bookCopy);
		return new ResponseEntity<BookLoan>(HttpStatus.OK);
	}
}

