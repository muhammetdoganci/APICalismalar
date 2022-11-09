package get_request;

import base_url.RestfullBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Get08 extends RestfullBaseUrl {
    /*
        Given
            https://restful-booker.herokuapp.com/booking/10986
        When
            I send GET Request to the url
        Then
            Response body should be like that;
             {
            "firstname": "Sally",
            "lastname": "Brown",
            "totalprice": 111,
            "depositpaid": true,
            "bookingdates": {
                "checkin": "2013-02-23",
                "checkout": "2014-10-23"
            },
            "additionalneeds": "Breakfast"
            }
     */
    @Test
    public void get08(){
        //Set the Url
        spec.pathParams("first", "booking","second", 10986);

//Set The Expected Data
        Map<String,String> bookingdatesMap = new HashMap<>();
        bookingdatesMap.put("checkin","2013-02-23");
        bookingdatesMap.put("checkout","2014-10-23");

        Map<String,Object> expectedData = new HashMap<>();

        expectedData.put("firstname","Sally");
        expectedData.put("lastname","Brown");
        expectedData.put("totalprice",111);
        expectedData.put("depositpaid",true);
        expectedData.put("bookingdates",bookingdatesMap);
        expectedData.put("additionalneeds","Breakfast");
        System.out.println(expectedData);

        //Send The Request and Get The Response
        Response response = given().spec(spec).when().get("/{first}/{second}");
        response.prettyPrint();

        //Do Assertion
        Map<String,Object> actualData = response.as(HashMap.class);
        System.out.println(actualData);
        assertEquals(expectedData.get("firstname"),actualData.get("firstname"));
        assertEquals(expectedData.get("lastname"),actualData.get("lastname"));
        assertEquals(expectedData.get("totalprice"),actualData.get("totalprice"));
        assertEquals(expectedData.get("depositpaid"),actualData.get("depositpaid"));
        assertEquals(bookingdatesMap.get("checkin"), ((Map)(actualData.get("bookingdates"))).get("checkin"));//Key-Value ikilileri String-Object şeklinde olduğundan, Bookingdata value kısmını casting ile Map yaptık.
        assertEquals(bookingdatesMap.get("checkout"), ((Map)(actualData.get("bookingdates"))).get("checkout"));
        assertEquals(expectedData.get("additionalneeds"),actualData.get("additionalneeds"));

    }
}
