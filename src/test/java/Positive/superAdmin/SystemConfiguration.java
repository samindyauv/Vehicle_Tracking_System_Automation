package Positive.superAdmin;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utils.baseTest;
import utils.extentReportManager;

import java.awt.*;
import java.io.IOException;

public class SystemConfiguration extends baseTest {

    @BeforeMethod
    public void setUp() throws IOException, InterruptedException {
        loadUrl();
        webSteps.loginSuperAdmin();
        webSteps.waiting();
        webSteps.click("clickAdmin");
        webSteps.click("clickSystemConfiguration");
    }

    @Test(priority = 1)
    public void changeSystemConfiguration() throws InterruptedException, AWTException {
        extentReportManager.startTest("Test Cases for System Configuration", "<b> Change System Configuration </b>");
        extentReportManager.testSteps("<b><font color='blue'>Test Case  : </font>TC01: Verify that the user can successfully update system configuration</b>");
        extentReportManager.testSteps("<b><font color='blue'>Test Steps : </font></b>Logged to System > Click Admin > Click System Configuration");
        webSteps.scrollToElement("clickClearSystemConfiguration");
        webSteps.click("clickClearSystemConfiguration");
        webSteps.scrollToElement("systemConfiguration");
        webSteps.type("#46b031","movingStatusColor");
        webSteps.type("#d33b1c","stoppedStatusColor");
        webSteps.type("#e5dc55","idleStatusColor");
        webSteps.type("#b9b9b9","offlineStatusColor");
        webSteps.type("#000000","neverConnectedStatusColor");
        webSteps.waiting();
        webSteps.select2("IgnitionDetectionBy");
        webSteps.waiting();
        webSteps.select2("SpeedDetectionBy");
        //webSteps.select("IgnitionDetectionBy", 2, 1);
        //webSteps.select("SpeedDetectionBy ", 2, 1);
        webSteps.type("40","MinMovingSpeed");
        webSteps.type("15","MinFuelDifferencetoDetectFuelFillings");
        webSteps.type("10","MinFuelDifferencetoDetectFuelThefts");
        webSteps.select("DetectFuelChangeAfterStop ", 3, 2);
        webSteps.type("##d15be8","TailColor");
        webSteps.type("5","TailLength");
        webSteps.select("FuelMeasurement", 2, 1);
        webSteps.type("20000","LPrice");
        webSteps.type("200","CostPerLiter");
        webSteps.click("SystemConfigurationUpdateButton");
        webSteps.waiting();
        Assert.assertEquals("System configurations saved successfully!",webSteps.getText("ToastMessage"), "Passed");

    }
}
