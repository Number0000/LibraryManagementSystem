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

import com.SmoothStack.SmoothStackLoginCase5.Entity.Publisher;
import com.SmoothStack.SmoothStackLoginCase5.Exception.ResourceNotFoundException;
import com.SmoothStack.SmoothStackLoginCase5.Repository.PublisherRepository;

@RestController
@RequestMapping("/lms")
public class PublisherController {
	
	@Autowired
	PublisherRepository publisherRepository;
	
	//Get All Publisher
//	@RequestMapping(value="/notes", method=RequestMethod.GET)
	@GetMapping("/publishers")
	public List<Publisher> getAllPublisher(){
		return publisherRepository.findAll();
	}
	
	//Create a new publisher
	@PostMapping("/publisher")
	public Publisher createPublisher(@Valid @RequestBody Publisher publisher) {
		return publisherRepository.save(publisher);
		
	}
	
	//Get a Single Publisher
	@GetMapping("/publisher/{id}")
	public Publisher getNoteById(@PathVariable(value = "id") int publisherId) {
		return publisherRepository.findById(publisherId)
				.orElseThrow(()-> new ResourceNotFoundException("Publisher", "id", publisherId));
	}
	
	//Get a Single Publisher by line within Publishers
	@GetMapping("/publishers/{line}")
	public List<Publisher> getPublisherByLine(@PathVariable(value = "line") int line) {
		Page<Publisher> publisherPage = publisherRepository.findAll(PageRequest.of(line-1, 1));
		List<Publisher> publisher = publisherPage.getContent();
		return publisher;
	}
	
	//Update a Publisher
	@PutMapping("/publisher/{id}")
	public Publisher updatePublisher(@PathVariable(value = "id") int publisherId,
							@Valid @RequestBody Publisher publisherDetails) {
		Publisher publisher = publisherRepository.findById(publisherId)
				.orElseThrow(()-> new ResourceNotFoundException("Publisher", "id", publisherId));
		publisher.setPublisherName(publisherDetails.getPublisherName());
		publisher.setPublisherAddress(publisherDetails.getPublisherAddress());
		publisher.setPublisherPhone(publisherDetails.getPublisherPhone());
		
		Publisher updatedPublisher = publisherRepository.save(publisher);
		return updatedPublisher;
	}
	
	//Delete a Publisher
	@DeleteMapping("/publisher/{id}")
	public ResponseEntity<?> deletePublisher(@PathVariable(value = "id") int publisherId){
		Publisher publisher = publisherRepository.findById(publisherId)
				.orElseThrow(()-> new ResourceNotFoundException("Publisher", "id", publisherId));
		publisherRepository.delete(publisher);
		
		return ResponseEntity.ok().build();
	}
}

