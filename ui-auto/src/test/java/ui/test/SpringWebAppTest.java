package ui.test;
import com.codeborne.selenide.junit5.ScreenShooterExtension;
import com.epam.reportportal.junit5.ReportPortalExtension;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;

import java.util.List;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;


public class SpringWebAppTest extends AbstractTest {
    private static final Logger LOGGER = LogManager.getLogger(SpringWebAppTest.class);

    @Test
    void addUserTest () {
        LOGGER.info("Add user");
        $(By.id("btn")).click();
        $(By.id("name")).sendKeys("Name");
        $(By.id("email")).sendKeys("email@gmail.com");
        $(By.id("submit")).click();

        LOGGER.info("Verify user added");
        LOGGER.info("Verify headers");
        $("h2").shouldHave(exactText("Users"));
        $(By.xpath("//th[1]")).shouldHave(exactText("Name"));
        $(By.xpath("//th[2]")).shouldHave(exactText("Email"));
        $(By.xpath("//th[3]")).shouldHave(exactText("Edit"));
        $(By.xpath("//th[4]")).shouldHave(exactText("Delete"));

        LOGGER.info("Verify added user");
        $(By.xpath("//td[1]")).shouldHave(exactText("Name"));
        $(By.xpath("//td[2]")).shouldHave(exactText("email@gmail.com"));
        $(By.xpath("//td[3]")).shouldHave(exactText("Edit"));
        $(By.xpath("//td[4]")).shouldHave(exactText("Delete"));
    }
}
