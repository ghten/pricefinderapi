package com.inditex.pricefinderapi.steps;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.format.DateTimeFormatter;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientException;

import com.inditex.pricefinderapi.application.dto.PriceDTO;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.spring.CucumberContextConfiguration;
import lombok.RequiredArgsConstructor;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@CucumberContextConfiguration
@RequiredArgsConstructor
public class PriceFinderSteps {

    private final TestRestTemplate restTemplate;

    private ResponseEntity<PriceDTO> response;
    private ResponseEntity<String> errorResponse;


    @Given("Service is running")
    public void ServiceIsRunning() {
        // This step doesn't need implementation
    }

    @When("I request price for the product {long} of the brand {long} on date {string}")
    public void requestPriceForProductOfBrandOnDate(Long productId, Long brandId, String applicationDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;
        String url = String.format("/prices?productId=%d&brandId=%d&applicationDate=%s", productId, brandId, applicationDate);
        response = restTemplate.getForEntity(url, PriceDTO.class);
    }

    @Then("returned price must be {double}")
    public void ReturnedPriceMustBe(double expectedPrice) {
        PriceDTO price = response.getBody();
        assertNotNull(price, "Response body is null");
        assertEquals(expectedPrice, price.getAmount(), 0.01, "Expected price not found in the returned price");
    }

    @When("I request price for the product {long} of the brand {long} on date {string} and service throws exception")
    public void requestPriceForProductOfBrandOnDateAndServiceThrowsException(Long productId, Long brandId, String applicationDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;
        String url = String.format("/prices?productId=%d&brandId=%d&applicationDate=%s", productId, brandId, applicationDate);
        try {
            errorResponse = restTemplate.getForEntity(url, String.class);
        } catch (RestClientException e) {
            errorResponse = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @Then("the response status should be {int}")
    public void theResponseStatusShouldBe(int expectedStatus) {
        assertEquals(expectedStatus, errorResponse.getStatusCode().value());
    }
}
