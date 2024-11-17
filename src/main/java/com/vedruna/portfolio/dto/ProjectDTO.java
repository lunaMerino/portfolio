package com.vedruna.portfolio.dto;
import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;

import com.vedruna.portfolio.persistance.models.Project;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProjectDTO {
    private int projectId;
    private String projectName;
    private String description;
    private Date startDate;
    private Date endDate;
    private String repositoryUrl;
    private String demoUrl;
    private String picture;
    private String statusName;
    private List<DeveloperDTO> developers;
    private List<String> technologies;

    public ProjectDTO(Project project) {
        this.projectId = project.getProjectId();
        this.projectName = project.getProjectName();
        this.description = project.getDescription();
        this.startDate = project.getStartDate();
        this.endDate = project.getEndDate();
        this.repositoryUrl = project.getRepositoryUrl();
        this.demoUrl = project.getDemoUrl();
        this.picture = project.getPicture();
        this.statusName = project.getStatus().getStatusName();
        this.developers = project.getDevelopers()
                .stream()
                .map(DeveloperDTO::new)
                .collect(Collectors.toList());
        this.technologies = project.getTechnologies()
                .stream()
                .map(technology -> technology.getTechName())
                .collect(Collectors.toList());
    }
}
