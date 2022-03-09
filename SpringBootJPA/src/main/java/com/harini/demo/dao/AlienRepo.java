package com.harini.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.harini.demo.model.Alien;

public interface AlienRepo extends JpaRepository<Alien,Integer> {
	
/*
	ArrayList<Alien> findByLang(String lang);
	ArrayList<Alien> findByAidGreaterThan(int aid);
	@Query("from Alien where lang=?1 order by a_name")
	ArrayList<Alien> findByLangSorted(String lang);
	
*/
}
