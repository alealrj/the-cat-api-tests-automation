import io.midway.core.BaseTests;
import io.midway.request.VotesRequest;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.Test;


public class Votes extends BaseTests {

    VotesRequest votesRequest = new VotesRequest();
    final String VOTES_SCHEMA_POST = "schemas/Votes/PostVotes.json";

    @Test(description = "Enviar Votos")
    public void validation_api_post_votes() {

        ValidatableResponse getConsultarSaldosEKS = votesRequest.postVotes();
        getConsultarSaldosEKS.statusCode(201);
        getConsultarSaldosEKS.body(JsonSchemaValidator.matchesJsonSchemaInClasspath(VOTES_SCHEMA_POST));
    }
}

