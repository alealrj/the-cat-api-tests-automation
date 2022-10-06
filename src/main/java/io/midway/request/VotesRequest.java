package io.midway.request;

import io.midway.core.BaseTests;
import io.midway.core.Constantes;
import io.midway.payloads.VotesPayloads;
import io.midway.report.TestListeners;
import io.restassured.response.ValidatableResponse;

import java.util.HashMap;
import java.util.Map;
import static io.restassured.RestAssured.given;


public class VotesRequest extends BaseTests {

    TestListeners report = new TestListeners();
    VotesPayloads votesPayloads = new VotesPayloads();

    public ValidatableResponse postVotes() {

        String basePath = "v1/votes";
        String payload = votesPayloads.postVote();

        Map<String, String> header = new HashMap<>();
        header.put("x-api-key", Constantes.X_API_KEY);

        ValidatableResponse response =
                given()
                        .headers(header)
                        .body(payload)
                        .when()
                        .post(basePath)
                        .then()
                        .log().all();

        report.logsExecucaoApi("VOTES", basePath, header.toString(), response);
        return response;
    }
}

