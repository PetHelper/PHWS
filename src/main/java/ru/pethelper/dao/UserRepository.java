package ru.pethelper.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.pethelper.model.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    UserEntity findByUsername(String username);

    UserEntity findByActivationCode(String code);

    UserEntity findByUserEmail(String userEmail);

    UserEntity findByUserPhone(long userPhone);
}
