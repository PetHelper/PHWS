package ru.pethelper.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.pethelper.dao.VetClinicRepository;

@RestController
@RequestMapping("vetclinic")
public class VetClinicController {
    @Autowired
    VetClinicRepository vetRepository;

    @GetMapping("/find")
    ResponseEntity getVetClinicsByDistrict(@RequestParam(name = "district") String district) {
        return new ResponseEntity(vetRepository.findByDistrict(district), HttpStatus.OK);
    }
}
