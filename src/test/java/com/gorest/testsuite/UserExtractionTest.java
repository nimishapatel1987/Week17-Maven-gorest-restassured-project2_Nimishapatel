package com.gorest.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class UserExtractionTest {
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

    //  1. Extract the All Ids
    @Test
    public void test1() {
        List<String> Ids = response.extract().path("id");

        System.out.println("------------------StartingTest------------------------");
        System.out.println("The all Ids : " + Ids);
        System.out.println("-------------------End of test-------------------------");
    }

    //2. Extract the all Names
    @Test
    public void test2() {
        List<String> Names = response.extract().path("name");

        System.out.println("------------------StartingTest------------------------");
        System.out.println("The all Names : " + Names);
        System.out.println("-------------------End of test-------------------------");
    }

    //3. Extract the name of 5th object
    @Test
    public void test3() {
        String objectName = response.extract().path("[4].name");

        System.out.println("------------------StartingTest------------------------");
        System.out.println("The name of 5th object : " + objectName);
        System.out.println("-------------------End of test-------------------------");
    }

    //4. Extract the names of all object whose status = inactive
    @Test
    public void test4() {
        List<String> object = response.extract().path("findAll{it.status=='inactive'}.id");

        System.out.println("------------------StartingTest------------------------");
        System.out.println("The names of all object whose status 'inactive' : " + object);
        System.out.println("-------------------End of test-------------------------");
    }

    //5. Extract the gender of all the object whose status = active
    @Test
    public void test5() {
        List<String> active = response.extract().path("findAll{it.status=='active'}.id");

        System.out.println("------------------StartingTest------------------------");
        System.out.println("The names of all object whose status 'active' : " + active);
        System.out.println("-------------------End of test-------------------------");
    }

    //6. Print the names of the object whose gender = female
    @Test
    public void test6() {
        List<String> names = response.extract().path("findAll{it.gender=='female'}.id");

        System.out.println("------------------StartingTest------------------------");
        System.out.println("The object whose gender 'female' : " + names);
        System.out.println("-------------------End of test-------------------------");
    }

    //7. Get all the emails of the object where status = inactive
    @Test
    public void test7() {
        List<String> emails = response.extract().path("findAll{it.status=='inactive'}.email");

        System.out.println("------------------StartingTest------------------------");
        System.out.println("The emails of the object where status 'inactive' : " + emails);
        System.out.println("-------------------End of test-------------------------");
    }

    //8. Get the ids of the object where gender = male
    @Test
    public void test8() {
        List<String> gender = response.extract().path("findAll{it.gender=='male'}.id");

        System.out.println("------------------StartingTest------------------------");
        System.out.println("The object whose gender 'female' : " + gender);
        System.out.println("-------------------End of test-------------------------");
    }

    //9. Get all the status
    @Test
    public void test9() {
        List<String> status = response.extract().path("status");

        System.out.println("------------------StartingTest------------------------");
        System.out.println("All the status : " + status);
        System.out.println("-------------------End of test-------------------------");
    }

    //10. Get email of the object where name = Karthik Dubashi IV
    @Test
    public void test10() {
        List<String> name = response.extract().path("findAll{it.name=='Chandrakin Johar'}.email");

        System.out.println("------------------StartingTest------------------------");
        System.out.println("Email of the object where name 'Karthik Dubashi IV' : " + name);
        System.out.println("-------------------End of test-------------------------");
    }

    //11. Get gender of id = 5471
    @Test
    public void test11() {
        List<String> id = response.extract().path("findAll{it.id==5393}.gender");

        System.out.println("------------------StartingTest------------------------");
        System.out.println("gender of id = 5471 : " + id);
        System.out.println("-------------------End of test-------------------------");
    }
}

