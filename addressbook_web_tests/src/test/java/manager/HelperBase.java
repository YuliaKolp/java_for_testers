package manager;

import org.openqa.selenium.By;
import org.openqa.selenium.*;

public class HelperBase {
    protected final ApplicationManager manager;

    public HelperBase(ApplicationManager manager) {
        this.manager = manager;
    }

    protected void click(By locator) {
        manager.driver.findElement(locator).click();
    }

    protected void type(By locator, String text) {
        click(locator);
        manager.driver.findElement(locator).clear();
        manager.driver.findElement(locator).sendKeys(text);
    }

    protected void dropDownType(By locator,String text){
        click(locator);
        String xpathString = "";
        WebElement dropdown = manager.driver.findElement(locator);
        xpathString = "//option[. = '" + text + "']"; // make xpath string
        dropdown.findElement(By.xpath(xpathString)).click();
    }

    /*
    ddropDownType(By.name("bday"), contact.bday());

    driver.findElement(By.name("bday")).click();
    {
        WebElement dropdown = driver.findElement(By.name("bday"));
        xpathString = "//option[. = '" + contact.bday() + "']"; // make xpath string
        dropdown.findElement(By.xpath(xpathString)).click();
    }*/
}
