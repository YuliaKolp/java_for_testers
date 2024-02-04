package manager;

import model.ContactData;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class ApplicationManager {
    protected WebDriver driver;
    private LoginHelper session;
    private GroupHelper groups;
    private ContactHelper contacts;

    public void init(String browser) {
        if (driver == null) {
            if ("Chrome".equals(browser)){
                driver = new ChromeDriver();
            } else if ("Firefox".equals(browser)){
                    driver = new FirefoxDriver();
            } else {
                throw new IllegalArgumentException(String.format("Unknown browser %s", browser));
            }
            Runtime.getRuntime().addShutdownHook(new Thread(driver::quit));
            driver.get("http://localhost/ADDRESSBOOK/");
            driver.manage().window().setSize(new Dimension(1296, 1400));
            session().login("admin","secret");
        }
    }

    public LoginHelper session(){
        if (session == null) {
            session = new LoginHelper(this);
        }
        return session;
    }

    public GroupHelper groups(){
        if (groups == null) {
            groups = new GroupHelper(this);
        }
        return groups;
    }

    public ContactHelper contacts(){
        if (contacts == null) {
            contacts = new ContactHelper(this);
        }
        return contacts;
    }

    protected boolean isElementPresent(By locator) {
        try {
            driver.findElement(locator);
            return true;
        } catch (NoSuchElementException exception) {
            return false;
        }
    }

    public boolean isContactPresent() {return isElementPresent(By.name("selected[]"));
    }

    public void openContactPage() {
        if (!isElementPresent(By.name("Enter"))) {
            //click(By.linkText("add new"));
            driver.findElement(By.linkText("add new")).click();
        }
    }

    public void openHomePage() {
        if (!isElementPresent(By.name("Delete"))) {
            driver.findElement(By.linkText("home")).click();
        }
    }

}
