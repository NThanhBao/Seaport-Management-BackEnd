package com.seaportwebapp.api.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.seaportwebapp.api.models.Berhts;
import com.seaportwebapp.api.models.Ships;

public interface BerthsRepository extends JpaRepository<Berhts, Long> {
	List<Berhts> findByBerthName(String berthName);
}
