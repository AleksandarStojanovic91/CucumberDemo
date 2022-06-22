package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage extends BasePage{

    public HomePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver,this);
    }

    @FindBy(css = "[title='Motori']")
    WebElement motori;
    @FindBy(css = "[title='Putnička vozila']")
    WebElement auto;

    public void goToMotori() throws InterruptedException {
        clickElement(motori);
    }

    public void goToAuto() throws InterruptedException {
        clickElement(auto);
    }

    public void goToVehicleType(String vehicleType) throws InterruptedException {
        switch (vehicleType){
            case "auto":{
//                goToAuto();
            }break;
            case "motor":{
                goToMotori();
            }break;
        }
    }
}