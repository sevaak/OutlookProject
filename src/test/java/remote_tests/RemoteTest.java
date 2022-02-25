package remote_tests;

import Factory.OutlookFactory;
import io.appium.java_client.windows.WindowsDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import static constants.LandingPageConstants.appCap;
import static constants.LandingPageConstants.outlookPath;

public class RemoteTest {
    private WindowsDriver outlookSession;

    private OutlookFactory outlookFactory;

    @BeforeClass
    public void setup() {
        System.out.println("setup");
        DesiredCapabilities outlookCapabilities = new DesiredCapabilities();
        outlookCapabilities.setCapability(appCap, outlookPath);
        outlookCapabilities.setCapability("ms:waitForAppLaunch", 5);
        try {
            outlookSession = (WindowsDriver)(new WindowsDriver(new URL("http://10.32.0.12:4723/wd/hub"), outlookCapabilities));
//            outlookSession = new WindowsDriver<WindowsElement>(new URL("http://10.32.0.12:4723/wd/hub"), outlookCapabilities);
            outlookSession.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testing(){

    }
}
