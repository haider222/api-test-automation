package UnHappyTestCases;

import baseClass.TestBase;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import org.json.simple.JSONObject;
import org.junit.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class updateUserMissingAddres extends TestBase {

    @Test(dataProvider = "UserAddressDetails")
    public void updateUserMissingAddress(String street, String suite, String city, String zipCode) {
        RestAssured.baseURI = properties.getProperty("baseurl");
        System.out.println(properties.getProperty("baseurl"));
        httpRequest = RestAssured.given();

        JSONObject requestParams = new JSONObject();
        requestParams.put("street", street);
        requestParams.put("suite", suite);
        requestParams.put("city", city);
        requestParams.put("zipcode", zipCode);

        httpRequest.header("Content-Type", "application/json");
        httpRequest.body(requestParams.toJSONString());
        response = httpRequest.request(Method.PUT, "/api/user/ilyasLHt/address");

        int statusCode = response.statusCode();
        Assert.assertEquals(400, statusCode);

        String responseBody = response.getBody().asString();

        Assert.assertEquals(responseBody.contains("InvalidAddressDataException"), true);
        Assert.assertEquals(responseBody.contains("Incorrect address. Please validate if all mandatory data is filled."), true);
    }

    @DataProvider(name = "UserAddressDetails")
    public Object[] UserDetails() {
        String[][] UserDetails = {
                {"","asdfasdf","liepeija","LV-1003"},
                {"asdf","","liepeija","LV-1003"},
                {"asdf","FDSASDF","","LV-1003"},
                {"asdf","FDSASDF","Riga",""}
        };

        return UserDetails;
    }
}
