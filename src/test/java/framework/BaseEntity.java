package framework;

import org.apache.log4j.Logger;
import org.testng.Assert;

public abstract class BaseEntity {
    protected Logger logger = Logger.getLogger(this.getClass());

    public void logInfo(String message){
        logger.info(message);
    }

    public void logError(String message){
        logger.warn("!!!! FAIL :: " + message);
    }

    public void assertFail(String message){
        logError(message);
        Assert.fail(message);
    }

    public void assertTrue(Boolean condition, String message){
        if(!condition) {
            logError(message);
        }
        Assert.assertTrue(condition, message);
    }

    public void assertNull(Object object, String message){
        if(object != null) {
            logError(message);
        }
        Assert.assertNull(object, message);
    }
}
