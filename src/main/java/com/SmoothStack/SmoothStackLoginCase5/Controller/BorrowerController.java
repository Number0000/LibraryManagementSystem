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

import com.SmoothStack.SmoothStackLoginCase5.Entity.Borrower;
import com.SmoothStack.SmoothStackLoginCase5.Exception.ResourceNotFoundException;
import com.SmoothStack.SmoothStackLoginCase5.Repository.BorrowerRepository;

@RestController
@RequestMapping("/lms")
public class BorrowerController {
	
	@Autowired
	BorrowerRepository borrowerRepository;
	
	//Get All Borrower
//	@RequestMapping(value="/borrowers", method=RequestMethod.GET)
	@GetMapping("/borrowers")
	public List<Borrower> getAllBorrower(){
		return borrowerRepository.findAll();
	}
	
	//Create a new borrower
	@PostMapping("/borrower")
	public Borrower createBorrower(@Valid @RequestBody Borrower borrower) {
		return borrowerRepository.save(borrower);
		
	}
	
	//Get a Single Borrower
	@GetMapping("/borrower/{id}")
	public Borrower getNoteById(@PathVariable(value = "id") int borrowerId) {
		return borrowerRepository.findById(borrowerId)
				.orElseThrow(()-> new ResourceNotFoundException("Borrower", "id", borrowerId));
	}
	
	//Get a Single Borrower by line within Borrowers
	@GetMapping("/borrowers/{line}")
	public List<Borrower> getBorrowerByLine(@PathVariable(value = "line") int line) {
		Page<Borrower> borrowerPage = borrowerRepository.findAll(PageRequest.of(line-1, 1));
		List<Borrower> borrower = borrowerPage.getContent();
		return borrower;
	}
	
	//Update a Borrower
	@PutMapping("/borrower/{id}")
	public Borrower updateBorrower(@PathVariable(value = "id") int borrowerId,
							@Valid @RequestBody Borrower borrowerDetails) {
		Borrower borrower = borrowerRepository.findById(borrowerId)
				.orElseThrow(()-> new ResourceNotFoundException("Borrower", "id", borrowerId));
		borrower.setBorrowerName(borrowerDetails.getBorrowerName());
		borrower.setBorrowerAddress(borrowerDetails.getBorrowerAddress());
		borrower.setBorrowerPhone(borrowerDetails.getBorrowerPhone());
		borrower.setBorrowerUserName(borrowerDetails.getBorrowerUserName());
		borrower.setBorrowerPassword(borrowerDetails.getBorrowerPassword());
		
		Borrower updatedBorrower = borrowerRepository.save(borrower);
		return updatedBorrower;
	}
	
	//Delete a Borrower
	@DeleteMapping("/borrower/{id}")
	public ResponseEntity<?> deleteBorrower(@PathVariable(value = "id") int borrowerId){
		Borrower borrower = borrowerRepository.findById(borrowerId)
				.orElseThrow(()-> new ResourceNotFoundException("Borrower", "id", borrowerId));
		borrowerRepository.delete(borrower);
		
		return ResponseEntity.ok().build();
	}
}

