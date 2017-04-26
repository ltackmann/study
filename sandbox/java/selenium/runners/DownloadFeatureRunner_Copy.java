package selenium.runners;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@CucumberOptions(features = "java/selenium/features/Download_Copy.feature",
        glue = "selenium.glue",
        format = {"pretty"})
public class DownloadFeatureRunner_Copy extends AbstractTestNGCucumberTests {
}
