package com.vedruna.portfolio.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vedruna.portfolio.dto.ProjectDTO;
import com.vedruna.portfolio.services.ProjectServiceI;

@RestController
@RequestMapping("/projects")
public class ProjectController {

    @Autowired
    private ProjectServiceI projectService;

    //obtener todos los proyectos paginados
    @GetMapping
    public Page<ProjectDTO> getAllProjects(Pageable pageable) {
        return projectService.showAllProjects(pageable);
    }

    //obtener proyectos por palabra
    @GetMapping("/{word}")
    public Page<ProjectDTO> getProjectsByName(@PathVariable String word, Pageable pageable) {
        return projectService.showProjectsByName(word, pageable);
    }

    //insertar un nuevo proyecto
    @PostMapping
    public void createProject(@RequestBody ProjectDTO projectDTO) {
        projectService.saveProject(projectDTO);
    }

    //editar un proyecto existente
    @PutMapping("/{id}")
    public void updateProject(@PathVariable Integer id, @RequestBody ProjectDTO projectDTO) {
        projectService.updateProject(id, projectDTO);
    }

    //eliminar un proyecto
    @DeleteMapping("/{id}")
    public void deleteProject(@PathVariable Integer id) {
        projectService.deleteProject(id);
    }
}
