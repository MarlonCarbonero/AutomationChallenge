package runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = {"src/test/java/Features"},
        glue = {"steps"},
        plugin = {"pretty",
                "html:reports/htmlreport.html"},
        publish = true
)


public class Runner {
}