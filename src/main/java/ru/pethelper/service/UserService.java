package ru.pethelper.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import ru.pethelper.dao.UserRepository;
import ru.pethelper.model.Role;
import ru.pethelper.model.UserEntity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.UUID;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepository userRepo;

    @Autowired
    private MailSender mailSender;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = userRepo.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(), passwordEncoder.encode(user.getPassword()),
                new ArrayList<>());
    }

    public long addUser(UserEntity user) {
        //UserEntity userFromDb = userRepo.findByUsername(user.getUsername());

        if (userRepo.findByUsername(user.getUsername()) != null) {
            return 2;
        }
        if (userRepo.findByUserEmail(user.getUserEmail()) != null) {
            return 3;
        }
        if (userRepo.findByUserPhone(user.getUserPhone()) != null) {
            return 4;
        }

        user.setUserRegDate(new Date());
        user.setUserBirthDate(new Date());
        user.setActive(false);
        user.setRoles(Collections.singleton(Role.USER));
        user.setActivationCode(UUID.randomUUID().toString());
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        userRepo.save(user);

        if (!StringUtils.isEmpty(user.getUserEmail())) {
            String message = String.format(
                    "Hello, %s! \n" +
                            "Welcome to PetHelper. Please, visit next link: https://localhost:8443/user/activate/%s",
                    user.getUsername(),
                    user.getActivationCode()
            );

            mailSender.send(user.getUserEmail(), "Activation code", message);
        }

        return 0;
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
}
