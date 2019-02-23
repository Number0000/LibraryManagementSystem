package com.SmoothStack.SmoothStackLoginCase5.Service;

import java.time.LocalDate;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.SmoothStack.SmoothStackLoginCase5.Entity.Author;
import com.SmoothStack.SmoothStackLoginCase5.Entity.Book;
import com.SmoothStack.SmoothStackLoginCase5.Entity.BookCopy;
import com.SmoothStack.SmoothStackLoginCase5.Entity.BookLoan;
import com.SmoothStack.SmoothStackLoginCase5.Entity.Borrower;
import com.SmoothStack.SmoothStackLoginCase5.Entity.LibraryBranch;
import com.SmoothStack.SmoothStackLoginCase5.Entity.Publisher;
import com.SmoothStack.SmoothStackLoginCase5.Exception.ResourceNotFoundException;
import com.SmoothStack.SmoothStackLoginCase5.Repository.AuthorRepository;
import com.SmoothStack.SmoothStackLoginCase5.Repository.BookCopyRepository;
import com.SmoothStack.SmoothStackLoginCase5.Repository.BookLoanRepository;
import com.SmoothStack.SmoothStackLoginCase5.Repository.BookRepository;
import com.SmoothStack.SmoothStackLoginCase5.Repository.BorrowerRepository;
import com.SmoothStack.SmoothStackLoginCase5.Repository.LibraryBranchRepository;
import com.SmoothStack.SmoothStackLoginCase5.Repository.PublisherRepository;

@RestController
@RequestMapping("/admin")
public class AdminService {
	@Autowired
	AuthorRepository authorRepository;
	
	@Autowired
	BookRepository bookRepository;
	
	@Autowired
	BookCopyRepository bookCopyRepository;
	
	@Autowired
	BookLoanRepository bookLoanRepository;
	
	@Autowired
	BorrowerRepository borrowerRepository;
	
	@Autowired
	LibraryBranchRepository libraryBranchRepository;
	
	@Autowired
	PublisherRepository publisherRepository;
	
	//Display
	@GetMapping("/author")
	public ResponseEntity<List<Author>> displayAuthor() {
		List<Author> list = authorRepository.findAll();
		return new ResponseEntity<List<Author>>(list, HttpStatus.OK);
	}
	
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
	
	@GetMapping("/borrower")
	public ResponseEntity<List<Borrower>> displayBorrower() {
		List<Borrower> list = borrowerRepository.findAll();
		return new ResponseEntity<List<Borrower>>(list, HttpStatus.OK);
	}
	
	@GetMapping("/libraryBranch")
	public ResponseEntity<List<LibraryBranch>> displayLibraryBranch() {
		List<LibraryBranch> list = libraryBranchRepository.findAll();
		return new ResponseEntity<List<LibraryBranch>>(list, HttpStatus.OK);
	}
	
	@GetMapping("/publisher")
	public ResponseEntity<List<Publisher>> displayPublisher() {
		List<Publisher> list = publisherRepository.findAll();
		return new ResponseEntity<List<Publisher>>(list, HttpStatus.OK);
	}
	
	//Add
	@PostMapping("/author")
	public ResponseEntity<Author> addAuthor(@Valid @RequestBody Author authorDetails) {
		authorRepository.save(authorDetails);
		return new ResponseEntity<Author>(authorDetails, HttpStatus.CREATED);
	}
	
	@PostMapping("/book")
	public ResponseEntity<Book> addBook(@Valid @RequestBody Book bookDetails) {
		int authorId = bookDetails.getAuthor().getAuthorId();
		Author author = authorRepository.findById(authorId)
				.orElseThrow(()-> new ResourceNotFoundException("Author", "id", authorId));
		int publisherId = bookDetails.getPublisher().getPublisherId();
		Publisher publisher = publisherRepository.findById(publisherId)
				.orElseThrow(()-> new ResourceNotFoundException("Publisher", "id", publisherId));
		Book book = new Book();
		book.setAuthor(author);
		book.setPublisher(publisher);
		book.setTitle(bookDetails.getTitle());
		bookRepository.save(book);
		return new ResponseEntity<Book>(book, HttpStatus.CREATED);
	}
	
