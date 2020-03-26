package ru.pethelper.service;

import ru.pethelper.domain.VetClinic;

import java.util.List;

public interface VetClinicService {
    List<VetClinic> findVetClinicByDistrict(String district);

    String addVetClinic(VetClinic vetClinic);
}
