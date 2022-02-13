package com.cryptocloudapi.cloud.controller;

import com.cryptocloudapi.cloud.dto.request.RoleDto;
import com.cryptocloudapi.cloud.dto.response.MessageResponseDto;
import com.cryptocloudapi.cloud.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/roles")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> createRole(@RequestBody RoleDto roleDto){
        try{
            return ResponseEntity.status(HttpStatus.CREATED).body(roleService.createRole(roleDto));
        }catch(IllegalArgumentException ex){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    MessageResponseDto.builder()
                            .message(ex.getMessage()).build()
            );
        }
    }
}
