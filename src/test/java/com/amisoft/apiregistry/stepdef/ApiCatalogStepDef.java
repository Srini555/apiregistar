package com.amisoft.apiregistry.stepdef;


import com.amisoft.apiregistry.model.ApiRegistryRequest;
import com.amisoft.apiregistry.model.ApiRegistryResponse;
import com.amisoft.apiregistry.service.ApplicationRegistrationService;
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
import static org.junit.Assert.assertThat;


@ContextConfiguration
@SpringBootTest
@ActiveProfiles("test")
public class ApiCatalogStepDef {

    @Autowired
    ApplicationRegistrationService applicationRegistrationService;
    private List<ApiRegistryResponse> actualApiCatalogResponseList = new ArrayList();
    private int count = 0;

    @Given("^xTron restaurant team given the information to register their api as$")
    public void xtron_restaurant_team_given_the_information_to_register_their_api_as(List<ApiRegistryRequest> requestTestDtoList)
            throws Throwable {

        requestTestDtoList.forEach(requestTestDto -> {

            actualApiCatalogResponseList.add(applicationRegistrationService.registerApplicationApi(requestTestDto).get());
        });

    }

    @Then("^Api should be registered as$")
    public void api_should_be_registered_as(List<String> responseDtoExpectedList) throws Throwable {

        actualApiCatalogResponseList.forEach(apiRegistryResponse -> {

            String  apiRegistryExpected = responseDtoExpectedList.get(count);
            List<String> splittedResponse = Arrays.asList(apiRegistryExpected.split(","));

            assertThat(apiRegistryResponse.getApplicationName(), is(splittedResponse.get(0)));
            assertThat(apiRegistryResponse.getApplicationOwner(), is(splittedResponse.get(1)));
            assertThat(apiRegistryResponse.getApplicationOwnerEmail(), is(splittedResponse.get(2)));
            assertThat(apiRegistryResponse.getApplicationApiUrl(), is(splittedResponse.get(3)));
            assertThat(apiRegistryResponse.getMessage(), is(splittedResponse.get(4)));
            count++;
        });

    }
}
