package com.vedruna.portfolio.dto;

import com.vedruna.portfolio.persistance.models.Developer;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DeveloperDTO {
    private int devId;
    private String devName;
    private String devSurname;
    private String email;
    private String linkedinUrl;
    private String githubUrl;

    public DeveloperDTO(Developer developer) {
        this.devId = developer.getDevId();
        this.devName = developer.getDevName();
        this.devSurname = developer.getDevSurname();
        this.email = developer.getEmail();
        this.linkedinUrl = developer.getLinkedinUrl();
        this.githubUrl = developer.getGithubUrl();
    }
}
