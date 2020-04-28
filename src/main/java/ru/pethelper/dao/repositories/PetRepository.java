package ru.pethelper.dao.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.pethelper.dao.PetEntity;
import ru.pethelper.dao.UserEntity;

import java.util.List;

@Repository
public interface PetRepository extends JpaRepository<PetEntity, Long> {
    List<PetEntity> findByPetName(String name);

    List<PetEntity> findByUserEntity(UserEntity userEntity);

    PetEntity findByPetId(long petId);
}
