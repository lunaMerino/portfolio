package com.vedruna.portfolio.persistance.models;

import java.sql.Date;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;


@Entity
@Data
@Table(name = "projects")
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "project_id")
    private int projectId;

    @Column(name = "project_name", nullable =false, unique = true, length =45)
    private String projectName;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "start_date")
    private Date startDate;

    @Column(name = "end_date")  
    private Date endDate; 

    @Column(name = "repository_url", length = 255)
    private String repositoryUrl;

    @Column(name = "demo_url", length = 255)
    private String demoUrl;

    @Column(name = "picture", length = 255)
    private String picture;

    //Relación Muchos a Uno con Status
    @ManyToOne
    @JoinColumn(name = "status_status_id", nullable = false)
    private Status status;

    //Relación Muchos a Muchos con Developer
    @ManyToMany
    @JoinTable(
        name = "developers_worked_on_projects",
        joinColumns = @JoinColumn(name = "projects_project_id"),
        inverseJoinColumns = @JoinColumn(name = "developers_dev_id")
    
    )
    private List<Developer> developers;

    //Relación Muchos a Muchos con Technology
    @ManyToMany
    @JoinTable(
        name = "technologies_used_in_projects",
        joinColumns = @JoinColumn(name = "projects_project_id"),
        inverseJoinColumns = @JoinColumn(name = "technologies_tech_id")
    )
    private List<Technology> technologies;
}
