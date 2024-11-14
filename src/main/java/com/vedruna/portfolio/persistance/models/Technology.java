package com.vedruna.portfolio.persistance.models;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "technologies")
public class Technology {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tech_id")
    private int techId;
    
    @Column(name = "tech_name", length = 45, unique = true)
    private String techName;

    //Relación muchos a muchos con projects
    @ManyToMany(mappedBy = "technologies")
    private List<Project> projects;

    //Esta entidad representa una tecnología que puede utilizarse en muchos proyectos
    

}
