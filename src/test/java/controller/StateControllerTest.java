package controller;

import io.restassured.RestAssured;
import io.restassured.response.ResponseBody;
import org.junit.Assert;
import org.junit.jupiter.api.Test;


public class StateControllerTest {

    @Test
    public void putButtonTest() {
        RestAssured.baseURI = "http://localhost:8080/simplewebapp/main";
        ResponseBody body = RestAssured.given().put().getBody();
        Assert.assertEquals("Test put fails", " Pressed button: PUT ", body.asString());
    }

    @Test
    public void postButtonTest() {
        RestAssured.baseURI = "http://localhost:8080/simplewebapp/main";
        ResponseBody body = RestAssured.given().post().getBody();
        Assert.assertEquals("Test post fails", " Pressed button: POST ", body.asString());
    }

    @Test
    public void deleteButtonTest() {
        RestAssured.baseURI = "http://localhost:8080/simplewebapp/main";
        ResponseBody body = RestAssured.given().delete().getBody();
        Assert.assertEquals("Test delete fails", "Pressed button: DELETE ", body.asString());
    }
}
