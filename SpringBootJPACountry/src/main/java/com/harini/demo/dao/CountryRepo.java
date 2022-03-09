package com.harini.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.harini.demo.model.Country;

public interface CountryRepo extends JpaRepository<Country, Object> {

}
