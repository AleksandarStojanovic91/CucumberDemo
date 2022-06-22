package selenium_core;

import org.openqa.selenium.WebDriver;

public abstract class DriverManager {
    WebDriver driver;

    abstract void createWebDriver();

    public void quitWebDriver(){
        if(driver != null) {
            driver.quit();
            driver = null;
        }
    }

    public WebDriver getWebDriver(){
        if(driver == null){
            createWebDriver();
        }
        return driver;
    }
}
