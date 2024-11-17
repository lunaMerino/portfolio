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
@Table(name = "developers")
public class Developer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "dev_id")
    private int devId;

    @Column(name = "dev_name", length = 45)
    private String devName;

    @Column(name = "dev_surname", length = 45)
    private String devSurname;

    @Column(name ="email", length = 255, unique = true)
    private String email;

    @Column(name ="linkedin_url", length = 255, unique = true)
    private String linkedinUrl;

    @Column(name = "github_url", length = 255, unique = true)
    private String githubUrl;

    //Entidad que presenta a un desarrollador que puede estar asociado a varios proyectos
    //Relación Muchos a Muchos con Projects
    @ManyToMany(mappedBy = "developers")
    private List<Project> projects;

   
    
}
