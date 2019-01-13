package serenity.bdd.steps;


import org.assertj.core.api.Assertions;
import org.assertj.core.api.SoftAssertions;
import io.restassured.response.Response;
import net.thucydides.core.annotations.Steps;

import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;

import serenity.bdd.pages.Order;
import serenity.bdd.pages.Status;
import serenity.bdd.steps.serenity.EndUserSteps;

public class DefinitionSteps {

    @Steps
    //EndUserSteps endUser;
    public static final EndUserSteps STORE_ENDPOINT = new EndUserSteps();

    //Order order;



    @Given("I create hardcoded order")
    public void ICreateMyOrder(){
        Order order = Order.placeOrder();
    }

    @When("I post order with body")
    public void IPostOrderWithBody(){
        Order order = Order.placeOrder();
        STORE_ENDPOINT.createOrder(order);

    }

    @Then("I extract order ID")
    public void IExtractOrderID(){
        Order order = Order.placeOrder();
        int createdOrderId = STORE_ENDPOINT.createOrder(order).body().as(Order.class).getId();
        Order createdFromService = STORE_ENDPOINT.getOrderById(createdOrderId).as(Order.class);
        SoftAssertions assertions = new SoftAssertions();
        assertions.assertThat(createdFromService.getPetId()).as("Pet ID").isEqualTo(order.getPetId());
        assertions.assertThat(createdFromService.getStatus()).as("Status").isEqualTo(order.getStatus());
        assertions.assertAll();
    }

}