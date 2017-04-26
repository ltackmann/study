package selenium.runners;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@CucumberOptions(features = "java/selenium/features/Download.feature",
        glue = "selenium.glue",
        format = {"pretty"})
public class DownloadFeatureRunner extends AbstractTestNGCucumberTests {
}