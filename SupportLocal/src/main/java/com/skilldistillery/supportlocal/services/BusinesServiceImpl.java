package com.skilldistillery.supportlocal.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.supportlocal.entities.Business;
import com.skilldistillery.supportlocal.repositories.BusinessRepository;

@Service
public class BusinesServiceImpl implements BusinessService {

	@Autowired
	private BusinessRepository busRepo;

	//Add User Repo.
	
	
	@Override //Done
	public List<Business> businessIndex() {

		// TODO Auto-generated method stub
		return busRepo.findAll();
	}

	@Override //Done
	public Business findById(int id) {
		Optional<Business> busOpt = busRepo.findById(id);
		if (busOpt.isPresent()) {
			return busOpt.get();
		}
		return null;
	}

	@Override //Done
	public Business updateBusiness(Business business, int id) {
		Optional<Business> optBus = busRepo.findById(id);
		if (optBus.isPresent()) {
			if (business.getName() != null) {
				Business manBus = optBus.get();
				manBus.setName(business.getName());
				manBus.setDescription(business.getDescription());
				manBus.setPhone(business.getPhone());
				manBus.setImageUrl(business.getImageUrl());
				manBus.setActive(business.isActive());
				return busRepo.saveAndFlush(manBus);
			}
		}
		// TODO Auto-generated method stub
		return null;
	}

	@Override //Done
	public boolean deleteBusiness(int id) {
		boolean deleted = false;
		Optional<Business> optBus = busRepo.findById(id);
		if (optBus.isPresent()) {
			Business manBus = optBus.get();
			try {
				busRepo.delete(manBus);
				deleted = true;
			} catch(Exception e) {
				System.out.println("Could not delete business with id:" + id);
				deleted = false;
			}
		}
		// TODO Auto-generated method stub
		return deleted;
	}

	@Override //Done
	public Business createBusiness(Business business) {
		
		if (business != null) {
			try {
				return busRepo.saveAndFlush(business);
			} catch (Exception e) {
				System.out.println("Could not create new business");
				System.out.println(business);
				System.out.println(e);
				return null;
			}
		} else {
			return null;
		}
		// TODO Auto-generated method stub
	}

	@Override
	public List<Business> findBusinessByName(String name) {
		List<Business> listByName = null;
		if(name.length() > 0) {
			try {
				listByName = busRepo.findByNameLike("%"+name+"%");
			} catch(Exception e) {
				System.out.println("Could not return a list of results from name " + name);
			}
		}
		// TODO Auto-generated method stub
		return listByName;
	}

	@Override
	public List<Business> findBusinessByDescription(String description) {
		List<Business> listByDescription = null;
		if(description.length() > 0) {
			try {
				listByDescription = busRepo.findByDescriptionLike("%"+description+"%");
			} catch(Exception e) {
				System.out.println("Could not return a list of results from description " + description);
			}
		}
		// TODO Auto-generated method stub
		return listByDescription;
	}

	@Override
	public List<Business> findBusinessByZipCode(String zip) {
		List<Business> listByZip = null;
		if(zip.length() > 0) {
			try {
				listByZip = busRepo.findByAddressPostalCodeLike("%"+zip+"%");
			} catch(Exception e) {
				System.out.println("Could not return a list of results from description " + zip);
			}
		}
		// TODO Auto-generated method stub
		return listByZip;
	}

}
