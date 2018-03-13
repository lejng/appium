package example_test.android;

import example_test.android.pages.AstroPage;
import framework.BaseTest;
import framework.elements.Button;
import org.openqa.selenium.By;

public class ExampleAndroidTest extends BaseTest {
    @Override
    public void runTest() {
        logStep("Skip start windows");
        new Button(By.id("btn_one"),"Accept").click();
        new Button(By.id("button2"),"Accept").click();
        new Button(By.id("button2"),"Accept").click();
        new Button(By.id("button1"),"Accept").click();

        logStep("Enter text 'Astro' in text field search");
        AstroPage astroPage = new AstroPage();
        astroPage.getTextViewSearch().click();
        astroPage.getTextFieldSearch().typeText("Astro");
    }
}
