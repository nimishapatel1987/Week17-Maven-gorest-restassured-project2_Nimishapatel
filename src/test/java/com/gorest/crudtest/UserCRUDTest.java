package com.gorest.crudtest;

import com.gorest.model.postsPojo;
import com.gorest.testbase.TestBase;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class UserCRUDTest extends TestBase {

    @Test
    public void verifyUserCreatedSuccessfully() {
        postsPojo productPojo = new postsPojo();
        productPojo.setName("Pari Patel");
        productPojo.setEmail("paripatel123@gmail.com");
        productPojo.setGender("female");
        productPojo.setStatus("Active");

        Response response = given().log().all()
                .header("content-Type", "application/json")
                .header("Authorization", "Bearer 600f4364266ef9256401822c412cbfa2a4fe3c13c5c708bf2206cbb120f2a4c9")
                .body(productPojo)
                .when()
                .post("/users");
        response.prettyPrint();
        response.then().log().all().statusCode(201);
    }

    @Test
    public void verifyUserUpdatedSuccessfully() {
        postsPojo productPojo = new postsPojo();
        productPojo.setName("Pari Patel");
        productPojo.setEmail("paripatel123456@gmail.com");
        productPojo.setGender("female");
        productPojo.setStatus("Active");

        Response response = given().log().all()
                .header("content-Type", "application/json")
                .header("Authorization", "Bearer 600f4364266ef9256401822c412cbfa2a4fe3c13c5c708bf2206cbb120f2a4c9")
                .body(productPojo)
                .when()
                .patch("/users/10606");
        response.prettyPrint();
        response.then().log().all().statusCode(200);
    }

    @Test
    public void verifyUserDeleteSuccessfully() {
        Response response = given()
                .header("content-Type", "application/json")
                .header("Authorization", "Bearer 600f4364266ef9256401822c412cbfa2a4fe3c13c5c708bf2206cbb120f2a4c9")
                .when()
                .delete("/users/10606");
        response.prettyPrint();
        response.then().statusCode(204);
    }
}
