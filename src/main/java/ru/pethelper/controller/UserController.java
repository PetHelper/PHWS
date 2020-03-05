package ru.pethelper.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.pethelper.dao.UserRepository;
import ru.pethelper.model.User;
import ru.pethelper.service.UserService;

import javax.validation.Valid;

@RestController
@RequestMapping("user")
public class UserController {
    @Autowired
    UserRepository userRepository;
    @Autowired
    private UserService userService;

    @PostMapping(path = "/register", consumes = "application/json", produces = "application/json")
    ResponseEntity register(@RequestBody @Valid User user, BindingResult result) {
        if (!userService.addUser(user)) {
            return new ResponseEntity("User already exists!", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(HttpStatus.OK);
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
}
