package steps;

import excel.ExcelReader;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Parameters;
import pages.HomePage;
import pages.MotoriPage;
import pages.MotoriSearchResultsPage;
import tests.BaseTest;

import java.io.IOException;
import java.util.Map;

public class PASteps extends BaseTest {

    String BROWSER = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest().getParameter("BROWSER");
    String WAIT = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest().getParameter("WAIT");
    String QUIT = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest().getParameter("QUIT");

    Map<String,String> data;

    @Before
    public void cucumberBefore() throws Exception {
        initDriver(BROWSER,WAIT);
    }

    @After
    public void cucumberAfter() {
        quitDriver(QUIT);
    }

    @Given("I am on polovni automobili app")
    public void iAmOnPolovniAutomobiliApp(){
        driver.get("https://www.polovniautomobili.com");
    }

    @When("I select vehicle type")
    public void iSelectVehicleType() throws InterruptedException {
        new HomePage(driver).goToVehicleType(data.get("VehicleType"));
        Thread.sleep(2000);
    }

    @And("I select model")
    public void iSelectModel() throws InterruptedException {
        new MotoriPage(driver).selectModel(data.get("Model"),data.get("VehicleType"));
    }

    @When("I click search")
    public void iClickSearch() throws InterruptedException {
        new MotoriPage(driver).search();
    }

    @Then("I should see search results")
    public void iShouldSeeSearchResults() throws IOException {
        checkText(new MotoriSearchResultsPage(driver).getHeaderText(),data.get("Result"));
    }

    @And("I select brand")
    public void iSelectBrand() throws InterruptedException {
        new MotoriPage(driver).selectBrand(data.get("Brand"));
    }

    @And("I get test data from {string} {string} {string}")
    public void iGetTestDataFrom(String file, String sheet, String column) throws IOException {
        file = "src/test/testData/" + file + ".xlsx";
//        data = new ExcelReader().getRowData(file,sheet,row);
        data = new ExcelReader().getColumnData(file,sheet,column);
        System.out.println(data.get("TC_ID"));
    }
}