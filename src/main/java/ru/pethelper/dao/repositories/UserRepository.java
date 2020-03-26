package ru.pethelper.dao.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.pethelper.dao.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    UserEntity findByUsername(String username);

    UserEntity findByActivationCode(String code);

    UserEntity findByUserPhone(long userPhone);

    UserEntity findByUserEmail(String email);
}
