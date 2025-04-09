package utils;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.io.File;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.time.Duration;
import java.util.Properties;
import java.util.concurrent.ThreadLocalRandom;

import static dataProviders.repositoryFileReader.constructElement;
import static dataProviders.repositoryFileReader.findElementRepo;

public class webSteps {
    protected static WebDriver driver;
    private final String emailSuperAdmin;
    private final String passwordSuperAdmin;
    private final String emailCustomer;
    private final String passwordCustomer;
    private final String emailSubCustomer;
    private final String passwordSubCustomer;


    public webSteps(WebDriver driver) {
        webSteps.driver = driver;

        // Load email and password from properties file
        Properties properties = propertyLoader.loadProperties("src/main/resources/dataset.properties");
        this.emailSuperAdmin = properties.getProperty("emailSuperAdmin");
        this.passwordSuperAdmin = properties.getProperty("passwordSuperAdmin");
        this.emailCustomer = properties.getProperty("emailCustomer");
        this.passwordCustomer = properties.getProperty("passwordCustomer");
        this.emailSubCustomer = properties.getProperty("emailSubCustomer");
        this.passwordSubCustomer = properties.getProperty("passwordSubCustomer");

    }

    public void loginSuperAdmin() throws InterruptedException {
        waiting();
        type(emailSuperAdmin, "customerEmailField");
        type(passwordSuperAdmin, "customerPasswordField");
        click("customerLoginButton");
        waiting();
    }

    public void loginCustomer() throws InterruptedException {
        waiting();
        type(emailCustomer, "customerEmailField");
        type(passwordCustomer, "customerPasswordField");
        click("customerLoginButton");
        waiting();
    }

    public void loginSubCustomer() throws InterruptedException {
        waiting();
        type(emailSubCustomer, "customerEmailField");
        type(passwordSubCustomer, "customerPasswordField");
        click("customerLoginButton");
        waiting();
    }

    // Common method to type text into an input field
    public void type(String text, String locator) throws InterruptedException {
        By xpath = constructElement(findElementRepo(locator));
        WebElement inputField = driver.findElement(xpath);
        inputField.clear();
        inputField.sendKeys(text);
        waiting();
    }


    // Common method to click an element
    public void click(String locator) throws InterruptedException {
        By xpath = constructElement(findElementRepo(locator));
        WebElement button =  driver.findElement(xpath);
        button.click();
        waiting();
    }

    // to click on any point of the main map
    public void clickOnPointOfMap(int x,int y) throws InterruptedException {
        By xpath = constructElement(findElementRepo("Main_Map"));
        WebElement map = driver.findElement(xpath);
        Actions actions = new Actions(driver);

        actions.moveToElement(map, x, y).click().perform();
        waiting();
    }

    public void zoomInMap() throws InterruptedException {
        By xpath = constructElement(findElementRepo("Main_Map"));
        WebElement map = driver.findElement(xpath);

        Actions actions = new Actions(driver);
        for (int i = 0; i < 3; i++) {  // Adjust zoom level (increase/decrease iterations)
            actions.moveToElement(map).sendKeys(Keys.chord(Keys.CONTROL, Keys.ADD)).perform();
            Thread.sleep(1000);
        }
        waiting();
        waiting();
    }

    public void dragTheMap(int x, int y) throws InterruptedException {
        By xpath = constructElement(findElementRepo("Main_Map"));
        WebElement map = driver.findElement(xpath);

        Actions actions = new Actions(driver);
        actions.moveToElement(map, x, y).click().perform();
        waiting();
        waiting();
    }


    // Common method to get text from an element
    public String getText(String locator) {
        By xpath = constructElement(findElementRepo(locator));
        return driver.findElement(xpath).getText();
    }

    // Method to wait 2000ms
    public void waiting() throws InterruptedException {
        Thread.sleep(2000);
    }

