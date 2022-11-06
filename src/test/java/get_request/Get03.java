package get_request;

import base_url.RegresinBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.core.IsEqual.equalTo;

public class Get03 extends RegresinBaseUrl {
    /*
     Given
         https://reqres.in/api/users/2
     When
         User send GET Request to the URL
     Then
         HTTP Status Code should be 200
     And
         Response format should be "application/json"
     And
         "email" is "janet.weaver@reqres.in",
     And
         "first_name" is "Janet"
     And
         "last_name" is "Weaver"
     And
         "text" is "To keep ReqRes free, contributions towards server costs are appreciated!"
    */

    @Test
    public void get03(){
        // set the url
        spec.pathParams("first","users","second", 2);

        // Set the expected data

        // Send the request and get the response
        Response response = given().spec(spec).when().get("/{first}/{second}");
        response.prettyPrint();

        // Do assertion
        response.then().assertThat().
                statusCode(200).
                contentType("application/json").
                body("data.email",equalTo("janet.weaver@reqres.in"),
                        "data.first_name",equalTo("Janet"),
                        "data.last_name", equalTo("Weaver"),
                        "support.text", equalTo("To keep ReqRes free, contributions towards server costs are appreciated!"));

    }

}