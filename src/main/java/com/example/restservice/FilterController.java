package com.example.restservice;

import com.example.restservice.model.Entry;
import com.example.restservice.model.Header;
import com.example.restservice.model.Root;
import com.example.restservice.service.FilterService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

import static com.example.restservice.Constants.*;

@RestController
public class FilterController {

	@Autowired
	ObjectMapper objectMapper;

	@Autowired
	FilterService filterService;

	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping(value = "/filter", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity greeting(@RequestParam(value = COUNTRY) String country,
								   @RequestParam(value= REGION) Boolean region,
								   @RequestParam(value= INCOME) Boolean income,
								   @RequestParam(value= LENDING) Boolean lending) throws Exception{

		System.out.println("Params: " + region + ": " +  income + ": " +  lending);
		Map<String, Boolean> criteria = new HashMap();
		criteria.put(REGION, region);
		criteria.put(INCOME, income);
		criteria.put(LENDING, lending);


		return new ResponseEntity<>(filterService.filterOnParams(country, criteria), HttpStatus.CREATED);
	}


}
