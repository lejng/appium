package framework;

import io.appium.java_client.MobileElement;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import java.io.File;
import java.net.URL;

public class BaseDriver {
    private static BaseDriver instance;
    private WebDriver driver;

    private BaseDriver(){
        initDriver();
    }

    public static BaseDriver getInstance(){
        if(instance == null){
            instance = new BaseDriver();
        }
        return instance;
    }

    private void initDriver(){
        File app = new File("src/test/resources/apps/Recipes.app");
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(MobileCapabilityType.BROWSER_NAME, "");
        capabilities.setCapability(MobileCapabilityType.APPIUM_VERSION, "1.7.2");
        capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "11.2");
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "iPhone 8 Plus");
        capabilities.setCapability(MobileCapabilityType.APP, app.getAbsolutePath());
        try {
            driver = new IOSDriver<MobileElement>(new URL("http://0.0.0.0:4723/wd/hub"), capabilities);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public WebDriver getDriver(){
        return driver;
    }
}
