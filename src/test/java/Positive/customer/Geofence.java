package Positive.customer;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utils.baseTest;
import utils.extentReportManager;

import java.io.IOException;

public class Geofence extends baseTest {
    @BeforeMethod
    public void setUp() throws InterruptedException, IOException {
        loadUrl();
        webSteps.loginCustomer();
        webSteps.waiting();
        webSteps.click("settings_Icon");
        webSteps.click("Geofencing_tab");
    }

    @Test(priority = 1)
    public void CreatePolygonGeofence() throws InterruptedException {
        extentReportManager.startTest("Geofence Configuration Operations", "<b> Create Geofence – Polygon Shape </b>");
        extentReportManager.testSteps("<b><font color='blue'>Test Case : </font>TC01: Verify that the user can successfully create a geofence using a polygon shape</b>");
        extentReportManager.testSteps("<b><font color='blue'>Test Steps : </font></b>Logged to System > Click Tool > Click Geofencing > Add Geofence");
        webSteps.click("addGeo_button");
        webSteps.click("Type_ploygon");

        // choose the coordinates for geofence
        webSteps.clickOnPointOfMap(200, -110);
        webSteps.clickOnPointOfMap(238, -126);
        webSteps.clickOnPointOfMap(226, -168);
        webSteps.clickOnPointOfMap(174, -168);
        webSteps.clickOnPointOfMap(162, -126);
        webSteps.clickOnPointOfMap(200, -110); // Closing the polygon

        webSteps.waitUntilElementToBeClickable("Geo_name");
        webSteps.type("Restricted Area", "Geo_name");
        webSteps.click("Open_colorPicker");
        webSteps.click("Color");
        webSteps.scrollToElement("Geo_saveBtn");
        webSteps.click("Geo_saveBtn");

        Assert.assertEquals("Geofence has been created successfully!", webSteps.getText("Toast_message"));
    }

    @Test(priority = 2)
    public void deleteGeofence() throws InterruptedException {
        extentReportManager.startTest("Geofence Configuration Operations", "<b> Delete Geofence </b>");
        extentReportManager.testSteps("<b><font color='blue'>Test Case : </font>TC01: Verify the user can delete an existing geofence</b>");
        extentReportManager.testSteps("<b><font color='blue'>Test Steps : </font></b>Logged to System > Click Tool > Click Geofencing > Click Action > Click Delete");

        webSteps.click("Geo_sb_action_btn");
        webSteps.click("Geo_sb_delete_btn");
        webSteps.scrollToElement("confirmation_delete_btn");
        webSteps.click("confirmation_delete_btn");
        Assert.assertEquals("GeoFence deleted successfully", webSteps.getText("Toast_message"), "Passed");
    }

    @Test(priority = 3)
    public void CreateRoundedGeofence() throws InterruptedException {
        extentReportManager.startTest("Geofence Configuration Operations", "<b> Create Geofence – Circle Shape </b>");
        extentReportManager.testSteps("<b><font color='blue'>Test Case : </font>TC01: Verify that the user can create a geofence using a circular shape</b>");
        extentReportManager.testSteps("<b><font color='blue'>Test Steps : </font></b>Logged to System > Click Tool > Click Geofencing > Add Geofence");
        webSteps.click("addGeo_button");
        webSteps.click("Type_circle");

        // choose the coordinates for geofence
        webSteps.clickOnPointOfMap(250, -150);
        webSteps.clickOnPointOfMap(300, -130);// Closing the polygon

        webSteps.waitUntilElementToBeClickable("Geo_name");
        webSteps.type("Restricted Area01", "Geo_name");
        webSteps.click("Open_colorPicker");
        webSteps.click("Color");
        webSteps.scrollToElement("Geo_saveBtn");
        webSteps.click("Geo_saveBtn");

        Assert.assertEquals("Geofence has been created successfully!", webSteps.getText("Toast_message"));
    }
}
