package com.SmoothStack.SmoothStackLoginCase5.Service;

import java.util.List;
import java.util.Optional;

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
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

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
	public ResponseEntity<Author> addAuthor(@Valid @RequestBody Author author) {
		authorRepository.save(author);
		return new ResponseEntity<Author>(author, HttpStatus.CREATED);
	}
	
	@PostMapping("/book")
	public ResponseEntity<Book> addBook(@Valid @RequestBody Book book) {
		int authorId = book.getAuthor().getAuthorId();
		Author author = authorRepository.findById(authorId)
				.orElseThrow(()-> new ResourceNotFoundException("Author", "id", authorId));
		int publisherId = book.getPublisher().getPublisherId();
		Publisher publisher = publisherRepository.findById(publisherId)
				.orElseThrow(()-> new ResourceNotFoundException("Publisher", "id", publisherId));
		Book newbook = new Book();
		newbook.setAuthor(author);
		newbook.setPublisher(publisher);
		newbook.setTitle(book.getTitle());
		bookRepository.save(newbook);
		return new ResponseEntity<Book>(newbook, HttpStatus.CREATED);
	}
	
	@PostMapping("/bookCopy")
	public ResponseEntity<BookCopy> addBookCopy(@RequestParam(value = "bookId", defaultValue = "543", required = true) int bookId,
			@RequestParam(value = "branchId", defaultValue = "1", required = true) int libraryBranchId,
			@RequestBody BookCopy bookCopy) {
		Book book = bookRepository.findById(bookId)
				.orElseThrow(()-> new ResourceNotFoundException("Book", "id", bookId));
		System.out.println("-----------" + book);
		LibraryBranch libraryBranch = libraryBranchRepository.findById(libraryBranchId)
				.orElseThrow(()-> new ResourceNotFoundException("LibraryBranch", "id", libraryBranchId));
		System.out.println("-----------" + libraryBranch);
	    	    
		BookCopy newbookcopy = new BookCopy();
		newbookcopy.setBook(book);;
		newbookcopy.setLibraryBranch(libraryBranch);;
		newbookcopy.setNoOfCopies(bookCopy.getNoOfCopies());
		System.out.println("-----------" + bookCopy);
		bookCopyRepository.save(bookCopy);
		return new ResponseEntity<BookCopy>(bookCopy, HttpStatus.CREATED);
	}
	
	@PostMapping("/bookLoan")
	public ResponseEntity<BookLoan> addBookLoan(@Valid @RequestBody BookLoan bookLoan) {
		bookLoanRepository.save(bookLoan);
		return new ResponseEntity<BookLoan>(bookLoan, HttpStatus.CREATED);
	}
	
	@PostMapping("/borrower")
	public ResponseEntity<Borrower> addBorrower(@Valid @RequestBody Borrower borrower) {
		borrowerRepository.save(borrower);
		return new ResponseEntity<Borrower>(borrower, HttpStatus.CREATED);
	}
	
	@PostMapping("/libraryBranch")
	public ResponseEntity<LibraryBranch> addLibraryBranch(@Valid @RequestBody LibraryBranch libraryBranch) {
		libraryBranchRepository.save(libraryBranch);
		return new ResponseEntity<LibraryBranch>(HttpStatus.CREATED);
	}
	
	@PostMapping("/publisher")
	public ResponseEntity<Publisher> addPublisher(@Valid @RequestBody Publisher publisher) {
		publisherRepository.save(publisher);
		return new ResponseEntity<Publisher>(publisher, HttpStatus.CREATED);
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
	
	@PutMapping("/bookCopy/{bookId}/{libraryBranchId}")
	public ResponseEntity<BookCopy> updateBookCopy(@PathVariable(value = "bookId") int bookId,
			@PathVariable(value = "libraryBranchId") int libraryBranchId,
			@Valid @RequestBody BookCopy bookCopyDetails){
		BookCopy bookCopy = bookCopyRepository.getByBookIdAndBranchId(bookId, libraryBranchId);
//				.orElseThrow(()-> new ResourceNotFoundException("Author", "id", authorId));
		bookCopy.setNoOfCopies(bookCopyDetails.getNoOfCopies());
		
		bookCopyRepository.saveAndFlush(bookCopy);
		return new ResponseEntity<BookCopy>(bookCopy, HttpStatus.OK);
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
		return new ResponseEntity<BookLoan>(bookLoan, HttpStatus.OK);
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
	
	@DeleteMapping("/bookCopy/{bookId}/{libraryBranchId}")
	public ResponseEntity<BookCopy> deleteBookCopy(@PathVariable(value = "bookId") int bookId,
			@PathVariable(value = "libraryBranchId") int libraryBranchId){
		BookCopy bookCopy = bookCopyRepository.getByBookIdAndBranchId(bookId, libraryBranchId);
//				.orElseThrow(()-> new ResourceNotFoundException("bookCopy", "id", authorId));
		bookCopyRepository.delete(bookCopy);
		
		return new ResponseEntity<BookCopy>(HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping("/bookLoan/{bookId}/{libraryBranchId}/{cardNo}")
	public ResponseEntity<BookLoan> deleteBookLoan(@PathVariable(value = "bookId") int bookId,
			@PathVariable(value = "libraryBranchId") int libraryBranchId,
			@PathVariable(value = "cardNo") int cardNo){
		BookLoan bookLoan = bookLoanRepository.getByBookIdAndBranchIdAndCardNo(bookId, libraryBranchId, cardNo);
//				.orElseThrow(()-> new ResourceNotFoundException("BookLoan", "id", authorId));
		bookLoanRepository.delete(bookLoan);
		
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
	@PutMapping("/extendBookLoan/{bookId}/{libraryBranchId}/{cardNo}")
	public ResponseEntity<BookLoan> extendBookLoan(@PathVariable(value = "bookId") int bookId,
			@PathVariable(value = "libraryBranchId") int libraryBranchId,
			@PathVariable(value = "cardNo") int cardNo){
		BookLoan bookLoan = bookLoanRepository.getByBookIdAndBranchIdAndCardNo(bookId, libraryBranchId, cardNo);
//				.orElseThrow(()-> new ResourceNotFoundException("Author", "id", authorId));
		if (bookLoan.getExtended() > 3 || bookLoan.isReturned() == true) {
			return new ResponseEntity<BookLoan>(bookLoan, HttpStatus.ALREADY_REPORTED);
		}
		else if(bookLoan.getExtended() < 3 && bookLoan.getExtended() > 0) {
			bookLoan.setDateOutToDueDate(bookLoan.getDueDate());
			bookLoan.setDueDateExtend7Day(bookLoan.getDueDate());
			bookLoan.setExtended1Time(bookLoan.getExtended());
		
			bookLoanRepository.saveAndFlush(bookLoan);
		}
		return new ResponseEntity<BookLoan>(bookLoan, HttpStatus.OK);
	}
	
}

