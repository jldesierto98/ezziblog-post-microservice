package com.eeezi.ezziblogrestapi.login.service;

import com.eeezi.ezziblogrestapi.login.dto.LoginDto;
import com.eeezi.ezziblogrestapi.login.dto.SignUpDto;

public interface AuthService {


    String login(LoginDto loginDto);

    String signup(SignUpDto signUpDto);

}
