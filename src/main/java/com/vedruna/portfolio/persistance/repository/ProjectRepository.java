package com.vedruna.portfolio.persistance.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vedruna.portfolio.persistance.models.Project;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Integer> {
    Page<Project> findByProjectNameContainingIgnoreCase(String projectName, Pageable pageable);
}
