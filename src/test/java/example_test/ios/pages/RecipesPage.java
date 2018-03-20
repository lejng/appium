package example_test.ios.pages;

import framework.BasePage;
import framework.elements.Button;
import org.openqa.selenium.By;

public class RecipesPage extends BasePage {
    private Button btbAdd = new Button(By.name("Add"),"Add");
    private Button btbEdit = new Button(By.name("Edit"), "Edit");

    public RecipesPage() {
        super(By.name("Recipes"), "Recipes");
    }

    public Button getBtbAdd() {
        return btbAdd;
    }

    public Button getBtbEdit() {
        return btbEdit;
    }

    public Button getRecipeByText(String text){
        return new Button(By.name(text), text);
    }

    public void deleteRecipeByName(String name){
        btbEdit.click();
        new Button(By.name(String.format("Delete %s, -", name)), "Delete recipe " + name).click();
        new Button(By.name("Delete"), "Delete").click();
        new Button(By.name("Done"), "Done").click();
    }
}
