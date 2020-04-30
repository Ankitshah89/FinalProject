package com.skilldistillery.supportlocal.Controllers;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
	public List<Business> businessIndex(HttpServletResponse response) {
		List<Business> index = bServ.businessIndex();
		if (index != null) {
			response.setStatus(200);
		} else {
			response.setStatus(404);
		}
		return index;
	}

	@GetMapping("businesses/{id}")
	public Business findById(@PathVariable int id, HttpServletResponse response) {
		Business business = bServ.findById(id);
		if (business != null) {
			response.setStatus(200);
		} else {
			response.setStatus(404);
		}
		return business;
	}

	@PostMapping("businesses")
	public Business createBusiness(@RequestBody Business business, HttpServletResponse response, Principal principal) {
		Business manBus = bServ.createBusiness(principal.getName(),business);
		if (manBus != null) {
			response.setStatus(201);
		} else {
			response.setStatus(400);
		}
		return manBus;
	}
	
	@PutMapping("businesses/{id}")
	public Business updateBusiness(@RequestBody Business business, @PathVariable int id, HttpServletResponse response,
			Principal principal) {
		Business manBus = bServ.updateBusiness(principal.getName(),business, id);
		if(manBus != null) {
			response.setStatus(202);
		} else {
			response.setStatus(400);
		}
		return manBus;
	}
	
	@DeleteMapping("businesses/{id}")
	public boolean deleteById(@PathVariable int id, HttpServletResponse response, Principal principal) {
		boolean deleted = bServ.deleteBusiness(principal.getName(),id);
		if(deleted) {
			response.setStatus(200);
		} else {
			response.setStatus(404);
		}
		return deleted;
	}
	
	@GetMapping("businesses/search/name/{keyword}")
	public List<Business> findByName(@PathVariable String keyword, HttpServletResponse reponse){
		List<Business> listBus = null;
		try {
			listBus = bServ.findBusinessByName(keyword);
			reponse.setStatus(200);
			
		}catch (Exception e) {
			System.out.println(e);
			
		}
		return listBus;
	}
	@GetMapping("businesses/search/description/{keyword}")
	public List<Business> findByDescription(@PathVariable String keyword, HttpServletResponse reponse){
		List<Business> listBus = null;
		try {
			listBus = bServ.findBusinessByDescription(keyword);
			reponse.setStatus(200);
			
		}catch (Exception e) {
			System.out.println(e);
			
		}
		return listBus;
	}
	@GetMapping("businesses/search/zip/{keyword}")
	public List<Business> findByZipCode(@PathVariable String keyword, HttpServletResponse reponse){
		List<Business> listBus = null;
		try {
			listBus = bServ.findBusinessByZipCode(keyword);
			reponse.setStatus(200);
			
		}catch (Exception e) {
			System.out.println(e);
			
		}
		return listBus;
	}
	
//	@PutMapping("businesses/{id}/manager")
}
