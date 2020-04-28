package ru.pethelper.service;

import ru.pethelper.domain.Pet;

import java.util.List;

public interface PetService {
    void addPet(Pet pet, long userId);

    List<Pet> getAll(long userId);

    Pet getPet(long petId);
}
