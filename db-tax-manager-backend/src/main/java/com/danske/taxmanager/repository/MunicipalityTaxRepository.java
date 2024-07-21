package com.danske.taxmanager.repository;

import com.danske.taxmanager.entity.MunicipalityTaxData;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface MunicipalityTaxRepository extends CrudRepository<MunicipalityTaxData, String> {
    Iterable<MunicipalityTaxData> findAllByMunicipalityName(String municipalityName);
    Optional<MunicipalityTaxData> findById(String id);
}
