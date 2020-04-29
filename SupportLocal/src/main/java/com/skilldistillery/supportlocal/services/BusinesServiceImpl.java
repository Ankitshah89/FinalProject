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

	@Override
	public List<Business> businessIndex() {
		
		// TODO Auto-generated method stub
		return busRepo.findAll();
	}

	@Override
	public Business findById(int id) {
		Optional<Business> busOpt = busRepo.findById(id);
		if(busOpt.isPresent()) {
			return busOpt.get();
		}
		return null;
	}

	@Override
	public Business updateBusiness(Business business, int id) {
		Optional<Business> optBus = busRepo.findById(id);
		if(optBus.isPresent()) {
			if(business.getName() != null) {
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

	@Override
	public boolean deleteBusiness(Business business) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Business> findBusinessByDescription(String description) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Business> findBusinessByZipCode(String zip) {
		// TODO Auto-generated method stub
		return null;
	}

}
