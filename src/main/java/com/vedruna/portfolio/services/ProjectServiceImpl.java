//Repasar bn

package com.vedruna.portfolio.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.vedruna.portfolio.dto.DeveloperDTO;
import com.vedruna.portfolio.dto.ProjectDTO;
import com.vedruna.portfolio.persistance.models.Developer;
import com.vedruna.portfolio.persistance.models.Project;
import com.vedruna.portfolio.persistance.models.Status;
import com.vedruna.portfolio.persistance.models.Technology;
import com.vedruna.portfolio.persistance.repository.ProjectRepository;


@Service
public class ProjectServiceImpl implements ProjectServiceI {

    @Autowired
    private ProjectRepository projectRepo;

    @Override
    public Page<ProjectDTO> showAllProjects(Pageable pageable) {
        //obtiene los proyectos paginados y los convierte a DTO
        return projectRepo.findAll(pageable).map(project -> new ProjectDTO(project));
    }

    @Override
    public Page<ProjectDTO> showProjectsByName(String projectName, Pageable pageable) {
        return projectRepo.findByProjectNameContainingIgnoreCase(projectName, pageable).map(project -> new ProjectDTO(project));
    }

    @Override
    public void saveProject(ProjectDTO projectDTO) {
        //convierte DTO a entidad Project
        Project project = new Project();
        project.setProjectName(projectDTO.getProjectName());
        project.setDescription(projectDTO.getDescription());
        project.setStartDate(projectDTO.getStartDate());
        project.setEndDate(projectDTO.getEndDate());
        project.setRepositoryUrl(projectDTO.getRepositoryUrl());
        project.setDemoUrl(projectDTO.getDemoUrl());
        project.setPicture(projectDTO.getPicture());

       // Crear el Status manualmente y asignarlo al proyecto
        Status status = new Status();
        status.setStatusName(projectDTO.getStatusName());
        project.setStatus(status);


        // Procesar los desarrolladores
        List<Developer> developers = new ArrayList<>();
        for (DeveloperDTO developerDTO : projectDTO.getDevelopers()) {
            Developer developer = new Developer();
            developer.setDevId(developerDTO.getDevId());
            developer.setDevName(developerDTO.getDevName());
            developer.setDevSurname(developerDTO.getDevSurname());
            developer.setEmail(developerDTO.getEmail());
            developer.setLinkedinUrl(developerDTO.getLinkedinUrl());
            developer.setGithubUrl(developerDTO.getGithubUrl());
            developers.add(developer);
        }
        project.setDevelopers(developers);

        List<Technology> technologies = new ArrayList<>();
        for (String techName : projectDTO.getTechnologies()) {
            // Crear una nueva instancia de Technology
            Technology technology = new Technology();
            technology.setTechName(techName);
            technologies.add(technology);
        }

        // Asignar las tecnologías al proyecto
        project.setTechnologies(technologies);



        projectRepo.save(project);
    }

    @Override
    public void updateProject(Integer id, ProjectDTO projectDTO) {
        //verifica si existe
        if (!projectRepo.existsById(id)) {
            throw new RuntimeException("Proyecto no encontrado");
        }

        Project project = projectRepo.findById(id).get();
        project.setProjectName(projectDTO.getProjectName());
        project.setDescription(projectDTO.getDescription());
        project.setStartDate(projectDTO.getStartDate());
        project.setEndDate(projectDTO.getEndDate());
        project.setRepositoryUrl(projectDTO.getRepositoryUrl());
        project.setDemoUrl(projectDTO.getDemoUrl());
        project.setPicture(projectDTO.getPicture());
        projectRepo.save(project);
    }

    @Override
    public void deleteProject(Integer id) {
        if (!projectRepo.existsById(id)) {
            throw new RuntimeException("Proyecto no encontrado");
        }

        projectRepo.deleteById(id);
    }
}
