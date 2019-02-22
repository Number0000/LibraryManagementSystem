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

import com.SmoothStack.SmoothStackLoginCase5.Entity.LibraryBranch;
import com.SmoothStack.SmoothStackLoginCase5.Exception.ResourceNotFoundException;
import com.SmoothStack.SmoothStackLoginCase5.Repository.LibraryBranchRepository;

@RestController
@RequestMapping("/lms")
public class LibraryBranchController {
	
	@Autowired
	LibraryBranchRepository libraryBranchRepository;
	
	//Get All LibraryBranch
//	@RequestMapping(value="/libraryBranches", method=RequestMethod.GET)
	@GetMapping("/libraryBranches")
	public List<LibraryBranch> getAllLibraryBranch(){
		return libraryBranchRepository.findAll();
	}
	
	//Create a new libraryBranch
	@PostMapping("/libraryBranch")
	public LibraryBranch createLibraryBranch(@Valid @RequestBody LibraryBranch libraryBranch) {
		return libraryBranchRepository.save(libraryBranch);
		
	}
	
	//Get a Single LibraryBranch
	@GetMapping("/libraryBranch/{id}")
	public LibraryBranch getLibraryBranchById(@PathVariable(value = "id") int libraryBranchId) {
		return libraryBranchRepository.findById(libraryBranchId)
				.orElseThrow(()-> new ResourceNotFoundException("LibraryBranch", "id", libraryBranchId));
	}
	
	//Get a Single libraryBranch by line within libraryBranchs
	@GetMapping("/libraryBranches/{line}")
	public List<LibraryBranch> getLibraryBranchByLine(@PathVariable(value = "line") int line) {
		Page<LibraryBranch> libraryBranchPage = libraryBranchRepository.findAll(PageRequest.of(line-1, 1));
		List<LibraryBranch> libraryBranch = libraryBranchPage.getContent();
		return libraryBranch;
	}
	
	//Update a LibraryBranch
	@PutMapping("/libraryBranch/{id}")
	public LibraryBranch updateLibraryBranch(@PathVariable(value = "id") int libraryBranchId,
							@Valid @RequestBody LibraryBranch libraryBranchDetails) {
		LibraryBranch libraryBranch = libraryBranchRepository.findById(libraryBranchId)
				.orElseThrow(()-> new ResourceNotFoundException("LibraryBranch", "id", libraryBranchId));
		libraryBranch.setLibraryBranchName(libraryBranchDetails.getLibraryBranchName());
		libraryBranch.setLibraryBranchAddress(libraryBranchDetails.getLibraryBranchAddress());
		
		LibraryBranch updatedLibraryBranch = libraryBranchRepository.save(libraryBranch);
		return updatedLibraryBranch;
	}
	
	//Delete a LibraryBranch
	@DeleteMapping("/libraryBranch/{id}")
	public ResponseEntity<?> deleteLibraryBranch(@PathVariable(value = "id") int libraryBranchId){
		LibraryBranch libraryBranch = libraryBranchRepository.findById(libraryBranchId)
				.orElseThrow(()-> new ResourceNotFoundException("LibraryBranch", "id", libraryBranchId));
		libraryBranchRepository.delete(libraryBranch);
		
		return ResponseEntity.ok().build();
	}
	
	@GetMapping("/libraryBranches/count")
	public int getLibraryBranchCount() {
		int count = (int) libraryBranchRepository.count();
		return count;
	}
}

