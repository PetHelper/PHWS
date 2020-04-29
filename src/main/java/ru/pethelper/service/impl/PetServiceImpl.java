package ru.pethelper.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.pethelper.dao.PetEntity;
import ru.pethelper.dao.UserEntity;
import ru.pethelper.dao.repositories.PetRepository;
import ru.pethelper.dao.repositories.UserRepository;
import ru.pethelper.domain.Pet;
import ru.pethelper.exception.UsersPetException;
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
    public Pet getPet(long petId, long userId) throws UsersPetException {
        UserEntity userEntity = userRepo.findByUserId(userId);
        Pet pet = PetMapper.PET_MAPPER.petEntityToPet(petRepository.getOneByPetIdAndUserEntity(petId, userEntity));
        petUserConnectionCheck(pet);
        return pet;
    }

    @Override
    public void saveImage(long petId, long userId, MultipartFile image) throws Exception {
        UserEntity userEntity = userRepo.findByUserId(userId);
        PetEntity petEntity = petRepository.getOneByPetIdAndUserEntity(petId, userEntity);
        petUserConnectionCheck(petEntity);
        petEntity.setPetImage(image.getBytes());
        petRepository.save(petEntity);
    }

    @Override
    public ByteArrayResource getImage(long petId, long userId) throws Exception {
        UserEntity userEntity = userRepo.findByUserId(userId);
        PetEntity petEntity = petRepository.getOneByPetIdAndUserEntity(petId, userEntity);
        petUserConnectionCheck(petEntity);
        return new ByteArrayResource(petEntity.getPetImage());
    }

    private <T> void petUserConnectionCheck(T pet) throws UsersPetException {
        if (pet == null) {
            throw new UsersPetException();
        }
    }
}
