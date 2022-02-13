package com.cryptocloudapi.cloud.controller;

import com.cryptocloudapi.cloud.app.config.security.service.dto.OAuthResponseDto;
import com.cryptocloudapi.cloud.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(path = "/api/v1/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(path = "/detail/{code}", produces = {APPLICATION_JSON_VALUE})
    public ResponseEntity<?> findUser(@PathVariable String code) {
        try {
            return ResponseEntity.status(HttpStatus.ACCEPTED)
                    .body(userService.findUserByCode(code));
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.status(404).body(
                    OAuthResponseDto.builder()
                            .message(ex.getMessage()).build()
            );
        }

    }

    @GetMapping
    public ResponseEntity<?> findAll() {
        try {
            return ResponseEntity.status(HttpStatus.ACCEPTED)
                    .body(userService.findAll());
        } catch(IllegalArgumentException ex){
            return ResponseEntity.status(400).body(
                    OAuthResponseDto.builder()
                            .message(ex.getMessage()).build()
            );
        }
    }
}
