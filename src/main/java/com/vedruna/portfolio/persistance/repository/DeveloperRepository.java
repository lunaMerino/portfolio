package com.vedruna.portfolio.persistance.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vedruna.portfolio.persistance.models.Developer;

@Repository
public interface DeveloperRepository extends JpaRepository<Developer, Integer>{
    
}
