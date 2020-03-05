package ru.pethelper.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.pethelper.dao.VetClinicRepository;

@RestController
@RequestMapping("vetclinic")
public class VetClinicController {
    @Autowired
    VetClinicRepository vetRepository;

    @GetMapping()
    ResponseEntity getVetClinicsByDistrict(@RequestParam String district) {
        return new ResponseEntity(vetRepository.findByDistrict(district), HttpStatus.OK);
    }
}
