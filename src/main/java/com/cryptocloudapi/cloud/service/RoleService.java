package com.cryptocloudapi.cloud.service;


import com.cryptocloudapi.cloud.dto.request.RoleDto;
import com.cryptocloudapi.cloud.dto.request.RoleUserDto;
import com.cryptocloudapi.cloud.dto.response.MessageResponseDto;
import com.cryptocloudapi.cloud.entity.Role;
import com.cryptocloudapi.cloud.entity.User;
import com.cryptocloudapi.cloud.repository.RoleRepository;
import com.cryptocloudapi.cloud.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class RoleService {

    private final RoleRepository roleRepository;
    private final UserService userService;
    private final UserRepository userRepository;

    public MessageResponseDto createRole(RoleDto roleDto) {
        isAValidRoleToInsertToUser(roleDto.getRoleName());
        roleRepository.save(Role.builder().name(roleDto.getRoleName()).build());
        return MessageResponseDto.builder().message("Role criada com sucesso!").build();
    }

    public MessageResponseDto insertRoleToUser(RoleUserDto roleUserDto){
        Role role = roleRepository.findByName(roleUserDto.getRoleName());
        User user = userService.findUserByCode(roleUserDto.getUserCode());
        user.getRoles().add(role);
        userRepository.save(user);
        return MessageResponseDto.builder().message("Role atribuida com sucesso!").build();
    }


    public void isAValidRoleToInsertToUser(String rolename){
        Role role = roleRepository.findByName(rolename);
        if(!Objects.isNull(role))
            throw new IllegalArgumentException("Role já existe!");
    }
}
