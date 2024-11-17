package com.vedruna.portfolio.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.vedruna.portfolio.persistance.models.Developer;

public interface DeveloperServiceI {
    
    //obtener todos los desarrolladores
    Page<Developer> showAllDevelopers(Pageable pageable);

    //buscar desarrolladores por nombre
    Page<Developer> showDevelopersByName(String devName, Pageable pageable);

    //guardar nuevo desarrollador
    void saveDeveloper(Developer developer);

    //editar desarrollador
    void updateDeveloper(Integer devId, Developer developer);

    //eliminar desarrollador
    void deleteDeveloper(Integer devId);
}
