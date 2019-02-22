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

import com.SmoothStack.SmoothStackLoginCase5.Entity.Book;
import com.SmoothStack.SmoothStackLoginCase5.Exception.ResourceNotFoundException;
import com.SmoothStack.SmoothStackLoginCase5.Repository.BookRepository;

@RestController
@RequestMapping("/lms")
public class BookController {
	
	@Autowired
	BookRepository bookRepository;
	
	//Get All Book
//	@RequestMapping(value="/books", method=RequestMethod.GET)
	@GetMapping("/books")
	public List<Book> getAllBook(){
		return bookRepository.findAll();
	}
	
	//Create a new book
	@PostMapping("/book")
	public Book createBook(@Valid @RequestBody Book book) {
		return bookRepository.save(book);
		
	}
	
	//Get a Single Book
	@GetMapping("/book/{id}")
	public Book getBookById(@PathVariable(value = "id") int bookId) {
		return bookRepository.findById(bookId)
				.orElseThrow(()-> new ResourceNotFoundException("Book", "id", bookId));
	}
	
	//Get a Single Author by line within authors
	@GetMapping("/books/{line}")
	public List<Book> getBookByLine(@PathVariable(value = "line") int line) {
		Page<Book> bookPage = bookRepository.findAll(PageRequest.of(line-1, 1));
		List<Book> book = bookPage.getContent();
		return book;
	}
	
	//Update a Book
	@PutMapping("/book/{id}")
	public Book updateBook(@PathVariable(value = "id") int bookId,
							@Valid @RequestBody Book bookDetails) {
		Book book = bookRepository.findById(bookId)
				.orElseThrow(()-> new ResourceNotFoundException("Book", "id", bookId));
		book.setTitle(bookDetails.getTitle());
		
		Book updatedBook = bookRepository.save(book);
		return updatedBook;
	}
	
	//Delete a Book
	@DeleteMapping("/book/{id}")
	public ResponseEntity<?> deleteBook(@PathVariable(value = "id") int bookId){
		Book book = bookRepository.findById(bookId)
				.orElseThrow(()-> new ResourceNotFoundException("Book", "id", bookId));
		bookRepository.delete(book);
		
		return ResponseEntity.ok().build();
	}
}

