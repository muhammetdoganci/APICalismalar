package get_request;

import base_url.JsonPlaceBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class Get06 extends JsonPlaceBaseUrl {
    /*
      Given
       https://jsonplaceholder.typicode.com/todos
    When
    I send GET Request to the URL == > URL'e Get Request gonderin
    Then
    1)Status code is 200 == > Status kodu 200 olmali
    2)Print all ids greater than 190 on the console ==> id si 190 dan buyuk olanlari konsola yazdirin
      Assert that there are 10 ids greater than 190 == > 10 tane id nin 190 dan buyuk oldugunu dogrulayin
    3)Print all userIds whose ids are less than 5 on the console ==> id si 5 den kucuk olan tum userid lerini konsolunu yazdirin
      Assert that the number of userIds whose ids are less than 5 is 4 ==> id si 5 den kucuk olan 4 tane userId oldugunu dogrulayin
    4)Print all titles whose ids are less than 5 ==> ıd si 5 den kucuk olan tum basliklari yazdirin
      Assert that "delectus aut autem" is one of the titles whose id is less than 5 ==> id si 5 den kucuk olan datalarin birinin
      basliginin "delectus aut autem" icerdigini dogrulayin
   */
    @Test
    public void get06(){
        // Set the url
        spec.pathParam("first","todos");

        // Set the expected data

        // Send the request and get the response
        Response response = given().spec(spec).when().get("/{first}");
        response.prettyPrint();

        // Do assertion
        assertEquals(200,response.statusCode());

        JsonPath jsonPath = response.jsonPath();

        List<Integer> idler190buyuk = jsonPath.getList("findAll{it.id>190}.id");
        System.out.println("idler190buyuk = " + idler190buyuk);
        assertEquals(10,idler190buyuk.size());

        List<String> id5kucukTumUserId = jsonPath.getList("findAll{it.id<5}.userId");
        System.out.println("id5kucukTumUserId = " + id5kucukTumUserId);
        assertEquals(4,id5kucukTumUserId.size());

        List<String> title = jsonPath.getList("findAll{it.id<5}.title");
        System.out.println("title = " + title);
        assertTrue(title.contains("delectus aut autem"));


    }
}
