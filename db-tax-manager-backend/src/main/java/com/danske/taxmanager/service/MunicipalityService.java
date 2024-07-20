package com.danske.taxmanager.service;

import com.danske.taxmanager.entity.Municipality;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface MunicipalityService {

    Iterable<Municipality> fetchAllMunicipalities();

    @Query("INSERT INTO Municipality (municipality_id, municipality_name, active) VALUES (:municipalityId, :municipalityName, :active)")
     Municipality saveMunicipality(String municipalityId, String municipalityName, Boolean active);

    Optional<Municipality> fetchMunicipalityById(String municipalityId);

    Optional<Municipality> fetchMunicipalityIdByMunicipalityName(String municipalityName);



}
