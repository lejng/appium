package example_test.ios.pages;

import framework.BasePage;
import framework.elements.Button;
import framework.elements.TextField;
import org.openqa.selenium.By;

public class AddRecipePage extends BasePage {
    private TextField textFieldEnterName = new TextField(By.name("Recipe Name"),"Recipe Name");
    private Button btbSave = new Button(By.name("Save"), "Save");

    public AddRecipePage() {
        super(By.name("Add Recipe"), "Add recipe");
    }

    public TextField getTextFieldEnterName() {
        return textFieldEnterName;
    }

    public Button getBtbSave() {
        return btbSave;
    }
}
