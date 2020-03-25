package ru.pethelper.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.pethelper.controller.model.PetWeb;
import ru.pethelper.domain.Pet;
import ru.pethelper.mapper.PetMapper;
import ru.pethelper.service.impl.PetServiceImpl;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("pet")
public class PetController {
    @Autowired
    PetServiceImpl petService;

    @GetMapping("/find-all")
    ResponseEntity findAll() {
        List<PetWeb> petWebList = new ArrayList<>();
        for (Pet pet : petService.findAll()) {
            petWebList.add(PetMapper.PET_MAPPER.petToPetWeb(pet));
        }
        return new ResponseEntity(petWebList, HttpStatus.OK);
    }

    @PostMapping(path = "/add-pet", consumes = "application/json", produces = "application/json")
    ResponseEntity addPet(@RequestBody List<PetWeb> petWebList) {
        for(PetWeb petDTO : petWebList) {
            petService.addPet(PetMapper.PET_MAPPER.petWebToPet(petDTO));
        }
        return new ResponseEntity("Pet added!", HttpStatus.OK);
    }
}
