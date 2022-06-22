package pages;

import io.qameta.allure.Allure;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;

public class BasePage {
    WebDriver driver;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    public void clickElement(WebElement element, String elementName) {
        WebDriverWait wait = new WebDriverWait(driver,  30);
        wait.until(ExpectedConditions.visibilityOf(element));
        wait.until(ExpectedConditions.elementToBeClickable(element));

        scrollToWebElement(element);

        Actions actions = new Actions(driver);
        actions.moveToElement(element)
                .click()
                .build()
                .perform();

        System.out.println("Clicked: " + elementName);
    }

    public void clickElement(WebElement element) throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.visibilityOf(element));
        wait.until(ExpectedConditions.elementToBeClickable(element));

        scrollToWebElement(element);

        Actions actions = new Actions(driver);
        actions.moveToElement(element)
                .click()
                .build()
                .perform();
        Thread.sleep(500);
    }

    public void typeText(WebElement element,String text, String elementName) {
        WebDriverWait wait = new WebDriverWait(driver,  30);
        wait.until(ExpectedConditions.visibilityOf(element));
        wait.until(ExpectedConditions.elementToBeClickable(element));

        scrollToWebElement(element);

        element.clear();
        element.sendKeys(text);

        System.out.println("Typed text: " + text + " in " + elementName);
    }

    public void typeText(WebElement element,String text) {
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.visibilityOf(element));
        wait.until(ExpectedConditions.elementToBeClickable(element));

        scrollToWebElement(element);

        element.clear();
        element.sendKeys(text);
    }

    public void typeText(WebElement element,Keys k) {
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.visibilityOf(element));
        wait.until(ExpectedConditions.elementToBeClickable(element));

        scrollToWebElement(element);

        element.clear();
        element.sendKeys(k);
    }

    public void selectElementByText(WebElement element, String text) {
//        WebDriverWait wait = new WebDriverWait(driver, 30);
//        wait.until(ExpectedConditions.visibilityOf(element));
//        wait.until(ExpectedConditions.elementToBeClickable(element));

//        scrollToWebElement(element);

        Select select = new Select(element);
        select.selectByVisibleText(text);
    }

    public String getText(WebElement element){
        WebDriverWait wait = new WebDriverWait(driver,  30);
        wait.until(ExpectedConditions.visibilityOf(element));

        return element.getText();
    }

    public void scrollToWebElement(WebElement element){
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true)",element);
    }

    public void takeScreenshot(String name) throws IOException {
        File file = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(file, new File("testResults/screenshots/" + name + ".png"));
    }

    public void reportScreenshot(String name, String desc) throws IOException {
        takeScreenshot(name);
        Path content = Paths.get("testResults/screenshots/" + name + ".png");
        try(InputStream is = Files.newInputStream(content)) {
            Allure.addAttachment(desc,is );
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
