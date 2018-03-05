package framework;

import io.appium.java_client.MobileElement;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import java.io.File;
import java.net.URL;

public class BaseDriver extends BaseEntity{
    private static BaseDriver instance;
    private static final String APP_PATH = "src/test/resources/apps/";
    private PropertiesHelper appiumProperty;
    private WebDriver driver;

    private BaseDriver(){
        appiumProperty = new PropertiesHelper("appium.properties");
        initDriver();
    }

    public static synchronized BaseDriver getInstance(){
        if(instance == null){
            instance = new BaseDriver();
        }
        return instance;
    }

    private DesiredCapabilities createCapability(){
        File app = new File(APP_PATH + appiumProperty.getProperty("appName"));
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(MobileCapabilityType.APPIUM_VERSION, appiumProperty.getProperty("appiumVersion"));
        capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, appiumProperty.getProperty("platformVersion"));
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, appiumProperty.getProperty("deviceName"));
        capabilities.setCapability(MobileCapabilityType.APP, app.getAbsolutePath());
        return capabilities;
    }

    private void initDriver(){
        try {
            URL appiumServerUrl = new URL(appiumProperty.getProperty("appiumServerUrl"));
            driver = new IOSDriver<MobileElement>(appiumServerUrl, createCapability());
        }catch (Exception e){
            assertFail("Cannot init driver: " + e.getMessage());
        }
    }

    public WebDriver getDriver(){
        return driver;
    }
}
