package com.vedruna.portfolio.dto;

public class StatusDTO {
    private String statusName;

    public StatusDTO(String statusName) {
        this.statusName = statusName;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }
}
