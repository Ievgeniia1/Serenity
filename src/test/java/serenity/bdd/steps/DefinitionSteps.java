package serenity.bdd.steps;


import io.restassured.response.ValidatableResponse;
import org.assertj.core.api.SoftAssertions;
import io.restassured.response.Response;
import net.thucydides.core.annotations.Steps;

import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;

import serenity.bdd.pages.Order;
import serenity.bdd.steps.serenity.EndUserSteps;

public class DefinitionSteps {

    @Steps

    public static final EndUserSteps STORE_ENDPOINT = new EndUserSteps();
    Response response;
    Order orderResponse;
    private ValidatableResponse json;
    Order order;
    int createdOrderId;

    @Given("I create default order")
    @When("I create default order")
    public void ICreateDefaultOrder(){
        order = Order.placeOrder();
        response = STORE_ENDPOINT.createOrder(order);
        createdOrderId = response.body().as(Order.class).getId();
    }


    @When("I update order")
    public void IUpdateOrder(){
        response = STORE_ENDPOINT.updateOrder(order);
    }

    @Given("I retrieve the order by ID")
    @When("I retrieve the order by ID")
    public void IRetrieveTheOrderByID(){
        orderResponse = STORE_ENDPOINT.getOrderById(createdOrderId).as(Order.class);
    }

    @Given("I get the response by ID")
    @When("I get the response by ID")
    public void IGetTheResponseByID(){
        response = STORE_ENDPOINT.getOrderById(createdOrderId);
    }

    @When("I delete the order by ID")
    public void IDeleteTheOrderByID(){
        response = STORE_ENDPOINT.deleteById(orderResponse.getId());
    }


    @Then("the status code is $code")
    public void IVerifyStatusCode(int code){
        json = response.then().statusCode(code);
    }

    @Then("ID of created order equals ID of default order")
    public void isIdCreatedSameAsIDDefault(){
        SoftAssertions assertions = new SoftAssertions();
        assertions.assertThat(orderResponse.getPetId()).as("Pet ID").isEqualTo(order.getPetId());
        assertions.assertThat(orderResponse.getStatus()).as("Status").isEqualTo(order.getStatus());
        assertions.assertAll();

    }












}