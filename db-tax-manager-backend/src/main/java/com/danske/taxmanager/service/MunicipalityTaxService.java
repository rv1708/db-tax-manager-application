package com.danske.taxmanager.service;

import com.danske.taxmanager.entity.MunicipalityTaxData;
import org.springframework.data.jpa.repository.Query;

import java.sql.Date;

public interface MunicipalityTaxService {

    Iterable<MunicipalityTaxData> fetchAllMunicipalityTaxData();

    @Query("INSERT INTO MunicipalityTaxData (id, municipality_id, tax_schedule_id, municipality_name, tax_value, start_date, end_date) " +
            "VALUES (:id, :municipality_id, :tax_schedule_id, municipality_name, :tax_value, :start_date, :end_date, :tax_schedule)")
    MunicipalityTaxData saveMunicipalityTaxData(String id, String municipalityId, String taxScheduleId, String municipalityName,
                                                float taxValue, Date startDate, Date endDate, String taxSchedule);

    Iterable<MunicipalityTaxData> fetchAllMunicipalityTaxDataByMunicipalityName(String municipalityName);

}
