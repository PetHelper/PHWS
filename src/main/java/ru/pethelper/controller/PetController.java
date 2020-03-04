package ru.pethelper.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.pethelper.dao.PetRepository;
import ru.pethelper.model.Pet;

import java.util.List;

@RestController
@RequestMapping("pet")
public class PetController {
    @Autowired
    PetRepository petRepository;

    @GetMapping("/findAll")
    public List<Pet> findAll() {
        return petRepository.findAll();
    }
}