	//Query Param for limit scope
	@PostMapping("/bookCopy")
	public ResponseEntity<BookCopy> addBookCopy(@RequestParam(value = "bookId", defaultValue = "543", required = true) int bookId,
			@RequestParam(value = "branchId", defaultValue = "1", required = true) int libraryBranchId,
			@Valid @RequestBody BookCopy bookCopyDetails) {
		Book book = bookRepository.findById(bookId)
				.orElseThrow(()-> new ResourceNotFoundException("Book", "id", bookId));
		LibraryBranch libraryBranch = libraryBranchRepository.findById(libraryBranchId)
				.orElseThrow(()-> new ResourceNotFoundException("LibraryBranch", "id", libraryBranchId));
		
		BookCopy bookcopy = new BookCopy(book, libraryBranch);
		bookcopy.setNoOfCopies(bookCopyDetails.getNoOfCopies());
		bookCopyRepository.save(bookcopy);
		return new ResponseEntity<BookCopy>(bookcopy, HttpStatus.CREATED);
	}
	
	//Query Param for limit scope
	@PostMapping("/bookLoan")
	public ResponseEntity<BookLoan> addBookLoan(@RequestParam(value = "bookId", defaultValue = "543", required = true) int bookId,
			@RequestParam(value = "branchId", defaultValue = "1", required = true) int libraryBranchId,
			@RequestParam(value = "cardNo", defaultValue = "1", required = true) int cardNo) {
		Book book = bookRepository.findById(bookId)
				.orElseThrow(()-> new ResourceNotFoundException("Book", "id", bookId));
		LibraryBranch libraryBranch = libraryBranchRepository.findById(libraryBranchId)
				.orElseThrow(()-> new ResourceNotFoundException("LibraryBranch", "id", libraryBranchId));
		Borrower borrower = borrowerRepository.findById(cardNo)
				.orElseThrow(()-> new ResourceNotFoundException("Borrower", "id", cardNo));
		//check if library have book copies
		BookCopy bookCopy = bookCopyRepository.getByBookIdAndBranchId(bookId, libraryBranchId);
		if (bookCopy.getNoOfCopies() > 0) {
			BookLoan bookloan = new BookLoan(book, libraryBranch, borrower);
			bookloan.setDateOut(LocalDate.now());
			bookloan.setDueDateExtend7Day(LocalDate.now());
			bookloan.setReturned(false);
			bookloan.setExtended(0);
			bookLoanRepository.save(bookloan);
			return new ResponseEntity<BookLoan>(bookloan, HttpStatus.CREATED);
		} else 
			return new ResponseEntity<BookLoan>(HttpStatus.CONFLICT);
	}
	
	@PostMapping("/borrower")
	public ResponseEntity<Borrower> addBorrower(@Valid @RequestBody Borrower borrowerDetails) {
		borrowerRepository.save(borrowerDetails);
		return new ResponseEntity<Borrower>(borrowerDetails, HttpStatus.CREATED);
	}
	
	@PostMapping("/libraryBranch")
	public ResponseEntity<LibraryBranch> addLibraryBranch(@Valid @RequestBody LibraryBranch libraryBranchDetails) {
		libraryBranchRepository.save(libraryBranchDetails);
		return new ResponseEntity<LibraryBranch>(libraryBranchDetails, HttpStatus.CREATED);
	}
	
	@PostMapping("/publisher")
	public ResponseEntity<Publisher> addPublisher(@Valid @RequestBody Publisher publisherDetails) {
		publisherRepository.save(publisherDetails);
		return new ResponseEntity<Publisher>(publisherDetails, HttpStatus.CREATED);
	}
	
