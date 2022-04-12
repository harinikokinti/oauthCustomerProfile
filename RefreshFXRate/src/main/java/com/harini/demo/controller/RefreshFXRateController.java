package com.harini.demo.controller;
/*

FXRATE project in Orbitz: 

ORBITZ is a global leading travel company that uses innovative technology to enable travelers to search, plan and book
range of travel products and services including airline tickets, hotels, car rentals, cruises, and vacation packages.

1. External File:  
The file from  https://api.exchangerate.host/latest  has rates with base currency and its corresponding to currency.

2. Refresh FXRate : 
Get the rates from the external file and populate the FXRATE table present in the postgresql database .
The FXRATE table(id, base_Currency, to_currency, rate, date) is in FXRATE service 
Using Rest template, we get the rates from the external file and using the same rest template we enter 
the rates into the FXRATE table.


I worked for orbitz.com or expedia.com site writing the services at the back end. 
In this project RefreshFXRate, the exchange rates are refreshed for every click
(clicking on the "english" button on the expedia.com site, where the respective country 
details with its corresponding currency can be changed before booking the ticket).
 -->The RefreshFXRate service populates  the FXRATE table with the exchange rate details
 	The front UI gets these exchange rate details from this service (makes Ajax calls to this service)
 -->It also maintains historical data of exchange rates with date. 
 	For example, If a user wanted to cancel the ticket which was booked earlier, the price of that 
 	booked date can be retrieved from the FXRATE table for cancellation



 */


//This is a Refresh FXRate service

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.harini.demo.model.RefreshFXRate;
import com.harini.demo.service.RefreshFXRateService;

@RestController
public class RefreshFXRateController {
	
	@Autowired
	RefreshFXRateService service;
	
	@GetMapping("/")
	public RefreshFXRate getRates() {
	
		return service.getRates();
		
	}
	
	
	
	
	

}
