package example_test.ios.pages;

import framework.BasePage;
import framework.elements.Button;
import org.openqa.selenium.By;

public class RecipesPage extends BasePage {
    private Button btbAdd = new Button(By.name("Add"),"Add");
    public RecipesPage() {
        super(By.name("Recipes"), "Recipes");
    }

    public Button getBtbAdd() {
        return btbAdd;
    }

    public Button getRecipeByText(String text){
        return new Button(By.name(text), text);
    }
}
