package UnHappyTestCases;

import baseClass.TestBase;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import org.json.simple.JSONObject;
import org.junit.Assert;
import org.testng.annotations.Test;

public class enterEmployeeDataWithSameUserName extends TestBase {

    @Test
    public void enterEmployeeDataWithSameUserName() throws InterruptedException {
        RestAssured.baseURI = properties.getProperty("baseurl");

        httpRequest = RestAssured.given();

        JSONObject requestParams = new JSONObject();
        requestParams.put("email", "ilyas1kdf@gmail.com");
        requestParams.put("name", "uktyuilyas");
        requestParams.put("phone", "2323232323");
        requestParams.put("userName", "Sherhan");
        requestParams.put("website", "https://www.www.com");


        httpRequest.header("Content-Type", "application/json");
        httpRequest.body(requestParams.toJSONString());
        response = httpRequest.request(Method.POST, properties.getProperty("postemployeedata"));

        logger.info("-------Checking Response Body-------");
        String responseBody = response.getBody().asString();
        Assert.assertEquals(responseBody.contains("DublicateUserNameException"), true);
        Assert.assertEquals(responseBody.contains("User with current username allready exists. Username: Sherhan"), true);

        logger.info("-------Checking Status code --------");
        int statusCode = response.statusCode();
        Assert.assertEquals(400, statusCode);
    }
}
