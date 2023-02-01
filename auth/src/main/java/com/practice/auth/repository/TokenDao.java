package com.practice.auth.repository;

import com.practice.auth.model.Auth.Token;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TokenDao extends JpaRepository<Token,String> {
}
