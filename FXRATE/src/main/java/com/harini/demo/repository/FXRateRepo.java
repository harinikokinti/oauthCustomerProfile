package com.harini.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.harini.demo.Entity.FXRate;

@Repository
public interface FXRateRepo extends JpaRepository<FXRate, Integer> {
	
	List<FXRate> findByBaseCurrencyAndToCurrency(String baseCurrency, String toCurrency);

}
