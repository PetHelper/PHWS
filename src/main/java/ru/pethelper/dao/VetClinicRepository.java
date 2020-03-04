package ru.pethelper.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.pethelper.model.VetClinic;

import java.util.List;

@Repository
public interface VetClinicRepository extends JpaRepository<VetClinic, Long> {
    List<VetClinic> findByDistrict(String district);
}
