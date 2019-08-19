package baseClass;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.testng.annotations.BeforeClass;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class TestBase<reader> {

    public static RequestSpecification httpRequest;
    public static Response response;
    public Logger logger;
    public Properties properties = new Properties();
    public String resoucesPath = "resources\\Configuration.properties";

    @BeforeClass
    public void setUp() throws IOException {

        logger = Logger.getLogger("EmployeesRestAPI");
        PropertyConfigurator.configure("Log4j.properties");
        logger.setLevel(Level.ALL);
        BufferedReader reader = new BufferedReader(new FileReader(resoucesPath));
        properties.load(reader);
    }
}
