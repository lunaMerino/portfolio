package com.vedruna.portfolio.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vedruna.portfolio.dto.DeveloperDTO;
import com.vedruna.portfolio.persistance.models.Developer;
import com.vedruna.portfolio.persistance.repository.DeveloperRepository;

@Service
public class DeveloperServiceImpl implements DeveloperServiceI {

    @Autowired
    private DeveloperRepository developerRepo;


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

        developerRepo.deleteById(devId);
    }
}
