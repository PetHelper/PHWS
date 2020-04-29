package ru.pethelper.service;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.web.multipart.MultipartFile;
import ru.pethelper.domain.Pet;

import java.util.List;

public interface PetService {
    void addPet(Pet pet, long userId);

    List<Pet> getAll(long userId);

    Pet getPet(long petId);

    void saveImage(long petId, MultipartFile image) throws Exception;

    ByteArrayResource getImage(long petId) throws Exception;
}
