package com.vedruna.portfolio.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vedruna.portfolio.dto.TechnologyDTO;
import com.vedruna.portfolio.services.TechnologyServiceI;

@RestController
@RequestMapping("/technologies")
public class TechnologyController {

    @Autowired
    private TechnologyServiceI technologyService;

    @PostMapping
    public void createTechnology(@RequestBody TechnologyDTO technologyDTO) {
        technologyService.insertTechnology(technologyDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteTechnology(@PathVariable int id) {
        technologyService.deleteTechnology(id);
    }
}
