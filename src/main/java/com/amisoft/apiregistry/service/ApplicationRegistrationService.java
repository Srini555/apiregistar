package com.amisoft.apiregistry.service;

import com.amisoft.apiregistry.entity.ApplicationApi;
import com.amisoft.apiregistry.model.ApiRegistryRequest;
import com.amisoft.apiregistry.model.ApiRegistryResponse;
import com.amisoft.apiregistry.repository.ApplicationApiRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ApplicationRegistrationService {

    @Autowired
    ApplicationApiRepository applicationApiRepository;

    public Optional<ApiRegistryResponse> registerApplicationApi(ApiRegistryRequest apiRegistryRequest){

        ApiRegistryResponse apiRegistryResponse = new ApiRegistryResponse();

        if(findApiByName(apiRegistryRequest.getApplicationName()).isPresent()){

            log.info("Application has already been registered :"+apiRegistryRequest.getApplicationName());
            apiRegistryResponse.setMessage("Application already registered");
        }
        else {

            ApplicationApi applicationApi = new ApplicationApi();
            BeanUtils.copyProperties(apiRegistryRequest, applicationApi);
            applicationApi.setIsActive(Boolean.TRUE);
            log.info("Saving application :" + applicationApi.getApplicationName());
            ApplicationApi applicationApiSaved = applicationApiRepository.save(applicationApi);
            log.info("Saved application :" + applicationApiSaved.getApplicationName());

            BeanUtils.copyProperties(applicationApiSaved, apiRegistryResponse);
            apiRegistryResponse.setMessage("Application saved successfully");
        }

        return Optional.of(apiRegistryResponse);
    }

    public Optional<List<ApiRegistryResponse>> findAllRegisteredApi(){

        List<ApplicationApi> registeredApiList =  applicationApiRepository.findAll();

        log.info("Total number of api found :"+registeredApiList.size());
        List<ApiRegistryResponse> applicationApiResponseList  = registeredApiList.stream().map(api -> convertToresponse(api)).collect(Collectors.toList());
        log.info("Converted to response");

        return Optional.of(applicationApiResponseList);
    }


    public Optional<ApiRegistryResponse> findApiByName(String name){

        log.info("Searching application for name :"+name);
        ApplicationApi applicationApi =  applicationApiRepository.findByApplicationName(name);

        if(null != applicationApi){

            log.info("Application found by name :"+name);
            ApiRegistryResponse apiRegistryResponse = new ApiRegistryResponse();
            BeanUtils.copyProperties(applicationApi,apiRegistryResponse);
            return Optional.of(apiRegistryResponse);
        }

        log.info("No application is registered by name :"+name);
        return Optional.empty();
    }


    public Optional<ApiRegistryResponse> updateApiRegistration(ApiRegistryRequest apiRegistryRequest){

        ApplicationApi applicationApi = applicationApiRepository.findByApplicationName(apiRegistryRequest.getApplicationName());
        ApiRegistryResponse apiRegistryResponse = new ApiRegistryResponse();

        if(null != applicationApi){

            log.info("Registered application :"+apiRegistryRequest.getApplicationName());
            BeanUtils.copyProperties(apiRegistryRequest,applicationApi);
            applicationApiRepository.save(applicationApi);
            log.info("Registered application updated :"+apiRegistryRequest.getApplicationName());

            BeanUtils.copyProperties(applicationApi,apiRegistryResponse);

            return Optional.of(apiRegistryResponse);

        }else{
            return Optional.empty();
        }

    }

    public void deleteApiRegistration(ApiRegistryRequest apiRegistryRequest){

        ApplicationApi applicationApi = applicationApiRepository.findByApplicationName(apiRegistryRequest.getApplicationName());
        ApiRegistryResponse apiRegistryResponse = new ApiRegistryResponse();

        if(null != applicationApi){

            log.info("Registered application :"+apiRegistryRequest.getApplicationName());
            BeanUtils.copyProperties(apiRegistryRequest,applicationApi);
            applicationApi.setIsActive(Boolean.FALSE);
            applicationApiRepository.save(applicationApi);
            log.info("Registered application deleted :"+apiRegistryRequest.getApplicationName());

        }
    }

    private ApiRegistryResponse convertToresponse(ApplicationApi applicationApi){

        ApiRegistryResponse apiRegistryResponse = new ApiRegistryResponse();
        BeanUtils.copyProperties(applicationApi,apiRegistryResponse);
        return apiRegistryResponse;
    }
}
