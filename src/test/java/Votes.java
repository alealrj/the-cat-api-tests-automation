import io.midway.core.BaseTests;
import io.midway.request.VotesRequest;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;


public class Votes extends BaseTests {

    VotesRequest votesRequest = new VotesRequest();
    final String VOTES_CRIAR_SCHEMA_POST = "schemas/Votes/CriarVotes.json";
    final String VOTES_CONSULTAR_SCHEMA_GET = "schemas/Votes/PesquisarVotes.json";

    @Test(description = "Criar Votos")
    public void criar_votes() {

        ValidatableResponse getConsultarSaldosEKS = votesRequest.criarVote();
        getConsultarSaldosEKS.statusCode(201);
        getConsultarSaldosEKS.body(JsonSchemaValidator.matchesJsonSchemaInClasspath(VOTES_CRIAR_SCHEMA_POST));
    }

    @Test(description = "Pesquisar Votos Criado")
    public void pesquisar_votes_criado() {

        ValidatableResponse getConsultarSaldosEKS = votesRequest.pesquiseVoteCriado();
        getConsultarSaldosEKS.statusCode(200);
        getConsultarSaldosEKS.body(JsonSchemaValidator.matchesJsonSchemaInClasspath(VOTES_CONSULTAR_SCHEMA_GET));
    }

    @Test(description = "Pesquisar Votos Inexistente")
    public void pesquisar_votes_inexistente() {

        ValidatableResponse getConsultarSaldosEKS = votesRequest.pesquiseVoteInexistente();
        getConsultarSaldosEKS.statusCode(404);
    }

    @Test(description = "Deletar Votos Inexistente")
    public void deletar_votes_inexistente() {

        ValidatableResponse getConsultarSaldosEKS = votesRequest.deleteVoteInexistente();
        getConsultarSaldosEKS.statusCode(404);
    }

    @Test(description = "Deletar Votos Criado")
    public void deletar_votes_criado() {

        ValidatableResponse getConsultarSaldosEKS = votesRequest.deleteVoteCriado();
        getConsultarSaldosEKS.statusCode(200);
    }
}

