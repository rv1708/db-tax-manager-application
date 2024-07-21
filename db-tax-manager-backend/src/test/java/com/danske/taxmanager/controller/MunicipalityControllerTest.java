package com.danske.taxmanager.controller;

import com.danske.taxmanager.entity.Municipality;
import com.danske.taxmanager.service.MunicipalityService;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class MunicipalityControllerTest {

    @InjectMocks
    MunicipalityController municipalityController;

    @Mock
    MunicipalityService municipalityService;

    @Before("")
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testGetAllMunicipalities2xx() {
        Municipality m1 = new Municipality("M-1001", "Copenhagen", true);
        Municipality m2 = new Municipality("M-1002", "Aarhus", true);
        ArrayList<Municipality> municipalityList = new ArrayList<>();
        municipalityList.add(m1);
        municipalityList.add(m2);
        given(municipalityService.fetchAllMunicipalities())
                .willReturn(municipalityList);
        ResponseEntity<?> result = municipalityController.getAllMunicipalities();
        assertThat(result.getStatusCode().is2xxSuccessful());
    }

    @Test
    void testGetAllMunicipalities5xx() {
        given(municipalityService.fetchAllMunicipalities())
                .willThrow(new NullPointerException());
        ResponseEntity<?> result = municipalityController.getAllMunicipalities();
        assertThat(result.getStatusCode().is5xxServerError());
    }
}