    // Method for scroll to given element
    public void scrollToElement(String locator) throws InterruptedException {
        By xpath = constructElement(findElementRepo(locator));
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(10000));
        WebElement webElement = wait.until(ExpectedConditions.presenceOfElementLocated(xpath));
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("arguments[0].scrollIntoView(true);", webElement);
        waiting();

    }

    // Method to generate random personal email address
    public String randomPersonalEmailAddress(){
        return ("example" + ThreadLocalRandom.current().nextInt(0, 10000) + "@gmail.com");
    }

    public void select(String locator, int loop, int bool) throws InterruptedException, AWTException {
        By xpath = constructElement(findElementRepo(locator));
        click(locator);

        Robot robot = new Robot();

        if(bool==1){
            for (int i=1;i<=loop; i++){
                robot.keyPress(KeyEvent.VK_DOWN);
                robot.keyRelease(KeyEvent.VK_DOWN);

                Thread.sleep(100);
            }

            robot.keyPress(KeyEvent.VK_ENTER);
            robot.keyRelease(KeyEvent.VK_ENTER);


        }else if(bool==0){
            robot.keyPress(KeyEvent.VK_ENTER);
            robot.keyRelease(KeyEvent.VK_ENTER);

        } else {
            System.out.println("The boolean value is invalid");
        }

        waiting();
    }

    public void MoveMap(String locator) throws InterruptedException {
        By xpath = constructElement(findElementRepo(locator));
        WebElement map = driver.findElement(xpath);

        Actions actions = new Actions(driver);

        actions.clickAndHold(map)
                .moveByOffset(-200, 0) // Move left (adjust offset based on need)
                .moveByOffset(0, -150) // Move up
                .release()
                .perform();


        waiting();

        actions.moveToElement(map).scrollByAmount(0, -500).perform();

        waiting();
    }

    // Method for wait until an element to be clickable
    public void waitUntilElementToBeClickable(String locator){
        By xpath = constructElement(findElementRepo(locator));
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.elementToBeClickable(xpath));
    }

    public String generateRandomProtocolName() {
        String randomProtocolName = "Protocol_T" + ThreadLocalRandom.current().nextInt(0, 100);
        return randomProtocolName;
    }
    public String generateRandomProtocolModelName() {
        String randomProtocolModelName = "Model_T" + ThreadLocalRandom.current().nextInt(0, 100);
        return randomProtocolModelName;
    }
    public String generateRandomProtocolProtocol() {
        String randomProtocolProtocol = "GT_T" + ThreadLocalRandom.current().nextInt(0, 100);
        return randomProtocolProtocol;
    }

    public void select2(String locator) throws InterruptedException, AWTException {
        By xpath = constructElement(findElementRepo(locator));
        click(locator);
        Robot robot = new Robot();
        robot.keyPress(KeyEvent.VK_DOWN);
        robot.keyRelease(KeyEvent.VK_DOWN);
        robot.keyPress(KeyEvent.VK_DOWN);
        robot.keyRelease(KeyEvent.VK_DOWN);
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
        waiting();
    }
    public void implicitWait(String locator){
        By xpath = constructElement(findElementRepo(locator));
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        try {

            wait.until(ExpectedConditions.presenceOfElementLocated(xpath));
        } catch (TimeoutException e) {
            System.out.println("Element not found after login: " + e.getMessage());

        }
    }

    // Common method to upload a file from resources folder
    public void uploadFile(String fileName, String locator) throws InterruptedException {
        click(locator);

        // Construct path to the image inside resources folder
        String resourcePath = "src/main/resources/assets/" + fileName;
        File file = new File(resourcePath);

        if (!file.exists()) {
            throw new RuntimeException("File not found: " + resourcePath);
        }

        // Get absolute path
        String absolutePath = file.getAbsolutePath();

        // Copy path to clipboard
        StringSelection selection = new StringSelection(absolutePath);
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(selection, null);

        try {
            waiting();
            Robot robot = new Robot();

            // Simulate Ctrl + V and Enter
            robot.keyPress(KeyEvent.VK_CONTROL);
            robot.keyPress(KeyEvent.VK_V);
            robot.keyRelease(KeyEvent.VK_V);
            robot.keyRelease(KeyEvent.VK_CONTROL);
            Thread.sleep(100);

            waiting();

            robot.keyPress(KeyEvent.VK_ENTER);
            robot.keyRelease(KeyEvent.VK_ENTER);

            waiting();

        } catch (AWTException e) {
            throw new RuntimeException(e);
        }
    }

}
