package com.harini.demo.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Country {
	
	@Id
	private int countryId;
	private String countryCode;
	private String countryName;	
	public int getCountryId() {
		return countryId;
	}
	public void setCountryId(int countryId) {
		this.countryId = countryId;
	}
	public String getCountryName() {
		return countryName;
	}
	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}
	public String getCountryCode() {
		return countryCode;
	}
	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}
	
	

}
