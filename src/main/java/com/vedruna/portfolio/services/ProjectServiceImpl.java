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
import com.vedruna.portfolio.dto.TechnologyDTO;
import com.vedruna.portfolio.persistance.models.Developer;
import com.vedruna.portfolio.persistance.models.Project;
import com.vedruna.portfolio.persistance.models.Status;
import com.vedruna.portfolio.persistance.models.Technology;
import com.vedruna.portfolio.persistance.repository.DeveloperRepository;
import com.vedruna.portfolio.persistance.repository.ProjectRepository;
import com.vedruna.portfolio.persistance.repository.StatusRepository;
import com.vedruna.portfolio.persistance.repository.TechnologyRepository;



@Service
public class ProjectServiceImpl implements ProjectServiceI {

    @Autowired
    private ProjectRepository projectRepo;

    @Autowired
    private StatusRepository statusRepo;

    @Autowired
    private TechnologyRepository technologyRepo;

    @Autowired
    private DeveloperRepository developerRepo;


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

       // verifica si el status existe
        Status status = statusRepo.findById(projectDTO.getStatusId())
           .orElseThrow(() -> new RuntimeException("Status not found"));
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
        for (TechnologyDTO techDTO : projectDTO.getTechnologies()) {
            Technology technology;
            
            if (techDTO.getTechId() != null) {
                // busca x id
                technology = technologyRepo.findById(techDTO.getTechId())
                    .orElseThrow(() -> new RuntimeException("Technology no encontrada con ID: " + techDTO.getTechId()));
            } else {
                // x nombre si no hay ID, o crear una nueva
                technology = technologyRepo.findByTechName(techDTO.getTechName()).stream().findFirst()
                    .orElseGet(() -> {
                        Technology newTech = new Technology();
                        newTech.setTechName(techDTO.getTechName());
                        return technologyRepo.save(newTech);
                    });
            }
            
            technologies.add(technology);
        }
        project.setTechnologies(technologies);




        projectRepo.save(project);
    }

    @Override
    public void updateProject(Integer id, ProjectDTO projectDTO) {
        //verifica si existe
        if (!projectRepo.existsById(id)) {
            throw new RuntimeException("Proyecto no encontrado");
        }

        if (projectDTO.getProjectName() == null || projectDTO.getProjectName().trim().isEmpty()) {
            throw new RuntimeException("El nombre del proyecto es obligatorio");
        }

            Project project = projectRepo.findById(id).get();
            project.setProjectName(projectDTO.getProjectName());
            project.setDescription(projectDTO.getDescription());
            project.setStartDate(projectDTO.getStartDate());
            project.setEndDate(projectDTO.getEndDate());
            project.setRepositoryUrl(projectDTO.getRepositoryUrl());
            project.setDemoUrl(projectDTO.getDemoUrl());
            project.setPicture(projectDTO.getPicture());

            Status status = statusRepo.findById(projectDTO.getStatusId())
                    .orElseThrow(() -> new RuntimeException("Status no encontrado"));
            project.setStatus(status);
        

            List<Developer> developers = new ArrayList<>();
            for (DeveloperDTO developerDTO : projectDTO.getDevelopers()) {
                Developer developer = developerRepo.findById(developerDTO.getDevId())  // Aquí se asume que DeveloperDTO tiene un método getDevId()
                    .orElseThrow(() -> new RuntimeException("Developer no encontrado"));
                developers.add(developer);
            }
            project.setDevelopers(developers);

            List<Technology> technologies = new ArrayList<>();
            for (TechnologyDTO technologyDTO : projectDTO.getTechnologies()) {
                Technology technology;
                if (technologyDTO.getTechId() != null) {
                    technology = technologyRepo.findById(technologyDTO.getTechId())
                        .orElseThrow(() -> new RuntimeException("Technology no encontrada con ID: " + technologyDTO.getTechId()));
                } 
                else if (technologyDTO.getTechName() != null && !technologyDTO.getTechName().isEmpty()) {
                    technology = technologyRepo.findByTechName(technologyDTO.getTechName()).stream().findFirst()
                        .orElseGet(() -> {
                            Technology newTech = new Technology();
                            newTech.setTechName(technologyDTO.getTechName());
                            return technologyRepo.save(newTech);
                        });
                } 
                else {
                    throw new RuntimeException("Debe proporcionar al menos el ID o el nombre de la tecnología");
                }

                technologies.add(technology);
            }
            project.setTechnologies(technologies);




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
