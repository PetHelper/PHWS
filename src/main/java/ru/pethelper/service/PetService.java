package ru.pethelper.service;

import ru.pethelper.domain.Pet;

import java.util.List;

public interface PetService {
    long addPet(Pet pet);

    List<Pet> findAll();
}
