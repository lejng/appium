package example_test.android.pages;

import framework.BasePage;
import framework.elements.Button;
import org.openqa.selenium.By;

public class WelcomePage extends BasePage {
    private Button btbGoToApp = new Button(By.id("button1"),"Go to app");

    public WelcomePage() {
        super(By.id("image_view"), "Welcome");
    }

    public Button getBtbGoToApp() {
        return btbGoToApp;
    }
}
