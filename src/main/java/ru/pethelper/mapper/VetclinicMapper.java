package ru.pethelper.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;
import ru.pethelper.controller.model.VetClinicWeb;
import ru.pethelper.dao.VetClinicEntity;
import ru.pethelper.domain.VetClinic;

@Component
@Mapper
public interface VetclinicMapper {
    VetclinicMapper VET_MAPPER = Mappers.getMapper(VetclinicMapper.class);

    @Mappings({@Mapping(target = "address", source = "domain.address"),
            @Mapping(target = "district", source = "domain.district"),
            @Mapping(target = "rate", source = "domain.rate"),
            @Mapping(target = "name", source = "domain.name"),
            @Mapping(target = "site", source = "domain.site"),
            @Mapping(target = "email", source = "domain.email"),
            @Mapping(target = "phoneNumbers", source = "domain.phoneNumbers")})
    VetClinicEntity vetClinicToVetClinicEntity(VetClinic domain);

    @Mappings({@Mapping(target = "address", source = "dto.address"),
            @Mapping(target = "district", source = "dto.district"),
            @Mapping(target = "rate", source = "dto.rate"),
            @Mapping(target = "name", source = "dto.name"),
            @Mapping(target = "site", source = "dto.site"),
            @Mapping(target = "email", source = "dto.email"),
            @Mapping(target = "phoneNumbers", source = "dto.phoneNumbers")})
    VetClinic VetClinicEntityToVetClinic(VetClinicEntity dto);

    @Mappings({@Mapping(target = "address", source = "domain.address"),
            @Mapping(target = "district", source = "domain.district"),
            @Mapping(target = "name", source = "domain.name"),
            @Mapping(target = "site", source = "domain.site"),
            @Mapping(target = "email", source = "domain.email"),
            @Mapping(target = "phoneNumbers", source = "domain.phoneNumbers")})
    VetClinicWeb VetClinicToVetClinicWeb(VetClinic domain);

    @Mappings({@Mapping(target = "address", source = "web.address"),
            @Mapping(target = "district", source = "web.district"),
            @Mapping(target = "name", source = "web.name"),
            @Mapping(target = "site", source = "web.site"),
            @Mapping(target = "email", source = "web.email"),
            @Mapping(target = "phoneNumbers", source = "web.phoneNumbers")})
    VetClinic VetClinicWebToVetClinic(VetClinicWeb web);
}
