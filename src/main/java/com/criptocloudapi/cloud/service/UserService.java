package com.criptocloudapi.cloud.service;

import com.criptocloudapi.cloud.dto.request.UserDto;
import com.criptocloudapi.cloud.dto.response.UserResponseDto;
import com.criptocloudapi.cloud.entity.User;
import com.criptocloudapi.cloud.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserService {

    private final UserRepository userRepository;

    public User findUserByCode(String code){
        Optional<User> userOptional = userRepository.findByCodUser(code);
        if(userOptional.isEmpty()) throw new IllegalArgumentException("Usuário não encontrado!");
        else
            return userOptional.get();
    }

    public void validateByCode(String code) {
        Optional<User> userOptional = userRepository.findByCodUser(code);
        if(userOptional.isEmpty()) throw new IllegalArgumentException("Usuário não encontrado!");
    }

    public Optional<User> findUserByEmail(String email){
        return userRepository.findByEmail(email);
    }

    public ResponseEntity<UserResponseDto> register(UserDto userBody) {
        UserResponseDto userResponseDto = UserResponseDto.builder().build();
        Optional<User> userByEmail = findUserByEmail(userBody.getEmail());
        if(userByEmail.isPresent()) {
            userResponseDto.setMessage("Usuário já cadastrado encontrado!");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(userResponseDto);
        }
        User newUser = User.builder()
                .email(userBody.getEmail())
                .codUser(UUID.randomUUID())
                .firstName(userBody.getFirstName())
                .lastName(userBody.getLastName())
                .password(userBody.getPassword()).build();
        User user = userRepository.save(newUser);
        userResponseDto.setMessage("Usuário cadastrado com sucesso!");
        return ResponseEntity.ok(userResponseDto);
    }
}
