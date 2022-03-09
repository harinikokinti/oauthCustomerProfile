package com.harini.demo.service;


/*
 REST TEMPLATE:

It is used to call one service from another .

RestTemplate.getForEntity():
----------------------------
The getForEntity method retrieves resources from the given URI or URL templates.
It returns response as ResponseEntity using which we can get response status code, response body, headers etc.,
To fetch data on the basis of some key properties, we can send them as path variables. 
We need to use URI template and pass a Map or Object varargs to getForEntity method to expand it on URI template.

RestTemplate.postForEntity()
----------------------------
The postForEntity method creates new resource by posting the given object to the given URI template using HTTP POST method. 
The postForEntity method returns instance of ResponseEntity using which we can fetch the information about HTTP status, URI of newly created resource, response content body etc.
 The postForEntity method accepts URI template, object to post, response type. We can also pass path variables as Map and object variable arguments to this method.


 */
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.function.ServerRequest.Headers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.harini.demo.model.FXRate;
import com.harini.demo.model.RefreshFXRate;

@Service
public class RefreshFXRateService {

	@Autowired
	private RestTemplate externalFileRestTemplate;
	
	@Autowired
	private RestTemplate fxRateRestTemplate;

	public RefreshFXRate getRates() {	
		
		// RestTemplate.getForEntity():		
		RefreshFXRate refreshFXRate = externalFileRestTemplate
				.getForEntity("https://api.exchangerate.host/latest", RefreshFXRate.class).getBody();
		
		
		/* here the jackson library converts the json data which we get from the URL into the object of class type RefreshFXRate.
		 getForEntity return type is ResonseEntity(which carries, headers, status body). So to get only body, we use getBody()

		 here "rates" has key, value pair data ( key = toCurrency, value = fxrate)
		 Create entryset and traverse and copy the map values into the FXRate object */
		
		
		// RestTemplate.postForEntity()
		var entrySet = refreshFXRate.getRates().entrySet(); // get all entries of the map rates

		for (Map.Entry<String, Double> m : entrySet) {
			FXRate fxRate = new FXRate();
			fxRate.setBaseCurrency(refreshFXRate.getBase());
			fxRate.setToCurrency(m.getKey());
			fxRate.setFXRate(m.getValue());
			fxRate.setDate(refreshFXRate.getDate());
			
			HttpHeaders httpHeaders = new HttpHeaders();
			httpHeaders.add("Content-Type", "application/json");
			
			HttpEntity<FXRate> httpEntity = new HttpEntity<>(fxRate, httpHeaders);
			
			ResponseEntity<String> responseEntity = fxRateRestTemplate
					.postForEntity("http://localhost:8080/rates/", httpEntity, String.class);
			
			System.out.println(responseEntity.getBody() + " : " + responseEntity.getStatusCode());

		}

		return refreshFXRate;

	}
	
	
}
