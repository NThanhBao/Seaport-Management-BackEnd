package com.seaportwebapp.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.seaportwebapp.api.models.Departures;

public interface DeparturesRepository extends JpaRepository<Departures, Long>{

}
