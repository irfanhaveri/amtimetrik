package com.example.restservice.service;


import com.example.restservice.Constants;
import com.example.restservice.model.Entry;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static com.example.restservice.Constants.*;

@Service
public class FilterService {

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    ObjectMapper objectMapper;


    public List<Entry> filterOnParams(String country, Map<String, Boolean> criteria) throws Exception{

        Object[] arr1 = restTemplate.getForObject("http://api.worldbank.org/v2/country?format=json", Object[].class);
        Entry[] entryArray = objectMapper.readValue(objectMapper.writeValueAsString(arr1[1]) , Entry[].class);

        //filter based on some criteria
        List<Entry> result = Arrays.asList(entryArray);

        Entry matchCountry = result.stream().filter(entry -> entry.getId().equalsIgnoreCase(country)).collect(Collectors.toList()).get(0);

        result = result.stream().filter(entry -> !entry.getId().equalsIgnoreCase(country)).collect(Collectors.toList());

        Predicate<Entry> matchIncome = e -> e.getIncomeLevel().getId().equalsIgnoreCase(matchCountry.getIncomeLevel().getId());
        Predicate<Entry> matchRegion = e -> e.getRegion().getId().equalsIgnoreCase(matchCountry.getRegion().getId());
        Predicate<Entry> matchLending = e -> e.getLendingType().getId().equalsIgnoreCase(matchCountry.getLendingType().getId());


        if(criteria.get(REGION) && StringUtils.isNotBlank(matchCountry.getRegion().getId())){
            System.out.println("filtering on region");
            result = result.stream().filter(matchRegion).collect(Collectors.toList());
        }
        if(criteria.get(LENDING) && StringUtils.isNotBlank(matchCountry.getLendingType().getId())){
            System.out.println("filtering on lending");
            result = result.stream().filter(matchLending).collect(Collectors.toList());
        }
        if(criteria.get(INCOME) && StringUtils.isNotBlank(matchCountry.getIncomeLevel().getId())){
            System.out.println("filtering on income");
            result = result.stream().filter(matchIncome).collect(Collectors.toList());
        }
        return result;
    }
}
