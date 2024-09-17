package com.seaportwebapp.api.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.seaportwebapp.api.models.GoMap;

public interface GoMapRepository extends JpaRepository<GoMap, Long> {
}
