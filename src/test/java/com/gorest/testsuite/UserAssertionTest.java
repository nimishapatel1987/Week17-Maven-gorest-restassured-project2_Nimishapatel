package com.gorest.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.port;
import static org.hamcrest.Matchers.*;

public class UserAssertionTest {
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

    //1.Verify the if the total record is 20
    @Test
    public void test1() {
        response.body("total", equalTo(20));
    }

    @Test
    public void test2() {
        response.body("[2].name",equalTo("Soma Desai"));
    }

    //3. Check the single ‘Name’ in the Array list (Subhashini Talwar)
    @Test
    public void test3() {
        response.body("[0].name", equalTo("Dhana Kaul"));
    }

    //4. Check the multiple ‘Names’ in the ArrayList ("Dhana Kaul", "Deeptimoyee Sharma", "Vimala Kakkar", "Rameshwar Varman")
    @Test
    public void test4() {
        response.body("name", hasItems("Rameshwar Varman, Raj Patil, Aashritha Bhattathiri, Trilochan Gupta, Chandak Deshpande, Subodh Menon, Ganesh Kaniyar, Kashyap Prajapat, Meghnad Jain, Diptendu Mukhopadhyay, Indra Jha MD, Nagabhushanam Johar, Mrs. Aruna Sharma, Rep. Chidambar Rana, Udit Menon, Goswamee Tandon, Dr. Kumuda Kaul, Chaitan Malik, Sen. Abhaya Butt, Amish Bhattacharya"));
    }

    //5. Verify the emai of userid = 5471 is equal “adiga_aanjaneya_rep@jast.org”
    @Test
    public void test5() {
        response.body("[3].email", equalTo("trilochan_gupta@moore.io"));
    }

    //   6. Verify the status is “Active” of user name is “Shanti Bhat V”
    @Test
    public void test6() {
        response.body("[6].status", equalTo("active"));
    }

    //     7. Verify the Gender = male of user name is “Niro Prajapat”
    @Test
    public void test7() {
        response.body("[11].gender", equalTo("male"));
    }

}
