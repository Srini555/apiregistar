package com.amisoft.apiregistry.stepdef;


import com.amisoft.apiregistry.model.ApiRegistryRequest;
import com.amisoft.apiregistry.model.ClientRegistrationRequest;
import com.amisoft.apiregistry.model.ClientRegistrationResponse;
import com.amisoft.apiregistry.service.ApiClientRegistrationService;
import com.amisoft.apiregistry.service.ApplicationRegistrationService;
import cucumber.api.DataTable;
import cucumber.api.PendingException;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

@ContextConfiguration
@SpringBootTest
@ActiveProfiles("test")
public class ApiSubscribeStepDef {

    @Autowired
    ApiClientRegistrationService apiClientRegistrationService;
    @Autowired
    ApplicationRegistrationService applicationRegistrationService;

    List<ClientRegistrationResponse> actualResponseList = new ArrayList<>();

    private int count = 0;



    @Given("^John wants to subscribe for registered api as$")
    public void john_wants_to_subscribe_for_registered_api_as(List<ClientRegistrationRequest> clientRegistrationRequest) throws Throwable {

        ApiRegistryRequest apiRegistryRequest = new ApiRegistryRequest("pcf_coe_api","PCFCOE",
                "amit.datta2@wipro.com","http://localhost:8002/swagger-ui.html");
        applicationRegistrationService.registerApplicationApi(apiRegistryRequest);

        clientRegistrationRequest.forEach(clientRegReq -> {

            actualResponseList.add(apiClientRegistrationService.registerClient(clientRegReq).get());
        });

    }

    @Then("^Client application should be able to subscribe the api$")
    public void client_application_should_be_able_to_subscribe_the_api(List<String> responseExpectedList) throws Throwable {

        actualResponseList.forEach(apiRegistryResponse -> {

            String  apiRegistryExpected = responseExpectedList.get(count);
            List<String> splittedResponse = Arrays.asList(apiRegistryExpected.split(","));

            assertThat(apiRegistryResponse.getClientApplicationName(), is(splittedResponse.get(0)));
            assertThat(apiRegistryResponse.getClientApplicationOwner(), is(splittedResponse.get(1)));
            assertThat(apiRegistryResponse.getApplicationNameToRegister(), is(splittedResponse.get(2)));
            assertThat(apiRegistryResponse.getClientApplicationOwnerEmail(), is(splittedResponse.get(3)));
            assertNotNull(apiRegistryResponse.getRegistrationKey());
            count++;
        });
    }


    @Given("^John wants to search for all registered client$")
    public void john_wants_to_search_for_all_registered_client() throws Throwable {

        actualResponseList.addAll(apiClientRegistrationService.findAllRegisteredClient().get());
    }

    @Given("^John wants to search for all registered client for api as$")
    public void john_wants_to_search_for_all_registered_client_for_api_as(List<String> apiNameList) throws Throwable {

        apiNameList.forEach(apiName->{
            actualResponseList.addAll(apiClientRegistrationService.findRegisteredClientForAPI(apiName).get());
        });

    }

    @Then("^He should get$")
    public void he_should_get(List<String> responseExpectedList) throws Throwable {

        actualResponseList.forEach(apiRegistryResponse -> {

            String  apiRegistryExpected = responseExpectedList.get(count);
            List<String> splittedResponse = Arrays.asList(apiRegistryExpected.split(","));

            assertThat(apiRegistryResponse.getClientApplicationName(), is(splittedResponse.get(0)));
            assertThat(apiRegistryResponse.getClientApplicationOwner(), is(splittedResponse.get(1)));
            assertThat(apiRegistryResponse.getApplicationNameToRegister(), is(splittedResponse.get(2)));
            assertThat(apiRegistryResponse.getClientApplicationOwnerEmail(), is(splittedResponse.get(3)));
            assertNotNull(apiRegistryResponse.getRegistrationKey());
            count++;
        });
    }
}
