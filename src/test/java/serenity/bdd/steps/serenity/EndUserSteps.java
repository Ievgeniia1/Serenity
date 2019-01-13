package serenity.bdd.steps.serenity;

import io.restassured.response.Response;
import serenity.bdd.EnvironmentPropertyLoader;
import net.thucydides.core.annotations.Step;
import serenity.bdd.pages.Order;

import static net.serenitybdd.rest.SerenityRest.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static serenity.bdd.config.Config.*;

public class EndUserSteps {


    EnvironmentPropertyLoader environmentPropertyLoader;
    Order order;

//    @Step
//    public void enters(String keyword) {
//        dictionaryPage.enter_keywords(keyword);
//    }
//
//    @Step
//    public void starts_search() {
//        dictionaryPage.lookup_terms();
//    }
//
//    @Step
//    public void should_see_definition(String definition) {
//        assertThat(dictionaryPage.getDefinitions(), hasItem(containsString(definition)));
//    }
//
//    @Step
//    public void is_the_home_page() {
//        dictionaryPage.open();
//    }
//
//    @Step
//    public void looks_for(String term) {
//        enters(term);
//        starts_search();
//    }

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
    public void retrievsEnvironment (String keyword){
            environmentPropertyLoader.getProperty("environment.config");
            //put logger here
            EnvironmentPropertyLoader.getLog();
            System.out.println(environmentPropertyLoader.getProperty("environment.config"));
    }
 }


