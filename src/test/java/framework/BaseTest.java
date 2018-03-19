package framework;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public abstract class BaseTest extends BaseEntity {
    private static final String STEP_MESSAGE_TEMPLATE = "-----==[ %s ]==-----";
    private static final String TEST_START_END_SEPARATOR = "*";
    protected int stepNumber = 1;

    @BeforeClass
    public void setUp() throws Exception {
        BaseDriver.getInstance();
    }

    @Test
    public void test() throws Throwable {
        try {
            wrapLogMessage(String.format("*********************  Start Test case: '%s' ********************", getClass()));
            runTest();
            wrapLogMessage(String.format("********************* End Test case: '%s' Passed! ***************", getClass()));
        }catch (Exception e){
            logError(">>>>>>>> TEST FAILED <<<<<<<<");
            throw e;
        }
    }

    private void wrapLogMessage(String message){
        String line = "";
        for(int i = 0; i < message.length(); i++){
            line += TEST_START_END_SEPARATOR;
        }
        logInfo(line);
        logInfo(message);
        logInfo(line);
    }

    @AfterClass
    public void tearDown() throws Exception {
        BaseDriver driver = BaseDriver.getInstance();
        driver.getDriver().quit();
        if(driver.getPlatform() == BaseDriver.Platform.iOS){
            driver.closeSimulatorIOS();
        }
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
