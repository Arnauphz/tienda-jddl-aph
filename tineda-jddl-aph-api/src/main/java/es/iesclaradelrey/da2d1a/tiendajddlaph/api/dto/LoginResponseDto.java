package es.iesclaradelrey.da2d1a.tiendajddlaph.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginResponseDto {
    private String accessToken;
    private String refreshToken;
}