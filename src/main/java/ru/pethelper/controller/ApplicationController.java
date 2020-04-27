package ru.pethelper.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import ru.pethelper.config.JwtTokenUtil;
import ru.pethelper.controller.model.UserWeb;
import ru.pethelper.exception.SignInException;
import ru.pethelper.exception.UserAlreadyExistsException;
import ru.pethelper.exception.UserNotActive;
import ru.pethelper.exception.UserNotFound;
import ru.pethelper.mapper.UserMapper;
import ru.pethelper.service.UserService;
import ru.pethelper.servlet.JwtResponse;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@CrossOrigin
public class ApplicationController {
    @Autowired
    private UserService userService;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    public ApplicationController(BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @PostMapping(path = "register", consumes = "application/json", produces = "application/json")
    ResponseEntity register(@RequestBody @Valid UserWeb userWeb) {
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
    public ResponseEntity signIn(@RequestParam(name = "email") String email, @RequestParam(name = "password") String password) throws Exception {
        try {
            return new ResponseEntity(new JwtResponse(userService.findUserForSignIn(email, password)), HttpStatus.OK);
        } catch (UserNotActive e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (UserNotFound e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (SignInException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("get-user")
    public ResponseEntity getUser(HttpServletRequest request) {
        int userId = jwtTokenUtil.getIdFromToken(request.getHeader("Authorization").substring(7));
        try {
            return new ResponseEntity(userService.getUser(userId), HttpStatus.OK);
        } catch (UserNotFound e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
