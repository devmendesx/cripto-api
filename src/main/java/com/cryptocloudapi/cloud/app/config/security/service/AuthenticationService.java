package com.cryptocloudapi.cloud.app.config.security.service;


import com.cryptocloudapi.cloud.dto.response.MessageResponseDto;
import com.cryptocloudapi.cloud.dto.request.UserDto;
import com.cryptocloudapi.cloud.dto.request.UserPasswordChangeDto;
import com.cryptocloudapi.cloud.entity.User;
import com.cryptocloudapi.cloud.repository.UserRepository;
import com.cryptocloudapi.cloud.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service @RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AuthenticationService implements UserDetailsService {

    private final UserRepository userRepository;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        return userRepository
                .findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));
    }

    public MessageResponseDto register(UserDto userBody) {
        MessageResponseDto messageResponseDto = MessageResponseDto.builder().build();
        Optional<User> userByEmail = userService.findUserByEmail(userBody.getEmail());
        if(userByEmail.isPresent()) {
            messageResponseDto.setMessage("Usuário já cadastrado!");
            return messageResponseDto;
        }
        User newUser = User.builder()
                .email(userBody.getEmail())
                .cod(UUID.randomUUID().toString())
                .firstName(userBody.getFirstName())
                .lastName(userBody.getLastName())
                .password(passwordEncoder.encode(userBody.getPassword())).build();
        userRepository.save(newUser);
        messageResponseDto.setMessage("Usuário cadastrado com sucesso!");
        return messageResponseDto;
    }


    public UsernamePasswordAuthenticationToken userTokenCreator(UserDto userDto){
        return new UsernamePasswordAuthenticationToken(userDto.getEmail(),userDto.getPassword());
    }

    public MessageResponseDto changePassword(UserPasswordChangeDto userDto) {
        User user = userService.findUserByCode(userDto.getCode());
        if(passwordEncoder.matches(userDto.getOldPassword(), user.getPassword())){
            user.setPassword(passwordEncoder.encode(userDto.getNewPassword()));
            return MessageResponseDto.builder().message("Senha alterada com sucesso!").build();
        }
        else
            return MessageResponseDto.builder().message("Senha atual inválida!").build();
    }
}
