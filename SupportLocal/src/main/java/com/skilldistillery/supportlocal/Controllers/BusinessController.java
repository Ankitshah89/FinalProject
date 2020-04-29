package com.skilldistillery.supportlocal.Controllers;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.supportlocal.entities.Business;
import com.skilldistillery.supportlocal.services.BusinessService;

@RestController
@RequestMapping("api")
@CrossOrigin({ "*", "http://localhost:4220" })
public class BusinessController {
	
	@Autowired 
	private BusinessService bServ;
	
	@GetMapping("businesses")
	public List<Business> businessIndex(HttpServletResponse response){
		List<Business> index = bServ.businessIndex();
		if(index != null) {
			response.setStatus(200);
		} else {
			response.setStatus(404);
		}
		return index;
	}
	
	@GetMapping("businesses/{id}")
	public Business findById(@PathVariable int id, HttpServletResponse response) {
		Business business = bServ.findById(id);
		if(business != null) {
			response.setStatus(200);
		} else {
			response.setStatus(404);
		}
		return business;
	}
	
	@PostMapping("businesses")
	public Business createBusiness(@RequestBody Business business, HttpServletResponse response) {
		Business manBus = bServ.createBusiness(business);
		if(manBus != null) {
			response.setStatus(201);
		} else {
			response.setStatus(400);
		}
		return manBus;
	}
}
