package serenity.bdd.steps.serenity;

import config.Config;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import models.Order;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;
import org.apache.http.HttpHeaders;

public class StoreOrderPetEndpoint {

    @Step("Returns pet inventories by status")
    public Response getStoreInventory(){
        return given()
                .when()
                .get(Config.STORE_INVENTORY)
                .then()
                .extract().response();
    }

    @Step("Find purchase order by ID")
    public Response getStoreOrderByOrderId(long id){
        return given()
                .pathParam("orderId", id)
                .when()
                .get(Config.STORE_ORDER_BY_ID)
                .then()
                .extract().response();
    }


    @Step("Place an order for a pet")
    public Response postStoreOrder(Order order){
        return given()
                .body(order)
                .when()
                .post(Config.CREATE_ORDER)
                .then()
                .extract().response();
    }

    @Step("Delete purchase order by ID")
    public Response deleteStoreOrderByOrderId(long id){
        return given()
                .when()
                .delete(Config.STORE_ORDER_BY_ID,id)
                .then()
                .extract().response();
    }

    private RequestSpecification given() {
        return SerenityRest.given()
                .baseUri(Config.PETSTORE_BASE_URL)
                .header(HttpHeaders.CONTENT_TYPE, "application/json");
    }

}
