package com.skilldistillery.supportlocal.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.supportlocal.entities.Address;
import com.skilldistillery.supportlocal.entities.Business;
import com.skilldistillery.supportlocal.entities.PreferenceCategory;
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
	public Address createAddress(String email, Address address) {
		User user = userRepo.findUserByEmail(email);

		try {
			if (user.getRole().equals(Role.Admin) || user.getRole().equals(Role.Business)) {

				if (address.getStreet() == null) {
					address.setStreet("");
				}

				if (address.getCity() == null) {
					address.setCity("");
				}
				if (address.getState() == null) {
					address.setState("");
				}
				if (address.getPostalCode() == null) {
					address.setPostalCode("");
				}
				if (address.getCountry() == null) {
					address.setCountry("");
				}

				System.out.println(address);
				return addRepo.saveAndFlush(address);
			}

		} catch (Exception e) {
			System.out.println(e);
			System.out.println("Could not complete creation of: " + address);
			System.out.println("Some fields may not have been provided.");
			return null;
		}
		return address;
	}

	// Update
	@Override
	public Address updateAddress(String email, Address address) {
		User user = userRepo.findUserByEmail(email);
		if (user != null) {

			Optional<Address> optAdd = addRepo.findById(address.getId());
			if (optAdd.isPresent()) {
				try {

					if (address.getStreet() == null || address.getCity() == null || address.getState() == null
							|| address.getPostalCode() == null || address.getCountry() == null || address.getId() < 0) {
						throw new Exception();

					} else {
						Address manAdd = optAdd.get();
						if (manAdd.getBusiness().getManager().getId() == user.getId()
								|| user.getRole().equals(Role.Admin)) {
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
		}
		return address;
	}

	// Delete
	@Override
	public boolean deleteAddress(String email, int id) {
		boolean deleted = false;

		User user = userRepo.findUserByEmail(email);

		if (user != null) {

			Optional<Address> toDelete = addRepo.findById(id);
			if (toDelete.isPresent()) {
				Address manAdd = toDelete.get();
				if (manAdd.getBusiness().getManager().getId() == user.getId() || user.getRole().equals(Role.Admin)) {

					addRepo.delete(manAdd);
					deleted = true;
				}
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

	@Override
	public List<Address> generalSearch(String keyword) {
		List<Address> genSearch = addRepo.findByBusinessDescriptionLikeOrPostalCodeLikeOrBusinessNameLike(keyword, keyword, keyword);
		// TODO Auto-generated method stub
		return genSearch;
	}

	@Override
	public List<Address> businessCategory(String categoryStr) {
		List<Address> busCat = new ArrayList<>();
		PreferenceCategory category = null;
		for(PreferenceCategory cat: PreferenceCategory.values()) {
			if(cat.toString().equals(categoryStr)) {
				
				category = cat;
				
				break;
			}
		}
		if(category != null) {
			try {
				busCat = addRepo.findByBusinessPreferencesPreferenceCategory(category);
				System.out.println("Retrieved values for Businesses By Category: " + category.toString());
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("Failed to retrieve of list of Businesses by Category: " + categoryStr);
				
			}
		}
		return busCat;
	}

}
