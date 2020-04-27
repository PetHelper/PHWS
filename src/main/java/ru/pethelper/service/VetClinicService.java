package ru.pethelper.service;

import ru.pethelper.domain.VetClinic;
import ru.pethelper.exception.VetClinicAlreadyExistsException;

import java.util.List;

public interface VetClinicService {
    List<VetClinic> findVetClinicByDistrict(String district);

    String addVetClinic(VetClinic vetClinic) throws VetClinicAlreadyExistsException;

    VetClinic getVetClinic(long vetClinicId);
}
