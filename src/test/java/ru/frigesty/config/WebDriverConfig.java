package ru.frigesty.config;

import org.aeonbits.owner.Config;


@Config.Sources({
        "classpath:properties/${env}.properties",

})

public interface WebDriverConfig extends Config {

    @Key("browser")
    @DefaultValue("CHROME")
    String browser();

    @Key("browser_size")
    @DefaultValue("1920x1080")
    String browserSize();

    @Key("browser_version")
    @DefaultValue("132.0")
    String browserVersion();

    @Key("baseUrl")
    @DefaultValue("https://migstroy.spb.ru")
    String baseUrl();

    @Key("isRemote")
    @DefaultValue("false")
    boolean isRemote();

    @Key("remoteUrl")
    @DefaultValue("selenoid.autotests.cloud/")
    String remoteUrl();

    @Key("loadStrategy")
    @DefaultValue("eager")
    String loadStrategy();


}