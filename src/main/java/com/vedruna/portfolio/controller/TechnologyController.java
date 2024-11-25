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

import com.vedruna.portfolio.dto.TechnologyDTO;
import com.vedruna.portfolio.services.TechnologyServiceI;

@RestController
@RequestMapping("/api/v1/technologies")
public class TechnologyController {

    @Autowired
    private TechnologyServiceI technologyService;

    @PostMapping
    public ResponseEntity<Void> createTechnology(@RequestBody TechnologyDTO technologyDTO) {
        technologyService.insertTechnology(technologyDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTechnology(@PathVariable int id) {
        technologyService.deleteTechnology(id);
        return ResponseEntity.noContent().build();
    }
}
