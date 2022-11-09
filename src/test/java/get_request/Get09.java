package get_request;

import base_url.GoRestBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import test_data.GoRestTestData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Get09 extends GoRestBaseUrl {
    /*
        Given
            https://gorest.co.in/public/v1/users/2986
        When
            User send GET Request to the URL
        Then
            Status Code should be 200
        And
            Response body should be like
            /*
            {
        "meta": null,
        "data": {
            "id": 2986,
            "name": "Navin Talwar",
            "email": "navin_talwar@mclaughlin.name",
            "gender": "male",
            "status": "inactive"
              }
         }
     */
    @Test
    public void  get09(){
        // Set the url
        spec.pathParams("first","users","second",2986);

        //set the expected data
        GoRestTestData obje = new GoRestTestData();
        Map<String, String> dataMap = obje.dataKeyMap("Navin Talwar","navin_talwar@mclaughlin.name","male","inactive");
        Map<String,Object> expectedData = obje.expectedDataMap(null,dataMap);
        System.out.println("expectedData = " + expectedData);

        // send the request and the get response
        Response response = given().spec(spec).when().get("/{first}/{second}");
        response.prettyPrint();

        // do assertion
        Map<String,Object> actualData = response.as(HashMap.class);
        System.out.println("actualData = " + actualData);

        assertEquals(expectedData.get("meta"),actualData.get("meta"));
        assertEquals(expectedData.get("name"),actualData.get("email"));
        assertEquals(expectedData.get("gender"),actualData.get("gender"));
        assertEquals(expectedData.get("status"),actualData.get("status"));
        assertEquals(200,response.statusCode());


    }
}
