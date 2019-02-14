package com.SmoothStack.SmoothStackLoginCase5.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.SmoothStack.SmoothStackLoginCase5.ControllerHelper.JsonConverter;
import com.SmoothStack.SmoothStackLoginCase5.Entity.Author;
import com.SmoothStack.SmoothStackLoginCase5.ServiceHelper.AuthorService;

@RestController
public class AuthorController {
	
	@Autowired
	AuthorService authorService;
	
	@RequestMapping(path="/lms/author", method=RequestMethod.GET)
	public String getAuthor(){
		List<Author> author = new AuthorService().getAuthor();
		JsonConverter converter = new JsonConverter();
		String output = converter.convertAuthorListToJson(author);
		return output;
	}
	
	@RequestMapping(path="/lms/author/{author}", method=RequestMethod.POST)
	public boolean addAuthor(@RequestBody Author author) {
		return authorService.addAuthor(author);
	}
	
//	@RequestMapping(path="/lms/author/returnList/{authorId}", method=RequestMethod.GET)
//	public String getAuthorById(@PathVariable(value = "authorId") int authorId){
//		List<Author> author = new AuthorService().getAuthorById(authorId);
//		JsonConverter converter = new JsonConverter();
//		String output = converter.convertAuthorListToJson(author);
//		return output;
//	}
	
	@RequestMapping(path="/lms/author/{authorId}", method=RequestMethod.GET)
	public Author getAuthorByIdReturn(@PathVariable(value = "authorId") int authorId) {
		return authorService.getAuthorByIdReturn(authorId);
	}
	
	@RequestMapping(path="/lms/author/{author}", method=RequestMethod.PUT)
	public boolean updateAuthor(@RequestBody Author author) {
		return authorService.updateAuthor(author);
	}
	
	@RequestMapping(path="/lms/author/{author}", method=RequestMethod.DELETE)
	public boolean deleteAuthor(@RequestBody Author author) {
		return authorService.deleteAuthor(author);
	}
	
	@RequestMapping(path="/lms/author/line/{lineNo}", method=RequestMethod.GET)
	public String getSingleAuthor(@PathVariable(value = "lineNo") int count) {
		List<Author> author = new AuthorService().getSingleAuthor(count);
		JsonConverter converter = new JsonConverter();
		String output = converter.convertAuthorListToJson(author);
		return output;
	}
}
