package com.danske.taxmanager.repository;

import com.danske.taxmanager.entity.TaxSchedule;
import org.springframework.data.repository.CrudRepository;

public interface TaxScheduleRepository extends CrudRepository<TaxSchedule, String> {
    Iterable<TaxSchedule> findAllByTaxScheduleId(String scheduleId);
}
