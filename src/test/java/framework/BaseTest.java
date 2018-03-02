package framework;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public abstract class BaseTest {

    @BeforeClass
    public void setUp() throws Exception {
        BaseDriver.getInstance();
    }

    @Test
    public void test() throws Throwable {
        runTest();
    }

    @AfterClass
    public void tearDown() throws Exception {
        BaseDriver.getInstance().getDriver().quit();
    }

    public abstract void runTest();
}
