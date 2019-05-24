package com.amisoft.apiregistry.controller;


import com.amisoft.apiregistry.model.ClientRegistrationRequest;
import com.amisoft.apiregistry.model.ClientRegistrationResponse;
import com.amisoft.apiregistry.service.ApiClientRegistrationService;
import com.amisoft.apiregistry.validator.ApplicationValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class ApiClientRegistrationController {

    @Autowired
    ApiClientRegistrationService apiClientRegistrationService;


    @PostMapping(path = "/registerClientForApplication")
    public ResponseEntity<ClientRegistrationResponse> createNewConsumerAccount(@RequestBody ClientRegistrationRequest clientRegistrationRequest) {


        if (ApplicationValidator.isValidEmailAddress(clientRegistrationRequest.getClientApplicationOwnerEmail())) {

            Optional<ClientRegistrationResponse> clientRegistryResponse = apiClientRegistrationService.
                    registerClient(clientRegistrationRequest);

            if (clientRegistryResponse.isPresent())
                return new ResponseEntity<>(clientRegistryResponse.get(), HttpStatus.OK);
            else
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(new ClientRegistrationResponse("Invalid email address"),HttpStatus.BAD_REQUEST);

    }

    @GetMapping("/findAllRegisteredClient")
    public ResponseEntity<List<ClientRegistrationResponse>> findAllRegisteredClient(){

        Optional<List<ClientRegistrationResponse>> registeredClientResponse = apiClientRegistrationService.findAllRegisteredClient();
        if(registeredClientResponse.isPresent())
            return new ResponseEntity<>(registeredClientResponse.get(),HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }





}
