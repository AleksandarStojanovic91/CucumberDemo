package tests;

import io.qameta.allure.Allure;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import selenium_core.DriverManager;
import selenium_core.DriverManagerFactory;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.TimeUnit;

public class BaseTest {
    public WebDriver driver;
    DriverManager driverManager;

    public void initDriver(String browser, String wait) throws Exception {
        driverManager = DriverManagerFactory.getDriverManager(browser);
        driver = driverManager.getWebDriver();
        driver.manage().timeouts().implicitlyWait(Integer.parseInt(wait), TimeUnit.SECONDS);
    }

    public void quitDriver(String quit){
        if(quit.equalsIgnoreCase("Yes")){
            driverManager.quitWebDriver();
        }
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

    public void checkText(String actual, String expected) throws IOException {
        reportScreenshot(actual, "Expected Text");
        Assert.assertEquals(actual,expected);
    }
}
