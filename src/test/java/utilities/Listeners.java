package utilities;


import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;


public class Listeners extends TestListenerAdapter {
   public ExtentHtmlReporter htmlReporter;
   public ExtentReports extent;
   public ExtentTest test;

   public void onStart(ITestContext testContext){
       htmlReporter = new ExtentHtmlReporter("Reports/myReport.html");
       htmlReporter.config().setDocumentTitle("Automation Report");
       htmlReporter.config().setReportName("API TEST AUTOMATION");
       htmlReporter.config().setTheme(Theme.DARK);

       extent = new ExtentReports();
       extent.attachReporter(htmlReporter);
       extent.setSystemInfo("ilyas","localhost");
   }

   public void onTestSuccess(ITestResult result){
       test = extent.createTest(result.getName());
       test.log(Status.PASS,"Test cases passed is " + result.getName());
   }

   public void onTestFailure(ITestResult result){
       test = extent.createTest(result.getName());
       test.log(Status.FAIL, "Test case failed is " + result.getName());

   }

   public void onTestSkipped(ITestResult result){
       test = extent.createTest(result.getName());
       test.log(Status.SKIP, "Test case skipped is " + result.getName());
   }
   public void onFinish(ITestContext result){
       extent.flush();
   }
}
