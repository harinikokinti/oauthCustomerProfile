package com.harini.demo.model;

public class FXRate {

	private String baseCurrency;
	private String toCurrency;
	private double fxRate;
	private String date;

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

	@Override
	public String toString() {
		return "FXRate [baseCurrency=" + baseCurrency + ", toCurrency=" + toCurrency + ", fxRate=" + fxRate + ", date="
				+ date + "]";
	}
	
	

}