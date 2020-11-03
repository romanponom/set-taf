package ui.test;

import com.codeborne.selenide.junit5.ScreenShooterExtension;
import com.epam.reportportal.junit5.ReportPortalExtension;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.extension.ExtendWith;

import static com.codeborne.selenide.Selenide.open;

@ExtendWith({ScreenShooterExtension.class, ReportPortalExtension.class})
public abstract class AbstractTest {
    private static final Logger LOGGER = LogManager.getLogger(AbstractTest.class);

    @BeforeAll
    public static void setUp() {
        //Configuration.remote = "http://localhost:4444/wd/hub";
        //Configuration.browser = "chrome";
        //Configuration.browserSize = "1920x1080";
        //DesiredCapabilities capabilities = new DesiredCapabilities();
        //capabilities.setCapability("enableVNC", true);
        //Configuration.browserCapabilities = capabilities;
        LOGGER.info("Open web app");
        open("http://localhost:8082");
    }
}
