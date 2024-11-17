package com.vedruna.portfolio.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.vedruna.portfolio.dto.ProjectDTO;

public interface ProjectServiceI {

    Page<ProjectDTO> showAllProjects(Pageable pageable);

    Page<ProjectDTO> showProjectsByName(String projectName, Pageable pageable);

    void saveProject(ProjectDTO projectDTO);

    void updateProject(Integer id, ProjectDTO projectDTO);

    void deleteProject(Integer id);
}
