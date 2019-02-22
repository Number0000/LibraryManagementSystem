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

import com.SmoothStack.SmoothStackLoginCase5.Entity.BookCopy;
import com.SmoothStack.SmoothStackLoginCase5.Exception.ResourceNotFoundException;
import com.SmoothStack.SmoothStackLoginCase5.Repository.BookCopyRepository;

@RestController
@RequestMapping("/lms")
public class BookCopyController {
	
	@Autowired
	private	BookCopyRepository bookCopyRepository;
	
	//Get All BookCopy
//	@RequestMapping(value="/notes", method=RequestMethod.GET)
	@GetMapping("/bookCopies")
	public List<BookCopy> getAllBookCopy(){
		return bookCopyRepository.findAll();
	}
	
	//Create a new bookCopy
	@PostMapping("/bookCopy")
	public BookCopy createBookCopy(@Valid @RequestBody BookCopy bookCopy) {
		return bookCopyRepository.save(bookCopy);
	}
	
	//Get a Single BookCopy
	@GetMapping("/bookCopy/{id}")
	public BookCopy getNoteById(@PathVariable(value = "id") int bookCopyId) {
		return bookCopyRepository.findById(bookCopyId)
				.orElseThrow(()-> new ResourceNotFoundException("BookCopy", "id", bookCopyId));
	}
	
	//Get a Single Author by line within authors
	@GetMapping("/bookCopies/{line}")
	public List<BookCopy> getBookCopyByLine(@PathVariable(value = "line") int line) {
		Page<BookCopy> bookCopyPage = bookCopyRepository.findAll(PageRequest.of(line-1, 1));
		List<BookCopy> bookCopy = bookCopyPage.getContent();
		return bookCopy;
	}
	
	//Update a BookCopy
	@PutMapping("/bookCopy/{bookid}/{branchId}")
	public BookCopy updateBookCopy(@PathVariable(value = "bookid") int bookId,
							@PathVariable(value = "branchid") int branchId,
							@Valid @RequestBody BookCopy bookCopyDetails) {
		BookCopy bookCopy = bookCopyRepository.getByBookIdAndBranchId(bookId, branchId);
		bookCopy.setNoOfCopies(bookCopyDetails.getNoOfCopies());
		
		BookCopy updatedBookCopy = bookCopyRepository.save(bookCopy);
		return updatedBookCopy;
	}
	
	//Delete a BookCopy
	@DeleteMapping("/bookCopy/{id}")
	public ResponseEntity<?> deleteBookCopy(@PathVariable(value = "id") int bookCopyId){
		BookCopy bookCopy = bookCopyRepository.findById(bookCopyId)
				.orElseThrow(()-> new ResourceNotFoundException("BookCopy", "id", bookCopyId));
		bookCopyRepository.delete(bookCopy);
		
		return ResponseEntity.ok().build();
	}
}

