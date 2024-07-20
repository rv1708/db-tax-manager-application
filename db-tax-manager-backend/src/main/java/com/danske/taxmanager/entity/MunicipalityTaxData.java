package com.danske.taxmanager.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.sql.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MunicipalityTaxData {

    @Id
    private String id;

    String municipalityId;

    private String taxScheduleId;

    private String municipalityName;

    private float taxValue;

    private Date startDate;

    private Date endDate;

    private String taxSchedule;


}
