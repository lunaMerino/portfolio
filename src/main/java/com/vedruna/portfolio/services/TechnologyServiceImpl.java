package com.vedruna.portfolio.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vedruna.portfolio.dto.TechnologyDTO;
import com.vedruna.portfolio.persistance.models.Technology;
import com.vedruna.portfolio.persistance.repository.TechnologyRepository;

@Service
public class TechnologyServiceImpl implements TechnologyServiceI {

    @Autowired
    private TechnologyRepository technologyRepo;

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

        technologyRepo.deleteById(techId);
    }
}
