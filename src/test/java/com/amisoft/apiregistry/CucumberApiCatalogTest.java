package com.amisoft.apiregistry;


        import cucumber.api.CucumberOptions;
        import cucumber.api.junit.Cucumber;
        import org.junit.runner.RunWith;
        import org.springframework.test.context.ActiveProfiles;

@RunWith(Cucumber.class)
@ActiveProfiles("test")
@CucumberOptions(features = "src/main/resources/feature", glue="com.amisoft.apiregistry.stepdef")
public class CucumberApiCatalogTest {
}
