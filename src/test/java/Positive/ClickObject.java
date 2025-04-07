package Positive;

import org.openqa.selenium.By;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utils.baseTest;
import utils.extentReportManager;

import java.io.IOException;
import java.time.Duration;

public class ClickObject extends baseTest {
    @BeforeMethod
    public void setUp() throws InterruptedException, IOException {

        loadUrl();
        webSteps.login();
        webSteps.waiting();
        extentReportManager.startTest("Map Interaction", "<b> Click Object in Map </b>");
        extentReportManager.testSteps("<b><font color='blue'>Test Case : </font>TC01: Verify clicking on an object in the map displays its details</b>");
        extentReportManager.testSteps("<b><font color='blue'>Test Steps : </font></b>Logged to System > Click Live Track > Click Object");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @Test
    public void loginWithValidCredentials() throws InterruptedException {
        extentReportManager.testSteps("Verifying Map Controls...");

        webSteps.zoomInMap();
        webSteps.dragTheMap(300, 200);
        driver.findElement(By.xpath("//div[contains(@style, 'touch-action: pan-x pan-y')]//child::img")).click();
        webSteps.waiting();
    }
}
