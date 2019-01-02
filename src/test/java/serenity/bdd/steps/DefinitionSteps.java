package serenity.bdd.steps;

import models.Order;
import net.thucydides.core.annotations.Steps;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;

import serenity.bdd.steps.serenity.EndUserSteps;
import serenity.bdd.steps.serenity.StoreOrderPetEndpoint;

public class DefinitionSteps {

    @Steps
    EndUserSteps endUser;
    StoreOrderPetEndpoint storeOrderPetEndpoint;
    private Object order;

    @Given("the user is on the Wikionary home page")
    public void givenTheUserIsOnTheWikionaryHomePage() {
        endUser.is_the_home_page();
    }

    @Given("the order is created")
    public void givenTheOrderIsCreated(){
        Order.createOrder();
    }

    @When("the user finds purchase order by $ID")
    public void whenTheUserFindsPurchaseOrderById(final long id){
        endUser.getStoreOrderByOrderId(id);
    }


    @Then("the order by $ID not found")
    public void thenTheUserDontFindPurchaseOrderById(final long id){
        endUser.getNotStoreOrderByOrderId(id);
    }

    @When("the user deletes purchase order by $ID")
    public void whenTheUserDeletesPurchaseOrderById(final long id){
        endUser.deleteStoreOrderByOrderId(id);
    }

    @When("the user looks up the definition of the word '$word'")
    public void whenTheUserLooksUpTheDefinitionOf(String word) {
        endUser.looks_for(word);
    }

    @Then("they should see the definition '$definition'")
    public void thenTheyShouldSeeADefinitionContainingTheWords(String definition) {
        endUser.should_see_definition(definition);
    }


}
