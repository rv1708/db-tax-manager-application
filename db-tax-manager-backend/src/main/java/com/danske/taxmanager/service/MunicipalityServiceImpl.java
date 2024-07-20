package com.danske.taxmanager.service;

import com.danske.taxmanager.entity.Municipality;
import com.danske.taxmanager.repository.MunicipalityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MunicipalityServiceImpl implements MunicipalityService {

    @Autowired
    private MunicipalityRepository municipalityRepository;

    @Override
    public List<Municipality> fetchAllMunicipalities() {
        return (List<Municipality>) municipalityRepository.findAll();
    }

    public Municipality saveMunicipality(String municipalityId, String municipalityName, Boolean active) {
        return municipalityRepository.save(new Municipality(municipalityId, municipalityName, active));
    }

    @Override
    public Optional<Municipality> fetchMunicipalityById(String municipalityId){
        return municipalityRepository.findById(municipalityId);
    }

    public Optional<Municipality> fetchMunicipalityIdByMunicipalityName(String municipalityName) {
        return municipalityRepository.findByMunicipalityName(municipalityName);
    }



}
