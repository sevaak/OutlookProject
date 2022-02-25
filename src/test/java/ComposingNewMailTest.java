import Factory.OutlookFactory;
import io.appium.java_client.windows.WindowsDriver;
import io.appium.java_client.windows.WindowsElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import static constants.EmailComposeConstants.mail;
import static constants.EmailComposeConstants.subjectText;
import static constants.LandingPageConstants.*;

public class ComposingNewMailTest {
    private WindowsDriver outlookSession = null;
    private WindowsDriver newMailSession = null;
    public OutlookFactory outlookFactory = null;

    @BeforeClass
    public void setup() {
        System.out.println("setup");
        DesiredCapabilities outlookCapabilities = new DesiredCapabilities();
        outlookCapabilities.setCapability(appCap, outlookPath);
        try {
            outlookSession = new WindowsDriver<WindowsElement>(new URL(localHost), outlookCapabilities);
            outlookFactory = new OutlookFactory(outlookSession);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void newEmail() throws MalformedURLException, InterruptedException {
        List<String> windowHandleLists = outlookSession.getWindowHandles().stream().toList();
        System.out.println(windowHandleLists.size());
        for (int i = 0; i < windowHandleLists.size(); i++) {
            System.out.println("Window Handles before opening: " + windowHandleLists.stream().toList().get(i));
        }
        outlookFactory.contextElement().click();
        outlookFactory.emailComposeButton().click();
        Thread.sleep(1000);



        List<String> mailWindowHandles = outlookSession.getWindowHandles().stream().toList();
        System.out.println(mailWindowHandles.size());
        for (int i = 0; i < mailWindowHandles.size(); i++) {
            System.out.println(mailWindowHandles.get(i));
        }

        String handleElement = mailWindowHandles.get(0);
        DesiredCapabilities newEmailCaps = new DesiredCapabilities();
        newEmailCaps.setCapability("appTopLevelWindow", handleElement);
        newMailSession = new WindowsDriver<WindowsElement>(new URL("http://127.0.0.1:4723"), newEmailCaps);
        Assert.assertNotNull(newMailSession);
        outlookFactory = new OutlookFactory(newMailSession);
        outlookFactory.mailToEditText().sendKeys(mail);
        outlookFactory.subjectEditText().sendKeys(subjectText);
        outlookFactory.mailText().click();
        outlookFactory.mailText().sendKeys("It Was Send By Automated Software");
        outlookFactory.mailSendButton().click();


    }
}
