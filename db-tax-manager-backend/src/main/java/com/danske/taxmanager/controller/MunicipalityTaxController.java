package com.danske.taxmanager.controller;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.danske.taxmanager.entity.Municipality;
import com.danske.taxmanager.entity.MunicipalityTaxData;
import com.danske.taxmanager.entity.TaxSchedule;
import com.danske.taxmanager.response.GenericErrorResponse;
import com.danske.taxmanager.response.SuccessResponse;
import com.danske.taxmanager.service.MunicipalityTaxService;
import com.danske.taxmanager.utils. IDGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.sql.Date;
import java.util.HashMap;
import java.util.Optional;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
    
@RestController
    public class MunicipalityTaxController {
        @Autowired
        MunicipalityTaxService municipalityTaxService;
        
        @Autowired
        MunicipalityController municipalityController;
        
        @Autowired
        TaxScheduleController taxScheduleController;
        
        //private DateFormat formatter = new SimpleDateFormat ("dd-MM-yyyy");

        @RequestMapping(value = "/municipality-tax-schedule-data", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
        public ResponseEntity<?> getAllMunicipalityTaxSchedules() {
            try {
                SuccessResponse successResponse;
                int numberOfRecords =
                        Lists.newArrayList(municipalityTaxService.fetchAllMunicipalityTaxData()).size();
                successResponse = new SuccessResponse(200, HttpStatus.OK.toString(),
                        municipalityTaxService.fetchAllMunicipalityTaxData(), numberOfRecords);
                ResponseEntity<SuccessResponse> responseEntity = new ResponseEntity<>(successResponse,
                        HttpStatus.OK);
                return responseEntity;
            }
            catch (Exception e) {
                GenericErrorResponse errorResponse;
                errorResponse = new GenericErrorResponse("ERROR004", e.getLocalizedMessage(), e.getMessage());
                ResponseEntity<GenericErrorResponse> responseEntity = new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
                return responseEntity;
            }

        }

        @RequestMapping(value = "/add-municipality-tax-schedule-data", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE )
        public ResponseEntity<?> addMunicipality(@RequestBody MunicipalityTaxData municipalityTaxScheduleData) {
            try {
                // Will get municipality in input, fetch municipality ID from a query

                boolean has_tax_schedule = false;
                String derivedTaxSchedule = "";
//                Gson gson = new Gson();
//                JsonElement element = gson.fromJson(municipalityTaxScheduleData, JsonElement.class);
//                JsonObject jsonObj = element.getAsJsonObject();

//                Optional<Municipality> municipalityData =
//                        municipalityController.getMunicipalityIdByMunicipalityName(jsonObj.get("municipalityName").getAsString());
                Optional<Municipality> municipalityData =
                        municipalityController.getMunicipalityIdByMunicipalityName(municipalityTaxScheduleData.getMunicipalityName());

                Iterable<TaxSchedule> taxScheduleData =
                        taxScheduleController.getTaxScheduleById(municipalityTaxScheduleData.getTaxScheduleId());

                if (Iterables.size(taxScheduleData) > 0) {
                    derivedTaxSchedule = ((ArrayList<TaxSchedule>) taxScheduleData).get(0).getTaxSchedule();
                    has_tax_schedule = true;
                }

                if (!municipalityData.isEmpty() && has_tax_schedule) {
                    IDGenerator idGenerator = new IDGenerator("Schedule");
                    String municipalityIdFromDB = municipalityData.get().getMunicipalityId();
                    float taxValueFromAPI = municipalityTaxScheduleData.getTaxValue();
                    Date startDate = Date.valueOf(String.valueOf(municipalityTaxScheduleData.getStartDate()));
                    Date endDate = municipalityTaxScheduleData.getEndDate();
                    MunicipalityTaxData municipalityTaxData = municipalityTaxService.saveMunicipalityTaxData(
                            idGenerator.getIdFromType(),
                            municipalityIdFromDB,
                            municipalityTaxScheduleData.getTaxScheduleId(),
                            municipalityTaxScheduleData.getMunicipalityName(),
                            taxValueFromAPI,
                            startDate, endDate, derivedTaxSchedule);
                    SuccessResponse successResponse = new SuccessResponse(202, "Saved successfully",
                            municipalityTaxData.getId(), 1);
                    return new ResponseEntity<>(successResponse, HttpStatus.ACCEPTED);
                } else {
                    GenericErrorResponse errorResponse;
                    errorResponse = new GenericErrorResponse("ERROR006", "No such Municipality exist", "No such Municipality exist");
                    ResponseEntity<GenericErrorResponse> responseEntity = new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
                    return responseEntity;
                }
            } catch (Exception e) {
                GenericErrorResponse errorResponse;
                errorResponse = new GenericErrorResponse("ERROR005", e.getLocalizedMessage(), e.getMessage());
                ResponseEntity<GenericErrorResponse> responseEntity = new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
                return responseEntity;
            }
        }

        @RequestMapping(value = "/municipality/{municipalityName}/tax-schedule-data", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
        public ResponseEntity<?> getScheduleDataByMunicipality(@PathVariable String municipalityName) {
            try {
                SuccessResponse successResponse;
                int numberOfRecords = Lists.newArrayList(municipalityTaxService.fetchAllMunicipalityTaxDataByMunicipalityName(municipalityName)).size();
                successResponse = new SuccessResponse(200, HttpStatus.OK.toString(), municipalityTaxService.fetchAllMunicipalityTaxDataByMunicipalityName(municipalityName), numberOfRecords);
                ResponseEntity<SuccessResponse> responseEntity = new ResponseEntity<>(successResponse, HttpStatus.OK);
                return responseEntity;
            } catch (Exception e) {
                GenericErrorResponse errorResponse;
                errorResponse = new GenericErrorResponse("ERROR004", e.getLocalizedMessage(), e.getMessage());
                ResponseEntity<GenericErrorResponse> responseEntity = new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
                return responseEntity;
            }
        }

        @Operation(summary = "Get Tax rate for a municipality for a given date", description = "Get Tax rate for a municipality for a given date",
            responses = {  @ApiResponse( responseCode = "404", description = "Tax rate not available for the given date",
                    content = { @Content(schema = @Schema(implementation = GenericErrorResponse.class)) }),
                    @ApiResponse( responseCode = "200", description = "Tax rate fetched for the given date",
                            content = { @Content(schema = @Schema(implementation = SuccessResponse.class)) })
        } )
        @RequestMapping(value = "/municipality/{municipalityName}/{date}/tax-schedule", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
        public ResponseEntity<?> getDateSpecificTaxScheduleByMunicipality(@PathVariable String municipalityName, @PathVariable String date) throws ParseException {
            try {

                int numberOfRecords = Lists.newArrayList(municipalityTaxService.fetchAllMunicipalityTaxDataByMunicipalityName(municipalityName)).size();
                if (numberOfRecords > 0) {
                    ArrayList<MunicipalityTaxData> result = (ArrayList<MunicipalityTaxData>) municipalityTaxService.fetchAllMunicipalityTaxDataByMunicipalityName(municipalityName);
                    int result_index = -1;
                    int n = 0;
                    while (n < numberOfRecords) {
                        MunicipalityTaxData d = result.get(n);
                        Date sd = d.getStartDate();
                        Date ed = d.getEndDate();
                        Date dateToCompare = Date.valueOf(date);
                        String tax = d.getTaxSchedule();
                        if (tax.equals("DAILY") && sd.toString().substring(0, 10).equals(date) && ed.toString().substring(0, 10).equals(date)) {
                            result_index = n;
                            break;
                        } else if (tax.equals("WEEKLY") && dateToCompare.after(sd) && dateToCompare.before(ed)) {
                            result_index = n;
                            break;
                        } else if (tax.equals("MONTHLY") && dateToCompare.after(sd) && dateToCompare.before(ed)) {
                            result_index = n;
                            break;
                        } else if (tax.equals("YEARLY") && dateToCompare.after(sd) && dateToCompare.before(ed)) {
                            result_index = n;
                            break;
                        }
                        n++;
                    }
                    if (result_index >= 0) {
                        HashMap<String, String> resultMap = new HashMap<>();
                        resultMap.put("taxSchedule", result.get(result_index).getTaxSchedule());
                        resultMap.put("municipality", result.get(result_index).getMunicipalityName());
                        resultMap.put("tax", String.valueOf(result.get(result_index).getTaxValue()));
                        SuccessResponse successResponse;
                        successResponse = new SuccessResponse(200, HttpStatus.OK.toString(), resultMap, 1);
                        ResponseEntity<SuccessResponse> resultResponse = new ResponseEntity<>(successResponse, HttpStatus.OK);
                        return resultResponse;
                    } else {
                        GenericErrorResponse errorResponse;
                        errorResponse = new GenericErrorResponse("ERROR009", "No data exists for such a combination", "No data exists for this combination");
                        ResponseEntity<GenericErrorResponse> responseEntity = new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
                        return responseEntity;
                    }
                }
                else {
                    GenericErrorResponse errorResponse;
                    errorResponse = new GenericErrorResponse("'ERROR008", "No data exists for this combination", "No data exists for this combination");
                    ResponseEntity<GenericErrorResponse> responseEntity = new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
                    return responseEntity;
                }
            }
            catch (Exception e) {
                GenericErrorResponse errorResponse;
                errorResponse = new GenericErrorResponse("ERROR010", "Invalid Input", "Invalid inputs. Please check the data and try again");
                ResponseEntity<GenericErrorResponse> responseEntity = new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST) ;
                return responseEntity;

            }
        }
}