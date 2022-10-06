package io.midway.report;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.CodeLanguage;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import io.midway.core.BaseTests;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.testng.*;

import java.util.HashMap;
import java.util.Map;

import static com.aventstack.extentreports.markuputils.ExtentColor.*;

public class TestListeners implements ITestListener, IInvokedMethodListener {

    private static ExtentReports extent = ExtentManager.getInstance();

    public void gerarLog(String descricaoLog, String mensagem) {
        System.out.println(descricaoLog + ": " + mensagem);
        ExtentTestManager.getTest().info(MarkupHelper.createCodeBlock(descricaoLog + ": " + mensagem));
    }

    public void printLogHashMap(String descricao, HashMap<String, String> log) {
        System.out.println("\n" + descricao.toUpperCase());
        if(log.size() == 0) {
            System.out.println("Nenhum registro encontrado.");
        } else {
            for (Map.Entry<String, String> logarHashMap: log.entrySet()) {
                System.out.println(logarHashMap.getKey() + " - " + logarHashMap.getValue() + " - " );
            }
        }
    }

    public void logsExecucaoApi(String nomeServico, String url, String request, ValidatableResponse response) {

        System.out.println("\n*********************************************************");

        //LOGAR URL
        if(url.contains("k8s")) {
            System.out.println("\nAPI: " + url);
            ExtentTestManager.getTest().info(MarkupHelper.createLabel(nomeServico, BLUE));
            ExtentTestManager.getTest().info(MarkupHelper.createCodeBlock("\nAPI: " + url));
        } else {
            System.out.println("\nAPI: " + BaseTests.APP_BASE_URL + url);
            ExtentTestManager.getTest().info(MarkupHelper.createLabel(nomeServico, BLACK));
            ExtentTestManager.getTest().info(MarkupHelper.createCodeBlock("\nAPI: " + BaseTests.APP_BASE_URL + url));
        }

        //LOGAR REQUEST
        if(request != null) {
            System.out.println("\nREQUEST: \n" + request);
            ExtentTestManager.getTest().info(MarkupHelper.createCodeBlock(request));
        }

        //LOGAR RESPONSE
        Response reportResponse = response.extract().response();
        String log = reportResponse.getBody().asString();
        System.out.println("\nRESPONSE: \n" + log);
        ExtentTestManager.getTest().info(MarkupHelper.createCodeBlock(log, CodeLanguage.JSON));

    }

    @Override
    public void onTestStart(ITestResult result) {
        ExtentTestManager.createTest(result.getMethod().getDescription(), result.getMethod().getDescription());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        ExtentTestManager.getTest().pass(MarkupHelper.createLabel("Cenário executado com sucesso.", GREEN));
    }

    @Override
    public void onTestFailure(ITestResult result) {
        ExtentTestManager.getTest().fail(MarkupHelper.createLabel("Erro na validação.", RED));
        ExtentTestManager.getTest().log(Status.FAIL, result.getThrowable());
    }

    @Override
    public void onTestSkipped(ITestResult result) {

    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        onTestFailure(result);
    }

    @Override
    public void onStart(ITestContext context) {

    }

    @Override
    public void onFinish(ITestContext context) {
        extent.flush();
    }

    @Override
    public void beforeInvocation(IInvokedMethod iInvokedMethod, ITestResult iTestResult) {

    }

    @Override
    public void afterInvocation(IInvokedMethod iInvokedMethod, ITestResult iTestResult) {

    }

}