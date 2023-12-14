package com.had.selfhelp.controller;

//import com.had.selfhelp.dao.DoctorRepository;
//import com.had.selfhelp.dao.PatientRepository;

import com.had.selfhelp.entity.Customer;

import com.had.selfhelp.entity.LoginRequest;
import com.had.selfhelp.jwt.JwtUtils;
import com.had.selfhelp.service.CustomerServices;
import com.had.selfhelp.service.MyCustomUserDetails;
import com.had.selfhelp.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

/*This code is a controller class in a Spring Boot application,
handling authentication-related HTTP requests. Here's a breakdown: */

@RestController
@Slf4j
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtUtils jwtUtils;
    @Autowired
    UserService userService;
    @Autowired
    CustomerServices customerServices;



    @PostMapping("/login")
    public ResponseEntity<Customer> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        log.info("login");
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        MyCustomUserDetails userDetails = (MyCustomUserDetails) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities()
                .stream().map(item -> item.getAuthority())
                .collect(Collectors.toList());
        Customer d= customerServices.login(loginRequest);
        if(d!=null) {

            HttpHeaders responseHeaders = new HttpHeaders();
            responseHeaders.set("JWT",
                    jwt);
            return ResponseEntity.ok().headers(responseHeaders)
                    .body(d);
        }
        else {
            throw new RuntimeException("Did not find doctor with these credentials");
        }

    }



    @PostMapping("/register/user") //ADMIN
    ResponseEntity<?> createUser(@RequestBody Customer user){
        log.info("registering the customer");
        user.setId(0);
        System.out.println(user);
        customerServices.save(user);
        userService.createUser(user);
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);
        MyCustomUserDetails userDetails = (MyCustomUserDetails) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities()
                .stream().map(item -> item.getAuthority())
                .collect(Collectors.toList());
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("JWT",
                jwt);
        return ResponseEntity.ok().headers(responseHeaders)
                .body(user);
    }



}
