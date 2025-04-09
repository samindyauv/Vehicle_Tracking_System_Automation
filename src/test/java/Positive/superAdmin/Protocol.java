package Positive.superAdmin;

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
        webSteps.loginSuperAdmin();
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

    //Edit Protocol

    @Test(priority = 3)
    public void filterProtocolByActiveStatus() throws InterruptedException {
        extentReportManager.startTest("Test Cases for Protocol Management", "<b> Filter Active Protocol </b>");
        extentReportManager.testSteps("<b><font color='blue'>Test Case : </font>TC03: Verify that the user can filter active protocols</b>");
        extentReportManager.testSteps("<b><font color='blue'>Test Steps : </font></b>Logged to System > Click Admin > Click Protocol > Click Filter > Select Active");
        webSteps.click("protocolFilterDropdown");
        webSteps.click("clickProtocolFilterActive");
        Assert.assertEquals("Active", webSteps.getText("activeStatusProtocol"));
    }

    @Test(priority = 4)
    public void filterProtocolsByInactiveStatus() throws InterruptedException {
        extentReportManager.startTest("Test Cases for Protocol Management", "<b> Filter Inactive Protocol </b>");
        extentReportManager.testSteps("<b><font color='blue'>Test Case : </font>TC04: Verify that the user can filter inactive protocols</b>");
        extentReportManager.testSteps("<b><font color='blue'>Test Steps : </font></b>Logged to System > Click Admin > Click Protocol > Click Filter > Select Inactive");
        webSteps.click("protocolFilterDropdown");
        webSteps.click("clickProtocolFilterInactive");
        Assert.assertEquals("Inactive", webSteps.getText("inactiveStatusProtocol"));
    }

    @Test(priority = 5)
    public void deactivateProtocolStatus() throws InterruptedException {
        extentReportManager.startTest("Test Cases for Protocol Management", "<b> Deactivate Protocol </b>");
        extentReportManager.testSteps("<b><font color='blue'>Test Case : </font>TC05: Verify that the user can deactivate a protocol</b>");
        extentReportManager.testSteps("<b><font color='blue'>Test Steps : </font></b>Logged to System > Click Admin > Click Protocol > Filter Activated Protocols > Click Deactivate");
        webSteps.click("protocolFilterDropdown");
        webSteps.click("clickProtocolFilterActive");
        webSteps.click("clickProtocolAction");
        webSteps.click("clickProtocolDeactivate");
        //webSteps.implicitWait("Toast_message");
        //Assert.assertEquals("Protocol updated successfully", webSteps.getText("Toast_message"));
    }

    @Test(priority = 6)
    public void activateProtocolStatus() throws InterruptedException {
        extentReportManager.startTest("Test Cases for Protocol Management", "<b> Activate Protocol </b>");
        extentReportManager.testSteps("<b><font color='blue'>Test Case : </font>TC06: Verify that the user can activate a protocol</b>");
        extentReportManager.testSteps("<b><font color='blue'>Test Steps : </font></b>Logged to System > Click Admin > Click Protocol > Filter Inactive Protocols > Click Activate");
        webSteps.click("protocolFilterDropdown");
        webSteps.click("clickProtocolFilterInactive");
        webSteps.click("clickProtocolAction");
        webSteps.click("clickProtocolActivate");
        //webSteps.implicitWait("Toast_message");
        //Assert.assertEquals("Protocol updated successfully", webSteps.getText("Toast_message"));
    }

    @Test(priority = 6)
    public void searchprotocol() throws InterruptedException {
        extentReportManager.startTest("Test Cases for Protocol Management", "<b> Search Protocol </b>");
        extentReportManager.testSteps("<b><font color='blue'>Test Case : </font>TC07: Verify that the user can search for a protocol</b>");
        extentReportManager.testSteps("<b><font color='blue'>Test Steps : </font></b>Logged to System > Click Admin > Click Protocol > Type in Search");
        webSteps.type("Protocol_1","searchProtocolField");
        webSteps.waiting();
        Assert.assertEquals("Protocol_1", webSteps.getText("protocolSearchResult"), "Passed");
    }
}
