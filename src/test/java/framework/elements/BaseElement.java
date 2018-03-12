package framework.elements;

import framework.BaseDriver;
import framework.BaseEntity;
import framework.utils.SmartWait;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public abstract class BaseElement extends BaseEntity{
    protected String name;
    protected By locator;
    protected WebElement element;

    public BaseElement(By locator, String name){
        this.locator = locator;
        this.name = name;
    }

    public boolean isElementExist(){
        return BaseDriver.getInstance().getDriver().findElements(locator).size() > 0;
    }

    public void waitForElementExist(){
        SmartWait.waitFor(() -> isElementExist(), SmartWait.Time.TEN_SECONDS, SmartWait.Time.ONE_SECONDS);
    }

    protected void initElement(){
        if(element == null) {
            waitForElementExist();
            if (!isElementExist()) {
                assertFail(String.format("%s '%s' :: is not found", getElementType(), name));
            }
            element = BaseDriver.getInstance().getDriver().findElement(locator);
        }
    }

    public void click(){
        initElement();
        logInfo(String.format("%s '%s' :: Clicking", getElementType(), name));
        element.click();
    }

    public String getText(){
        initElement();
        logInfo(String.format("%s '%s' :: Getting text", getElementType(), name));
        return element.getText();
    }

    public String getElementType(){
        return getClass().getSimpleName();
    }
}
