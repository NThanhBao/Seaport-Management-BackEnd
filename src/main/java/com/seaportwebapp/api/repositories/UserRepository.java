package com.seaportwebapp.api.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.seaportwebapp.api.models.UserInfo;

public interface UserRepository extends JpaRepository<UserInfo, Long> {
	List<UserInfo> findByUsername(String username);
}
