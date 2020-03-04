package ru.pethelper.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.pethelper.dao.PetRepository;
import ru.pethelper.model.Pet;

import java.util.List;

@RestController
@RequestMapping("pet")
public class PetController {
    @Autowired
    PetRepository petRepository;

    @GetMapping("/findAll")
    List<Pet> findAll() {
        return petRepository.findAll();
    }

    @PostMapping(path = "/addPet", consumes = "application/json", produces = "application/json")
    void addPet(@RequestBody List<Pet> petList) {
        for(Pet pet : petList) {
            petRepository.save(pet);
        }
    }
}
