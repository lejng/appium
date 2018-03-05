package example_test;

import framework.BaseDriver;
import framework.BaseTest;
import org.openqa.selenium.By;

public class ExampleIOSTest extends BaseTest {
    @Override
    public void runTest() {
        logStep("Click by recipe");
        BaseDriver.getInstance().getDriver().findElement(By.name("Recipe 1")).click();
        try{Thread.sleep(3000);}catch (Exception e){}
    }
}
