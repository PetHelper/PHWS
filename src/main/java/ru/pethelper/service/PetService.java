package ru.pethelper.service;

import ru.pethelper.domain.Pet;

import java.util.List;

public interface PetService {
    void addPet(Pet pet, int userId);

    List<Pet> getAll(int userId);

    Pet getPet(int petId);
}
