package com.inditex.pricefinderapi.steps;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.format.DateTimeFormatter;
import java.util.Arrays;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;

import com.inditex.pricefinderapi.api.dto.PriceDTO;
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

    private ResponseEntity<PriceDTO[]> response;


    @Given("Service is running")
    public void ServiceIsRunning() {
        // This step doesn't need implementation
    }

    @When("I request price for the product {long} of the brand {long} on date {string}")
    public void requestPriceForProductOfBrandOnDate(Long productId, Long brandId, String applicationDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;
        String url = String.format("/prices?productId=%d&brandId=%d&applicationDate=%s", productId, brandId, applicationDate);
        response = restTemplate.getForEntity(url, PriceDTO[].class);
    }

    @Then("returned price must be {double}")
    public void ReturnedPriceMustBe(double expectedPrice) {
        PriceDTO[] prices = response.getBody();
        boolean priceFound = Arrays.stream(prices)
                                   .anyMatch(price -> Math.abs(price.getAmount() - expectedPrice) < 0.01);

        assertTrue(priceFound, "Expected price not found in the returned prices");
    }
}
