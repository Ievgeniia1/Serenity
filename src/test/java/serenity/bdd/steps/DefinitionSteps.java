package serenity.bdd.steps;


import io.restassured.response.ValidatableResponse;
import net.serenitybdd.core.Serenity;
import org.assertj.core.api.SoftAssertions;
import io.restassured.response.Response;
import net.thucydides.core.annotations.Steps;

import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;

import org.junit.Assert;
import serenity.bdd.models.Order;

import static org.hamcrest.Matchers.equalTo;

public class DefinitionSteps {


    @Steps

    public EndUserSteps storeEndpoint = new EndUserSteps();

    @Given("I create default order")
    @When("I create default order")
    public void iCreateDefaultOrder(){
        Order order = Order.placeOrder();
        Response response = storeEndpoint.createOrder(order);
        int id = response.body().as(Order.class).getId();
        Serenity.setSessionVariable("lastCreatedOrderID").to(id);
        Serenity.setSessionVariable("lastOperationCode").to(response.statusCode());
    }


    @When("I set quantity $quantity to created order")
    public void iUpdateOrderQuantity(int quantity){
        Order order = Order.placeOrder();
        order.setQuantity(quantity);
        Response response = storeEndpoint.updateOrder(order);
        Serenity.setSessionVariable("lastOperationCode").to(response.statusCode());
    }

    @Given("The status code is $code")
    @Then("The status code is $code")
        public void iVerifyStatusCode(int code){
        int lastOperationCode = Serenity.sessionVariableCalled("lastOperationCode");
        Assert.assertEquals(code,lastOperationCode);
    }



    @When("I delete order by id")
    public void iDeleteOrderById(){
        Response response = storeEndpoint.deleteById(Serenity.sessionVariableCalled("lastCreatedOrderID"));
        Serenity.setSessionVariable("lastOperationCode").to(response.statusCode());
    }


    @Given("I get order by id")
    @When("I get order by id")
    public void iLookForCreatedOrderById(){
       Response response = storeEndpoint.getOrderById(Serenity.sessionVariableCalled("lastCreatedOrderID"));
        Serenity.setSessionVariable("lastOperationCode").to(response.statusCode());
    }


    @Then("Parameters (ID,Status) of created order equals parameters  of default order")
    public void isIdCreatedSameAsIDDefault(){
        Order order = Order.placeOrder();
        int createdID = Serenity.sessionVariableCalled("lastCreatedOrderID");
        Order orderResponse = storeEndpoint.getOrderById(createdID).as(Order.class);
        SoftAssertions assertions = new SoftAssertions();
        assertions.assertThat(orderResponse.getPetId()).as("Pet ID").isEqualTo(order.getPetId());
        assertions.assertThat(orderResponse.getStatus()).as("Status").isEqualTo(order.getStatus());
        assertions.assertAll();

    }






}