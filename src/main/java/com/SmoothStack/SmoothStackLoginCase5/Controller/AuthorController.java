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

import com.SmoothStack.SmoothStackLoginCase5.Entity.Author;
import com.SmoothStack.SmoothStackLoginCase5.Exception.ResourceNotFoundException;
import com.SmoothStack.SmoothStackLoginCase5.Repository.AuthorRepository;

@RestController
@RequestMapping("/lms")
public class AuthorController {
	
	@Autowired
	AuthorRepository authorRepository;
	
	//Get All Author
//	@RequestMapping(value="/authors", method=RequestMethod.GET)
	@GetMapping("/authors")
	public List<Author> getAllAuthor(){
		return authorRepository.findAll();
	}
	
	//Create a new author
	@PostMapping("/author")
	public Author createAuthor(@Valid @RequestBody Author author) {
		return authorRepository.save(author);
	}
	
	//Get a Single Author
	@GetMapping("/author/{id}")
	public Author getAuthorById(@PathVariable(value = "id") int authorId) {
		return authorRepository.findById(authorId)
				.orElseThrow(()-> new ResourceNotFoundException("Author", "id", authorId));
	}
	
	//Get a Single Author by line within authors
	@GetMapping("/authors/{line}")
	public List<Author> getAuthorByLine(@PathVariable(value = "line") int line) {
		Page<Author> authorPage = authorRepository.findAll(PageRequest.of(line-1, 1));
		List<Author> author = authorPage.getContent();
		return author;
	}
	
	//Update a Author
	@PutMapping("/author/{id}")
	public Author updateAuthor(@PathVariable(value = "id") int authorId,
							@Valid @RequestBody Author authorDetails) {
		Author author = authorRepository.findById(authorId)
				.orElseThrow(()-> new ResourceNotFoundException("Author", "id", authorId));
		author.setAuthorName(authorDetails.getAuthorName());
		
		Author updatedAuthor = authorRepository.save(author);
		return updatedAuthor;
	}
	
	//Delete a Author
	@DeleteMapping("/author/{id}")
	public ResponseEntity<?> deleteAuthor(@PathVariable(value = "id") int authorId){
		Author author = authorRepository.findById(authorId)
				.orElseThrow(()-> new ResourceNotFoundException("Author", "id", authorId));
		authorRepository.delete(author);
		
		return ResponseEntity.ok().build();
	}
}

