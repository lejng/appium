package example_test;

import framework.BaseDriver;
import framework.BaseTest;
import org.openqa.selenium.By;

public class ExampleAndroidTest extends BaseTest {
    @Override
    public void runTest() {
        logStep("Click by button");
        BaseDriver.getInstance().getDriver().findElement(By.id("btn_one")).click();
        try{Thread.sleep(3000);}catch (Exception e){}
    }
}
