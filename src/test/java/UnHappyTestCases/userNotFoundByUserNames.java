package UnHappyTestCases;

import baseClass.TestBase;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import org.junit.Assert;
import org.testng.annotations.Test;

public class userNotFoundByUserNames extends TestBase {

    @Test
    public void userNotFoundByUserName() {
        RestAssured.baseURI = properties.getProperty("baseurl");
        System.out.println(properties.getProperty("baseurl"));
        httpRequest = RestAssured.given();
        String path = "/api/users/findByUserName/ilyas";
        response = httpRequest.request(Method.GET, path);

        int statusCode = response.getStatusCode();
        Assert.assertEquals(404, statusCode);

        String responseBody = response.getBody().asString();
        Assert.assertEquals(responseBody.contains("UserNotFoundException"), true);
        Assert.assertEquals(responseBody.contains("User not found. User name: ilyas"), true);
    }
}
