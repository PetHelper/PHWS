package ru.pethelper.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.pethelper.config.JwtTokenUtil;
import ru.pethelper.controller.model.PetWeb;
import ru.pethelper.controller.model.Response;
import ru.pethelper.exception.UsersPetException;
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
        return new ResponseEntity(petService.getAll(jwtTokenUtil.getIdFromToken(request.getHeader("Authorization").substring(7))), HttpStatus.OK);
    }

    @PostMapping(path = "/add-pet", consumes = "application/json", produces = "application/json")
    ResponseEntity addPet(HttpServletRequest request, @RequestBody PetWeb petWeb) {
        try {
            petService.addPet(PetMapper.PET_MAPPER.petWebToPet(petWeb), jwtTokenUtil.getIdFromToken(request.getHeader("Authorization").substring(7)));
            return new ResponseEntity(new Response(0, "Pet added!"), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(new Response(1, "Could not add pet " + e.toString()), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/get")
    ResponseEntity getPet(HttpServletRequest request, @RequestHeader int petId) {
        try {
            return new ResponseEntity(petService.getPet(petId, jwtTokenUtil.getIdFromToken(request.getHeader("Authorization").substring(7))), HttpStatus.OK);
        } catch (UsersPetException e) {
            return new ResponseEntity(new Response(1, e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/save-pet-photo")
    public ResponseEntity savePetPhoto(HttpServletRequest request, @RequestHeader int petId, @RequestParam("image") MultipartFile image) {
        try {
            petService.saveImage(petId, jwtTokenUtil.getIdFromToken(request.getHeader("Authorization").substring(7)), image);
            return new ResponseEntity(new Response(0, "Successfully uploaded image"), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(new Response(1, e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/get-pet-photo")
    public ResponseEntity<?> getPetPhoto(HttpServletRequest request, @RequestHeader int petId) {
        try {
            return ResponseEntity.ok()
                    .contentType(MediaType.parseMediaType("image/jpeg"))
                    .body(petService.getImage(petId, jwtTokenUtil.getIdFromToken(request.getHeader("Authorization").substring(7))));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new Response(1, e.getMessage()));
        }
    }

}
