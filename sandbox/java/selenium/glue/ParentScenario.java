package selenium.glue;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import selenium.pages.DownloadPage;
import selenium.pages.MainPage;

import java.util.concurrent.TimeUnit;

public class ParentScenario {

    private WebDriver driver;

    protected DownloadPage downloadPage;
    protected MainPage mainPage;

    protected void startBrowser() {

        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        downloadPage = new DownloadPage(driver);
        mainPage = new MainPage(driver);
    }

    protected void navigateTo() {
        driver.navigate().to("http://docs.seleniumhq.org/");
    }

    protected void closeBrowser() {
        driver.quit();
    }

}