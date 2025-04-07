package Positive;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utils.baseTest;
import utils.extentReportManager;

import java.awt.*;
import java.io.IOException;

public class Protocol extends baseTest {
    @BeforeMethod
    public void setUp() throws IOException, InterruptedException {
        loadUrl();
        //webSteps.login();
        webSteps.waiting();
        webSteps.type("imeshbro@gmail.com", "customerEmailField");
        webSteps.type("123456@Cd", "customerPasswordField");
        webSteps.click("customerLoginButton");
        webSteps.waiting();
        webSteps.click("clickAdmin");
        webSteps.click("clickProtocol");
    }

    @Test(priority = 1)
    public void addProtocol() throws InterruptedException, AWTException {
        extentReportManager.startTest("Test Cases for Protocol Management", "<b> Add Protocol </b>");
        extentReportManager.testSteps("<b><font color='blue'>Test Case  : </font>TC01: Verify that the user can successfully add a protocol</b>");
        extentReportManager.testSteps("<b><font color='blue'>Test Steps : </font></b>Logged to System > Click Admin > Click Protocol > Click Add Protocol");
        webSteps.click("clickAddProtocol");
        webSteps.type(webSteps.generateRandomProtocolName(), "protocolNameField");
        webSteps.type(webSteps.generateRandomProtocolModelName(), "protocolModelNameField");
        webSteps.type(webSteps.generateRandomProtocolProtocol(), "protocolProtocolField");
        webSteps.select2("protocolConnectivityDropdown");
        webSteps.click("addProtocolButton");
        Assert.assertEquals("Protocol added successfully",webSteps.getText("ToastMessage"), "Passed");
    }
}
