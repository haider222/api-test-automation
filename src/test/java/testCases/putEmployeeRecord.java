package testCases;

import baseClass.TestBase;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import org.json.simple.JSONObject;
import org.junit.Assert;
import org.testng.annotations.Test;
import utilities.RestUtility;

public class putEmployeeRecord extends TestBase {

    String street = RestUtility.street();
    String suite = RestUtility.suite();
    String zipcode = RestUtility.zipcode();

    @Test
    public void enterEmployeeData() {

        RestAssured.baseURI = properties.getProperty("baseurl");
        httpRequest = RestAssured.given();

        JSONObject requestParams = new JSONObject();
        requestParams.put("street", street);
        requestParams.put("suite", suite);
        requestParams.put("city", "Liepeija");
        requestParams.put("zipcode", zipcode);

        httpRequest.header("Content-Type", "application/json");
        httpRequest.body(requestParams.toJSONString());
        response = httpRequest.request(Method.PUT, "/api/user/Sherhan/address");

        int statusCode = response.statusCode();
        logger.info("------Response status code = " + statusCode);
        Assert.assertEquals(204, statusCode);
    }
}
