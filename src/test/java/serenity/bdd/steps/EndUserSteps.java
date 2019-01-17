package serenity.bdd.steps;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.BeforeClass;
import net.thucydides.core.annotations.Step;
import serenity.bdd.models.Order;


import io.restassured.specification.RequestSpecification;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItem;
import static serenity.bdd.config.Config.*;

public class EndUserSteps {




    @Step
    public Response getOrderById(int id) {
         return given()
                .pathParam("orderId",id)
                .when()
                .get(ORDER_ID, id);
    }

    @Step
    public Response getInventory() {
        return given()
                .when()
                .get(STORE_INVENTORY)
                .then().extract().response();
    }


    @Step
    public Response createOrder(Order order) {

        return given()
                .body(order)
                .when()
                .post(ORDER)
                .then()
                .extract().response();

    }


    @Step
    public Response updateOrder(Order order){

        return given()
                .body(order)
                .when()
                .put(ORDER).then().extract().response();

    }


    @Step
    public Response deleteById(int id){

        return given()
//                .pathParam("orderId",id)
                .delete(ORDER_ID, id)
                .then()
                .extract().response();

    }

    @Step
    private RequestSpecification given() {
        System.getProperty("environment.config");
        return RestAssured.given()
                .log().uri()
                .log().body()
                .baseUri(PETSTORE_BASE_URL)
                .contentType(ContentType.JSON);

    }


 }


