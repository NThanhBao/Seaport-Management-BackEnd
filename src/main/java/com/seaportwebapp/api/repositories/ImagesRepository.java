package com.seaportwebapp.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.seaportwebapp.api.models.Images;

public interface ImagesRepository extends JpaRepository<Images, Long> {

}
