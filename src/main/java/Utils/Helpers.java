package Utils;

import Factory.OutlookFactory;
import io.appium.java_client.windows.WindowsDriver;
import io.appium.java_client.windows.WindowsElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class Helpers {
    private WindowsDriver firstSessionDriver;
    private OutlookFactory outlookFactory;

    public Helpers(WindowsDriver oldDriver) {
        this.firstSessionDriver = oldDriver;

    }

    public WindowsDriver getNewEmailWindowFocus(WindowsDriver oldDriver) throws InterruptedException, MalformedURLException {
        outlookFactory = new OutlookFactory(oldDriver);
        List<String> windowHandleLists = oldDriver.getWindowHandles().stream().toList();
        System.out.println(windowHandleLists.size());
        for (int i = 0; i < windowHandleLists.size(); i++) {
            System.out.println("Window Handles before opening: " + windowHandleLists.stream().toList().get(i));
            outlookFactory.emailComposeButton().click();
            Thread.sleep(1000);
        }
        List<String> mailWindowHandles = oldDriver.getWindowHandles().stream().toList();
        System.out.println(mailWindowHandles.size());
        for (int i = 0; i < mailWindowHandles.size(); i++) {
            System.out.println(mailWindowHandles.get(i));
        }
        String handleElement = mailWindowHandles.get(0);
        DesiredCapabilities newEmailCaps = new DesiredCapabilities();
        newEmailCaps.setCapability("appTopLevelWindow", handleElement);

        WindowsDriver newDriver = new WindowsDriver<WindowsElement>(new URL("http://127.0.0.1:4723"), newEmailCaps);
        outlookFactory = new OutlookFactory(newDriver);
        return newDriver;
    }

    public WindowsDriver getMainSession(WindowsDriver oldDriver){
        List<String> mailWindowHandles = oldDriver.getWindowHandles().stream().toList();
        System.out.println(mailWindowHandles.size());
        for (int i = 0; i < mailWindowHandles.size(); i++) {
            System.out.println(mailWindowHandles.get(i));
        }

        return oldDriver;
    }
}
