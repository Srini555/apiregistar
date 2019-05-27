package com.amisoft.apiregistry.stepdef;


import com.amisoft.apiregistry.model.ApiRegistryRequest;
import com.amisoft.apiregistry.model.ApiRegistryResponse;
import com.amisoft.apiregistry.model.ApiRegistryResponseDelete;
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
    private List<ApiRegistryResponseDelete> actualApiCatalogResponseDeleteList = new ArrayList();
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


    @Given("^xTron restaurant team already registered restaurant_account and trying to register again$")
    public void xtron_restaurant_team_already_registered_restaurant_account_and_trying_to_register_again(List<ApiRegistryRequest> requestTestDtoList)
            throws Throwable {

        requestTestDtoList.forEach(requestTestDto -> {

            actualApiCatalogResponseList.add(applicationRegistrationService.registerApplicationApi(requestTestDto).get());
        });
    }

    @Then("^Api should not be registered$")
    public void api_should_not_be_registered(List<String> responseDtoExpectedList) throws Throwable {

        actualApiCatalogResponseList.forEach(apiRegistryResponse -> {

            String  apiRegistryExpected = responseDtoExpectedList.get(count);
            assertThat(apiRegistryResponse.getMessage(), is(apiRegistryExpected));
            count++;
        });
    }


    @Given("^John wants to use xTron registered api and searching for registered api$")
    public void john_wants_to_use_xTron_registered_api() throws Throwable {

        actualApiCatalogResponseList.addAll(applicationRegistrationService.findAllRegisteredApi().get());
    }

    @Given("^John is searching for api as$")
    public void john_is_searching_for_api_as(List<String> aliNameList) throws Throwable {

        aliNameList.forEach(apiName -> {

            actualApiCatalogResponseList.add(applicationRegistrationService.findApiByName(apiName).get());
        });
    }

    @Then("^John should get registered Api as$")
    public void john_should_get_registered_Api_as(List<String> responseDtoExpectedList) throws Throwable {

        actualApiCatalogResponseList.forEach(apiRegistryResponse -> {

            String  apiRegistryExpected = responseDtoExpectedList.get(count);
            List<String> splittedResponse = Arrays.asList(apiRegistryExpected.split(","));

            assertThat(apiRegistryResponse.getApplicationName(), is(splittedResponse.get(0)));
            assertThat(apiRegistryResponse.getApplicationOwner(), is(splittedResponse.get(1)));
            assertThat(apiRegistryResponse.getApplicationOwnerEmail(), is(splittedResponse.get(2)));
            assertThat(apiRegistryResponse.getApplicationApiUrl(), is(splittedResponse.get(3)));
            count++;
        });
    }


    @Given("^xTron team wants to update api as$")
    public void xtron_team_wants_to_update_api_as(List<ApiRegistryRequest> requestTestDtoList) throws Throwable {

        requestTestDtoList.forEach(requestTestDto -> {

            actualApiCatalogResponseList.add(applicationRegistrationService.updateApiRegistration(requestTestDto).get());
        });
    }

    @Then("^API should be updated as$")
    public void api_should_be_updated_as(List<String> responseDtoExpectedList) throws Throwable {
        actualApiCatalogResponseList.forEach(apiRegistryResponse -> {

            String  apiRegistryExpected = responseDtoExpectedList.get(count);
            List<String> splittedResponse = Arrays.asList(apiRegistryExpected.split(","));

            assertThat(apiRegistryResponse.getApplicationName(), is(splittedResponse.get(0)));
            assertThat(apiRegistryResponse.getApplicationOwner(), is(splittedResponse.get(1)));
            assertThat(apiRegistryResponse.getApplicationOwnerEmail(), is(splittedResponse.get(2)));
            assertThat(apiRegistryResponse.getApplicationApiUrl(), is(splittedResponse.get(3)));
            count++;
        });
    }


    @Given("^xTron team wants to delete api as$")
    public void xtron_team_wants_to_delete_api_as(List<ApiRegistryRequest> requestTestDtoList) throws Throwable {

        requestTestDtoList.forEach(requestTestDto -> {

            actualApiCatalogResponseDeleteList.add(applicationRegistrationService.deleteApiRegistration(requestTestDto).get());
        });
    }

    @Then("^API should be set inactive  as$")
    public void api_should_be_set_inactive_as(List<String> responseDtoExpectedList) throws Throwable {

        actualApiCatalogResponseDeleteList.forEach(apiRegistryResponse -> {

            String  apiRegistryExpected = responseDtoExpectedList.get(count);
            List<String> splittedResponse = Arrays.asList(apiRegistryExpected.split(","));

            assertThat(apiRegistryResponse.getApplicationName(), is(splittedResponse.get(0)));
            assertThat(apiRegistryResponse.getApplicationOwner(), is(splittedResponse.get(1)));
            assertThat(apiRegistryResponse.getApplicationOwnerEmail(), is(splittedResponse.get(2)));
            assertThat(apiRegistryResponse.getApplicationApiUrl(), is(splittedResponse.get(3)));
            assertThat(apiRegistryResponse.getMessage(),is(splittedResponse.get(4)));
            assertThat(apiRegistryResponse.getIsActive(),is(Boolean.valueOf(splittedResponse.get(5))));

            count++;
        });
    }

}
