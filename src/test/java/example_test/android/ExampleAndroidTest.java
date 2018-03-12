package example_test.android;

import example_test.android.pages.AstroPage;
import framework.BaseTest;
import framework.elements.Button;
import org.openqa.selenium.By;

public class ExampleAndroidTest extends BaseTest {
    @Override
    public void runTest() {
        logStep("Click by button");
        Button btbAccept = new Button(By.id("btn_one"),"Accept");
        btbAccept.click();
        new Button(By.id("button2"),"Accept").click();
        new Button(By.id("button2"),"Accept").click();
        new Button(By.id("button1"),"Accept").click();
        AstroPage astroPage = new AstroPage();
        astroPage.getBtbLocations().click();
    }
}
