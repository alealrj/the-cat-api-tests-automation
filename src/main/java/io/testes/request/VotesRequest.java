package io.testes.request;

import io.testes.core.BaseTests;
import io.testes.core.Constantes;
import io.testes.payloads.VotesPayloads;
import io.testes.report.TestListeners;
import io.restassured.response.ValidatableResponse;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;


public class VotesRequest extends BaseTests {

    TestListeners report = new TestListeners();
    VotesPayloads votesPayloads = new VotesPayloads();

    public ValidatableResponse criarVote() {

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

    public ValidatableResponse pesquiseVoteCriado() {

        String basePath = "v1/votes";

        Map<String, String> header = new HashMap<>();
        header.put("x-api-key", Constantes.X_API_KEY);

        ValidatableResponse response =
                given()
                        .headers(header)
                        .when()
                        .get(basePath)
                        .then()
                        .log().all();

        report.logsExecucaoApi("VOTES", basePath, header.toString(), response);
        return response;
    }

    public ValidatableResponse pesquiseVoteInexistente() {

        String basePath = "v1/votes/0000";

        Map<String, String> header = new HashMap<>();
        header.put("x-api-key", Constantes.X_API_KEY);

        ValidatableResponse response =
                given()
                        .headers(header)
                        .when()
                        .get(basePath)
                        .then()
                        .log().all();

        report.logsExecucaoApi("VOTES", basePath, header.toString(), response);
        return response;
    }

    public ValidatableResponse deleteVoteInexistente() {

        String basePath = "v1/votes/0000";

        Map<String, String> header = new HashMap<>();
        header.put("x-api-key", Constantes.X_API_KEY);

        ValidatableResponse response =
                given()
                        .headers(header)
                        .when()
                        .delete(basePath)
                        .then()
                        .log().all();

        report.logsExecucaoApi("VOTES", basePath, header.toString(), response);
        return response;
    }

    public ValidatableResponse deleteVoteCriado() {

        String basePath = "v1/votes/607353";

        Map<String, String> header = new HashMap<>();
        header.put("x-api-key", Constantes.X_API_KEY);

        ValidatableResponse response =
                given()
                        .headers(header)
                        .when()
                        .delete(basePath)
                        .then()
                        .log().all();

        report.logsExecucaoApi("VOTES", basePath, header.toString(), response);
        return response;
    }
}


