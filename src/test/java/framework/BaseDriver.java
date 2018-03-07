package framework;

import framework.utils.SmartWait;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import java.io.File;
import java.io.IOException;
import java.net.URL;

public class BaseDriver extends BaseEntity {
    private static final String COMMAND_TEMPLATE_START_WIN_CMD = "cmd.exe /c start cmd.exe /k \"%s\"";
    private static final String COMMAND_TEMPLATE_START_APPIUM = "appium --address %s --port %s";
    private static final String APP_PATH = "src/test/resources/apps/";
    private static final String APPIUM_UTL_TEMPLATE = "http://%s:%s/wd/hub";
    private static BaseDriver instance;
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
        String platform = appiumProperty.getProperty("platform");
        try {
            startAppiumServer();
            switch (platform) {
                case "Android":
                    initAndroidDriver();
                    break;
                case "iOS":
                    initIOSDriver();
                    break;
            }
        }catch (Exception e){
            assertFail("Cannot create driver: " + e.getMessage());
        }
    }

    public void stopAppiumServer() {
        if(getOS().contains("win")) {
            try {
                Runtime.getRuntime().exec("taskkill /F /IM node.exe");
                Runtime.getRuntime().exec("taskkill /F /IM cmd.exe");
            } catch (IOException e) {
                logError("Error to stop appium server: " + e.getMessage());
            }
        }
    }

    private void startAppiumServer() throws Exception {
        String command = String.format(COMMAND_TEMPLATE_START_APPIUM,
                appiumProperty.getProperty("appiumServerAddress"),
                appiumProperty.getProperty("appiumServerPort"));
        if(getOS().contains("win")){
            Runtime.getRuntime().exec(String.format(COMMAND_TEMPLATE_START_WIN_CMD, command));
        }
        else if(getOS().contains("mac") || getOS().contains("unix")){
            Runtime.getRuntime().exec(command);
        }
        SmartWait.sleep(SmartWait.Time.TEN_SECONDS);
    }

    private String getOS(){
        return System.getProperty("os.name").toLowerCase();
    }

    private URL getAppiumUrl() throws Exception {
        URL appiumServerUrl = new URL(String.format(APPIUM_UTL_TEMPLATE,
                appiumProperty.getProperty("appiumServerAddress"),
                appiumProperty.getProperty("appiumServerPort")));
        return appiumServerUrl;
    }

    private void initIOSDriver() throws Exception {
        driver = new IOSDriver<MobileElement>(getAppiumUrl(), createCapability());
    }

    private void initAndroidDriver() throws Exception {
        driver = new AndroidDriver<MobileElement>(getAppiumUrl(), createCapability());
    }

    public WebDriver getDriver(){
        return driver;
    }
}
