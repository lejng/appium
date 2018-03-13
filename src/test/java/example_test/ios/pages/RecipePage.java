package example_test.ios.pages;

import framework.BasePage;
import framework.elements.TextView;
import org.openqa.selenium.By;

public class RecipePage extends BasePage{
    private TextView textViewRecipeName = new TextView(By.xpath("//XCUIElementTypeTable/XCUIElementTypeTextField[1]"), "Name");

    public RecipePage(By uniqueLocator, String name) {
        super(uniqueLocator, name);
    }

    public TextView getTextViewRecipeName() {
        return textViewRecipeName;
    }
}
