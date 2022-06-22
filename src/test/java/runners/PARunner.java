package runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

@Test
@CucumberOptions(
        features = "src/test/java/features/PolovniAutomobili.feature",
        glue = "steps",
        plugin = {
                "io.qameta.allure.cucumber6jvm.AllureCucumber6Jvm",
                "rerun:testResults/failures.txt"
        }
)
public class PARunner extends AbstractTestNGCucumberTests {
    @Override
    @DataProvider(parallel = true)
    public Object[][] scenarios() {
        return super.scenarios();
    }
}