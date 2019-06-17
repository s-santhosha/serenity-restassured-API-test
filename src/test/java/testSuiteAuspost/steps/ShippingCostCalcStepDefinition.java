package testSuiteAuspost.steps;

import base.BaseTest;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import org.junit.Assert;
import testSuiteAuspost.pages.APIHelpers;

import java.io.IOException;
import java.util.List;
import java.util.Random;


public class ShippingCostCalcStepDefinition extends BaseTest {

    public APIHelpers apiHelpers = new APIHelpers();
    public int sourcePostCode;
    public int destinationPostCode;
    public String randomDeliveryService;
    public String totalShipmentCost;


    @Before
    public void before() throws IOException {
        setup();
    }

    @Given("^User retrieves the postcode for the source and destination$")
    public void user_retrieves_the_postcode_for_the_source_and_destination() {
        sourcePostCode = apiHelpers.getPostalCode(sourceArea, sourceState);
        Assert.assertEquals(sourcePostCode, 2138);
        destinationPostCode = apiHelpers.getPostalCode(destinationArea, destinationState);
        Assert.assertEquals(destinationPostCode, 2016);
    }


    @When("^User confirms the delivery type$")
    public void selectDeliveryType() {
        List<String> deliveryServiceCodes = apiHelpers.getDeliveryCodes(sourcePostCode, destinationPostCode);

        Random rand = new Random();
        int randomServiceCode = rand.nextInt(deliveryServiceCodes.size());
        randomDeliveryService = deliveryServiceCodes.get(randomServiceCode);

    }

    @Then("^Calculate the total shipping cost value$")
    public void calculateTotalShipmentCost() {
        totalShipmentCost = apiHelpers.calculateShippingCost(sourcePostCode, destinationPostCode, randomDeliveryService);
        if (randomDeliveryService.equalsIgnoreCase("AUS_PARCEL_COURIER")) {
            Assert.assertEquals(totalShipmentCost, "34.95");
        } else if (randomDeliveryService.equalsIgnoreCase("AUS_PARCEL_EXPRESS")) {
            Assert.assertEquals(totalShipmentCost, "15.50");
        } else if (randomDeliveryService.equalsIgnoreCase("AUS_PARCEL_EXPRESS_SATCHEL_5KG")) {
            Assert.assertEquals(totalShipmentCost, "24.90");
        } else if (randomDeliveryService.equalsIgnoreCase("AUS_PARCEL_REGULAR")) {
            Assert.assertEquals(totalShipmentCost, "11.05");
        }

    }

}




