package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MotoriSearchResultsPage extends BasePage{

    public MotoriSearchResultsPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver,this);
    }

    @FindBy(css = "h1")
    WebElement header;

    public String getHeaderText(){
        return getText(header);
    }

}