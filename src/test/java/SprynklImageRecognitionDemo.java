import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.sprynkl.mobile.core.ImageRecognition;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

public class SprynklImageRecognitionDemo {
    private AppiumDriver driver;
    private WebDriverWait wait;
    private ImageRecognition imageRecognition;
    private File imgDir;

    @Before
    public void setUp() throws MalformedURLException, InterruptedException {
        DesiredCapabilities dc = new DesiredCapabilities();
        dc.setCapability(MobileCapabilityType.PLATFORM_VERSION, "9");
        dc.setCapability(MobileCapabilityType.PLATFORM_NAME, "android");
        dc.setCapability(MobileCapabilityType.DEVICE_NAME, "Google Pixel 2 (Android 9)");
        dc.setCapability("appPackage", "com.sprynkl");
        dc.setCapability("appActivity", "com.sprynkl.MainActivity");

        driver = new AndroidDriver(new URL("http://0.0.0.0:4723/wd/hub"), dc);
        wait = new WebDriverWait(driver, 10);

        //Sikuli settings
        imageRecognition = new ImageRecognition(driver);

        //location of screenshots
        File classpathRoot = new File(System.getProperty("user.dir"));
        imgDir = new File(classpathRoot, "src/main/resources");

        Thread.sleep(2000);
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void clickGetStartedButton() throws InterruptedException {
        String getstartedBtn = imgDir + "/get-started.png";

        imageRecognition.waitUntilImageExists(getstartedBtn, 30);
        imageRecognition.tapByImage(getstartedBtn);
        Thread.sleep(5000);
    }
}
