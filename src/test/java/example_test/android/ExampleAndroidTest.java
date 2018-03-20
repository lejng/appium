package example_test.android;

import example_test.android.pages.AstroPage;
import example_test.android.pages.LicensePage;
import example_test.android.pages.WelcomePage;
import framework.BaseTest;

public class ExampleAndroidTest extends BaseTest {
    @Override
    public void runTest() {
        String text = "DCIM";

        logStep("Accept license");
        new LicensePage().getBtbAccept().click();

        logStep("Skip welcome page");
        new WelcomePage().getBtbGoToApp().click();

        logStep("Enter text 'Astro' in text field search");
        AstroPage astroPage = new AstroPage();
        astroPage.getTextViewSearch().click();
        astroPage.getTextFieldSearch().typeText(text);
        String actualText = astroPage.getTextFieldSearch().getText();
        assertTrue(actualText.equals(text),String.format("Wrong entered text expected = '%s', actual = '%s'", text, actualText));
    }
}
