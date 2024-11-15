package com.vedruna.portfolio.persistance.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.vedruna.portfolio.persistance.models.Project;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Integer>{

    List<Project> findByProjectNameContainingIgnoreCase(String word);
    Project findByProjectName(String projectName); // ???
    Page<Project> findByProjectName(String projectName, Pageable pageable);
}