	//Update
	@PutMapping("/author/{id}")
	public ResponseEntity<Author> updateAuthor(@PathVariable(value = "id") int authorId,
			@Valid @RequestBody Author authorDetails){
		Author author = authorRepository.findById(authorId)
				.orElseThrow(()-> new ResourceNotFoundException("Author", "id", authorId));
		author.setAuthorName(authorDetails.getAuthorName());
		
		authorRepository.saveAndFlush(author);
		return new ResponseEntity<Author>(author, HttpStatus.OK);
	}
	
	@PutMapping("/book/{id}")
	public ResponseEntity<Book> updateBook(@PathVariable(value = "id") int bookId,
			@Valid @RequestBody Book bookDetails){
		Book book = bookRepository.findById(bookId)
				.orElseThrow(()-> new ResourceNotFoundException("Book", "id", bookId));
		book.setTitle(bookDetails.getTitle());
		
		bookRepository.saveAndFlush(book);
		return new ResponseEntity<Book>(book, HttpStatus.OK);
	}
	
	//Query Param for limit scope
	@PutMapping("/bookCopy")
	public ResponseEntity<BookCopy> updateBookCopy(@RequestParam(value = "bookId", defaultValue = "543", required = true) int bookId,
			@RequestParam(value = "branchId", defaultValue = "1", required = true) int libraryBranchId,
			@Valid @RequestBody BookCopy bookCopyDetails){
		Book book = bookRepository.findById(bookId)
				.orElseThrow(()-> new ResourceNotFoundException("Book", "id", bookId));
		LibraryBranch libraryBranch = libraryBranchRepository.findById(libraryBranchId)
				.orElseThrow(()-> new ResourceNotFoundException("LibraryBranch", "id", libraryBranchId));
		
		BookCopy bookcopy = new BookCopy(book, libraryBranch);
		bookcopy.setNoOfCopies(bookCopyDetails.getNoOfCopies());
		bookCopyRepository.saveAndFlush(bookcopy);
		return new ResponseEntity<BookCopy>(bookcopy, HttpStatus.OK);
	}
	
	//Query Param for limit scope
	@PutMapping("/returnBookLoan")
	public ResponseEntity<BookLoan> returnBookLoan(@RequestParam(value = "bookId", defaultValue = "543", required = true) int bookId,
			@RequestParam(value = "branchId", defaultValue = "1", required = true) int libraryBranchId,
			@RequestParam(value = "cardNo", defaultValue = "1", required = true) int cardNo){
		Book book = bookRepository.findById(bookId)
				.orElseThrow(()-> new ResourceNotFoundException("Book", "id", bookId));
		LibraryBranch libraryBranch = libraryBranchRepository.findById(libraryBranchId)
				.orElseThrow(()-> new ResourceNotFoundException("LibraryBranch", "id", libraryBranchId));
		Borrower borrower = borrowerRepository.findById(cardNo)
				.orElseThrow(()-> new ResourceNotFoundException("Borrower", "id", cardNo));
		
		BookLoan bookloan = new BookLoan(book, libraryBranch, borrower);
		if(bookloan.isReturned() == false) {
			bookloan.setReturned(true);
			bookLoanRepository.saveAndFlush(bookloan);
			BookCopy bookCopy = bookCopyRepository.getByBookIdAndBranchId(bookId, libraryBranchId);
			bookCopy.incrementNumberOfCopies();
			bookCopyRepository.saveAndFlush(bookCopy);
			return new ResponseEntity<BookLoan>(bookloan, HttpStatus.OK);
		} else {
			return new ResponseEntity<BookLoan>(bookloan, HttpStatus.ALREADY_REPORTED);
		}
	}
	
