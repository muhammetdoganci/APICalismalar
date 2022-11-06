package base_url;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.junit.Before;

public class RegresinBaseUrl {
    protected RequestSpecification spec;
    // protected access modifiers = sadece class seviyesinden ulaşılabiir bu classtakiler erişebilir
    // RequestSpecification methodundan spec objesi (konteynerı) ürettik.

    @Before // @Before annotation her test annotation dan once calisir.
    public void setup(){
        spec = new RequestSpecBuilder().setBaseUri("https://reqres.in/api").build();
        // spec objesine url mizi assign ettik.
    }
}
