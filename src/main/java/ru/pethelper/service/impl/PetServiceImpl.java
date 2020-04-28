package ru.pethelper.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.pethelper.dao.PetEntity;
import ru.pethelper.dao.UserEntity;
import ru.pethelper.dao.repositories.PetRepository;
import ru.pethelper.dao.repositories.UserRepository;
import ru.pethelper.domain.Pet;
import ru.pethelper.mapper.PetMapper;
import ru.pethelper.service.PetService;

import java.util.ArrayList;
import java.util.List;

@Service
public class PetServiceImpl implements PetService {
    @Autowired
    PetRepository petRepository;

    @Autowired
    UserRepository userRepo;

    @Override
    public void addPet(Pet pet, long userId) {
        UserEntity userEntity = userRepo.findByUserId(userId);
        PetEntity petEntity = PetMapper.PET_MAPPER.petToPetEntity(pet);
        petEntity.setUserEntity(userEntity);
        petRepository.save(petEntity);
    }

    @Override
    public List<Pet> getAll(long userId) {
        UserEntity userEntity = userRepo.findByUserId(userId);
        List<Pet> petList = new ArrayList<>();
        for (PetEntity petEntity : petRepository.findByUserEntity(userEntity)) {
            petList.add(PetMapper.PET_MAPPER.petEntityToPet(petEntity));
        }
        return petList;
    }

    @Override
    public Pet getPet(long petId) {
        return PetMapper.PET_MAPPER.petEntityToPet(petRepository.findByPetId(petId));
    }
}
