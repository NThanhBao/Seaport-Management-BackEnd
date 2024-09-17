package com.seaportwebapp.api.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.seaportwebapp.api.models.Ships;

public interface ShipsRepository extends JpaRepository<Ships, Long> {
	List<Ships> findByShipName(String shipName);
}
