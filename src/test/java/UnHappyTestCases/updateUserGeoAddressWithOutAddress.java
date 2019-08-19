package UnHappyTestCases;

import baseClass.TestBase;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import org.json.simple.JSONObject;
import org.junit.Assert;
import org.testng.annotations.Test;

public class updateUserGeoAddressWithOutAddress extends TestBase {
    @Test
    public void updateUserGeoAddressWithOutAddress() {
        RestAssured.baseURI = properties.getProperty("baseurl");
        System.out.println(properties.getProperty("baseurl"));
        httpRequest = RestAssured.given();

        JSONObject requestParams = new JSONObject();

        requestParams.put("lat", "123232");
        requestParams.put("lng", "1234323");

        httpRequest.header("Content-Type", "application/json");
        httpRequest.body(requestParams.toJSONString());
        response = httpRequest.request(Method.PUT, "/api/user/muhammad.ilyas/address/geo");

        int statusCode = response.statusCode();
        Assert.assertEquals(400, statusCode);

        logger.info("-------Checking Response Body-------");
        String responseBody = response.getBody().asString();
        Assert.assertEquals(responseBody.contains("MissingAddressException"), true);
        Assert.assertEquals(responseBody.contains("Address for current user: muhammad.ilyas is not defined."), true);
    }
}
