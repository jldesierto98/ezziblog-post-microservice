package com.eeezi.ezziblogrestapi.login.service;

import com.eeezi.ezziblogrestapi.exception.BlogAPIException;
import com.eeezi.ezziblogrestapi.login.dto.LoginDto;
import com.eeezi.ezziblogrestapi.login.dto.SignUpDto;
import com.eeezi.ezziblogrestapi.users.entity.Role;
import com.eeezi.ezziblogrestapi.users.entity.User;
import com.eeezi.ezziblogrestapi.users.repository.RoleRepository;
import com.eeezi.ezziblogrestapi.users.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService{

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final ModelMapper modelMapper;
    private static final String LOGIN_SUCCESS = "User login successful!";
    private static final String SIGNUP_SUCCESS = "Signup successful!";
    PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();


    @Override
    public String login(LoginDto loginDto) {

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginDto.getUserNameOrEmail(), loginDto.getPassword()
        ));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        return LOGIN_SUCCESS;
    }

    @Override
    public String signup(SignUpDto signUpDto) {

        if(userRepository.existsByEmail(signUpDto.getEmail())){
            throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Email is already registered.");
        }

        if(userRepository.existsByUserName(signUpDto.getUserName())){
            throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Username is already registered.");
        }

        User user = modelMapper.map(signUpDto, User.class);

        String encodedPassword = passwordEncoder.encode(user.getPassword());
        Set<Role> roleSet = new HashSet<>();

        Role role = roleRepository.findByName("ROLE_USER").orElseThrow(() -> new BlogAPIException(HttpStatus.BAD_REQUEST,"Role does not exist"));
        roleSet.add(role);

        user.setPassword(encodedPassword);
        user.setRoles(roleSet);

        userRepository.save(user);

        return SIGNUP_SUCCESS;
    }
}
