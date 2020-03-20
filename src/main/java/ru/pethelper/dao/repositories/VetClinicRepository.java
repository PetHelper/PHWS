package ru.pethelper.dao.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.pethelper.dao.VetClinicEntity;

import java.util.List;

@Repository
public interface VetClinicRepository extends JpaRepository<VetClinicEntity, Long> {
    List<VetClinicEntity> findByDistrict(String district);
}
