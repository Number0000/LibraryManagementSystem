package com.SmoothStack.SmoothStackLoginCase5.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.SmoothStack.SmoothStackLoginCase5.ControllerHelper.JsonConverter;
import com.SmoothStack.SmoothStackLoginCase5.Entity.LibraryBranch;
import com.SmoothStack.SmoothStackLoginCase5.ServiceHelper.LibraryBranchService;

@RestController
public class LibraryBranchController {
	
	@Autowired
	LibraryBranchService libraryBranchService;
	
	@RequestMapping(path="/lms/libraryBranch", method=RequestMethod.GET)
	public String getLibraryBranch(){
		List<LibraryBranch> libraryBranch = new LibraryBranchService().getLibraryBranch();
		JsonConverter converter = new JsonConverter();
		String output = converter.convertLibraryBranchListToJson(libraryBranch);
		return output;
	}
	
	@RequestMapping(path="/lms/libraryBranch/{libraryBranch}", method=RequestMethod.POST)
	public boolean addLibraryBranch(@RequestBody LibraryBranch libraryBranch) {
		return libraryBranchService.addLibraryBranch(libraryBranch);
	}
	
//	@RequestMapping(path="/lms/libraryBranch/returnList/{branchId}", method=RequestMethod.GET)
//	public String getLibraryBranchById(@PathVariable(value = "branchId") int branchId){
//		List<LibraryBranch> libraryBranch = new LibraryBranchService().getLibraryBranchById(branchId);
//		JsonConverter converter = new JsonConverter();
//		String output = converter.convertToJson(libraryBranch);
//		return output;
//	}
	
	@RequestMapping(path="/lms/libraryBranch/{branchId}", method=RequestMethod.GET)
	public LibraryBranch getLibraryBranchByIdReturn(@PathVariable(value = "branchId") int branchId) {
		return libraryBranchService.getLibraryBranchByIdReturn(branchId);
	}
	
	@RequestMapping(path="/lms/libraryBranch/{libraryBranch}", method=RequestMethod.PUT)
	public boolean updateLibraryBranch(@RequestBody LibraryBranch libraryBranch) {
		return libraryBranchService.updateLibraryBranch(libraryBranch);
	}
	
	@RequestMapping(path="/lms/libraryBranch/{libraryBranch}", method=RequestMethod.DELETE)
	public boolean deleteLibraryBranch(@RequestBody LibraryBranch libraryBranch) {
		return libraryBranchService.deleteLibraryBranch(libraryBranch);
	}
	
	@RequestMapping(path="/lms/libraryBranch/line/{lineNo}", method=RequestMethod.GET)
	public String getSingleLibraryBranch(@PathVariable(value = "lineNo") int count) {
		List<LibraryBranch> libraryBranch = new LibraryBranchService().getSingleLibraryBranch(count);
		JsonConverter converter = new JsonConverter();
		String output = converter.convertToJson(libraryBranch);
		return output;
	}

}
