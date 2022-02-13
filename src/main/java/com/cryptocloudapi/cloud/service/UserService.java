package com.cryptocloudapi.cloud.service;

import com.cryptocloudapi.cloud.dto.request.UserDto;
import com.cryptocloudapi.cloud.dto.response.UserDetailsDto;
import com.cryptocloudapi.cloud.entity.User;
import com.cryptocloudapi.cloud.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Transactional
@Slf4j
public class UserService {

    private final UserRepository userRepository;

    public User findUserByCode(String code){
        Optional<User> userOptional = userRepository.findByCod(code.toLowerCase());
        if(userOptional.isEmpty()) throw new IllegalArgumentException("Usuário não encontrado!");
        else
            return userOptional.get();
    }

    public void validateByCode(String code) {
        Optional<User> userOptional = userRepository.findByCod(code.toLowerCase());
        if(userOptional.isEmpty()) throw new IllegalArgumentException("Usuário não encontrado!");
    }

    public Optional<User> findUserByEmail(String email){
        return userRepository.findByEmail(email);
    }


    public List<User> findAll(){

        return userRepository.findAll();
    }
    public UserDetailsDto findUser(String code){
        User user = findUserByCode(code);
        return UserDetailsDto.builder()
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .cod(user.getCod())
                .createdAt(user.getCreatedAt())
                .updatedAt(user.getUpdatedAt())
                .roles(user.getRoles()).build();
    }


}
