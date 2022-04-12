package com.example.demo.repository;

import com.example.demo.entity.CustomerProfileEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerProfileRepo extends JpaRepository<CustomerProfileEntity, Integer> {

   // @Query("from CustomerProfileEntity where customerid=?1")
   public CustomerProfileEntity findByCustomerId(String customerId);

   // @Query("from Alien where lang=?1 order by a_name")
}


