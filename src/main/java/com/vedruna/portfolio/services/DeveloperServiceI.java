package com.vedruna.portfolio.services;

import com.vedruna.portfolio.dto.DeveloperDTO;

public interface DeveloperServiceI {    
    void insertDeveloper(DeveloperDTO developerDTO);

    void deleteDeveloper(int devId);
}
