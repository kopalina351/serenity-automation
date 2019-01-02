package serenity.bdd.steps.serenity;

import config.Config;
import environment.EnvironmentPropertyLoader;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import models.Order;
import models.Pet;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;
import org.apache.http.HttpHeaders;
import serenity.bdd.pages.DictionaryPage;

import java.io.IOException;

import static config.Config.PETSTORE_BASE_URL;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.hasItem;

public class EndUserSteps {

    DictionaryPage dictionaryPage;

    @Step
    public void enters(String keyword) {
        dictionaryPage.enter_keywords(keyword);
    }

    @Step
    public void starts_search() {
        dictionaryPage.lookup_terms();
    }

    @Step
    public void should_see_definition(String definition) {
        assertThat(dictionaryPage.getDefinitions(), hasItem(containsString(definition)));
    }

    @Step
    public void is_the_home_page() {
        dictionaryPage.open();
    }

    @Step
    public void looks_for(String term) {
        enters(term);
        starts_search();
    }

    @Step("Returns pet inventories by status")
    public Response getStoreInventory() {
        return given().when().get(Config.STORE_INVENTORY).then().extract().response();
    }

    @Step("Find purchase order by ID")
    public Response getStoreOrderByOrderId(long id) {
        return RestAssured.given().pathParam("orderId", id).get(Config.STORE_ORDER_BY_ID).then().extract().response();
    }

    @Step("Don't Find purchase order by ID")
    public Response getNotStoreOrderByOrderId(long id) {
        return (Response) RestAssured.given().pathParam("orderId", id).get(Config.STORE_ORDER_BY_ID).then().statusCode(404).extract().response();
    }


    @Step("Place an order for a pet")
    public Response postStoreOrder(Order order) {
        return given().body(order).when().post(Config.CREATE_ORDER).then().extract().response();
    }

    @Step("Delete purchase order by ID")
    public Response deleteStoreOrderByOrderId(long id) {
        return RestAssured.given().when().delete(Config.STORE_ORDER_BY_ID, id).then().extract().response();
    }

    @Step("get Pet by Id")
    public Response getPetById(String id) {
        return given().pathParam("petId", id).when().get(Config.PET_BY_ID).then().extract().response();
    }

    @Step("get Pet by Status")
    public Response getPetByStatus(String status) {
        return given().queryParam("status", status).when().get(Config.FIND_BY_STATUS).then().extract().response();
    }


    @Step("create Pet")
    public Response createPet(Pet pet) {
        return given().body(pet).when().post(Config.CREATE_PET).then().extract().response();
    }

    @Step("update Pet")
    public Response updatePet(Pet pet) {
        return given().body(pet).when().put(Config.CREATE_PET).then().extract().response();
    }

    @Step("delete Pet by Id {}")
    public Response deleteById(long id) {
        return RestAssured.given().when().delete(Config.PET_BY_ID, id).then().extract().response();
    }

    private RequestSpecification given() {
        return SerenityRest.given().baseUri(PETSTORE_BASE_URL).header(HttpHeaders.CONTENT_TYPE, "application/json");
    }

    public static EnvironmentPropertyLoader environmentPropertyLoader() throws IOException {

        EnvironmentPropertyLoader.getProperties("env1.properties");
        return environmentPropertyLoader();
    }


}