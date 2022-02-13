package com.cryptocloudapi.cloud.controller.authentication;

import com.cryptocloudapi.cloud.app.config.security.service.AuthenticationService;
import com.cryptocloudapi.cloud.app.config.security.service.TokenService;
import com.cryptocloudapi.cloud.dto.request.UserDto;
import com.cryptocloudapi.cloud.dto.response.TokenDto;
import com.cryptocloudapi.cloud.app.config.security.service.dto.OAuthResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;



/**
 * @author Matheus Mendes
 * @version v1
 * @apiNote Authentication Controller for users register and login.
 */

@RestController
@RequestMapping("/api/v1/oauth")
public class AuthenticationController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping
    public ResponseEntity<?> auth(@RequestBody UserDto userDto){
        UsernamePasswordAuthenticationToken user = authenticationService.userTokenCreator(userDto);
        try{
            Authentication authentication = authenticationManager.authenticate(user);
            String token = tokenService.generateToken(authentication);
            return ResponseEntity.status(200).body(new TokenDto(token, "Bearer"));
        }catch(AuthenticationException ex ){
            return ResponseEntity.status(400).body(ex.getMessage());
        }
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> register(@RequestBody UserDto userBody) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(authenticationService.register(userBody));
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.status(400).body(
                    OAuthResponseDto.builder()
                            .message(ex.getMessage())
            );
        }
    }

}
