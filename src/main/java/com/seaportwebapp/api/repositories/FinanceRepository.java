package com.seaportwebapp.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.seaportwebapp.api.models.Finance;

public interface FinanceRepository extends JpaRepository<Finance, Long> {

}
