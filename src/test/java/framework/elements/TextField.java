package framework.elements;

import org.openqa.selenium.By;

public class TextField extends BaseElement {

    public TextField(By locator, String name) {
        super(locator, name);
    }

    public void typeText(String text){
        initElement();
        logInfo(String.format("%s '%s' :: Typing text '%s'", getElementType(), name, text));
        element.clear();
        element.sendKeys(text);
    }
}
