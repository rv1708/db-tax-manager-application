package com.danske.taxmanager.service;

import com.danske.taxmanager.entity.MunicipalityTaxData;
import com.danske.taxmanager.repository.MunicipalityTaxRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.sql.Date;
import java.util.Optional;

@Service
public class MunicipalityTaxServiceImpl implements MunicipalityTaxService {

    @Autowired
    MunicipalityTaxRepository municipalityTaxRepository;

    @Override
    public Iterable<MunicipalityTaxData> fetchAllMunicipalityTaxData() {
        return municipalityTaxRepository.findAll();
    }

    @Override
    public MunicipalityTaxData saveMunicipalityTaxData(String id, String municipalityId, String taxScheduleId, String municipalityName,
                                                       float taxValue, Date startDate, Date endDate, String taxSchedule) {
        return municipalityTaxRepository.save(new MunicipalityTaxData(id, municipalityId, taxScheduleId, municipalityName, taxValue, startDate, endDate, taxSchedule));
    }

    @Override
    public Iterable<MunicipalityTaxData> fetchAllMunicipalityTaxDataByMunicipalityName(String municipalityName) {
        return municipalityTaxRepository.findAllByMunicipalityName(municipalityName);
    }

    @Override
    public Optional<MunicipalityTaxData> fetchDataById(String id) {
        return municipalityTaxRepository.findById(id);
    }

}
