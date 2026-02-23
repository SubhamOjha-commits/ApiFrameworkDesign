package api.utilities;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ReportManager implements ITestListener {

    public ExtentHtmlReporter htmlReporter;
    public ExtentReports extent;
    public ExtentTest logger;


    @Override
    public void onStart(ITestContext testContext) {
        String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
        String repName = "Test-Report-" + timeStamp + ".html";

        // Create test-output directory if it doesn't exist
        File reportDir = new File(System.getProperty("user.dir") + "/test-output");
        if (!reportDir.exists()) {
            reportDir.mkdirs();
        }

        htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") + "/test-output/" + repName);

        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);

        // System Information
        extent.setSystemInfo("Application", "Pet Store API");
        extent.setSystemInfo("Operating System", System.getProperty("os.name"));
        extent.setSystemInfo("User Name", System.getProperty("user.name"));
        extent.setSystemInfo("Environment", "QA");
        extent.setSystemInfo("Java Version", System.getProperty("java.version"));

        // Report Configuration
        htmlReporter.config().setDocumentTitle("RestAssured API Test Report");
        htmlReporter.config().setReportName("Pet Store API Automation Report");
        htmlReporter.config().setTheme(Theme.DARK);
    }

    @Override
    public void onTestSuccess(ITestResult tr) {
        logger = extent.createTest(tr.getName());
        logger.log(Status.PASS, MarkupHelper.createLabel(tr.getName(), ExtentColor.GREEN));
        logger.pass("Test passed successfully");
    }

    @Override
    public void onTestFailure(ITestResult tr) {
        logger = extent.createTest(tr.getName());
        logger.log(Status.FAIL, MarkupHelper.createLabel(tr.getName(), ExtentColor.RED));

        // Log the failure message
        logger.fail("Test failed: " + tr.getThrowable());

        // Screenshot path (using forward slash for cross-platform compatibility)
        String screenshotPath = System.getProperty("user.dir") + "/Screenshots/" + tr.getName() + ".png";
        File f = new File(screenshotPath);

        if (f.exists()) {
            try {
                logger.addScreenCaptureFromPath(screenshotPath);
            } catch (Exception e) {
                logger.fail("Failed to attach screenshot: " + e.getMessage());
            }
        }
    }

    @Override
    public void onTestSkipped(ITestResult tr) {
        logger = extent.createTest(tr.getName());
        logger.log(Status.SKIP, MarkupHelper.createLabel(tr.getName(), ExtentColor.ORANGE));
        logger.skip("Test skipped: " + tr.getThrowable());
    }

    @Override
    public void onFinish(ITestContext testContext) {
        extent.flush();
    }
}
