package com.vedruna.portfolio.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.vedruna.portfolio.persistance.models.Project;
import com.vedruna.portfolio.persistance.repository.ProjectRepository;
import com.vedruna.portfolio.services.ProjectServiceI;

@Service
public class ProjectServiceImpl implements ProjectServiceI {

    @Autowired
    private ProjectRepository projectRepo;

    @Override
    public Page<Project> showAllProjects(Pageable pageable) {
        return projectRepo.findAll(pageable);
    }

    @Override
    public Page<Project> showProjectsByName(String projectName, Pageable pageable) {
        return projectRepo.findByProjectName(projectName, pageable);
    }

    @Override
    public void saveProject(Project project) {
        projectRepo.save(project);
    }

    @Override
    public void updateProject(Integer projectId, Project project) {
        project.setProjectId(projectId); 
        projectRepo.save(project);
    }

    @Override
    public void deleteProject(Integer id) {
        projectRepo.deleteById(id);
    }
}
