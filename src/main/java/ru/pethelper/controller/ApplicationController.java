package ru.pethelper.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.pethelper.config.JwtTokenUtil;
import ru.pethelper.controller.model.Response;
import ru.pethelper.controller.model.UserWeb;
import ru.pethelper.exception.UserNotFound;
import ru.pethelper.mapper.UserMapper;
import ru.pethelper.service.UserService;
import ru.pethelper.servlet.JwtResponse;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

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
            return new ResponseEntity(new Response(1, "Passwords dont match!"), HttpStatus.BAD_REQUEST);
        }
        try {
            return new ResponseEntity(new Response(0, userService.addUser(UserMapper.USER_MAPPER.userWebToUser(userWeb)))
                    , HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(new Response(1, e.toString()), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("activate/{code}")
    public ResponseEntity activate(@PathVariable String code) {
        boolean isActivated = userService.activateUser(code);

        if (isActivated) {
            return new ResponseEntity(new Response(0, "User successfully activated"), HttpStatus.OK);
        } else {
            return new ResponseEntity(new Response(1, "Activation code is not found!"), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("sign-in")
    public ResponseEntity signIn(@RequestHeader(name = "email") String email, @RequestHeader(name = "password") String password) throws Exception {
        try {
            return new ResponseEntity(new JwtResponse(userService.findUserForSignIn(email, password)), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(new Response(1, e.toString()), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("get-user")
    public ResponseEntity getUser(HttpServletRequest request) {
        try {
            return new ResponseEntity(userService.getUser(jwtTokenUtil.getIdFromToken(request.getHeader("Authorization").substring(7))), HttpStatus.OK);
        } catch (UserNotFound e) {
            return new ResponseEntity(new Response(1, e.toString()), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("save-user-photo")
    public ResponseEntity saveUserPhoto(HttpServletRequest request, @RequestParam("image") MultipartFile image) {
        try {
            userService.saveImage(jwtTokenUtil.getIdFromToken(request.getHeader("Authorization").substring(7)), image);
            return new ResponseEntity(new Response(0, "Successfully uploaded image"), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(new Response(1, e.toString()), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("get-user-photo")
    public ResponseEntity<?> getUserPhoto(HttpServletRequest request) {
        try {
            return ResponseEntity.ok()
                    .contentType(MediaType.parseMediaType("image/jpeg"))
                    .body(userService.getImage(jwtTokenUtil.getIdFromToken(request.getHeader("Authorization").substring(7))));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new Response(1, e.toString() + " could not upload image"));
        }
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Response handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        StringBuilder stringBuilder = new StringBuilder();
        for (Map.Entry<String, String> entry : errors.entrySet()) {
            stringBuilder.append(entry.getValue() + " ; ");
        }
        return new Response(1, stringBuilder.toString());
    }
}
