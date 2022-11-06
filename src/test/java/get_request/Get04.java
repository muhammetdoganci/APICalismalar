package get_request;

import base_url.RegresinBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;
import org.testng.asserts.SoftAssert;

import static io.restassured.RestAssured.given;

public class Get04 extends RegresinBaseUrl {
    /*
        Given
          https://reqres.in/api/unknown/3
        When
            User send a GET request to the URL
        Then
            HTTP Status Code should be 200
        And
            Response content type is "application/json"
        And
            Response body should be like;(Soft Assertion)
        {
        "data": {
            "id": 3,
            "name": "true red",
            "year": 2002,
            "color": "#BF1932",
            "pantone_value": "19-1664"
        },
        "support": {
            "url": "https://reqres.in/#support-heading",
            "text": "To keep ReqRes free, contributions towards server costs are appreciated!"
        }
      */
    @Test
    public void get04(){
        // Set the url
        spec.pathParams("first","unknown","second",3);

        // Set the expected data

        // Send the request and get the response
        Response response = given().spec(spec).when().get("/{first}/{second}");
        response.prettyPrint();

        // Do assertion
        SoftAssert softAssert = new SoftAssert();
        JsonPath json = response.jsonPath();
        // JSON formatinda olan response objeyi String bir data tipine cevirmek icin jsonPath() data formatini kullaniyoruz
        System.out.println("json = " + json.get()); // String formatında cikti verir

        softAssert.assertEquals(response.statusCode(),200);
        softAssert.assertEquals(response.contentType(),"application/json; charset=utf-8");
        softAssert.assertEquals(json.getInt("data.id"),3,"id degeri dogru degil");
        softAssert.assertEquals(json.getString("data.name"), "true red","isim dogru degil");
        softAssert.assertEquals(json.getInt("data.year"),2002,"yil dogru degil");
        softAssert.assertEquals(json.getString("data.color"),"#BF1932","renk dogru degil");
        softAssert.assertEquals(json.getString("data.pantone_value"),"19-1664","pantone degeri dogru degil");
        softAssert.assertEquals(json.getString("support.url"),"https://reqres.in/#support-heading","url dogru degil");
        softAssert.assertEquals(json.getString("support.text"),"To keep ReqRes free, contributions towards server costs are appreciated!","text dogru degil");
        softAssert.assertAll();


    }


}
