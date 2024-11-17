package com.vedruna.portfolio.services;

import com.vedruna.portfolio.dto.TechnologyDTO;

public interface TechnologyServiceI {
    void insertTechnology(TechnologyDTO technologyDTO);

    void deleteTechnology(int techId);
}
