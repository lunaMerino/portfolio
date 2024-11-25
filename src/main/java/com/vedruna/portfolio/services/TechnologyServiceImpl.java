package com.vedruna.portfolio.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vedruna.portfolio.dto.TechnologyDTO;
import com.vedruna.portfolio.persistance.models.Project;
import com.vedruna.portfolio.persistance.models.Technology;
import com.vedruna.portfolio.persistance.repository.ProjectRepository;
import com.vedruna.portfolio.persistance.repository.TechnologyRepository;

@Service
public class TechnologyServiceImpl implements TechnologyServiceI {

    @Autowired
    private TechnologyRepository technologyRepo;

    @Autowired
    private ProjectRepository projectRepo;

    @Override
    public void insertTechnology(TechnologyDTO technologyDTO) {
        //convertir DTO en entidad Technology
        Technology technology = new Technology();
        technology.setTechName(technologyDTO.getTechName());

        technologyRepo.save(technology);
    }

    @Override
    public void deleteTechnology(int techId) {
        //verificar
        if (!technologyRepo.existsById(techId)) {
            throw new RuntimeException("Tecnología no encontrada");
        }
        Technology technology = technologyRepo.findById(techId)
            .orElseThrow(() -> new RuntimeException("Technology not found"));

        for (Project project : technology.getProjects()) {

            project.getTechnologies().remove(technology);
        }
        projectRepo.saveAll(technology.getProjects());

        technologyRepo.delete(technology);
    }
}
