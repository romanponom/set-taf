package pak;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.junit5.ScreenShooterExtension;
import com.epam.reportportal.junit5.ReportPortalExtension;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.screenshot;

@ExtendWith({ScreenShooterExtension.class, ReportPortalExtension.class})
public class Test1 {
    private static final Logger LOGGER = LogManager.getLogger(Test1.class);
    @BeforeAll
    public static void setUp() {
        Configuration.remote = "http://localhost:4444/wd/hub";
        Configuration.browser = "chrome";
        Configuration.browserSize = "1920x1080";
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("enableVNC", true);
        Configuration.browserCapabilities = capabilities;

    }
    @Test
    void qq () {
        LOGGER.info("ololo");
        open("https://ru.selenide.org/quick-start.html");
        screenshot("my_file_name");
        $(By.name("q")).val("selenide").pressEnter();
        $$("#ires .g").shouldHave(size(10));
        open("https://ru.selenide.org");

    }
}
