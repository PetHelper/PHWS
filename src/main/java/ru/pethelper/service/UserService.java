package ru.pethelper.service;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.web.multipart.MultipartFile;
import ru.pethelper.domain.User;
import ru.pethelper.exception.UserAlreadyExistsException;
import ru.pethelper.exception.UserNotFound;
import ru.pethelper.servlet.JwtRequest;

public interface UserService {
    User getUser(long userId) throws UserNotFound;

    String addUser(User user) throws UserAlreadyExistsException;

    boolean activateUser(String code);

    String findUserForSignIn(String email, String Password) throws Exception;

    String createAuthenticationToken(JwtRequest authenticationRequest) throws Exception;

    void saveImage(long userId, MultipartFile image) throws Exception;

    ByteArrayResource getImage(long userId) throws Exception;
}
