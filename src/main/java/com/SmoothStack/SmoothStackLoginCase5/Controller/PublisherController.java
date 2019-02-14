package com.SmoothStack.SmoothStackLoginCase5.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.SmoothStack.SmoothStackLoginCase5.ControllerHelper.JsonConverter;
import com.SmoothStack.SmoothStackLoginCase5.Entity.Publisher;
import com.SmoothStack.SmoothStackLoginCase5.ServiceHelper.PublisherService;

@RestController
public class PublisherController {    
	
	@Autowired
	PublisherService publisherService;
	
	@RequestMapping(path="/lms/publisher", method=RequestMethod.GET)
	public String getPublisher(){
		List<Publisher> publisher = new PublisherService().getPublisher();
		JsonConverter converter = new JsonConverter();
		String output = converter.convertPublisherListToJson(publisher);
		return output;
	}
	
	@RequestMapping(path="/lms/publisher/{publisher}", method=RequestMethod.POST)
	public boolean addPublisher(@RequestBody Publisher publisher) {
		return publisherService.addPublisher(publisher);
	}
	
//	@RequestMapping(path="/lms/publisher/returnList/{publisherId}", method=RequestMethod.GET)
//	public String getPublisherById(@PathVariable(value = "publisherId") int publisherId){
//		List<Publisher> publisher = new PublisherService().getPublisherById(publisherId);
//		JsonConverter converter = new JsonConverter();
//		String output = converter.convertPublisherListToJson(publisher);
//		return output;
//	}
	
	@RequestMapping(path="/lms/publisher/{publisherId}", method=RequestMethod.GET)
	public Publisher getPublisherByIdReturn(@PathVariable(value = "publisherId") int publisherId) {
		return publisherService.getPublisherByIdReturn(publisherId);
	}
	
	@RequestMapping(path="/lms/publisher/{publisher}", method=RequestMethod.PUT)
	public boolean updatePublisher(@RequestBody Publisher publisher) {
		return publisherService.updatePublisher(publisher);
	}
	
	@RequestMapping(path="/lms/publisher/{publisher}", method=RequestMethod.DELETE)
	public boolean deletePublisher(@RequestBody Publisher publisher) {
		return publisherService.deletePublisher(publisher);
	}
	
	@RequestMapping(path="/lms/publisher/line/{lineNo}", method=RequestMethod.GET)
	public String getSinglePublisher(@PathVariable(value = "lineNo") int count) {
		List<Publisher> publisher = new PublisherService().getSinglePublisher(count);
		JsonConverter converter = new JsonConverter();
		String output = converter.convertPublisherListToJson(publisher);
		return output;
	}

}
