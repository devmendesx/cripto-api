package com.criptocloudapi.cloud.service;

import com.criptocloudapi.cloud.entity.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {

    public Optional<User> findUserByEmail(String email){




        return Optional.empty();
    }
}
