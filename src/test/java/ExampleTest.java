import framework.BaseDriver;
import framework.BaseTest;
import org.openqa.selenium.By;

public class ExampleTest extends BaseTest {
    @Override
    public void runTest() {
        BaseDriver.getInstance().getDriver().findElement(By.name("Recipe 1")).click();
        try{Thread.sleep(3000);}catch (Exception e){}
    }
}
