package framework.util;

import framework.items.Food;
import framework.items.FoodType;
import framework.managers.PropManager;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import java.util.List;
import static framework.util.Props.BASE_URL;
import static io.restassured.RestAssured.given;

public class RESTCallers {

    //BASE URI CONTAINS SLASH AT THE END!!!
    private static final String baseURI = PropManager.getPropInstance().getProp(BASE_URL);

    private static RequestSpecification reqSpec(String url, ContentType cType) {
        return new RequestSpecBuilder()
                .setBaseUri(url)
                .setContentType(cType)
                .build();
    }

    private static RequestSpecification reqSpec(String url) {
        return new RequestSpecBuilder()
                .setBaseUri(url)
                .build();
    }

    private static ResponseSpecification resSpec(int statusCode) {
        return new ResponseSpecBuilder()
                .expectStatusCode(statusCode)
                .build();
    }

    public static void installSpecs(ContentType cType, int statusCode) {
        RestAssured.requestSpecification = reqSpec(baseURI, cType);
        RestAssured.responseSpecification = resSpec(statusCode);
    }

    public static void installSpecs(int statusCode) {
        RestAssured.requestSpecification = reqSpec(baseURI);
        RestAssured.responseSpecification = resSpec(statusCode);
    }


    public static List<Food> getItemList(ContentType cType, int statusCode) {
        installSpecs(cType, statusCode);
        List<Food> f = given()
                .basePath("api/")
                .get("food")
                .then()
                .extract()
                .jsonPath().getList("", Food.class);
        return f;
    }

    public static void addItem(String name, String type, boolean exotic, int statusCode) {
        installSpecs(statusCode);

        String sBody = String.format("{\n" +
                "    \"name\": \"%s\",\n" +
                "    \"type\": \"%2s\",\n" +
                "    \"exotic\": %3s\n" +
                "}", name, type, exotic);
        given()
                .body(sBody)
                .basePath("api/food")
                .contentType(ContentType.JSON)
                .post();
    }

    public static void resetData(int statusCode) {
        installSpecs(statusCode);
        given()
                .basePath("api/")
                .post("data/reset");
    }
}
