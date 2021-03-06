package com.skilldistillery.supportlocal.Controllers;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
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

import com.skilldistillery.supportlocal.entities.Address;
import com.skilldistillery.supportlocal.entities.Business;
import com.skilldistillery.supportlocal.services.AddressService;

@RestController
@RequestMapping("api")
@CrossOrigin({ "*", "http://localhost:4220" })
public class AddressController {
	@Autowired
	private AddressService addServe;

	@GetMapping("addresses")
	public List<Address> addressIndex(HttpServletResponse response) {
		List<Address> index = addServe.indexAddress();
		if (index != null) {
			response.setStatus(200);

		} else {
			response.setStatus(404);
		}
		return index;
	}

	@GetMapping("addresses/{id}")
	public Address findById(@PathVariable int id, HttpServletResponse response) {
		Address address = addServe.findById(id);
		if (address != null) {
			response.setStatus(200);
		} else {
			response.setStatus(404);
		}
		return address;
	}

	@PostMapping("addresses")
	public Address createAddress(@RequestBody Address address, HttpServletResponse response, HttpServletRequest request,
			Principal principal) {
		Address newAddress = addServe.createAddress(principal.getName(), address);

		if (newAddress != null) {
			response.setStatus(201);
		} else {
			response.setStatus(400);
		}
		return newAddress;
	}

	@PutMapping("addresses/{id}")
	public Address updateAddress(@PathVariable int id, @RequestBody Address address, HttpServletResponse response,
			Principal principal) {
		Address manAdd = addServe.findById(id);
		if (manAdd != null) {
			manAdd.setStreet(address.getStreet());
			manAdd.setStreet2(address.getStreet2());
			manAdd.setCity(address.getCity());
			manAdd.setState(address.getState());
			manAdd.setPostalCode(address.getPostalCode());
			manAdd.setCountry(address.getCountry());
			response.setStatus(200);
			return addServe.updateAddress(principal.getName(), manAdd);
		}

		return null;
	}

	@DeleteMapping("addresses/{id}")
	public boolean deleteAddress(@PathVariable int id, HttpServletResponse response, Principal principal) {
		boolean deleted = addServe.deleteAddress(principal.getName(), id);
		if (deleted) {
			response.setStatus(200);
		} else {
			response.setStatus(404);
		}
		return deleted;
	}

	@GetMapping("addresses/search/{keyword}")
	public List<Address> generalSearch(@PathVariable String keyword, HttpServletResponse response) {
		List<Address> genSearch = null;
		if (keyword.length() > 0) {
			genSearch = addServe.generalSearch("%" + keyword + "%");
		}
		if (genSearch != null) {
			response.setStatus(200);
		} else {
			response.setStatus(404);
		}
		return genSearch;
	}
	
	@GetMapping("/businesses/search/category/{category}")
	public List<Address> businessesByCategory(@PathVariable String category, HttpServletResponse response){
		List<Address> managedBus = null;
		try {
			managedBus = addServe.businessCategory(category);
			response.setStatus(200);
		} catch(Exception e) {
			e.printStackTrace();
			System.out.println("Business Controller: Failed to retrieve businesses by category");
			response.setStatus(404);
		}
		
		return managedBus;
	}

}
