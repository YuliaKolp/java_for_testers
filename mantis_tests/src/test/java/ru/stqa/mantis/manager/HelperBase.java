package ru.stqa.mantis.manager;

import org.openqa.selenium.By;
import org.openqa.selenium.*;

import java.nio.file.Paths;

public class HelperBase {
    protected final ApplicationManager manager;

    public HelperBase(ApplicationManager manager) {
        this.manager = manager;
    }

    protected void click(By locator) {
        manager.driver().findElement(locator).click();
    }

    protected void type(By locator, String text) {
        click(locator);
        manager.driver().findElement(locator).clear();
        manager.driver().findElement(locator).sendKeys(text);
    }

    protected void attach(By locator, String file) {
        manager.driver().findElement(locator).sendKeys(Paths.get(file).toAbsolutePath().toString());
    }

    protected void dropDownType(By locator,String text){
        click(locator);
        String xpathString = "";
        WebElement dropdown = manager.driver().findElement(locator);
        xpathString = "//option[. = '" + text + "']"; // make xpath string
        dropdown.findElement(By.xpath(xpathString)).click();
    }

    protected boolean isElementPresent(By locator) {
        return manager.driver().findElements(locator).size() > 0;
    }

}
