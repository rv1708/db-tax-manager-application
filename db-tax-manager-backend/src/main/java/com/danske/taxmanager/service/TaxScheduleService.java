package com.danske.taxmanager.service;

import com.danske.taxmanager.entity.TaxSchedule;

public interface TaxScheduleService {

    Iterable<TaxSchedule> fetchAllTaxSchedules();

    Iterable<TaxSchedule> findAllById(String taxScheduleId);
}
