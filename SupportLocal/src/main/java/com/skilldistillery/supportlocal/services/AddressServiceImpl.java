package com.skilldistillery.supportlocal.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.supportlocal.entities.Address;
import com.skilldistillery.supportlocal.entities.Role;
import com.skilldistillery.supportlocal.entities.User;
import com.skilldistillery.supportlocal.repositories.AddressRepository;
import com.skilldistillery.supportlocal.repositories.UserRepository;

@Service
public class AddressServiceImpl implements AddressService {

	@Autowired
	private AddressRepository addRepo;
	@Autowired 
	private BusinessService busServe;
	@Autowired
	private UserRepository userRepo;
	
	

//	@Override //Probably going to move this to the business service
//	public Business findBusinessByAddressBusinessId(int id) {
//		// TODO Auto-generated method stub
//		return null;
//	}

	@Override
	public List<Address> indexAddress() {
		// TODO Auto-generated method stub
		return addRepo.findAll();
	}
	// Create

	@Override
	public Address createAddress(String email,Address address) {
		User user = userRepo.findUserByEmail(email);
		
		try {
			if(user.getRole().equals(Role.Admin) || user.getRole().equals(Role.Business)) {
				
			
			if (address.getStreet() == null 
					|| address.getCity() == null 
					|| address.getState() == null
					|| address.getPostalCode() == null 
					|| address.getCountry() == null
					) {
				//|| manBusiness == null
				System.out.println("Had a null in Address");
				throw new Exception();

			} else {
				System.out.println(address);
				return addRepo.saveAndFlush(address);
			}
			}
		}
		catch (Exception e) {
			System.out.println(e);
			System.out.println("Could not complete creation of: " + address);
			System.out.println("Some fields may not have been provided.");
			return null;
		}
		return address;
	}

	// Update
	@Override
	public Address updateAddress(String email,Address address) {
		User user = userRepo.findUserByEmail(email);
		
			
		Optional<Address> optAdd = addRepo.findById(address.getId());
		if (optAdd.isPresent()) {
			try {
				if(user.getRole().equals(Role.Admin) || user.getRole().equals(Role.Business)) {
				if (address.getStreet() == null 
						|| address.getCity() == null 
						|| address.getState() == null
						|| address.getPostalCode() == null 
						|| address.getCountry() == null 
						|| address.getId() < 0) {
					throw new Exception();

				} else {
					Address manAdd = optAdd.get();
					manAdd.setStreet(address.getStreet());
					manAdd.setStreet2(address.getStreet2());
					manAdd.setCity(address.getCity());
					manAdd.setState(address.getState());
					manAdd.setPostalCode(address.getPostalCode());
					manAdd.setCountry(address.getCountry());
					return addRepo.saveAndFlush(manAdd);
				}
				}
			} catch (Exception e) {
				System.out.println(e);
				System.out.println("Could not complete update of: " + address);
				return null;
			}
		}
		return address;
	}

	// Delete
	@Override
	public boolean deleteAddress(String email, int id) {
		boolean deleted = false;
		
		User user = userRepo.findUserByEmail(email);
		if(user.getRole().equals(Role.Admin) || user.getRole().equals(Role.Business)) {
			
		Optional<Address> toDelete = addRepo.findById(id);
		if(toDelete.isPresent()) {
			Address manAdd = toDelete.get();
			addRepo.delete(manAdd);
			deleted = true;
		}
		}
		// TODO Auto-generated method stub
		return deleted;
	}

	// Retrieve
	@Override
	public Address findById(int id) {
		Optional<Address> optAdd = addRepo.findById(id);
		Address address = null;
		if (optAdd != null) {
			address = optAdd.get();

		}
		// TODO Auto-generated method stub
		return address;
	}

}
