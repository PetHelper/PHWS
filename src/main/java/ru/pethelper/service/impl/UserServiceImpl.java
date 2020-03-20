package ru.pethelper.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import ru.pethelper.config.JwtTokenUtil;
import ru.pethelper.dao.repositories.UserRepository;
import ru.pethelper.dao.Role;
import ru.pethelper.dao.UserEntity;
import ru.pethelper.domain.User;
import ru.pethelper.exception.UserAlreadyExistsException;
import ru.pethelper.exception.UserNotActive;
import ru.pethelper.exception.UserNotFound;
import ru.pethelper.mapper.UserMapper;
import ru.pethelper.service.MailSender;
import ru.pethelper.service.UserService;
import ru.pethelper.servlet.JwtRequest;

import java.util.*;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {
    @Autowired
    private UserRepository userRepo;

    @Autowired
    private MailSender mailSender;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = userRepo.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(), passwordEncoder.encode(user.getPassword()),
                new ArrayList<>());
    }

    public String addUser(User user) throws UserAlreadyExistsException {

        if (userRepo.findByUsername(user.getUsername()) != null) {
            throw new UserAlreadyExistsException(user.getUsername());
        }
        if (userRepo.findByUserEmail(user.getUserEmail()) != null) {
            throw new UserAlreadyExistsException(user.getUserEmail());
        }
        if (userRepo.findByUserPhone(user.getUserPhone()) != null) {
            throw new UserAlreadyExistsException(user.getUserPhone());
        }

        UserEntity userEntity = UserMapper.USER_MAPPER.userToUserEntity(user);

        userEntity.setUserRegDate(new Date());
        userEntity.setUserBirthDate(new Date());
        userEntity.setActive(false);
        userEntity.setRoles(Collections.singleton(Role.USER));
        userEntity.setActivationCode(UUID.randomUUID().toString());
        userEntity.setPassword(passwordEncoder.encode(user.getPassword()));

        userRepo.save(userEntity);

        if (!StringUtils.isEmpty(user.getUserEmail())) {
            String message = String.format(
                    "Hello, %s! \n" +
                            "Welcome to PetHelper. Please, visit next link: https://localhost:8443/user/activate/%s",
                    userEntity.getUsername(),
                    userEntity.getActivationCode()
            );

            mailSender.send(user.getUserEmail(), "Activation code", message);
        }

        return "User successfully registred!";
    }

    public boolean activateUser(String code) {
        UserEntity user = userRepo.findByActivationCode(code);

        if (user == null) {
            return false;
        }

        user.setActive(true);
        user.setActivationCode(null);

        userRepo.save(user);

        return true;
    }

    @Override
    public String findByUserEmail(String email) throws Exception {
        System.out.println("Email : " + email);
        User user = UserMapper.USER_MAPPER.userEntityToUser(userRepo.findByUserEmail(email));
        if (user != null) {
            System.out.println(user.isActive());
            if (user.isActive()) {
                return createAuthenticationToken(new JwtRequest(user.getUsername(), user.getPassword()));
            } else {
                throw new UserNotActive(email);
            }
        } else {
            throw new UserNotFound(email);
        }
    }

    public String createAuthenticationToken(JwtRequest authenticationRequest)
            throws Exception {

        System.out.println("Password 1 : " + authenticationRequest.getPassword());

        authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());

        UserDetails userDetails = loadUserByUsername(authenticationRequest.getUsername());

        String token = jwtTokenUtil.generateToken(userDetails);

        return token;
    }

    private void authenticate(String username, String password) throws Exception {
        Objects.requireNonNull(username);
        Objects.requireNonNull(password);

        System.out.println("Password 2 : " + password);

        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }
}
