package ru.pethelper.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    ResponseEntity findAll() {
        return new ResponseEntity(petRepository.findAll(), HttpStatus.OK);
    }

    @PostMapping(path = "/addPet", consumes = "application/json", produces = "application/json")
    ResponseEntity addPet(@RequestBody List<Pet> petList) {
        for(Pet pet : petList) {
            petRepository.save(pet);
        }
        return new ResponseEntity(HttpStatus.OK);
    }
}
