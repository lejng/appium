package example_test.ios;

import example_test.ios.pages.AddRecipePage;
import example_test.ios.pages.RecipePage;
import example_test.ios.pages.RecipesPage;
import framework.BaseDriver;
import framework.BaseTest;
import framework.elements.TextField;
import org.openqa.selenium.By;

public class ExampleIOSTest extends BaseTest {
    @Override
    public void runTest() {
        String recipeName = "Fish";

        logStep(String.format("Add new recipe with name: '%s'", recipeName));
        RecipesPage recipesPage = new RecipesPage();
        recipesPage.getBtbAdd().click();
        AddRecipePage addRecipePage = new AddRecipePage();
        addRecipePage.getTextFieldEnterName().typeText(recipeName);
        addRecipePage.getBtbSave().click();

        logStep(String.format("Check recipe was added with name: '%s'", recipeName));
        RecipePage recipePage = new RecipePage(By.name(recipeName), recipeName);
        assertTrue(recipeName.equals(recipePage.getTextViewRecipeName().getText()),"Recipe added is not correct");

        logStep("Open recipes page");
        recipePage.getBtbRecipes().click();

        logStep(String.format("Delete recipe with name: '%s'", recipeName));
        recipesPage = new RecipesPage();
        recipesPage.deleteRecipeByName(recipeName);
        assertTrue(!recipesPage.getRecipeByText(recipeName).isElementExist(),String.format("Recipe with name '%s' is not delete", recipeName));


    }
}
