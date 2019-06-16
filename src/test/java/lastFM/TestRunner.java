package lastFM;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;


@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features",
        plugin = {"pretty", "html:target/cucumber", "junit:target/cucumber.xml"},
        glue = {"lastFM.Steps"}
)
public class TestRunner {
}
