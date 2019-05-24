package com.amisoft.apiregistry.controller;


import com.amisoft.apiregistry.model.ApiRegistryRequest;
import com.amisoft.apiregistry.model.ApiRegistryResponse;
import com.amisoft.apiregistry.service.ApplicationRegistrationService;
import com.amisoft.apiregistry.validator.ApplicationValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(value = "*")
public class ApiRegistryController {

    @Autowired
    ApplicationRegistrationService applicationRegistrationService;


    @PostMapping(path = "/registernewapi")
    public ResponseEntity<ApiRegistryResponse> registerApi(@RequestBody ApiRegistryRequest apiRegistryRequest) {


        if (ApplicationValidator.isValidEmailAddress(apiRegistryRequest.getApplicationOwnerEmail())) {

            Optional<ApiRegistryResponse> apiRegistryResponse = applicationRegistrationService.
                    registerApplicationApi(apiRegistryRequest);

            return apiRegistryResponse.map(apiRegistry -> new ResponseEntity<>(apiRegistry,HttpStatus.OK))
                    .orElseGet(()->new ResponseEntity<>(HttpStatus.NOT_FOUND));

        }

        return new ResponseEntity<>(new ApiRegistryResponse("Invalid email address"),HttpStatus.BAD_REQUEST);

    }


    @GetMapping("/findAllApi")
    public ResponseEntity<List<ApiRegistryResponse>> findAllRegisteredApi(){

        Optional<List<ApiRegistryResponse>> apiRegistryResponses = applicationRegistrationService.findAllRegisteredApi();

        return apiRegistryResponses.map(apiRegistry -> new ResponseEntity<>(apiRegistry,HttpStatus.OK))
                .orElseGet(()->new ResponseEntity<>(HttpStatus.NOT_FOUND));

    }

    @GetMapping("/findApiByName")
    public ResponseEntity<ApiRegistryResponse> findApiByName(@RequestParam String name){

        Optional<ApiRegistryResponse> apiRegistryResponse = applicationRegistrationService.findApiByName(name);

        return apiRegistryResponse.map(apiRegistry -> new ResponseEntity<>(apiRegistry,HttpStatus.OK))
                .orElseGet(()->new ResponseEntity<>(HttpStatus.NOT_FOUND));

    }


    @PutMapping(path = "/updateRegisteredApi")
    public ResponseEntity<ApiRegistryResponse> updateApi(@RequestBody ApiRegistryRequest apiRegistryRequest) {

        if (ApplicationValidator.isValidEmailAddress(apiRegistryRequest.getApplicationOwnerEmail())) {

            Optional<ApiRegistryResponse> apiRegistryResponse = applicationRegistrationService.
                    updateApiRegistration(apiRegistryRequest);

            return apiRegistryResponse.map(apiRegistry -> new ResponseEntity<>(apiRegistry,HttpStatus.OK))
                    .orElseGet(()->new ResponseEntity<>(HttpStatus.NOT_FOUND));
        }
        return new ResponseEntity<>(new ApiRegistryResponse("Invalid email address"),HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping(path = "/deleteRegisteredApi")
    public ResponseEntity<ApiRegistryResponse> deleteApi(@RequestBody ApiRegistryRequest apiRegistryRequest) {

        applicationRegistrationService.deleteApiRegistration(apiRegistryRequest);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }



}
