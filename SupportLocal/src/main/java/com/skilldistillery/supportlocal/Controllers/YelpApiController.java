package com.skilldistillery.supportlocal.Controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.skilldistillery.supportlocal.entities.YelpRating;
import com.skilldistillery.supportlocal.entities.YelpReview;

@RestController
@RequestMapping("api")
@CrossOrigin({ "*", "http://localhost:4220" })
public class YelpApiController {

	@GetMapping("yelp/{phoneNo}")
	public YelpRating giveMeMicroserviceDetails(@PathVariable("phoneNo") String phoneNo)
			throws JsonMappingException, JsonProcessingException {

		YelpRating yelpRating = new YelpRating();
		ObjectMapper objectMapper = new ObjectMapper();
		String businessId = null;

		// set url for rating
		String url = "https://api.yelp.com/v3/businesses/search/phone?phone=+1" + phoneNo;
		String responseForRating = callYelp(url);

		// set rating
		JsonNode jsonNode = objectMapper.readTree(responseForRating);
		yelpRating.setRating(jsonNode.get("businesses").get(0).get("rating").asText());

		// set url for reviews
		String url2 = "https://api.yelp.com/v3/businesses/" + jsonNode.get("businesses").get(0).get("id").asText()
				+ "/reviews";
		String responseForReviews = callYelp(url2);

		// set reviews
		// convert JSON string to `JsonNode`
		JsonNode jsonNode2 = objectMapper.readTree(responseForReviews);

		JsonNode arrNode = jsonNode2.get("reviews");
		List<YelpReview> reviews = new ArrayList<>();
		for (JsonNode objNode : arrNode) {
			YelpReview reviewYelp = new YelpReview();
			String nameOfUser = objNode.get("user").get("name").asText();
			String reviewOfUser = objNode.get("text").asText();

			reviewYelp.setUser(nameOfUser);
			reviewYelp.setText(reviewOfUser);
			reviews.add(reviewYelp);

		}
		yelpRating.setReviews(reviews);

		return yelpRating;
	}

	private String callYelp(String url) {
		// create an instance of RestTemplate
		RestTemplate restTemplate = new RestTemplate();

		// create headers
		HttpHeaders headers = new HttpHeaders();

		headers.setBearerAuth(
				"GznpzSFIoicRGoelXgA2lp0Od_2zwlIs06ymqXCIfvXoE0xeTcKsQT7nZjflY_72DIMpJkavEkoAyRx7ufVW227IdM4vE2Joo0HfVZ7XfN0KUxrI1cXD0hkpZUCnXnYx");

		// build the request
		HttpEntity request = new HttpEntity(headers);

		// make an HTTP GET request with headers
		ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, request, String.class, 1);
		return response.getBody();

	}

}
