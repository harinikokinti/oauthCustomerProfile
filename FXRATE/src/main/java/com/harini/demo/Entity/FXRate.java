package com.harini.demo.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="FXRATE")
public class FXRate {
	@Id
	@GeneratedValue
	private int Id;
	
	@Column(name = "base_currency")
	private String baseCurrency;
	
	@Column(name = "to_currency")
	private String toCurrency;
	
	@Column(name = "fx_rate")
	private double fxRate;
	
	@Column(name = "date")
	private String date;

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public String getBaseCurrency() {
		return baseCurrency;
	}

	public void setBaseCurrency(String baseCurrency) {
		this.baseCurrency = baseCurrency;
	}

	public String getToCurrency() {
		return toCurrency;
	}

	public void setToCurrency(String toCurrency) {
		this.toCurrency = toCurrency;
	}

	public double getFXRate() {
		return fxRate;
	}

	public void setFXRate(double fXRate) {
		fxRate = fXRate;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
	
	

}
