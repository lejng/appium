package framework;

import org.apache.log4j.Logger;
import org.testng.Assert;

public abstract class BaseEntity {
    protected Logger logger = Logger.getLogger(this.getClass());

    public void logInfo(String message){
        logger.info(message);
    }

    public void logError(String message){
        logger.error(message);
    }

    public void assertFail(String message){
        logError(message);
        Assert.fail(message);
    }
}