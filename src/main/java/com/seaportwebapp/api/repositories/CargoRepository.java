package com.seaportwebapp.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.seaportwebapp.api.models.Cargo;

public interface CargoRepository extends JpaRepository<Cargo, Long> {
}
