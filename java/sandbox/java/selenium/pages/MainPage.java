package selenium.pages;

import org.openqa.selenium.WebDriver;
import selenium.framework.ParentPage;

public class MainPage extends ParentPage {

    public MainPage(WebDriver driver) {
        super(driver);
    }

    public void clickTab(String tab) {
        click(tab);
    }
}