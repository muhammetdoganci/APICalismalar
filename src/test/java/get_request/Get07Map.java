package get_request;

import base_url.JsonPlaceBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Get07Map extends JsonPlaceBaseUrl {
    /*
    Given
       https://jsonplaceholder.typicode.com/todos/2
   When
       I send GET Request to the URL
   Then
       Status code is 200
       And "completed" is false
       And "userId" is 1
       And "title" is "quis ut nam facilis et officia qui"
       And header "Via" is "1.1 vegur"
       And header "Server" is "cloudflare"
       {
           "userId": 1,
           "id": 2,
           "title": "quis ut nam facilis et officia qui",
           "completed": false
       }Via
*/
    @Test
    public void get07Map(){
        // Set the url
        spec.pathParams("first","todos","second",2);

        // Set the expected data ==> Payload
        Map<String,Object> expectedData = new HashMap<>();
        expectedData.put("userId",1);
        expectedData.put("id",2);
        expectedData.put("title","quis ut nam facilis et officia qui");
        expectedData.put("completed",false);
        System.out.println("expectedData = " + expectedData);

        // Send the get request and get the response
        Response response = given().spec(spec).when().get("/{first}/{second}");
        response.prettyPrint();

        // Do assertion
        Map<String,Object> actualData = response.as(HashMap.class); // De Serialization
        System.out.println("actualData = " + actualData);
        /*
        JSON formatinda olan datalari String bir formata cevirdik.
        response.as(HashMap.class); --> response objeyi HashMap class gibi olarak ayarla cevir demek.
        parantezin icine class bir map yazmamiz lazim.
        HashMap formatindaki bir data Map seklinde hareket edecek.
        polimorfizim = Bir tek metot adıyla, birden çok farklı metotları koşturma özeliğine,
        java'da polymorphism denir. Şu önemli özeliği biliyoruz.
        Bir üst sınıfın referansı (işaretçi, pointer) bir alt sınıfa ait nesneyi işaret edebilir.
         */

        assertEquals(expectedData.get("userId"),actualData.get("userId"));
        assertEquals(expectedData.get("id"),actualData.get("id"));
        assertEquals(expectedData.get("title"),actualData.get("title"));
        assertEquals(expectedData.get("completed"),actualData.get("completed"));

        assertEquals(200,response.statusCode());
        assertEquals("1.1 vegur",response.header("Via"));
        assertEquals("cloudflare",response.header("Server"));




    }
}