	@PutMapping("/borrower/{id}")
	public ResponseEntity<Borrower> updateBorrower(@PathVariable(value = "id") int cardNo,
			@Valid @RequestBody Borrower borrowerDetails){
		Borrower borrower = borrowerRepository.findById(cardNo)
				.orElseThrow(()-> new ResourceNotFoundException("Borrower", "id", cardNo));
		borrower.setBorrowerName(borrowerDetails.getBorrowerName());
		borrower.setBorrowerAddress(borrowerDetails.getBorrowerAddress());
		borrower.setBorrowerPhone(borrowerDetails.getBorrowerPhone());
		borrower.setBorrowerUserName(borrowerDetails.getBorrowerUserName());
		borrower.setBorrowerPassword(borrowerDetails.getBorrowerPassword());
		
		borrowerRepository.saveAndFlush(borrower);
		return new ResponseEntity<Borrower>(borrower, HttpStatus.OK);
	}
	
	@PutMapping("/libraryBranch/{id}")
	public ResponseEntity<LibraryBranch> updateLibraryBranch(@PathVariable(value = "id") int libraryBranchId,
			@Valid @RequestBody LibraryBranch libraryBranchDetails){
		LibraryBranch libraryBranch = libraryBranchRepository.findById(libraryBranchId)
				.orElseThrow(()-> new ResourceNotFoundException("LibraryBranch", "id", libraryBranchId));
		libraryBranch.setLibraryBranchName(libraryBranchDetails.getLibraryBranchName());
		libraryBranch.setLibraryBranchAddress(libraryBranchDetails.getLibraryBranchAddress());
		
		libraryBranchRepository.saveAndFlush(libraryBranch);
		return new ResponseEntity<LibraryBranch>(libraryBranch, HttpStatus.OK);
	}
	
	@PutMapping("/publisher/{id}")
	public ResponseEntity<Publisher> updatePublisher(@PathVariable(value = "id") int publisherId,
			@Valid @RequestBody Publisher publisherDetails){
		Publisher publisher = publisherRepository.findById(publisherId)
				.orElseThrow(()-> new ResourceNotFoundException("Publisher", "id", publisherId));
		publisher.setPublisherName(publisherDetails.getPublisherName());
		publisher.setPublisherAddress(publisherDetails.getPublisherAddress());
		publisher.setPublisherPhone(publisherDetails.getPublisherPhone());
		
		publisherRepository.saveAndFlush(publisher);
		return new ResponseEntity<Publisher>(publisher, HttpStatus.OK);
	}
	
