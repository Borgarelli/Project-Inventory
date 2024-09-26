package com.as2group.crm.util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.io.IOException;

import java.util.Date;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import com.as2group.crm.security.Login;
import com.fasterxml.jackson.core.JsonProcessingException;

import com.fasterxml.jackson.databind.ObjectMapper;
public class JwtUtils {

    private static final String SECRET_KEY = "4856b14f-f0a0-40b4-8db5-1eddc71508b7";

    public static String generateToken(Authentication employee) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Login employeeNoPass = new Login();
        employeeNoPass.setLogin(employee.getName());
        if (!employee.getAuthorities().isEmpty()) {
            employeeNoPass.setRole(employee.getAuthorities().iterator().next().getAuthority());
        }
        String employeeJson = mapper.writeValueAsString(employeeNoPass);
        Date now = new Date();
        Long hour = 1000L * 60L * 60L; 
        return Jwts.builder()
                .claim("userDetails", employeeJson)
                .setIssuer("br.gov.sp.fatec")
                .setSubject(employee.getName())
                .setExpiration(new Date(now.getTime() + hour))
                .signWith(Keys.hmacShaKeyFor(SECRET_KEY.getBytes()), SignatureAlgorithm.HS256).compact();
    }


    public static Authentication parseToken(String token) throws IOException, JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        String credentialsJson = Jwts.parserBuilder()
            .setSigningKey(Keys.hmacShaKeyFor(SECRET_KEY.getBytes())).build()
            .parseClaimsJws(token).getBody().get("userDetails", String.class);
        Login employee = mapper.readValue(credentialsJson, Login.class);
        UserDetails userDetails = User.builder().username(employee.getLogin()).password("secret")
            .authorities(employee.getRole()).build();
        return new UsernamePasswordAuthenticationToken(employee.getLogin(), employee.getPassword(),
            userDetails.getAuthorities());
    }

}