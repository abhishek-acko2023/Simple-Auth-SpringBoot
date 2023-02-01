package com.practice.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExeptionResponseDTO {
    private String status;
    private Integer code;
    private String message;

}
