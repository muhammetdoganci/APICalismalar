package get_request;

import io.restassured.response.Response;
import org.junit.Test;
import org.testng.asserts.SoftAssert;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class Get01 {
    /*
    Given
        https://restful-booker.herokuapp.com/booking/101
    When
        User sends a GET Request to the url  /  (Kullanıcı url'ye bir alma İsteği gönderir)
    Then
        HTTP Status Code should be 200  /  (HTTP Status Kodu 200 olmalıdır)
    And
        Content Type should be JSON  /  (Content Type JSON olmalıdır)
    And
        Status Line should be HTTP/1.1 200 OK  /  (Status Line HTTP/1.1 olmalıdır 200 OK)
 */
    @Test
    public void get01(){
        // Set the url
        String url = "https://restful-booker.herokuapp.com/booking/101";

        // Set the expected data

        // Send the request and get the response
        Response response = given().when().get(url);
        response.prettyPrint();

        // Do assertion
        // 1. yol
        //response.then().assertThat().statusCode(200).contentType("application/json").statusLine("HTTP/1.1 200 OK");

        // 2. yol
        //assertEquals(200, response.statusCode());
        //assertEquals("application/json; charset=utf-8", response.contentType());
        //assertEquals("HTTP/1.1 200 OK", response.statusLine());

        // 3. yol

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(response.statusCode(), 200);
        softAssert.assertEquals(response.contentType(), "application/json; charset=utf-8");
        softAssert.assertEquals(response.statusLine(), "HTTP/1.1 200 OK");
        softAssert.assertAll();

        /*
        -softAssert kullanımında önce softAssert objesi oluşturmamız gerekiyor
        -softAssert kullandığımızda kodumuzda bir hata olsa bile kod sonuna kadar çalışacaktır.
         çalıştırma işlemi bittikten sonra testimiz failed olacak hata olan kodu bize uyaracaktır.
        -tüm doğrulamalarımızı yaptıktan sonra softAssert.assertAll(); yapmamız gerekiyor
        -softAssert.assertAll(); yapmadığımız takdirde kodumuzda bir hata olsa bile hata olarak alğılamayıp
         testimiz passed olacaktır.
         -softAssert.assertAll(); yaptıktan sonra kodumuzdaki hataları bize uyarı olarak konsolda dönecektir.
         */


    }
}
