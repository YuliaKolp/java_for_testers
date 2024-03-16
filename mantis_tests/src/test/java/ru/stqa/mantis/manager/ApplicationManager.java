package ru.stqa.mantis.manager;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.Properties;

public class ApplicationManager {
    private WebDriver driver;

    private String browser;
    private Properties properties;

    private SessionHelper sessionHelper;

    private HttpSessionHelper httpSessionHelper;

    public void init(String browser, Properties properties) {
        this.browser = browser;
        this.properties = properties;

    }

    public WebDriver driver(){
        if (driver == null){
            if ("Chrome".equals(browser)){
                driver = new ChromeDriver();
            } else if ("Firefox".equals(browser)){
                driver = new FirefoxDriver();
            } else {
                throw new IllegalArgumentException(String.format("Unknown browser %s", browser));
            }
            Runtime.getRuntime().addShutdownHook(new Thread(driver::quit));
            driver.get(properties.getProperty("web.baseUrl"));
            driver.manage().window().setSize(new Dimension(1296, 1400));
            //session().login(properties.getProperty("web.username"),properties.getProperty("web.password"));
        }
        return driver;
    }
    public SessionHelper session(){
        if (sessionHelper == null) {
            sessionHelper = new SessionHelper(this);
        }
        return sessionHelper;
    }

    public HttpSessionHelper http() {
        if (httpSessionHelper == null) {
            httpSessionHelper = new HttpSessionHelper(this);
        }
        return httpSessionHelper;
    }
    public String property(String name){
        return properties.getProperty(name);
    }

}
