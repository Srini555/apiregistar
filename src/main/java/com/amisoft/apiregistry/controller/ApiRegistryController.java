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
    public ResponseEntity<ApiRegistryResponse> createNewConsumerAccount(@RequestBody ApiRegistryRequest apiRegistryRequest) {


        if (ApplicationValidator.isValidEmailAddress(apiRegistryRequest.getApplicationOwnerEmail())) {

            Optional<ApiRegistryResponse> apiRegistryResponse = applicationRegistrationService.
                    registerApplicationApi(apiRegistryRequest);

            if (apiRegistryResponse.isPresent())
                return new ResponseEntity<>(apiRegistryResponse.get(), HttpStatus.OK);
            else
                return new ResponseEntity<>( HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(new ApiRegistryResponse("Invalid email address"),HttpStatus.BAD_REQUEST);

    }


    @GetMapping("/findAllApi")
    public ResponseEntity<List<ApiRegistryResponse>> findAllRegisteredApi(){

        Optional<List<ApiRegistryResponse>> apiRegistryResponses = applicationRegistrationService.findAllRegisteredApi();
        if(apiRegistryResponses.isPresent())
            return new ResponseEntity<>(apiRegistryResponses.get(),HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/findApiByName")
    public ResponseEntity<ApiRegistryResponse> findApiByName(@RequestParam String name){

        Optional<ApiRegistryResponse> apiRegistryResponse = applicationRegistrationService.findApiByName(name);
        if(apiRegistryResponse.isPresent())
            return new ResponseEntity<>(apiRegistryResponse.get(),HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }





}
