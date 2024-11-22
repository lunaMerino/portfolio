package com.vedruna.portfolio.persistance.repository;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vedruna.portfolio.persistance.models.Technology;

@Repository
public interface TechnologyRepository extends JpaRepository<Technology, Integer>{
        List<Technology> findByTechName(String techName);
}


