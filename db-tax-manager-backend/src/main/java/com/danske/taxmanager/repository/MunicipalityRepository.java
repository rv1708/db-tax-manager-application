package com.danske.taxmanager.repository;

import com.danske.taxmanager.entity.Municipality;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface MunicipalityRepository extends CrudRepository<Municipality, String> {
    Optional<Municipality> findByMunicipalityName(String municipalityName);
}
