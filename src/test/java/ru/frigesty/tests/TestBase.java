package ru.frigesty.tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.remote.DesiredCapabilities;
import ru.frigesty.config.WebDriverConfig;
import ru.frigesty.helpers.Attach;
import java.util.Map;
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class TestBase {

    static WebDriverConfig webDriverConfig = ConfigFactory.create(WebDriverConfig.class, System.getProperties());

    @BeforeAll
    static void setUpBrowserConfiguration() {

        Configuration.browser = webDriverConfig.browser();
        Configuration.browserVersion = webDriverConfig.browserVersion();
        Configuration.browserSize = webDriverConfig.browserSize();
        Configuration.pageLoadStrategy = webDriverConfig.loadStrategy();
        Configuration.baseUrl = webDriverConfig.baseUrl();

        if(webDriverConfig.isRemote()){
            Configuration.remote = webDriverConfig.remoteUrl();

            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setCapability("selenoid:options", Map.<String, Object>of(
                    "enableVNC", true,
                    "enableVideo", true
            ));
            Configuration.browserCapabilities = capabilities;
        }
    }

    @BeforeEach
    void addListener(){
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
    }

    @AfterEach
    void addAttachments() {
        Attach.screenshotAs("Last screenshot");
        Attach.addVideo();
        if (!Configuration.browser.equalsIgnoreCase("firefox")) {
            Attach.browserConsoleLogs();
        }
        Attach.pageSource();

    }

    @AfterAll
    public static void tearDownWebDriver() {
        closeWebDriver();
    }
}