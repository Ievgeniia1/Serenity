package serenity.bdd.steps.serenity;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import serenity.bdd.EnvironmentPropertyLoader;
import net.thucydides.core.annotations.Step;
import serenity.bdd.pages.Order;


import io.restassured.specification.RequestSpecification;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static serenity.bdd.config.Config.*;

public class EndUserSteps {


    EnvironmentPropertyLoader environmentPropertyLoader;

    @Step
    public Response getOrderById(int id) {
        return given()
                //.pathParam("orderId",id)
                .when()
                .get(ORDER_ID, id)
                .then().extract().response();
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
                .put(ORDER)
                .then()
                .extract().response();

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
        return RestAssured.given()
                .log().uri()
                .log().body()
                .baseUri(PETSTORE_BASE_URL)
                .contentType(ContentType.JSON);
    }

    @BeforeClass
    public void retrievsEnvironment (String keyword){
            environmentPropertyLoader.getProperty("environment.config");
            EnvironmentPropertyLoader.getLog();

    }
 }


