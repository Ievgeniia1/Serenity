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
    EndUserSteps endUser;
    Order order;
    //Response updateResponse;

    public static final Order STORE_ENDPOINT = new Order();

    @Given("I start with created order")
    public void givenTheOrderIsCreated() {
        order = Order.placeOrder();
        order.setStatus(Status.DELIVERED);
    }

    @When("I run put request to update order")
    public void whenIRunPutRequest() {
        endUser.updateOrder(order);
    }
//
//
    @Then("I  get response $response")
    public void thenIGetResponse(int response) {
        Assertions.assertThat(updateResponse.getStatusCode()).isEqualTo(response);
    }

}
