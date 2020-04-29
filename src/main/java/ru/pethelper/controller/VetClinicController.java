package ru.pethelper.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.pethelper.controller.model.Response;
import ru.pethelper.controller.model.VetClinicWeb;
import ru.pethelper.exception.VetClinicAlreadyExistsException;
import ru.pethelper.mapper.VetclinicMapper;
import ru.pethelper.service.VetClinicService;

import javax.validation.Valid;

@RestController
@RequestMapping("vetclinic")
public class VetClinicController {
    @Autowired
    VetClinicService vetClinicService;

    @GetMapping("/find")
    ResponseEntity getVetClinicsByDistrict(@RequestParam(name = "district", required = false) String district) {
        return new ResponseEntity(vetClinicService.findVetClinicByDistrict(district), HttpStatus.OK);
    }

    @PostMapping(path = "add", consumes = "application/json", produces = "application/json")
    ResponseEntity add(@RequestBody @Valid VetClinicWeb vetClinicWeb) {
        try {
            return new ResponseEntity(new Response(0, vetClinicService.addVetClinic(VetclinicMapper.VET_MAPPER.VetClinicWebToVetClinic(vetClinicWeb))), HttpStatus.OK);
        } catch (VetClinicAlreadyExistsException e) {
            return new ResponseEntity(new Response(1, e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/get-vet")
    ResponseEntity getVet(@RequestHeader(value = "vet-clinic-id") long vetClinicId) {
        System.out.println("What happened?");
        try {
            return new ResponseEntity(vetClinicService.getVetClinic(vetClinicId), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(new Response(1, e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }
}
