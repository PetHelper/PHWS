package ru.pethelper.service;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.web.multipart.MultipartFile;
import ru.pethelper.domain.Pet;
import ru.pethelper.exception.UsersPetException;

import java.util.List;

public interface PetService {
    void addPet(Pet pet, long userId);

    List<Pet> getAll(long userId);

    Pet getPet(long petId, long userId) throws UsersPetException;

    void saveImage(long petId, long userId, MultipartFile image) throws Exception;

    ByteArrayResource getImage(long petId, long userId) throws Exception;
}
