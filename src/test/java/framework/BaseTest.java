package framework;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public abstract class BaseTest extends BaseEntity {
    private static final String STEP_MESSAGE_TEMPLATE = "-----==[ %s ]==-----";
    protected int stepNumber = 1;

    @BeforeClass
    public void setUp() throws Exception {
        logInfo(String.format(STEP_MESSAGE_TEMPLATE, "START TEST"));
        BaseDriver.getInstance();
    }

    @Test
    public void test() throws Throwable {
        runTest();
    }

    @AfterClass
    public void tearDown() throws Exception {
        BaseDriver.getInstance().getDriver().quit();
        BaseDriver.getInstance().stopAppiumServer();
        logInfo(String.format(STEP_MESSAGE_TEMPLATE, "END TEST"));
    }

    public void logStep(String message, int number){
        logInfo(String.format(STEP_MESSAGE_TEMPLATE, "Step " + number ));
        logInfo(String.format(STEP_MESSAGE_TEMPLATE, message));
    }

    public void logStep(String message){
        logStep(message, stepNumber++);
    }

    public abstract void runTest();
}
