package ru.pethelper.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.pethelper.config.JwtTokenUtil;
import ru.pethelper.controller.model.PetWeb;
import ru.pethelper.mapper.PetMapper;
import ru.pethelper.service.PetService;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("pet")
public class PetController {
    @Autowired
    PetService petService;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @GetMapping("/get-all")
    ResponseEntity getAll(HttpServletRequest request) {
        int userId = jwtTokenUtil.getIdFromToken(request.getHeader("Authorization").substring(7));
        return new ResponseEntity(petService.getAll(userId), HttpStatus.OK);
    }

    @PostMapping(path = "/add-pet", consumes = "application/json", produces = "application/json")
    ResponseEntity addPet(HttpServletRequest request, @RequestBody PetWeb petWeb) {
        int userId = jwtTokenUtil.getIdFromToken(request.getHeader("Authorization").substring(7));
        try {
            petService.addPet(PetMapper.PET_MAPPER.petWebToPet(petWeb), userId);
            return new ResponseEntity("Pet added!", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity("Could not add pet " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/get")
    ResponseEntity getPet(@RequestHeader int petId) {
        return new ResponseEntity(petService.getPet(petId), HttpStatus.OK);
    }
}