	//Delete
	@DeleteMapping("/author/{id}")
	public ResponseEntity<Author> deleteAuthor(@PathVariable(value = "id") int authorId){
		Author author = authorRepository.findById(authorId)
				.orElseThrow(()-> new ResourceNotFoundException("Author", "id", authorId));
		authorRepository.delete(author);
		
		return new ResponseEntity<Author>(HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping("/book/{id}")
	public ResponseEntity<Book> deleteBook(@PathVariable(value = "id") int bookId){
		Book book = bookRepository.findById(bookId)
				.orElseThrow(()-> new ResourceNotFoundException("Book", "id", bookId));
		bookRepository.delete(book);
		
		return new ResponseEntity<Book>(HttpStatus.ACCEPTED);
	}
	
	//Query Param
	@DeleteMapping("/bookCopy")
	public ResponseEntity<BookCopy> deleteBookCopy(@RequestParam(value = "bookId", defaultValue = "543", required = true) int bookId,
			@RequestParam(value = "branchId", defaultValue = "1", required = true) int libraryBranchId){
		Book book = bookRepository.findById(bookId)
				.orElseThrow(()-> new ResourceNotFoundException("Book", "id", bookId));
		LibraryBranch libraryBranch = libraryBranchRepository.findById(libraryBranchId)
				.orElseThrow(()-> new ResourceNotFoundException("LibraryBranch", "id", libraryBranchId));
		
		BookCopy bookCopy = bookCopyRepository.getByBookIdAndBranchId(bookId, libraryBranchId);
//				.orElseThrow(()-> new ResourceNotFoundException("bookCopy", "id", authorId));
		bookCopyRepository.delete(bookCopy);
		
		return new ResponseEntity<BookCopy>(HttpStatus.ACCEPTED);
	}
	
	//QueryParam
	@DeleteMapping("/bookLoan")
	public ResponseEntity<BookLoan> deleteBookLoan(@RequestParam(value = "bookId", defaultValue = "543", required = true) int bookId,
			@RequestParam(value = "branchId", defaultValue = "1", required = true) int libraryBranchId,
			@RequestParam(value = "cardNo", defaultValue = "1", required = true) int cardNo){
		Book book = bookRepository.findById(bookId)
				.orElseThrow(()-> new ResourceNotFoundException("Book", "id", bookId));
		LibraryBranch libraryBranch = libraryBranchRepository.findById(libraryBranchId)
				.orElseThrow(()-> new ResourceNotFoundException("LibraryBranch", "id", libraryBranchId));
		Borrower borrower = borrowerRepository.findById(cardNo)
				.orElseThrow(()-> new ResourceNotFoundException("Borrower", "id", cardNo));
		
		BookLoan bookloan = bookLoanRepository.getByBookIdAndBranchIdAndCardNo(bookId, libraryBranchId, cardNo);
		bookLoanRepository.delete(bookloan);
		
		return new ResponseEntity<BookLoan>(HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping("/borrower/{id}")
	public ResponseEntity<Borrower> deleteBorrower(@PathVariable(value = "id") int cardNo){
		Borrower borrower = borrowerRepository.findById(cardNo)
				.orElseThrow(()-> new ResourceNotFoundException("Borrower", "id", cardNo));
		borrowerRepository.delete(borrower);
		
		return new ResponseEntity<Borrower>(HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping("/libraryBranch/{id}")
	public ResponseEntity<LibraryBranch> deleteLibraryBranch(@PathVariable(value = "id") int libraryBranchId){
		LibraryBranch libraryBranch = libraryBranchRepository.findById(libraryBranchId)
				.orElseThrow(()-> new ResourceNotFoundException("LibraryBranch", "id", libraryBranchId));
		libraryBranchRepository.delete(libraryBranch);
		
		return new ResponseEntity<LibraryBranch>(HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping("/publisher/{id}")
	public ResponseEntity<Publisher> deletePublisher(@PathVariable(value = "id") int publisherId){
		Publisher publisher = publisherRepository.findById(publisherId)
				.orElseThrow(()-> new ResourceNotFoundException("Publisher", "id", publisherId));
		publisherRepository.delete(publisher);
		
		return new ResponseEntity<Publisher>(HttpStatus.ACCEPTED);
	}
	
	//Override
	//Query param for limit scope
	@PutMapping("/extendBookLoan")
	public ResponseEntity<BookLoan> extendBookLoan(@RequestParam(value = "bookId", defaultValue = "543", required = true) int bookId,
			@RequestParam(value = "branchId", defaultValue = "1", required = true) int libraryBranchId,
			@RequestParam(value = "cardNo", defaultValue = "1", required = true) int cardNo){
		Book book = bookRepository.findById(bookId)
				.orElseThrow(()-> new ResourceNotFoundException("Book", "id", bookId));
		LibraryBranch libraryBranch = libraryBranchRepository.findById(libraryBranchId)
				.orElseThrow(()-> new ResourceNotFoundException("LibraryBranch", "id", libraryBranchId));
		Borrower borrower = borrowerRepository.findById(cardNo)
				.orElseThrow(()-> new ResourceNotFoundException("Borrower", "id", cardNo));
		
		BookLoan bookloan = bookLoanRepository.getByBookIdAndBranchIdAndCardNo(bookId, libraryBranchId, cardNo);
		if(bookloan.getExtended() <= 3 && bookloan.getExtended() > 0) {
			bookloan.setDateOutToDueDate(bookloan.getDueDate());
			bookloan.setDueDateExtend7Day(bookloan.getDueDate());
			bookloan.setExtended1Time(bookloan.getExtended());
		
			bookLoanRepository.saveAndFlush(bookloan);
			return new ResponseEntity<BookLoan>(bookloan, HttpStatus.OK);
		} else {
			return new ResponseEntity<BookLoan>(bookloan, HttpStatus.CONFLICT);
		}
	}
	
}

