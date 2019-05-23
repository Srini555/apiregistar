package com.amisoft.apiregistry.service;


import antlr.StringUtils;
import com.amisoft.apiregistry.entity.ClientRegistation;
import com.amisoft.apiregistry.model.ApiRegistryResponse;
import com.amisoft.apiregistry.model.ClientRegistrationRequest;
import com.amisoft.apiregistry.model.ClientRegistrationResponse;
import com.amisoft.apiregistry.repository.ClientRegistrationRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
public class ApiClientRegistrationService {

    public static final String UNDER_SCORE = "-";
    public static final String EMPTY = "";
    @Autowired
    ClientRegistrationRepository clientRegistrationRepository;

    @Autowired
    ApplicationRegistrationService applicationRegistrationService;

    public Optional<ClientRegistrationResponse> registerClient(ClientRegistrationRequest clientRegistrationRequest){

        Optional<ApiRegistryResponse> apiRegistryResponse = applicationRegistrationService.
                                                   findApiByName(clientRegistrationRequest.getApplicationNameToRegister());

        if(apiRegistryResponse.isPresent()){

            ClientRegistation clientRegistation = new ClientRegistation();
            BeanUtils.copyProperties(clientRegistrationRequest,clientRegistation);
            clientRegistation.setRegistrationKey(UUID.randomUUID().toString().replace(UNDER_SCORE, EMPTY));

            log.info("Registering client "+clientRegistrationRequest.getClientApplicationName()+
                    " for application :"+clientRegistrationRequest.getApplicationNameToRegister());

            ClientRegistation clientRegistrationSaved = clientRegistrationRepository.save(clientRegistation);


            log.info("Registered client "+clientRegistrationRequest.getClientApplicationName()+
                    " for application :"+clientRegistrationRequest.getApplicationNameToRegister());

            ClientRegistrationResponse clientRegistrationResponse = new ClientRegistrationResponse();
            BeanUtils.copyProperties(clientRegistation,clientRegistrationResponse);

            return Optional.of(clientRegistrationResponse);


        }else{

            log.error("Target application not found :"+clientRegistrationRequest.getApplicationNameToRegister());
            return Optional.empty();
        }


    }

}
