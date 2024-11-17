package com.vedruna.portfolio.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.vedruna.portfolio.dto.DeveloperDTO;
import com.vedruna.portfolio.persistance.models.Developer;
import com.vedruna.portfolio.persistance.repository.DeveloperRepository;

@Service
public class DeveloperServiceImpl implements DeveloperServiceI {

    @Autowired
    private DeveloperRepository developerRepo;

    @Override
    public Page<Developer> showAllDevelopers(Pageable pageable) {
        return developerRepo.findAll(pageable);
    }

    @Override
    public Page<Developer> showDevelopersByName(String devName, Pageable pageable) {
        return developerRepo.findByDevName(devName, pageable);
    }

    @Override
    public void saveDeveloper(Developer developer) {
        developerRepo.save(developer);
    }

    @Override
    public void updateDeveloper(Integer devId, Developer developer) {
        if (!developerRepo.existsById(devId)) {
            throw new RuntimeException("Desarrollador no encontrado");
        }

        developer.setDevId(devId);
        developerRepo.save(developer);
    }

    @Override
    public void deleteDeveloper(Integer devId) {

        if (!developerRepo.existsById(devId)) {
            throw new RuntimeException("Desarrollador no encontrado");
        }

        developerRepo.deleteById(devId);
    }
    
    //convierte la página de desarrolladores a DTOs
    public List<DeveloperDTO> convertPageToDTO(Page<Developer> developersPage) {
        List<DeveloperDTO> developerDTOList = new ArrayList<>();
        
        // mapea cada Developer a DeveloperDTO
        developersPage.forEach(developer -> {
            developerDTOList.add(new DeveloperDTO(developer));
        });
        
        return developerDTOList;
    }
}
