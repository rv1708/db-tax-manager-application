package com.danske.taxmanager.controller;

import com.danske.taxmanager.entity.TaxSchedule;
import com.danske.taxmanager.response.GenericErrorResponse;
import com.danske.taxmanager.response.SuccessResponse;
import com.danske.taxmanager.service.TaxScheduleService;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.v3.oas.annotations.Operation;

@RestController
public class TaxScheduleController {

    @Autowired
    TaxScheduleService taxScheduleService;

    @RequestMapping(value = "/tax-schedules", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAllTaxSchedules() {
        try {
            SuccessResponse successResponse;
            int numberOfRecords = Lists.newArrayList(taxScheduleService.fetchAllTaxSchedules()).size();
            successResponse = new SuccessResponse(200, HttpStatus.OK.toString(), taxScheduleService.fetchAllTaxSchedules(), numberOfRecords);
            ResponseEntity<SuccessResponse> responseEntity = new ResponseEntity<>(successResponse, HttpStatus.OK);
            return responseEntity;
        } catch (Exception e) {
            GenericErrorResponse errorResponse;
            errorResponse = new GenericErrorResponse("ERROR03", e.getLocalizedMessage(), e.getMessage());
            ResponseEntity<GenericErrorResponse> responseEntity = new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
            return responseEntity;
        }
    }

    public Iterable<TaxSchedule> getTaxScheduleById(String taxScheduleld) {
        return taxScheduleService.findAllById(taxScheduleld);
    }

}

