package com.practice.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginResponseDTO {
    private Boolean success;
    private String response;
    private String AuthToken;

    public LoginResponseDTO(Boolean success, String response) {
        this.success = success;
        this.response = response;
    }
}
