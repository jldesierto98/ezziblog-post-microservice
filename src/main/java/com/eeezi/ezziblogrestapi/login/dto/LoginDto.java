package com.eeezi.ezziblogrestapi.login.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LoginDto {
    private String userNameOrEmail;
    private String password;
}
