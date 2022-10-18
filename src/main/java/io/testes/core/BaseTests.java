package io.testes.core;

import io.testes.report.TestListeners;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;

import static org.hamcrest.Matchers.lessThan;

@Listeners(TestListeners.class)
public class BaseTests implements Constantes {

    @BeforeClass
    public static void setup() {

        RestAssured.baseURI = APP_BASE_URL;

        RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder();
        requestSpecBuilder.setContentType(APP_CONTENT_TYPE);
        RestAssured.requestSpecification = requestSpecBuilder.build();

        ResponseSpecBuilder responseSpecBuilder = new ResponseSpecBuilder();
        responseSpecBuilder.expectResponseTime(lessThan(MAX_TIMEOUT));
        RestAssured.responseSpecification = responseSpecBuilder.build();

        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }
}
