package Positive.superAdmin;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utils.baseTest;
import utils.extentReportManager;

import java.awt.*;
import java.io.IOException;

public class Customer extends baseTest{

    @BeforeMethod
    public void setUp() throws IOException, InterruptedException {
        loadUrl();
        webSteps.loginSuperAdmin();
        webSteps.waiting();
        webSteps.click("clickAdmin");
        webSteps.click("click_customer");
    }

    @Test(priority = 1)
    public void createSubAccount() throws InterruptedException, AWTException {
        extentReportManager.startTest("Test Cases for Customer Management", "<b> Add Customer </b>");
        extentReportManager.testSteps("<b><font color='blue'>Test Case  : </font>TC01: Verify that the user can successfully add a customer</b>");
        extentReportManager.testSteps("<b><font color='blue'>Test Steps : </font></b>Logged to System > Click Admin > Click Customer > Click Add Customer");
        webSteps.click("click_addCustomer");
        webSteps.type("SamindyaX", "sc_companyName");
        webSteps.type("REG04451", "sc_regCode");
        webSteps.type("971501234567","sc_companyNum");
        webSteps.type("TAX1247525", "sc_taxNum");
        webSteps.type("samindyax@gmail.com", "sc_email");
        webSteps.type("971501234567","sc_phoneNum");
        webSteps.type("971501234567","sc_whatsappNum");
        webSteps.select("sc_country", 2, 1);
        webSteps.select("sc_state", 3, 1);
        webSteps.uploadFile("profile.jpg", "sc_profileImage");
        webSteps.scrollToElement("sc_state");
        webSteps.uploadFile("testing.pdf", "sc_document");
        webSteps.type("Samindya", "scp_firstName");
        webSteps.type("Vass", "scp_lastName");
        webSteps.type("12345@Aa", "scp_password");
        webSteps.type("12345@Aa", "scp_confPassword");
        webSteps.type("samindya@gmail.com", "scp_email");
        webSteps.type("971501234567","scp_phoneNum");
        webSteps.type("971501234567","scp_whatsappNum");
        webSteps.select("scp_country", 2, 1);
        webSteps.select("scp_state", 3, 1);
        webSteps.uploadFile("profile.jpg", "sc_profileImage");
        Thread.sleep(100);
        webSteps.scrollToElement("scf_AddBtn");
        webSteps.click("scf_AddBtn");
        //Assert.assertEquals("Protocol added successfully",webSteps.getText("ToastMessage"), "Passed");
    }

    @Test(priority = 3)
    public void filterCustomerByActiveStatus() throws InterruptedException {
        extentReportManager.startTest("Test Cases for Customer Management", "<b> Filter Active Customer </b>");
        extentReportManager.testSteps("<b><font color='blue'>Test Case : </font>TC03: Verify that the user can filter active customer</b>");
        extentReportManager.testSteps("<b><font color='blue'>Test Steps : </font></b>Logged to System > Click Admin > Click Customer > Click Filter > Select Active");
        webSteps.click("customerFilterDropdown");
        webSteps.click("clickCustomerFilterActive");
        Assert.assertEquals("Active", webSteps.getText("activeStatusCustomer"));
    }

    @Test(priority = 4)
    public void filterCustomerByInactiveStatus() throws InterruptedException {
        extentReportManager.startTest("Test Cases for Customer Management", "<b> Filter Inactive Customer </b>");
        extentReportManager.testSteps("<b><font color='blue'>Test Case : </font>TC04: Verify that the user can filter inactive customer</b>");
        extentReportManager.testSteps("<b><font color='blue'>Test Steps : </font></b>Logged to System > Click Admin > Click Customer > Click Filter > Select Inactive");
        webSteps.click("customerFilterDropdown");
        webSteps.click("clickCustomerFilterInactive");
        Assert.assertEquals("Inactive", webSteps.getText("inactiveStatusCustomer"));
    }

    @Test(priority = 5)
    public void deactivateCustomerStatus() throws InterruptedException {
        extentReportManager.startTest("Test Cases for Customer Management", "<b> Deactivate Customer </b>");
        extentReportManager.testSteps("<b><font color='blue'>Test Case : </font>TC05: Verify that the user can deactivate a customer</b>");
        extentReportManager.testSteps("<b><font color='blue'>Test Steps : </font></b>Logged to System > Click Admin > Click Customer > Filter Activated Customers > Click Deactivate");
        webSteps.click("customerFilterDropdown");
        webSteps.click("clickCustomerFilterActive");
        webSteps.click("clickCustomerAction");
        webSteps.click("clickCustomerDeactivate");
        //webSteps.implicitWait("Toast_message");
        //Assert.assertEquals("Protocol updated successfully", webSteps.getText("Toast_message"));
    }

    @Test(priority = 6)
    public void activateCustomerStatus() throws InterruptedException {
        extentReportManager.startTest("Test Cases for Customer Management", "<b> Activate Customer </b>");
        extentReportManager.testSteps("<b><font color='blue'>Test Case : </font>TC06: Verify that the user can activate a customer</b>");
        extentReportManager.testSteps("<b><font color='blue'>Test Steps : </font></b>Logged to System > Click Admin > Click Customer > Filter Inactive Customer > Click Activate");
        webSteps.click("customerFilterDropdown");
        webSteps.click("clickCustomerFilterInactive");
        webSteps.click("clickCustomerAction");
        webSteps.click("clickCustomerActivate");
        //webSteps.implicitWait("Toast_message");
        //Assert.assertEquals("Protocol updated successfully", webSteps.getText("Toast_message"));
    }

    @Test(priority = 7)
    public void searchCustomer() throws InterruptedException {
        extentReportManager.startTest("Test Cases for Customer Management", "<b> Search Customer </b>");
        extentReportManager.testSteps("<b><font color='blue'>Test Case : </font>TC07: Verify that the user can search a customer</b>");
        extentReportManager.testSteps("<b><font color='blue'>Test Steps : </font></b>Logged to System > Click Admin > Click Customer > Type in Search");
        webSteps.type("Glen","customerSearchField");
        webSteps.waiting();
        Assert.assertEquals("Glen Maxwell", webSteps.getText("customerSearchResult"), "Passed");
    }


}
