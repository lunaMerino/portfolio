package com.vedruna.portfolio.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.vedruna.portfolio.persistance.models.Project;
public interface ProjectServiceI {
    //obtener todos los proyectos
    Page<Project> showAllProjects(Pageable pageable);

    //buscar proyecto por nombre
    Page<Project> showProjectsByName(String projectName, Pageable pageable);

    //guardar proyecto nuevo
    void saveProject(Project project);

    //editar proyecto
    void updateProject(Integer id, Project project);

    //eliminar proyecto
    void deleteProject(Integer id);
}
