package com.amisoft.apiregistry.controller;


import com.amisoft.apiregistry.model.ApiRegistryRequest;
import com.amisoft.apiregistry.model.ApiRegistryResponse;
import com.amisoft.apiregistry.service.ApplicationRegistrationService;
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
    public ResponseEntity<ApiRegistryResponse> createNewConsumerAccount(@RequestBody ApiRegistryRequest apiRegistryRequest) {

        Optional<ApiRegistryResponse> apiRegistryResponse = applicationRegistrationService.
                                                    registerApplicationApi(apiRegistryRequest);

        if(apiRegistryResponse.isPresent())
            return new ResponseEntity<>(apiRegistryResponse.get(), HttpStatus.OK);
        else
            return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);

    }


    @GetMapping("/findAllApi")
    public ResponseEntity<List<ApiRegistryResponse>> findAllRegisteredApi(){

        Optional<List<ApiRegistryResponse>> apiRegistryResponses = applicationRegistrationService.findAllRegisteredApi();
        if(apiRegistryResponses.isPresent())
            return new ResponseEntity<>(apiRegistryResponses.get(),HttpStatus.OK);
        else
            return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
    }


}
