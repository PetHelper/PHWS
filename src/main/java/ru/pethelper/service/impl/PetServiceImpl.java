package ru.pethelper.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.pethelper.dao.PetEntity;
import ru.pethelper.dao.repositories.PetRepository;
import ru.pethelper.domain.Pet;
import ru.pethelper.mapper.PetMapper;
import ru.pethelper.service.PetService;

import java.util.ArrayList;
import java.util.List;

@Service
public class PetServiceImpl implements PetService {
    @Autowired
    PetRepository petRepository;

    @Override
    public long addPet(Pet pet) {
        petRepository.save(PetMapper.PET_MAPPER.petToPetEntity(pet));
        return 0;
    }

    @Override
    public List<Pet> findAll() {
        List<Pet> petList = new ArrayList<>();
        for (PetEntity petEntity : petRepository.findAll()) {
            petList.add(PetMapper.PET_MAPPER.petEntityToPet(petEntity));
        }
        return petList;
    }
}
