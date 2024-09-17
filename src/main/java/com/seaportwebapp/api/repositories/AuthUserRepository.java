package com.seaportwebapp.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.seaportwebapp.api.models.AuthUser;

public interface AuthUserRepository extends JpaRepository<AuthUser, Long>{

}
