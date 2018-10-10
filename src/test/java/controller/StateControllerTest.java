package controller;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.Request;

public class StateControllerTest {
    /*@Before
    public void setup() {
        RestAssured.baseURI = "http://localhost:8080/simplewebapp/main?ajax=true";
    }*/

    /*@Test
    public void get(){
        RestAssured.baseURI ="http://localhost:8080/simplewebapp/main?ajax=true";
        RequestSpecification httpReq=RestAssured.given();
        Response response=httpReq.get();
        int i=response.getStatusCode();
        System.out.println(i);
    }*/
    @Test
    public void put(){
        RestAssured.baseURI = "http://localhost:8080/simplewebapp/main?ajax=true";
        RequestSpecification httpReq=RestAssured.given();
        Response response=httpReq.put();
        ResponseBody body=response.getBody();
        System.out.println(body.asString());
    }
}
