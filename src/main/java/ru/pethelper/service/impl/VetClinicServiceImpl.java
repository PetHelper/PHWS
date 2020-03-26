package ru.pethelper.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.pethelper.dao.VetClinicEntity;
import ru.pethelper.dao.repositories.VetClinicRepository;
import ru.pethelper.domain.VetClinic;
import ru.pethelper.exception.VetClinicAlreadyExistsException;
import ru.pethelper.mapper.VetclinicMapper;
import ru.pethelper.service.VetClinicService;

import java.util.ArrayList;
import java.util.List;

@Service
public class VetClinicServiceImpl implements VetClinicService {
    @Autowired
    VetClinicRepository vetClinicRepository;

    @Override
    public List<VetClinic> findVetClinicByDistrict(String district) {
        List<VetClinic> vetClinicList = new ArrayList<>();
        for (VetClinicEntity vetClinicEntity : vetClinicRepository.findByDistrict(district)) {
            vetClinicList.add(VetclinicMapper.VET_MAPPER.VetClinicEntityToVetClinic(vetClinicEntity));
        }
        return vetClinicList;
    }

    @Override
    public String addVetClinic(VetClinic vetClinic) throws VetClinicAlreadyExistsException {
        if (vetClinicRepository.findByName(vetClinic.getName()).isEmpty()) {
            vetClinicRepository.save(VetclinicMapper.VET_MAPPER.vetClinicToVetClinicEntity(vetClinic));
            return "Vet clinic successfully added!";
        } else {
            throw new VetClinicAlreadyExistsException(vetClinic.getName());
        }
    }
}
