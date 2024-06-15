package com.eeezi.ezziblogrestapi.login.service;

import com.eeezi.ezziblogrestapi.login.dto.LoginDto;
import com.eeezi.ezziblogrestapi.login.dto.SignUpDto;


/**
 * AuthService interface defining methods for user authentication and registration.
 */
public interface AuthService {

    /**
     * Authenticates a user based on login credentials.
     *
     * @param loginDto the login data transfer object containing username/email and password
     * @return a JWT token if authentication is successful
     */

    String login(LoginDto loginDto);

    /**
     * Registers a new user.
     *
     * @param signUpDto the signup data transfer object containing user registration details
     * @return a success message if registration is successful
     */
    String signup(SignUpDto signUpDto);

}
