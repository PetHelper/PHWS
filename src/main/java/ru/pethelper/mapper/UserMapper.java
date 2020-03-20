package ru.pethelper.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;
import ru.pethelper.controller.model.UserWeb;
import ru.pethelper.dao.UserEntity;
import ru.pethelper.domain.User;

@Component
@Mapper
public interface UserMapper {
    UserMapper USER_MAPPER = Mappers.getMapper(UserMapper.class);

    @Mappings({@Mapping(target = "username", source = "domain.username"),
            @Mapping(target = "userSurname", source = "domain.userSurname"),
            @Mapping(target = "userAddress", source = "domain.userAddress"),
            @Mapping(target = "userEmail", source = "domain.userEmail"),
            @Mapping(target = "userPhone", source = "domain.userPhone"),
            @Mapping(target = "password", source = "domain.password"),
            @Mapping(target = "userBirthDate", source = "domain.userBirthDate", dateFormat = "dd-MM-yyyy HH:mm:ss")})
    UserEntity userToUserEntity(User domain);

    @Mappings({@Mapping(target = "username", source = "dto.username"),
            @Mapping(target = "userSurname", source = "dto.userSurname"),
            @Mapping(target = "userAddress", source = "dto.userAddress"),
            @Mapping(target = "userEmail", source = "dto.userEmail"),
            @Mapping(target = "userPhone", source = "dto.userPhone"),
            @Mapping(target = "password", source = "dto.password"),
            @Mapping(target = "active", source = "dto.active"),
            @Mapping(target = "userBirthDate", source = "dto.userBirthDate", dateFormat = "dd-MM-yyyy HH:mm:ss")})
    User userEntityToUser(UserEntity dto);

    @Mappings({@Mapping(target = "username", source = "domain.username"),
            @Mapping(target = "userSurname", source = "domain.userSurname"),
            @Mapping(target = "userAddress", source = "domain.userAddress"),
            @Mapping(target = "userEmail", source = "domain.userEmail"),
            @Mapping(target = "userPhone", source = "domain.userPhone"),
            @Mapping(target = "password", source = "domain.password"),
            @Mapping(target = "userBirthDate", source = "domain.userBirthDate", dateFormat = "dd-MM-yyyy HH:mm:ss")})
    UserWeb userToUserWeb(User domain);

    @Mappings({@Mapping(target = "username", source = "web.username"),
            @Mapping(target = "userSurname", source = "web.userSurname"),
            @Mapping(target = "userAddress", source = "web.userAddress"),
            @Mapping(target = "userEmail", source = "web.userEmail"),
            @Mapping(target = "userPhone", source = "web.userPhone"),
            @Mapping(target = "password", source = "web.password"),
            @Mapping(target = "userBirthDate", source = "web.userBirthDate", dateFormat = "dd-MM-yyyy HH:mm:ss")})
    User userWebToUser(UserWeb web);
}
