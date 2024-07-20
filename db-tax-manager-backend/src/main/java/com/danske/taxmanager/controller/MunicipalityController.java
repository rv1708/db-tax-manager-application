package com.danske.taxmanager.controller;

import com.danske.taxmanager.response.GenericErrorResponse;
import com.danske.taxmanager.response.SuccessResponse;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.danske.taxmanager.service.MunicipalityService;
import com.danske.taxmanager.entity.Municipality;
import com.danske.taxmanager.response.GenericErrorResponse;
import com.danske.taxmanager.response.SuccessResponse;
import com.danske.taxmanager.utils.IDGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody; 
import org.springframework.web.bind.annotation.RequestMapping; 
import org.springframework.web.bind.annotation.RequestMethod;
import com.google.common.collect.Lists;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.v3.oas.annotations.Operation;

import java.util.Optional;

@RestController
public class MunicipalityController {

    @Autowired
    MunicipalityService municipalityService;

    @Operation( summary = "Fetch all municipalities registered", description = "Get all municipalities registered" )
    @RequestMapping(value = "/municipalities", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAllMunicipalities () {
        try {
            SuccessResponse successResponse;
            int numberOfRecords = Lists.newArrayList(municipalityService.fetchAllMunicipalities()).size();
            successResponse = new SuccessResponse(200, HttpStatus.OK.toString(), municipalityService.fetchAllMunicipalities(), numberOfRecords);
            ResponseEntity<SuccessResponse> responseEntity = new ResponseEntity<>(successResponse, HttpStatus.OK);
            return responseEntity;
        } catch (Exception e) {
            GenericErrorResponse errorResponse;
            errorResponse = new GenericErrorResponse("ERROR001", e.getLocalizedMessage(), e.getMessage());
            ResponseEntity<GenericErrorResponse> responseEntity = new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
            return responseEntity;
        }

    }
    @RequestMapping(value = "/add-municipality", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> addMunicipality (@RequestBody Municipality municipalityData) {
        try {
            //Gson gson = new Gson();
            IDGenerator idGenerator = new IDGenerator("Municipality");
            //JsonElement element = gson.fromJson(municipalityData, JsonElement.class);
            //JsonObject jsonObj = element.getAsJsonObject();
            Municipality result = municipalityService.saveMunicipality(idGenerator.getIdFromType(), municipalityData.getMunicipalityName().toUpperCase(), municipalityData.getActive());
            SuccessResponse successResponse = new SuccessResponse(202, "Saved successfully", result.getMunicipalityId(), 1);
            return new ResponseEntity<>(successResponse, HttpStatus.ACCEPTED);
        } catch (Exception e) {
            GenericErrorResponse errorResponse;
            errorResponse = new GenericErrorResponse("ERROR002", e.getLocalizedMessage(), e.getMessage());
            ResponseEntity<GenericErrorResponse> responseEntity = new ResponseEntity<>(errorResponse,
                    HttpStatus.INTERNAL_SERVER_ERROR);
            return responseEntity;
        }
    }

    public Optional<Municipality> getMunicipalityById(String municipalityId) {
        return municipalityService.fetchMunicipalityById(municipalityId);
    }

    public Optional<Municipality> getMunicipalityIdByMunicipalityName (String municipalityName) {
        return municipalityService.fetchMunicipalityIdByMunicipalityName(municipalityName);
    }

}
