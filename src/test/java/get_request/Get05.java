package get_request;

import base_url.RegresinBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class Get05 extends RegresinBaseUrl {
    /*
   Given
          https://reqres.in/api/unknown/
   When
        I send GET Request to the URL
   Then

        1)Status code is 200
        2)Print all pantone_values
        3)Print all ids greater than 3 on the console
          Assert that there are 3 ids greater than 3
        4)Print all names whose ids are less than 3 on the console
          Assert that the number of names whose ids are less than 3 is 2

     */
    @Test
    public void get05(){

        // set the url
        spec.pathParam("first","unknown");

        // set the expected data

        // Send the request and get the response
        Response response = given().spec(spec).when().get("/{first}");
        response.prettyPrint();

        // Do assertion
        assertEquals(200,response.statusCode());

        //2)Print all pantone_values
        JsonPath jsonPath = response.jsonPath();
        System.out.println("pantone_value : " + jsonPath.getList("data.pantone_value"));

        //3)Print all ids greater than 3 on the console
        List<Integer> idler = jsonPath.getList("data.findAll{it.id>3}.id");
        System.out.println("idler = " + idler);

        //Assert that there are 3 ids greater than 3
        assertEquals(3,idler.size());

        //4)Print all names whose ids are less than 3 on the console
        List<String> names = jsonPath.getList("data.findAll{it.id<3}.name");
        System.out.println("names = " + names);

        //Assert that the number of names whose ids are less than 3 is 2
        assertEquals(2,names.size());

        // tum name ler
        List<String> name = jsonPath.getList("data.name");
        System.out.println("name = " + name);

        // id si 3 ten buyuk name ler
        List<String> idUctenBuyukName = jsonPath.getList("data.findAll{it.id>3}.name");
        System.out.println("id3buyukName = " + idUctenBuyukName);

        // year ı 2005 olanın ismi
        List<String> year2005OlanName = jsonPath.getList("data.findAll{it.year==2005}.name");
        System.out.println("year2005Name = " + year2005OlanName);

        /*
        JSON formatindaki respons objeyi jsonPath ile String bir data turune cevirdikten sonra
        liste seklinde getList() ile yazdirabiliyorum.
        Liste seklindeki bu String'in istedigim kisimlarina ulasabilmek icin
        jsonPath.getList("data.id");  jsonPath.getList("data.name"); vb. seklinde yazmaliyiz.
        Eger bizden referans gosterip baska dogrulamalar istenecekse
        jsonPath.getList("data.findAll{it.id>3}.name"); (id'si 3 ten buyuk olanlarin isimleri)
        formatında yazmaliyiz it. seklinde yazilmalidir baska kabul etmemektedir.

         */
    }
}
