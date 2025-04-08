package Positive;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utils.baseTest;
import utils.extentReportManager;
import java.io.IOException;

public class POI extends baseTest{

    @BeforeMethod
    public void setUp() throws InterruptedException, IOException {
        loadUrl();
        webSteps.login();
        webSteps.waiting();
        webSteps.click("settings_Icon");
        webSteps.click("POI_tab");
    }

    @Test(priority = 1)
    public void createPOI() throws InterruptedException {
        extentReportManager.startTest("POI (Point of Interest) Management", "<b>Create POI</b>");
        extentReportManager.testSteps("<b><font color='blue'>Test Case : </font>TC01: Verify that the user can create a POI with valid information</b>");
        extentReportManager.testSteps("<b><font color='blue'>Test Steps : </font></b>Logged to System > Click Tool > Click POI > Add POI");

        webSteps.click("addPOI_button");
        webSteps.click("select_location");
        webSteps.clickOnPointOfMap(200, -150);
        webSteps.waitUntilElementToBeClickable("POI_name");
        webSteps.type("Bus Stop", "POI_name");
        webSteps.type("This is dummy description", "POI_description");
        webSteps.click("POI_icon");
        webSteps.scrollToElement("POI_saveBtn");
        webSteps.click("POI_saveBtn");
        Assert.assertEquals("Poi created successfully!", webSteps.getText("Toast_message"), "Passed");
    }
}
