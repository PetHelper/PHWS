package ru.pethelper.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;
import ru.pethelper.controller.model.PetWeb;
import ru.pethelper.dao.PetEntity;
import ru.pethelper.domain.Pet;

@Component
@Mapper
public interface PetMapper {
    PetMapper PET_MAPPER = Mappers.getMapper(PetMapper.class);

    @Mappings({@Mapping(target = "petName", source = "domain.petName"),
            @Mapping(target = "colour", source = "domain.colour"),
            @Mapping(target = "weight", source = "domain.weight"),
            @Mapping(target = "breed", source = "domain.breed"),
            @Mapping(target = "sex", source = "domain.sex"),
            @Mapping(target = "distMarks", source = "domain.distMarks"),
            @Mapping(target = "tagNumber", source = "domain.tagNumber"),
            @Mapping(target = "pedigreeNum", source = "domain.pedigreeNum"),
            @Mapping(target = "animalCardNum", source = "domain.animalCardNum"),
            @Mapping(target = "birthDate", source = "domain.birthDate", dateFormat = "dd-MM-yyyy HH:mm:ss")})
    PetEntity petToPetEntity(Pet domain);

    @Mappings({@Mapping(target = "petName", source = "dto.petName"),
            @Mapping(target = "colour", source = "dto.colour"),
            @Mapping(target = "weight", source = "dto.weight"),
            @Mapping(target = "breed", source = "dto.breed"),
            @Mapping(target = "sex", source = "dto.sex"),
            @Mapping(target = "distMarks", source = "dto.distMarks"),
            @Mapping(target = "tagNumber", source = "dto.tagNumber"),
            @Mapping(target = "pedigreeNum", source = "dto.pedigreeNum"),
            @Mapping(target = "animalCardNum", source = "dto.animalCardNum"),
            @Mapping(target = "birthDate", source = "dto.birthDate", dateFormat = "dd-MM-yyyy HH:mm:ss")})
    Pet petEntityToPet(PetEntity dto);

    @Mappings({
            @Mapping(target = "petId", source = "domain.petId"),
            @Mapping(target = "petName", source = "domain.petName"),
            @Mapping(target = "colour", source = "domain.colour"),
            @Mapping(target = "weight", source = "domain.weight"),
            @Mapping(target = "breed", source = "domain.breed"),
            @Mapping(target = "sex", source = "domain.sex"),
            @Mapping(target = "distMarks", source = "domain.distMarks"),
            @Mapping(target = "tagNumber", source = "domain.tagNumber"),
            @Mapping(target = "pedigreeNum", source = "domain.pedigreeNum"),
            @Mapping(target = "animalCardNum", source = "domain.animalCardNum"),
            @Mapping(target = "birthDate", source = "domain.birthDate", dateFormat = "dd-MM-yyyy HH:mm:ss")})
    PetWeb petToPetWeb(Pet domain);

    @Mappings({
            @Mapping(target = "petId", source = "web.petId"),
            @Mapping(target = "petName", source = "web.petName"),
            @Mapping(target = "colour", source = "web.colour"),
            @Mapping(target = "weight", source = "web.weight"),
            @Mapping(target = "breed", source = "web.breed"),
            @Mapping(target = "sex", source = "web.sex"),
            @Mapping(target = "distMarks", source = "web.distMarks"),
            @Mapping(target = "tagNumber", source = "web.tagNumber"),
            @Mapping(target = "pedigreeNum", source = "web.pedigreeNum"),
            @Mapping(target = "animalCardNum", source = "web.animalCardNum"),
            @Mapping(target = "birthDate", source = "web.birthDate", dateFormat = "dd-MM-yyyy HH:mm:ss")})
    Pet petWebToPet(PetWeb web);
}
