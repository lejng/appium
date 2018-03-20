package framework;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import java.io.File;

public class BaseDriver extends BaseEntity {
    public final int DEFAULT_TIMEOUT_FOR_WAIT_ELEMENT;
    public final int DEFAULT_TIMEOUT_FOR_WAIT_PAGE_EXIST;
    private static final String APP_PATH = "src/test/resources/apps/";
    private static BaseDriver instance;
    private PropertiesHelper appiumProperty;
    private WebDriver driver;

    private BaseDriver(){
        appiumProperty = new PropertiesHelper("appium.properties");
        initDriver();
        DEFAULT_TIMEOUT_FOR_WAIT_ELEMENT = appiumProperty.getProperty("defaultTimeoutForWaitElement",20000);
        DEFAULT_TIMEOUT_FOR_WAIT_PAGE_EXIST = appiumProperty.getProperty("defaultTimeoutForWaitPageExist",20000);
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
            switch (getPlatform()) {
                case Android:
                    initAndroidDriver();
                    break;
                case iOS:
                    initIOSDriver();
                    break;
            }
        }catch (Exception e){
            assertFail("Cannot create driver: " + e.getMessage());
        }
    }

    public Platform getPlatform(){
        return Platform.valueOf(appiumProperty.getProperty("platform"));
    }

    private void initIOSDriver() throws Exception {
        driver = new IOSDriver<MobileElement>(startAppiumServer(), createCapability());
    }

    private void initAndroidDriver() throws Exception {
        DesiredCapabilities capabilities = createCapability();
        capabilities.setCapability(AndroidMobileCapabilityType.AVD, appiumProperty.getProperty("avd"));
        driver = new AndroidDriver<MobileElement>(startAppiumServer(), capabilities);
    }

    private AppiumDriverLocalService startAppiumServer(){
        AppiumServiceBuilder builder = new AppiumServiceBuilder().
                withIPAddress(appiumProperty.getProperty("appiumServerAddress")).
                usingPort(Integer.parseInt(appiumProperty.getProperty("appiumServerPort")));
        return AppiumDriverLocalService.buildService(builder);
    }

    public void closeSimulatorIOS(){
        String kill[] = {"killall","Simulator"};
        try {
            Runtime.getRuntime().exec(kill);
        }catch (Exception e){
            logError("Cannot close ios simulator: " + e.getMessage());
        }
    }

    public WebDriver getDriver(){
        return driver;
    }

    public enum Platform{
        Android,
        iOS
    }
}
