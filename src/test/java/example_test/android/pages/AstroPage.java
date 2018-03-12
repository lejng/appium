package example_test.android.pages;

import framework.BasePage;
import framework.elements.Button;
import org.openqa.selenium.By;

public class AstroPage extends BasePage {
    private Button btbLocations = new Button(By.id("locations_header"), "Locations");
    public AstroPage() {
        super(By.className("android.widget.TextView"),"Astro");
    }

    public Button getBtbLocations() {
        return btbLocations;
    }
}
