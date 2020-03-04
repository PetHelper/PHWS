package ru.pethelper.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.pethelper.dao.VetClinicRepository;
import ru.pethelper.model.VetClinic;

import java.util.List;

@RestController
@RequestMapping("vetclinic")
public class VetClinicController {
    @Autowired
    VetClinicRepository vetRepository;

    @GetMapping()
    List<VetClinic> getVetClinicsByDistrict(@RequestParam String district) {
        return vetRepository.findByDistrict(district);
    }
}
