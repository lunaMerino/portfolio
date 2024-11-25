package com.vedruna.portfolio.services;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vedruna.portfolio.dto.DeveloperDTO;
import com.vedruna.portfolio.persistance.models.Developer;
import com.vedruna.portfolio.persistance.models.Project;
import com.vedruna.portfolio.persistance.repository.DeveloperRepository;
import com.vedruna.portfolio.persistance.repository.ProjectRepository;

@Service
public class DeveloperServiceImpl implements DeveloperServiceI {

    @Autowired
    private DeveloperRepository developerRepo;

    @Autowired
    private ProjectRepository projectRepo;


    @Override
    public void insertDeveloper(DeveloperDTO developerDTO) {
        //convierte el dto a entidad developer
        Developer developer = new Developer();
        developer.setDevName(developerDTO.getDevName());
        developer.setDevSurname(developerDTO.getDevSurname());
        developer.setEmail(developerDTO.getEmail());
        developer.setLinkedinUrl(developerDTO.getLinkedinUrl());
        developer.setGithubUrl(developerDTO.getGithubUrl());

        developerRepo.save(developer);
    }

    @Override
    public void deleteDeveloper(int devId) {
        //verifica
        if (!developerRepo.existsById(devId)) {
            throw new RuntimeException("Desarrollador no encontrado");
        }
        Developer developer = developerRepo.findById(devId)
                .orElseThrow(() -> new RuntimeException("Desarrollador no encontrado"));

        List<Project> projects = developer.getProjects();

        for (Project project : projects) {
            project.getDevelopers().remove(developer);
            projectRepo.save(project);
        }

        developerRepo.delete(developer);
    }

}
