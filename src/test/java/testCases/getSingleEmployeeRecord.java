package testCases;

import baseClass.TestBase;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import org.testng.Assert;
import org.testng.annotations.Test;

public class getSingleEmployeeRecord extends TestBase {
    @Test
    public void getALLEmployees() {
        logger.info("---------Test Case Start Here-----------");
        RestAssured.baseURI = properties.getProperty("baseurl");
        httpRequest = RestAssured.given();
        response = httpRequest.request(Method.GET, properties.getProperty("getSingleEmployeeData") + "Sherhan");


        logger.info("-------Checking Response Body-------");
        String responseBody = response.getBody().asString();
        Assert.assertEquals(responseBody.contains("1"), true);
        Assert.assertEquals(responseBody.contains("Valentins"), true);
        Assert.assertEquals(responseBody.contains("Sherhan"), true);
        Assert.assertEquals(responseBody.contains("ukrainis.v@gmail.com"), true);

        logger.info("-------Checking Status code --------");
        int statusCode = response.statusCode();
        Assert.assertEquals(statusCode,200);
    }
}
