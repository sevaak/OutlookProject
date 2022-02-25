import Factory.OutlookFactory;
import io.appium.java_client.windows.WindowsDriver;
import io.appium.java_client.windows.WindowsElement;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import static constants.LandingPageConstants.*;

public class SearchingWithSubjectTest {
    private WindowsDriver outlookSession = null;
    private WindowsDriver newMailSession = null;
    private OutlookFactory outlookFactory = null;


    @BeforeClass
    public void setup() {
        System.out.println("setup");
        DesiredCapabilities outlookCapabilities = new DesiredCapabilities();
        outlookCapabilities.setCapability("ms:waitForAppLaunch",5);
        outlookCapabilities.setCapability(appCap, outlookPath);
        try {
            outlookSession = new WindowsDriver<WindowsElement>(new URL(localHost), outlookCapabilities);
            outlookFactory = new OutlookFactory(outlookSession);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }


    @Test
    public void searchIT() {
        findMailBySubject("Second Subject");
        System.out.println(outlookFactory.openedMailBody().getText());

    }


    public void findMailBySubject(String subjectName) {
        outlookFactory.menuInbox().click();
        outlookFactory.contextElement().click();
        List<WebElement> mailsList = outlookFactory.receivedMails();
        for (int i = 0; i < mailsList.size(); i++) {
//            System.out.println(mailsList.get(i).getAttribute("Name"));
            if (mailsList.get(i).getAttribute("Name").contains(", Subject " + subjectName)) {
//                System.out.println("SEARCHED SUBJECT " + mailsList.get(i).getAttribute("Name"));
                mailsList.get(i).click();
                outlookFactory.openedMail().click();
            }
        }
    }
}


