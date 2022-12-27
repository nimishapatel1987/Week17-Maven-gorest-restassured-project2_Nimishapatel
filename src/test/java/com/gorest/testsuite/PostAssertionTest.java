package com.gorest.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.Matchers.equalTo;

public class PostAssertionTest {
    static ValidatableResponse response;

    @BeforeClass
    public static void inIt() {
        RestAssured.baseURI = "https://gorest.co.in/public/v2";

        response = given()
                .when()
                .queryParam("page", "1")
                .queryParam("per_page", "20")
                .get("/users?page=1&per_page=20")
                .then().statusCode(200);
    }
    //1.Verify the if the total record is 25
    @Test
    public void test1() {
        response.body("total", equalTo(25));
    }

    //2.Verify the if the title of id = 2730 is equal to ”Ad ipsa coruscus ipsam eos demitto centum.”
    @Test
    public void test2() {
        response.body("[2].title",equalTo("Ad ipsa coruscus ipsam eos demitto centum"));
    }
    //3. Check the single user_id in the Array list (5522)
    @Test
    public void test3() {
        response.body("[4].user_id",equalTo(5522));
    }
    //4.Check the multiple ids in the ArrayList (2693, 2684,2681)
    @Test
    public void multipleIds() {
        response.body("id",hasItems(2693,2674,2714));
    }
    //5.Verify the body of userid = 5296 is equal “Carus eaque voluptatem. Calcar
    //spectaculum coniuratio. Abstergo consequatur deleo. Amiculum advenio dolorem.
    //Sollers conservo adiuvo. Concedo campana capitulus. Adfectus tibi truculenter.
    //Canto temptatio adimpleo. Ter degenero animus. Adeo optio crapula. Abduco et
    //antiquus. Chirographum baiulus spoliatio. Suscipit fuga deleo. Comburo aequus
    //cuppedia. Crur cuppedia voluptates. Argentum adduco vindico. Denique undique
    //adflicto. Assentator umquam pel."”
    @Test
    public void userid(){
        response.body("[14].id", equalTo("Carus eaque voluptatem. Calcar" +
                "spectaculum coniuratio. Abstergo consequatur deleo. Amiculum advenio dolorem." +
                "Sollers conservo adiuvo. Concedo campana capitulus. Adfectus tibi truculenter." +
                "Canto temptatio adimpleo. Ter degenero animus. Adeo optio crapula. Abduco et" +
                "antiquus. Chirographum baiulus spoliatio. Suscipit fuga deleo. Comburo aequus" +
                "cuppedia. Crur cuppedia voluptates. Argentum adduco vindico. Denique undique" +
                "adflicto. Assentator umquam pel"));
    }


}