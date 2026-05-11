package es.iesclaradelrey.da2d1a.tiendajddlaph.api.dto;

import lombok.Data;

@Data
public class LoginRequestDto {
    private String username;
    private String password;
}