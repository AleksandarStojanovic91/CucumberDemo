package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MotoriPage extends BasePage{

    public MotoriPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver,this);
    }

    @FindBy(css = "[title=' Sve marke']")
    WebElement sveMarke;
    @FindBy(css = "#modeltxt")
    WebElement model;
    @FindBy(css = "[title=' Svi modeli']")
    WebElement modelAutoP;
    @FindBy(css = "[name='submit_1']")
    WebElement search;

    public void selectBrand(String brandText) throws InterruptedException {
        clickElement(sveMarke);
        clickElement(driver.findElement(By.xpath("//li/label[text()='"+brandText+"']")));
    }

    public void selectModel(String modelText, String vehicleType) throws InterruptedException {
        if(vehicleType.equalsIgnoreCase("auto")){
            clickElement(modelAutoP);
            clickElement(driver.findElement(By.xpath("//li/label[contains(text(),'"+modelText+"')]")));
        }else {
            typeText(model, modelText);
        }
    }

    public void search() throws InterruptedException {
        clickElement(search);
    }
}
