package example_test.android.pages;

import framework.BasePage;
import framework.elements.TextField;
import framework.elements.TextView;
import org.openqa.selenium.By;

public class AstroPage extends BasePage {
    private TextView textViewSearch = new TextView(By.id("menu_search"),"Search");
    private TextField textFieldSearch = new TextField(By.id("search_src_text"),"Search");

    public AstroPage() {
        super(By.className("android.widget.TextView"),"Astro");
    }

    public TextView getTextViewSearch(){return textViewSearch;}
    public TextField getTextFieldSearch() {
        return textFieldSearch;
    }
}
