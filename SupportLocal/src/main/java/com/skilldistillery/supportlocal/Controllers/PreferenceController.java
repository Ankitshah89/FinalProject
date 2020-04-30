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

import com.skilldistillery.supportlocal.entities.Preference;
import com.skilldistillery.supportlocal.entities.PreferenceCategory;
import com.skilldistillery.supportlocal.services.PreferenceService;

@RestController
@RequestMapping("api")
@CrossOrigin({ "*", "http://localhost:4220" })
public class PreferenceController {

	@Autowired
	private PreferenceService prefServe;

	@GetMapping("preferences") // Tested
	public List<Preference> preferenceIndex(HttpServletResponse response) {
		List<Preference> index = prefServe.prefIndex();
		if (index != null) {
			response.setStatus(200);
		} else {
			response.setStatus(404);
		}
		return index;
	}

	@GetMapping("preferences/{id}") // Tested
	public Preference prefById(@PathVariable int id, HttpServletResponse response) {
		Preference pref = prefServe.prefById(id);
		if (pref != null) {
			response.setStatus(200);
		} else {
			response.setStatus(404);
		}
		return pref;
	}

	@DeleteMapping("preferences/{id}") // Tested
	public boolean deletedPref(@PathVariable int id, HttpServletResponse response,
			Principal principal) {
		boolean deleted = prefServe.deleteById(principal.getName(),id);
		if (deleted) {
			response.setStatus(200);
		} else {
			response.setStatus(404);
		}
		return deleted;
	}

	@PostMapping("preferences") // Tested
	public Preference createPref(@RequestBody Preference pref, HttpServletResponse response,Principal principal) {
		
		if (pref.getPreferenceCategory() != null && pref.getPreferenceType() != null) {
			PreferenceCategory category[] = PreferenceCategory.values();
			for (PreferenceCategory pc : category) {
				if (pref.getPreferenceCategory() == pc) {
					response.setStatus(201);
					return prefServe.createPref(principal.getName(),pref);
				} else {
					response.setStatus(400);
				}
			}

		}
		return null;
	}

	@PutMapping("preferences/{id}") // Tested
	public Preference updatePref(@RequestBody Preference pref, @PathVariable int id, HttpServletResponse response,
			Principal principal) {

		if (pref.getPreferenceCategory() != null && pref.getPreferenceType() != null) {

			PreferenceCategory category[] = PreferenceCategory.values();

			for (PreferenceCategory pc : category) {
				if (pref.getPreferenceCategory() == pc) {
					response.setStatus(201);
					return prefServe.updatePref(principal.getName(),pref, id);
				} else {
					response.setStatus(400);
				}
			}

		}
		return null;
	}

	@GetMapping("preferences/search/type/{type}")
	public List<Preference> searchPrefByType(@PathVariable String type, HttpServletResponse response){
		List<Preference> prefList = null;
		if(type.length() > 0) {
			prefList = prefServe.searchByType(type);
				if(prefList.size() > 0) {
				response.setStatus(200);
			} else {
				response.setStatus(404);
			}
		}
		return prefList;
	}

	@GetMapping("preferences/search/category/{category}")
	public List<Preference> searchPrefByCategory(@PathVariable String category, HttpServletResponse response) {
		List<Preference> prefList = null;
		if(category.length() > 0) {
			prefList = prefServe.searchByCategory(category);
				if(prefList.size() > 0) {
				response.setStatus(200);
			} else {
				response.setStatus(404);
			}
		}
		return prefList;
	}

}
