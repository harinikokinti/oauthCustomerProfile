package com.harini.oauth.repository;

import com.harini.oauth.entity.CustomerIdentityEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerIdentityRepo extends JpaRepository<CustomerIdentityEntity, Integer>{
    CustomerIdentityEntity findByUsername(String username);
}
