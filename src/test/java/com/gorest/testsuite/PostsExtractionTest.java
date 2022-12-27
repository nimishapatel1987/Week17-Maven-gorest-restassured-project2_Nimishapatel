package com.gorest.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

import static io.restassured.RestAssured.given;

public class PostsExtractionTest {
    static ValidatableResponse response;

    @BeforeClass
    public static void inIt() {
        RestAssured.baseURI = "https://gorest.co.in/public/v2";

        response = given()
                .when()
                .queryParam("page", "1")
                .queryParam("per_page", "20")
                .get("/posts?page=1&per_page=25")
                .then().statusCode(200);
    }

    //1. Extract the title
    @Test
    public void test1() {
        List<String> title = response.extract().path("title");

        System.out.println("------------------StartingTest------------------------");
        System.out.println("The title : " + title);
        System.out.println("-------------------End of test-------------------------");
    }

    //2. Extract the total number of record
    @Test
    public void test2() {
        List<Object> totalrecord = response.extract().path("data.record");

        System.out.println("------------------StartingTest------------------------");
        System.out.println("The number of record : " + totalrecord);
        System.out.println("-------------------End of test-------------------------");
    }

    //3. Extract the body of 15th record
    @Test
    public void test3() {
        String body = response.extract().path("[14].body");

        System.out.println("------------------StartingTest------------------------");
        System.out.println("The body nof 15th record : " + body);
        System.out.println("-------------------End of test-------------------------");
    }

    //4. Extract the user_id of all the records
    @Test
    public void test4() {
        List<String> user_id = response.extract().path("user_id");

        System.out.println("------------------StartingTest------------------------");
        System.out.println("The user_id of all the records : " + user_id);
        System.out.println("-------------------End of test-------------------------");
    }

    //5. Extract the title of all the records
    @Test
    public void test5() {
        List<String> allrecords = response.extract().path("title");

        System.out.println("------------------StartingTest------------------------");
        System.out.println("The user_id of all the records : " + allrecords);
        System.out.println("-------------------End of test-------------------------");
    }

    //6. Extract the title of all records whose user_id = 5448
    @Test
    public void test6() {
        List<String> allrecords = response.extract().path("findAll{it.user_id==5448}.record");

        System.out.println("------------------StartingTest------------------------");
        System.out.println("The title of all records whose user_id '5448' : " + allrecords);
        System.out.println("-------------------End of test-------------------------");
    }

    //7. Extract the body of all records whose id = 2670
    @Test
    public void test7() {
        List<String> allrecords = response.extract().path("findAll{it.user_id==2670}.record");

        System.out.println("------------------StartingTest------------------------");
        System.out.println("The title of all records whose user_id '2670' : " + allrecords);
        System.out.println("-------------------End of test-------------------------");
    }


}

