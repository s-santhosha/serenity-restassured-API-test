package testSuiteAuspost.pages;

import base.BaseTest;
import io.restassured.RestAssured;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.ResponseSpecification;
import java.util.List;
import io.restassured.http.ContentType;


public class APIHelpers extends BaseTest {

    protected ResponseSpecification checkStatusCodeAndContentType =
            new ResponseSpecBuilder().
                    expectStatusCode(200).
                    expectContentType(ContentType.JSON).
                    build();

    public int getPostalCode(String area, String state) {
        return RestAssured.given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("AUTH-KEY", apiKey)
                .formParam("q", area)
                .formParam("state", state)

                .when()
                .get(ausPostURL + "/postcode/search")

                .then()
                .spec(checkStatusCodeAndContentType)
                .extract().response().jsonPath().getInt("localities.locality.postcode");
    }

    public List<String> getDeliveryCodes(int sourcePostcode, int destinationPostcode) {
        return RestAssured.given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("AUTH-KEY", apiKey)
                .formParam("from_postcode", sourcePostcode)
                .formParam("to_postcode", destinationPostcode)
                .formParam("length", 30)
                .formParam("width", 22)
                .formParam("height", 10)
                .formParam("weight", 5)

                .when()
                .get(ausPostURL + "/postage/parcel/domestic/service")

                .then()
                .spec(checkStatusCodeAndContentType)
                .extract().response().jsonPath().getList("services.service.code");

    }

    public String calculateShippingCost(int sourcePostcode, int destinationPostcode, String serviceCode) {
        return RestAssured.given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("AUTH-KEY", apiKey)
                .formParam("from_postcode", sourcePostcode)
                .formParam("to_postcode", destinationPostcode)
                .formParam("length", 30)
                .formParam("width", 22)
                .formParam("height", 10)
                .formParam("weight", 5)
                .formParam("service_code", serviceCode)

                .when()
                .get(ausPostURL + "/postage/parcel/domestic/calculate")

                .then()
                .spec(checkStatusCodeAndContentType)
                .extract().response().jsonPath().getString("postage_result.total_cost");
    }


}
