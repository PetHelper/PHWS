package ru.pethelper.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.pethelper.controller.model.UserWeb;
import ru.pethelper.controller.model.VetClinicWeb;
import ru.pethelper.exception.UserAlreadyExistsException;
import ru.pethelper.exception.VetClinicAlreadyExistsException;
import ru.pethelper.mapper.UserMapper;
import ru.pethelper.mapper.VetclinicMapper;
import ru.pethelper.service.VetClinicService;

import javax.validation.Valid;

@RestController
@RequestMapping("vetclinic")
public class VetClinicController {
    @Autowired
    VetClinicService vetClinicService;

    @GetMapping("/find")
    ResponseEntity getVetClinicsByDistrict(@RequestParam(name = "district") String district) {
        return new ResponseEntity(vetClinicService.findVetClinicByDistrict(district), HttpStatus.OK);
    }

    @PostMapping(path = "add", consumes = "application/json", produces = "application/json")
    ResponseEntity add(@RequestBody @Valid VetClinicWeb vetClinicWeb) throws Exception {
        try {
            return new ResponseEntity(vetClinicService.addVetClinic(VetclinicMapper.VET_MAPPER.VetClinicWebToVetClinic(vetClinicWeb)), HttpStatus.OK);
        } catch (VetClinicAlreadyExistsException e) {
            return new ResponseEntity(e.getMessage() , HttpStatus.BAD_REQUEST);
        }
    }
}
