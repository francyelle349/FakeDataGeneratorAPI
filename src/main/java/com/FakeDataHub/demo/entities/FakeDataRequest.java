package com.FakeDataHub.demo.entities;

import java.util.Map;

public class FakeDataRequest {
	private String entity;
	private Map<String, String> field;
	private int count;
	
	
	public String getEntity() {
		return entity;
	}
	public void setEntity(String entity) {
		this.entity = entity;
	}
	
	public Map<String, String> getField() {
		return field;
	}
	public void setField(Map<String, String> field) {
		this.field = field;
	}
	
	
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
}
