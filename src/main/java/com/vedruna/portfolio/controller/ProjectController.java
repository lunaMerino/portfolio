package com.vedruna.portfolio.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
@RequestMapping("/api/v1/projects")
public class ProjectController {

    @Autowired
    private ProjectServiceI projectService;

    //obtener todos los proyectos paginados
    @GetMapping
    public ResponseEntity<Page<ProjectDTO>> getAllProjects(Pageable pageable) {
        Page<ProjectDTO> projects = projectService.showAllProjects(pageable);
        return ResponseEntity.ok(projects); // Código 200
    }

    //obtener proyectos por palabra
    @GetMapping("/{word}")
    public ResponseEntity<Page<ProjectDTO>> getProjectsByName(@PathVariable String word, Pageable pageable) {
        Page<ProjectDTO> projects = projectService.showProjectsByName(word, pageable);
        return ResponseEntity.ok(projects); // Código 200
    }

    //insertar un nuevo proyecto
    @PostMapping
    public ResponseEntity<Void> createProject(@RequestBody ProjectDTO projectDTO) {
        projectService.saveProject(projectDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build(); // Código 201
    
    }

    //editar un proyecto existente
    @PutMapping("/{id}")
    public ResponseEntity<Void> updateProject(@PathVariable Integer id, @RequestBody ProjectDTO projectDTO) {
        projectService.updateProject(id, projectDTO);
        return ResponseEntity.ok().build(); // Código 200
    }

    //eliminar un proyecto
    @DeleteMapping("/projects/{id}")
    public ResponseEntity<Void> deleteProject(@PathVariable Integer id) {
        projectService.deleteProject(id);
        return ResponseEntity.noContent().build(); // Código 204
    }
}
