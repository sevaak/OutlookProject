import Factory.OutlookFactory;
import Utils.Helpers;
import io.appium.java_client.windows.WindowsDriver;
import io.appium.java_client.windows.WindowsElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;

import static constants.EmailComposeConstants.mail;
import static constants.EmailComposeConstants.subjectText;
import static constants.LandingPageConstants.*;

public class MakingDraftEmailTest {
    private WindowsDriver outlookSession;
    private WindowsDriver newMailSession;
    private OutlookFactory outlookFactory;
    private Helpers helpers;

    @BeforeClass
    public void setup() {
        System.out.println("setup");
        DesiredCapabilities outlookCapabilities = new DesiredCapabilities();
        outlookCapabilities.setCapability(appCap, outlookPath);
        outlookCapabilities.setCapability("ms:waitForAppLaunch", 10);
        try {
            outlookSession = new WindowsDriver<WindowsElement>(new URL(localHost), outlookCapabilities);
            helpers = new Helpers(outlookSession);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void draftTest() throws MalformedURLException, InterruptedException {
        outlookFactory = new OutlookFactory(helpers.getNewEmailWindowFocus(outlookSession));
        outlookFactory.mailToEditText().sendKeys(mail);
        outlookFactory.subjectEditText().sendKeys(subjectText);
        outlookFactory.mailText().click();
        outlookFactory.mailText().sendKeys("[FOR DRAFT] It Was Send By Automated Software");
        outlookFactory.closeWindow().click();
        outlookFactory.yesButtonInPopUp().click();
        outlookFactory=new OutlookFactory(outlookSession);
        outlookFactory.menuDrafts().click();

    }

    @AfterClass
    public void quit() {
//        outlookSession.quit();
    }
}
