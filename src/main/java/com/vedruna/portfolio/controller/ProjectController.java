package com.vedruna.portfolio.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vedruna.portfolio.services.ProjectServiceI;
import com.vedruna.portfolio.persistance.models.Project;
import com.vedruna.portfolio.dto.ResponseDTO;


@RestController
@RequestMapping("/api/v1/projects")
@CrossOrigin
public class ProjectController {

    @Autowired
    private ProjectServiceI projectMngmnt; // Mantengo el nombre consistente con tu estilo

    // obtener todos los proyectos paginados
    @GetMapping
    public ResponseEntity<Page<Project>> getAllProjects(Pageable pageable) {
        return ResponseEntity.ok(projectMngmnt.showAllProjects(pageable));
    }

    // obtener el proyecto que contenga la palabra 'word' en su nombre
    @GetMapping("/{word}")
    public ResponseEntity<Page<Project>> getProjectsByName(@PathVariable String word, Pageable pageable) {
       return ResponseEntity.ok(projectMngmnt.showProjectsByName(word, pageable));
    }

    // insertar proyecto
    @PostMapping
    public ResponseEntity<ResponseDTO> createProject(@RequestBody Project project) {
        projectMngmnt.saveProject(project);
        return ResponseEntity.ok(new ResponseDTO("Project saved", project));
    }

    // editar el proyecto
    @PutMapping("update/{id}")
    public ResponseEntity<ResponseDTO> updateProject(@PathVariable Integer id, @RequestBody Project project) {
        projectMngmnt.updateProject(id, project);
        return ResponseEntity.ok(new ResponseDTO("Project updated", project));
    }

    // eliminar el proyecto
    @DeleteMapping("delete/{id}")
    public ResponseEntity<ResponseDTO> deleteProject(@PathVariable Integer id) {
        projectMngmnt.deleteProject(id);
        return ResponseEntity.ok(new ResponseDTO("Project deleted", null));
    }
}
