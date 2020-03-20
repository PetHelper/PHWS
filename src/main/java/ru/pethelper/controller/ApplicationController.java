package ru.pethelper.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import ru.pethelper.config.JwtTokenUtil;
import ru.pethelper.controller.model.UserWeb;
import ru.pethelper.dao.repositories.UserRepository;
import ru.pethelper.domain.User;
import ru.pethelper.exception.UserAlreadyExistsException;
import ru.pethelper.exception.UserNotActive;
import ru.pethelper.mapper.UserMapper;
import ru.pethelper.servlet.JwtRequest;
import ru.pethelper.servlet.JwtResponse;
import ru.pethelper.dao.UserEntity;
import ru.pethelper.service.impl.UserServiceImpl;

import javax.validation.Valid;
import java.util.Objects;

@RestController
@CrossOrigin
public class ApplicationController {
    @Autowired
    UserRepository userRepository;
    @Autowired
    private UserServiceImpl userService;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public ApplicationController(UserRepository userRepository,
                                 BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @PostMapping(path = "register", consumes = "application/json", produces = "application/json")
    ResponseEntity register(@RequestBody @Valid UserWeb userWeb) throws Exception {
        if (userWeb.getPassword() != null && !userWeb.getPassword().equals(userWeb.getMatchingPassword())) {
            return new ResponseEntity("Passwords dont match!", HttpStatus.BAD_REQUEST);
        }
        try {
            return new ResponseEntity(userService.addUser(UserMapper.USER_MAPPER.userWebToUser(userWeb)), HttpStatus.BAD_REQUEST);
        } catch (UserAlreadyExistsException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping("activate/{code}")
    public ResponseEntity activate(@PathVariable String code) {
        boolean isActivated = userService.activateUser(code);

        if (isActivated) {
            return new ResponseEntity("User successfully activated", HttpStatus.OK);
        } else {
            return new ResponseEntity("Activation code is not found!", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("sign-in")
    public ResponseEntity signIn(@RequestParam(name = "email") String email) throws Exception {
        try {
            return new ResponseEntity(new JwtResponse(userService.findByUserEmail(email)), HttpStatus.OK);
        } catch (UserNotActive e) {
            return new ResponseEntity("You must activate your user on your email", HttpStatus.BAD_REQUEST);
        }
    }
}
