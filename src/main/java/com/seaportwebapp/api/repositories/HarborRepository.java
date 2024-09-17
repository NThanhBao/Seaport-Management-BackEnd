package com.seaportwebapp.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.seaportwebapp.api.models.Harbor;

public interface HarborRepository extends JpaRepository<Harbor, Long> {

}
