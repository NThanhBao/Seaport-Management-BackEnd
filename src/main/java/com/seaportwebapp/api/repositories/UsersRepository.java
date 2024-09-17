package com.seaportwebapp.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.seaportwebapp.api.models.Users;

public interface UsersRepository extends JpaRepository<Users, Long> {

}
