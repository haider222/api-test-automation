package testCases;

import baseClass.TestBase;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import org.junit.Assert;
import org.testng.annotations.Test;

public class getAllEmployeeData extends TestBase {

    @Test
    public void getAllEmployeeRecords(){
        logger.info("---------Test Case Start Here-----------");
        RestAssured.baseURI = properties.getProperty("baseurl");;
        httpRequest = RestAssured.given();
        response = httpRequest.request(Method.GET, "/api/users");

        logger.info("-------Checking Response Body-------");
        String responseBody = response.getBody().asString();
        Assert.assertTrue(responseBody != null);

        logger.info("-------Checking Status Code --------");
        int statusCode = response.statusCode();
        Assert.assertEquals(statusCode,200);

        logger.info("-------Checking Server Type --------");
        String serverType = response.header("Server");
        Assert.assertEquals(serverType.equals("Cowboy"), true);

        logger.info("-------Checking Status line --------");
        String statusLine = response.getStatusLine();
        Assert.assertEquals(statusLine, "HTTP/1.1 200 ");

        logger.info("-------Checking Content Type --------");
        String contentType = response.contentType();
        logger.info("Content type = " + contentType);
    }
}
