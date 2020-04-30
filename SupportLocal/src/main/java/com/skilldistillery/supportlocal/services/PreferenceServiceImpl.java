package com.skilldistillery.supportlocal.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.supportlocal.entities.Preference;
import com.skilldistillery.supportlocal.repositories.PreferenceRepository;

@Service
public class PreferenceServiceImpl implements PreferenceService {
	@Autowired
	private PreferenceRepository pRepo;

	@Override //Done
	public List<Preference> prefIndex() {
		// TODO Auto-generated method stub
		return pRepo.findAll();
	}

	@Override //Done
	public Preference createPref(Preference pref) {
		if(pref != null) {
			try {
				return pRepo.saveAndFlush(pref);
			} catch(Exception e) {
				System.out.println("Could not create a new preference");
				System.out.println(pref);
				System.out.println(e);
				return null;
			}
		}
		return null;
	}

	@Override //Done
	public Preference updatePref(Preference pref, int id) {
		Optional<Preference> optPref = pRepo.findById(id);
		if(optPref.isPresent()) {
			if(pref.getPreferenceCategory() != null && pref.getPreferenceType() != null) {
				try {
					Preference manPref = optPref.get();
					manPref.setPreferenceCategory(pref.getPreferenceCategory());
					manPref.setPreferenceType(pref.getPreferenceType());
					return pRepo.saveAndFlush(manPref);
				}catch (Exception e) {
					System.out.println("Could not update Preference: " + pref);
					System.out.println(e);
					return null;
				}
			}
		}
		// TODO Auto-generated method stub
		return null;
	}

	@Override //Done
	public boolean deleteById(int id) {
		boolean deleted = false;
		Optional<Preference> optPref = pRepo.findById(id);
		if(optPref.isPresent()) {
			Preference toDelete = optPref.get();
			try {
				pRepo.delete(toDelete);
				deleted = true;
			} catch (Exception e) {
				System.out.println("Could not delete preference with id: " + id);
				System.out.println(e);
			}
		}
		// TODO Auto-generated method stub
		return deleted;
	}

	@Override //Done
	public Preference prefById(int id) {
		Optional<Preference> prefOpt = pRepo.findById(id);
		if(prefOpt.isPresent()) {
			return prefOpt.get();
		}
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Preference> searchByType(String type) {
		List<Preference> preferences = null;
		if(type.length() > 0) {
			try {
				preferences = pRepo.findByPreferenceTypeLike("%" + type + "%");
				
			} catch (Exception e) {
				System.out.println("Could not retrieve list of preferences: " + type);
			}
		}
		// TODO Auto-generated method stub
		return preferences;
	}

	@Override
	public List<Preference> searchByCategory(String category) {
		List<Preference> preferences = null;
		if(category.length() > 0) {
			try {
				preferences = pRepo.findByPreferenceCategoryLike("%" + category + "%");
				
			} catch (Exception e) {
				System.out.println("Could not retrieve list of preferences: " + category);
			}
		}
		// TODO Auto-generated method stub
		return preferences;
	}
}
