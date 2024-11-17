package com.vedruna.portfolio.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vedruna.portfolio.dto.DeveloperDTO;
import com.vedruna.portfolio.services.DeveloperServiceI;

@RestController
@RequestMapping("/developers")
public class DeveloperController {

    @Autowired
    private DeveloperServiceI developerService;
    
    @PostMapping
    public ResponseEntity<Void> createDeveloper(@RequestBody DeveloperDTO developerDTO) {
        developerService.insertDeveloper(developerDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build(); // Código 201
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDeveloper(@PathVariable int id) {
        developerService.deleteDeveloper(id);
        return ResponseEntity.noContent().build(); // Código 204
    }
}
