package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.AadharCard;

public interface AadharRepository extends JpaRepository<AadharCard, String>{

	Optional<AadharCard> findByAdharnumber(String adharnumber);


}
