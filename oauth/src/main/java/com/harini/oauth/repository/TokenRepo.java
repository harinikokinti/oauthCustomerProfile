package com.harini.oauth.repository;

import com.harini.oauth.entity.TokenEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface TokenRepo extends JpaRepository<TokenEntity,Integer> {

    TokenEntity findByRefreshToken(String refreshToken);

}
