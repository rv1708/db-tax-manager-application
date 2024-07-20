package com.danske.taxmanager.service;

import com.danske.taxmanager.entity.TaxSchedule;
import com.danske.taxmanager.repository.TaxScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaxScheduleServiceImpl implements TaxScheduleService {

    @Autowired
    TaxScheduleRepository taxScheduleRepository;

    @Override
    public Iterable<TaxSchedule> fetchAllTaxSchedules() {
        return taxScheduleRepository.findAll();
    }

    @Override
    public Iterable<TaxSchedule> findAllById(String taxScheduleId) {
        return taxScheduleRepository.findAllByTaxScheduleId(taxScheduleId);
    }
}
