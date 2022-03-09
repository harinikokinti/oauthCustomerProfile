package com.harini.demo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.harini.demo.model.Alien;

@RepositoryRestResource(collectionResourceRel = "alien" , path = "alien" )
public interface AlienRepo extends JpaRepository<Alien, Integer> {

}
