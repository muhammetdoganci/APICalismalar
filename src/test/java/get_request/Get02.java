package get_request;

import base_url.RegresinBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.defaultParser;
import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Get02 extends RegresinBaseUrl {
    /*
    Given
        https://reqres.in/api/users/23
    When
        User send a GET Request to the url
    Then
        HTTP Status code should be 404
    And
        Status Line should be HTTP/1.1 404 Not Found
    And
        Server is "cloudflare"
    And
        Response body should be empty

     */
    @Test
    public void get02(){
        // Set the url
        spec.pathParams("first","users","second",23);

        // Set the expected data

        // Send the request and get the response
        Response response = given().spec(spec).when().get("/{first}/{second}");
        response.prettyPrint();

        // Do assertion

        assertEquals(404, response.statusCode());
        assertEquals("HTTP/1.1 404 Not Found", response.statusLine());
        assertEquals("cloudflare", response.header("Server"));
        assertEquals(0, response.asString().replaceAll("[^A-Za-z0-9]","").length());
        assertEquals(2,response.asString().replaceAll("\\s","").length());


        /*
        String body = "(    )";
        System.out.println("body = " + body);
        String bosBody = body.replaceAll("\\s","");
        System.out.println("bosBody = " + bosBody);
        String harfsizBody = body.replaceAll("[^A-Za-z0-9]","");
        System.out.println("harfsizBody = " + harfsizBody);

         */


    }
}
