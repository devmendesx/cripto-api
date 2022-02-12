package com.criptocloudapi.cloud.controller;

import com.criptocloudapi.cloud.dto.request.UserDto;
import com.criptocloudapi.cloud.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/v1/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> register(@RequestBody UserDto userBody){
        return ResponseEntity.ok(userService.register(userBody));
    }
}
