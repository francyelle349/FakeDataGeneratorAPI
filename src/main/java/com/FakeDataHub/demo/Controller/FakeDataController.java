package com.FakeDataHub.demo.Controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.FakeDataHub.demo.Service.GenerateFakeData;
import com.FakeDataHub.demo.entities.FakeDataRequest;
import com.github.javafaker.Faker;

@RestController
public class FakeDataController {
	
	
	@PostMapping("/generate")
	public List<Map<String, Object>> generate(@RequestBody FakeDataRequest jsonRequest){
		
		Faker faker = new Faker();		
		
		List<Map<String, Object>> resultList = new ArrayList<>(); 
		
		for(int i = 0; i < jsonRequest.getCount(); i++) {
			// map for each new Fake entity
			
			Map<String, Object> MockDataObj = new HashMap<>();
			
			
			Map<String, String> field = jsonRequest.getField();
			
			for(Map.Entry<String, String> obj : field.entrySet() ) {
			  String fieldName = obj.getKey();
			  String type = obj.getValue();
			  Object mockValue = GenerateFakeData.generateMockValue(type, faker);
			  MockDataObj.put(fieldName, mockValue);
			  
			}
			
			resultList.add(MockDataObj);
		}
		
		return resultList;
		
		
		
	
	}

}
