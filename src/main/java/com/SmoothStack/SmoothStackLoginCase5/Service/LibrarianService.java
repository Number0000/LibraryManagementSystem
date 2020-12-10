package com.SmoothStack.SmoothStackLoginCase5.Service;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.SmoothStack.SmoothStackLoginCase5.Repository.BookRepository;
import com.SmoothStack.SmoothStackLoginCase5.Repository.BookCopyRepository;
import com.SmoothStack.SmoothStackLoginCase5.Repository.LibraryBranchRepository;
import com.SmoothStack.SmoothStackLoginCase5.Entity.BookCopy;
import com.SmoothStack.SmoothStackLoginCase5.Entity.LibraryBranch;
import com.SmoothStack.SmoothStackLoginCase5.Exception.ResourceNotFoundException;

@RestController
@RequestMapping("/librarian")
public class LibrarianService {

	@Autowired
	LibraryBranchRepository libraryBranchRepository;

	@Autowired
	BookCopyRepository bookCopyRepository;

	@Autowired
	BookRepository bookRepository;

	@GetMapping("/libraryBranch")
	public List<LibraryBranch> getLibraryBranch() {
		return libraryBranchRepository.findAll();
	}

	@GetMapping("/libraryBranch/{line}")
	public List<LibraryBranch> getLibraryBranchByLine(@PathVariable(value = "line") int line) {
		List<LibraryBranch> libraryBranch = null;

		if ((line - 1) < libraryBranchRepository.count() && line > 0) {
			Page<LibraryBranch> libraryBranchPage = libraryBranchRepository.findAll(PageRequest.of(line - 1, 1));
			libraryBranch = libraryBranchPage.getContent();
		}
		return libraryBranch;
	}

	@GetMapping("/menu")
	public ResponseEntity<String> librarianMenu() {
		return ResponseEntity.ok()
				.body("1) Update Library Branch" + "2) Add Copies of Book to the Branch" + "3) Exit Library Menu");
	}

	@GetMapping("/menu/{option}")
	public String librarianReturnOption(@PathVariable(value = "line") int option) {
		if (option == 1) {
			return "redirect:/menu/updateLibraryBranch/{id}.do";
		} else if (option == 2) {
			return "redirect:/menu/updateBookCopies/{bookId}/{branchId}.do";
		} else
			return null;
	}

	@PutMapping("/libraryBranch/{id}")
	public ResponseEntity<LibraryBranch> updateLibraryBranch(@PathVariable(value = "id") int libraryBranchId,
			@Valid @RequestBody LibraryBranch libraryBranchDetails) {
		LibraryBranch libraryBranch = libraryBranchRepository.findById(libraryBranchId)
				.orElseThrow(() -> new ResourceNotFoundException("LibraryBranch", "id", libraryBranchId));
		libraryBranch.setLibraryBranchName(libraryBranchDetails.getLibraryBranchName());
		libraryBranch.setLibraryBranchAddress(libraryBranchDetails.getLibraryBranchAddress());

		LibraryBranch updatedLibraryBranch = libraryBranchRepository.save(libraryBranch);

		return new ResponseEntity<LibraryBranch>(updatedLibraryBranch, HttpStatus.OK);
	}

	@PutMapping("/bookCopies/{bookId}/{branchId}")
	public ResponseEntity<BookCopy> updateBookCopies(@PathVariable(value = "bookId") int bookId,
			@PathVariable(value = "branchId") int libraryBranchId, @Valid @RequestBody BookCopy bookCopyDetails) {
		BookCopy bookCopy = bookCopyRepository.getByBookIdAndBranchId(bookId, libraryBranchId);
		bookCopy = new BookCopy(bookCopyDetails.getBook(), bookCopyDetails.getLibraryBranch());
		bookCopy.setNoOfCopies(bookCopyDetails.getNoOfCopies());

		BookCopy updatedBookCopy = bookCopyRepository.save(bookCopy);

		return new ResponseEntity<BookCopy>(updatedBookCopy, HttpStatus.OK);
	}
}
