package com.vedruna.portfolio.dto;

import com.vedruna.portfolio.persistance.models.Technology;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TechnologyDTO {
    private int techId;
    private String techName;

    public TechnologyDTO(Technology technology) {
        this.techId = technology.getTechId();
        this.techName = technology.getTechName();
    }
}
