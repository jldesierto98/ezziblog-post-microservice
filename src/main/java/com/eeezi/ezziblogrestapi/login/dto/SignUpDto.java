package com.eeezi.ezziblogrestapi.login.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignUpDto {
    private String userName;
    private String email;
    private String name;
    private String password;
}
