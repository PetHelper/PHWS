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
import ru.pethelper.dao.UserRepository;
import ru.pethelper.model.JwtRequest;
import ru.pethelper.model.JwtResponse;
import ru.pethelper.model.UserEntity;
import ru.pethelper.service.UserService;

import javax.validation.Valid;
import java.util.Objects;

@RestController
@RequestMapping("user")
@CrossOrigin
public class UserController {
    @Autowired
    UserRepository userRepository;
    @Autowired
    private UserService userService;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    public UserController(UserRepository userRepository,
                          BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @PostMapping(path = "/register", consumes = "application/json", produces = "application/json")
    ResponseEntity register(@RequestBody @Valid UserEntity user) throws Exception {
        if (user.getPassword() != null && !user.getPassword().equals(user.getMatchingPassword())) {
            return new ResponseEntity("Passwords dont match!", HttpStatus.BAD_REQUEST);
        }
        long addUserStatus = userService.addUser(user);
        if (addUserStatus != 0) {
            if (addUserStatus == 2) {
                return new ResponseEntity("User with this USERNAME already exists!", HttpStatus.BAD_REQUEST);
            }
            if (addUserStatus == 3) {
                return new ResponseEntity("User with this EMAIL already exists!", HttpStatus.BAD_REQUEST);
            }
            if (addUserStatus == 4) {
                return new ResponseEntity("User with this PhoneNumber already exists!", HttpStatus.BAD_REQUEST);
            }
        }
        String token = createAuthenticationToken(new JwtRequest(user.getUsername(),user.getPassword()));
        return new ResponseEntity(new JwtResponse(token), HttpStatus.OK);
    }

    @GetMapping("/activate/{code}")
    public ResponseEntity activate(@PathVariable String code) {
        boolean isActivated = userService.activateUser(code);

        if (isActivated) {
            return new ResponseEntity("User successfully activated", HttpStatus.OK);
        } else {
            return new ResponseEntity("Activation code is not found!", HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/sign-up")
    public void signUp(@RequestBody UserEntity user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    public String createAuthenticationToken(JwtRequest authenticationRequest)
            throws Exception {

        authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());

        UserDetails userDetails = userService
                .loadUserByUsername(authenticationRequest.getUsername());

        String token = jwtTokenUtil.generateToken(userDetails);

        return token;
    }

    private void authenticate(String username, String password) throws Exception {
        Objects.requireNonNull(username);
        Objects.requireNonNull(password);

        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }
}
