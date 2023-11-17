package com.as2group.crm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.as2group.crm.security.Login;
import com.as2group.crm.util.JwtUtils;
import com.fasterxml.jackson.core.JsonProcessingException;

@RestController
@CrossOrigin
@RequestMapping(value = "/api/login")
public class LoginController {
    
    @Autowired
    private AuthenticationManager authenticationManager;


    @PostMapping
    public Login autenticate (@RequestBody Login login) throws JsonProcessingException {
        Authentication auth = new UsernamePasswordAuthenticationToken(login.getLogin(), login.getPassword());
        auth = authenticationManager.authenticate(auth);
        login.setToken(JwtUtils.generateToken(auth));
        login.setRole(auth.getAuthorities().iterator().next().getAuthority());
        return login;
    }     
}
