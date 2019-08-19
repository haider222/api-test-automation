package UnHappyTestCases;

import baseClass.TestBase;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import org.json.simple.JSONObject;
import org.junit.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class updateUserMissingCompanyAddress extends TestBase {
    @Test(dataProvider = "UserCompanyDetails")
    public void updateUserMissingCompanyAddress(String bs, String catchPhrase, String name) {
        RestAssured.baseURI = properties.getProperty("baseurl");
        System.out.println(properties.getProperty("baseurl"));
        httpRequest = RestAssured.given();
        JSONObject requestParams = new JSONObject();

        requestParams.put("bs", bs);
        requestParams.put("catchPhrase", catchPhrase);
        requestParams.put("name", name);

        httpRequest.header("Content-Type", "application/json");
        httpRequest.body(requestParams.toJSONString());
        response = httpRequest.request(Method.PUT, "/api/user/Sherhan/company");

        int statusCode = response.statusCode();
        Assert.assertEquals(400, statusCode);
    }

    @DataProvider(name = "UserCompanyDetails")
    public Object[] UserCompanyDetails() {
        String[][] UserCompanyDetails = {
                {"","CatchPhrase","CompanyName"},
                {"Bs","","CompanyName1"},
                {"bs1","CatchPhrase1",""},
        };

        return UserCompanyDetails;
    }
}
