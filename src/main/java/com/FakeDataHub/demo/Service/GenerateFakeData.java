package com.FakeDataHub.demo.Service;

import com.github.javafaker.Faker;

public class GenerateFakeData {
	
	public static Object generateMockValue(String type, Faker faker) {
		if("String".equalsIgnoreCase(type)) {
			return faker.lorem().word();
		}
		else if ("Double".equalsIgnoreCase(type)) {
			return faker.number().randomDouble(2, 0, 1000);
		}
		else if("Integer".equalsIgnoreCase(type)) {
			return faker.number().numberBetween(1, 100);
		}
		else {
			return "Unknow ERROR";
		}
	}

}
