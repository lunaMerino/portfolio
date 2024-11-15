package com.vedruna.portfolio.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResponseDTO<T> {
    private String message; 
    private T data;         
}
