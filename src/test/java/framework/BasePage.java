package framework;

import framework.utils.SmartWait;
import org.openqa.selenium.By;

public abstract class BasePage extends BaseEntity {
    protected By uniqueLocator;
    protected String name;

    public BasePage(By uniqueLocator, String name){
        this.uniqueLocator = uniqueLocator;
        this.name = name;
        AssertPageExist();
    }

    public boolean isPageExist(){
        return BaseDriver.getInstance().getDriver().findElements(uniqueLocator).size() > 0;
    }

    public void waitIsPageExist(){
        SmartWait.waitFor(() -> isPageExist(), SmartWait.Time.TEN_SECONDS.getTime(), SmartWait.Time.ONE_SECONDS.getTime());
    }

    public void AssertPageExist(){
        waitIsPageExist();
        if(!isPageExist()){
            assertFail(String.format("Page '%s' is not exist", name));
        }
    }
}
