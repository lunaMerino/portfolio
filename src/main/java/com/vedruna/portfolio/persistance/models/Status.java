package com.vedruna.portfolio.persistance.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "status")
public class Status {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "statusId")
    private int statusId;
    
    @Column(name = "status_name", nullable = false, unique = true, length = 20)
    private String statusName;

    //Esta columna representa el estado de un proyecto (Development, testing...)

}
