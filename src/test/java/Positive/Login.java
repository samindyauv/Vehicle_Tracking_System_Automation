package Positive;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utils.baseTest;
import utils.extentReportManager;

import java.io.IOException;

public class Login extends baseTest {
    @BeforeMethod
    public void setUp() throws InterruptedException, IOException {

        loadUrl();
        extentReportManager.testSteps("<b><font color='blue'>Opened the application URL</font></b>");
        extentReportManager.startTest("Login with Valid Credentials", "<b>Login with Valid Credentials</b>");

        webSteps.login();
        extentReportManager.testSteps("Open URL > Enter valid credentials > Click login");
        webSteps.waiting();
    }

    @Test
    public void loginWithValidCredentials() throws InterruptedException {
        boolean urlVerification = driver.getCurrentUrl().contains("live-tracking");
        Assert.assertTrue(urlVerification, "Expecting login success but not navigated to dashboard");

//        Assert.assertEquals("Successfully logged in", webSteps.getText("successMessage"), "Passed");
//        System.out.println("Test passed: Actual and expected messages match!");
    }
}
