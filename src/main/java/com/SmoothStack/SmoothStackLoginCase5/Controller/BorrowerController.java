package com.SmoothStack.SmoothStackLoginCase5.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.SmoothStack.SmoothStackLoginCase5.ControllerHelper.JsonConverter;
import com.SmoothStack.SmoothStackLoginCase5.Entity.Borrower;
import com.SmoothStack.SmoothStackLoginCase5.ServiceHelper.BorrowerService;



@RestController
public class BorrowerController {
    
	@Autowired
	BorrowerService borrowerService;
	
	@RequestMapping(path="/lms/borrower", method=RequestMethod.GET)
	public String getBorrower(){
		List<Borrower> borrower = new BorrowerService().getBorrower();
		JsonConverter converter = new JsonConverter();
		String output = converter.convertBorrowerListToJson(borrower);
		return output;
	}
	
	@RequestMapping(path="/lms/borrower/{borrower}", method=RequestMethod.POST)
	public boolean addBorrower(@RequestBody Borrower borrower) {
		return borrowerService.addBorrower(borrower);
	}
	
//	@RequestMapping(path="/lms/borrower/returnList/{cardNo}", method=RequestMethod.GET)
//	public String getBorrowerById(@PathVariable(value = "cardNo") int cardNo){
//		List<Borrower> borrower = new BorrowerService().getBorrowerById(cardNo);
//		JsonConverter converter = new JsonConverter();
//		String output = converter.convertBorrowerListToJson(borrower);
//		return output;
//	}
	
	@RequestMapping(path="/lms/borrower/{cardNo}", method=RequestMethod.GET)
	public Borrower getBorrowerByIdReturn(@PathVariable(value = "cardNo") int cardNo) {
		return borrowerService.getBorrowerByIdReturn(cardNo);
	}
	
	@RequestMapping(path="/lms/borrower/{borrower}", method=RequestMethod.PUT)
	public boolean updateBorrower(@RequestBody Borrower borrower) {
		return borrowerService.updateBorrower(borrower);
	}
	
	@RequestMapping(path="/lms/borrower/{borrower}", method=RequestMethod.DELETE)
	public boolean deleteBorrower(@RequestBody Borrower borrower) {
		return borrowerService.deleteBorrower(borrower);
	}
	
	@RequestMapping(path="/lms/borrower/line/{lineNo}", method=RequestMethod.GET)
	public String getSingleBorrower(@PathVariable(value = "lineNo") int count) {
		List<Borrower> borrower = new BorrowerService().getSingleBorrower(count);
		JsonConverter converter = new JsonConverter();
		String output = converter.convertBorrowerListToJson(borrower);
		return output;
	}

}
