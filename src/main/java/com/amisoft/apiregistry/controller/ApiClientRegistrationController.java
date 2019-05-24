package com.amisoft.apiregistry.controller;


import com.amisoft.apiregistry.model.ClientRegistrationRequest;
import com.amisoft.apiregistry.model.ClientRegistrationResponse;
import com.amisoft.apiregistry.service.ApiClientRegistrationService;
import com.amisoft.apiregistry.validator.ApplicationValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
        return registeredClientResponse.map(clientRegistrationResponses -> new ResponseEntity<>(clientRegistrationResponses, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/findClientByApplicationName")
    public ResponseEntity <List<ClientRegistrationResponse>> findRegisteredClientForApp(@RequestParam String name){

        Optional<List<ClientRegistrationResponse>> registeredClientList = apiClientRegistrationService.findRegisteredClientForAPI(name);

        return registeredClientList.map(registeredClient -> new ResponseEntity<>(registeredClient,HttpStatus.OK))
                .orElseGet(()->new ResponseEntity<>(HttpStatus.NOT_FOUND));

    }





}
