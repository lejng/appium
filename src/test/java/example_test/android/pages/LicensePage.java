package example_test.android.pages;

import framework.BasePage;
import framework.elements.Button;
import org.openqa.selenium.By;

public class LicensePage extends BasePage {
    private Button btbAccept = new Button(By.id("btn_one"),"Accept");

    public LicensePage() {
        super(By.id("eula_text"),"License");
    }

    public Button getBtbAccept() {
        return btbAccept;
    }
}
