package com.eeezi.ezziblogrestapi.login.controller;

import com.eeezi.ezziblogrestapi.login.dto.LoginDto;
import com.eeezi.ezziblogrestapi.login.dto.SignUpDto;
import com.eeezi.ezziblogrestapi.login.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/authenticate")
@AllArgsConstructor
public class LoginController {

    private final AuthService authService;

    @PostMapping(value = {"/login", "/signin"})
    public ResponseEntity<Object> login(@RequestBody LoginDto loginDto){
        return new ResponseEntity<>(authService.login(loginDto), HttpStatus.ACCEPTED);
    }

    @PostMapping(value = {"/signup", "/register"})
    public ResponseEntity<Object> signup(@RequestBody SignUpDto signUpDto){
        return new ResponseEntity<>(authService.signup(signUpDto), HttpStatus.CREATED);
    }
}