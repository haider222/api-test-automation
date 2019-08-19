package testCases;

import baseClass.TestBase;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import org.json.simple.JSONObject;
import org.junit.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utilities.RestUtility;

public class postUpdateGetDelete extends TestBase {

    String emial = RestUtility.email();
    String name = RestUtility.name();
    String phone = RestUtility.phone();
    String userNme = RestUtility.userName();
    String wbsite = RestUtility.website();
    String street = RestUtility.street();
    String suite = RestUtility.suite();
    String zipcode = RestUtility.zipcode();
    String langu = RestUtility.lan();
    String latitu = RestUtility.lat();
    String bs = RestUtility.bs();
    String catchPhrase  = RestUtility.catchPhrase();
    String companyName = RestUtility.companyName();
    @BeforeClass
    public void baseURL(){
        RestAssured.baseURI = properties.getProperty("baseurl");
        httpRequest = RestAssured.given();
    }

    @Test(priority = 1)
    public void enterEmployeeData() throws InterruptedException {

        JSONObject requestParams = new JSONObject();
        requestParams.put("email", emial);
        requestParams.put("name", name);
        requestParams.put("phone", phone);
        requestParams.put("userName", userNme);
        requestParams.put("website", wbsite);


        httpRequest.header("Content-Type", "application/json");
        httpRequest.body(requestParams.toJSONString());
        response = httpRequest.request(Method.POST, properties.getProperty("postemployeedata"));

        logger.info("-------Checking Response Body-------");
        String responseBody = response.getBody().asString();
        logger.info("Response body = " + responseBody);

        logger.info("-------Checking Status code --------");
        int statusCode = response.statusCode();
        Assert.assertEquals(200, statusCode);
    }


    @Test(priority = 2)
    public void updateUserAddress() {

        JSONObject requestParams = new JSONObject();
        requestParams.put("street", street);
        requestParams.put("suite", suite);
        requestParams.put("city", "Liepeija");
        requestParams.put("zipcode", zipcode);

        httpRequest.header("Content-Type", "application/json");
        httpRequest.body(requestParams.toJSONString());
        response = httpRequest.request(Method.PUT, "/api/user/"+userNme+"/address");

        int statusCode = response.statusCode();
        logger.info("------Response status code = " + statusCode);
        Assert.assertEquals(204, statusCode);

    }

    @Test(priority = 3)
    public void getUpdatedAddressRecord() {

        String path = "/api/users/findByUserName/"+userNme;
        response = httpRequest.request(Method.GET, path);
        String responseBody = response.getBody().asString();

        Assert.assertEquals(responseBody.contains(street), true);
        Assert.assertEquals(responseBody.contains(suite), true);
        Assert.assertEquals(responseBody.contains(zipcode), true);
        Assert.assertEquals(responseBody.contains("Liepeija"), true);

        logger.info("-------Checking Status code --------");
        int statusCode = response.statusCode();
        org.testng.Assert.assertEquals(statusCode,200);
    }

    @Test(priority = 4)
    public void updateUserGeoAddress() {

        JSONObject requestParams = new JSONObject();

        requestParams.put("lat", latitu);
        requestParams.put("lng", langu);

        httpRequest.header("Content-Type", "application/json");
        httpRequest.body(requestParams.toJSONString());
        response = httpRequest.request(Method.PUT, "/api/user/"+userNme+"/address/geo");

        int statusCode = response.statusCode();
        logger.info("------Response status code = " + statusCode);
        Assert.assertEquals(204, statusCode);
    }

    @Test(priority = 5)
    public void getUpdatedGeoAddressRecord() {

        String path = properties.getProperty("getSingleEmployeeData")+userNme;
        response = httpRequest.request(Method.GET, path);

        String responseBody = response.getBody().asString();
        Assert.assertEquals(responseBody.contains(latitu), true);
        Assert.assertEquals(responseBody.contains(langu), true);

        logger.info("-------Checking Status code --------");
        int statusCode = response.statusCode();
        org.testng.Assert.assertEquals(statusCode,200);

    }

    @Test(priority = 6)
    public void updateUserCompanyAddress() {

        JSONObject requestParams = new JSONObject();

        requestParams.put("bs", bs);
        requestParams.put("catchPhrase", catchPhrase);
        requestParams.put("name", companyName);

        httpRequest.header("Content-Type", "application/json");
        httpRequest.body(requestParams.toJSONString());
        response = httpRequest.request(Method.PUT, "/api/user/"+userNme+"/company");

        int statusCode = response.statusCode();
        logger.info("------Response status code = " + statusCode);
        Assert.assertEquals(204, statusCode);
    }

    @Test(priority = 7)
    public void getUpdatedCompanyAddressRecord() {

        String path = properties.getProperty("getSingleEmployeeData")+userNme;
        response = httpRequest.request(Method.GET, path);
        String responseBody = response.getBody().asString();
        Assert.assertEquals(responseBody.contains(bs), true);
        Assert.assertEquals(responseBody.contains(catchPhrase), true);
        Assert.assertEquals(responseBody.contains(companyName), true);

        logger.info("-------Checking Status code --------");
        int statusCode = response.statusCode();
        org.testng.Assert.assertEquals(statusCode,200);
    }

    @Test(priority = 8)
    public void deleteEmployeeRecord() {

        response = httpRequest.request(Method.DELETE, "/api/user/"+userNme);

        logger.info("-------Checking Status code of deleted end point--------");
        int statusCode = response.statusCode();
        Assert.assertEquals(204, statusCode);

    }
    @Test(priority = 9)
    public void verifyDeletedUserNotPresent() {

        String path = properties.getProperty("getSingleEmployeeData")+userNme;

        response = httpRequest.request(Method.GET, path);

        int statusCode = response.statusCode();
        Assert.assertEquals(404, statusCode);
    }
}
