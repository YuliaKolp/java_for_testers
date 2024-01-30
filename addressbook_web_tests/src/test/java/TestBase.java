import model.ContactData;
import model.GroupData;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

public class TestBase {
    protected static WebDriver driver;

    protected static void createContact(ContactData contact)
    {
        String xpathString = ""; // create variable for xpath string
        driver.findElement(By.name("firstname")).click();
        driver.findElement(By.name("firstname")).sendKeys(contact.name());
        driver.findElement(By.name("middlename")).click();
        driver.findElement(By.name("middlename")).sendKeys(contact.middlename());
        driver.findElement(By.name("lastname")).click();
        driver.findElement(By.name("lastname")).sendKeys(contact.lastname());
        driver.findElement(By.name("nickname")).click();
        driver.findElement(By.name("nickname")).sendKeys(contact.nickname());
        driver.findElement(By.name("title")).click();
        driver.findElement(By.name("title")).sendKeys(contact.title());
        driver.findElement(By.name("company")).click();
        driver.findElement(By.name("company")).sendKeys(contact.company());
        driver.findElement(By.name("address")).click();
        driver.findElement(By.name("address")).sendKeys(contact.address());
        driver.findElement(By.name("home")).click();
        driver.findElement(By.name("home")).sendKeys(contact.home());
        driver.findElement(By.name("mobile")).click();
        driver.findElement(By.name("mobile")).sendKeys(contact.mobile());
        driver.findElement(By.name("work")).click();
        driver.findElement(By.name("work")).sendKeys(contact.work());
        driver.findElement(By.name("fax")).click();
        driver.findElement(By.name("fax")).sendKeys(contact.fax());
        driver.findElement(By.name("email")).click();
        driver.findElement(By.name("email")).sendKeys(contact.email());
        driver.findElement(By.name("email2")).click();
        driver.findElement(By.name("email2")).sendKeys(contact.email2());
        driver.findElement(By.name("email3")).click();
        driver.findElement(By.name("email3")).sendKeys(contact.email3());
        driver.findElement(By.name("homepage")).click();
        driver.findElement(By.name("homepage")).sendKeys(contact.homepage());

        driver.findElement(By.name("bday")).click();
        {
            WebElement dropdown = driver.findElement(By.name("bday"));
            xpathString = "//option[. = '" + contact.bday() + "']"; // make xpath string
            dropdown.findElement(By.xpath(xpathString)).click();
        }

        driver.findElement(By.name("bmonth")).click();
        {
            WebElement dropdown = driver.findElement(By.name("bmonth"));
            xpathString = "//option[. = '" + contact.bmonth() + "']"; // make xpath string
            dropdown.findElement(By.xpath(xpathString)).click();
        }

        driver.findElement(By.name("byear")).click();
        driver.findElement(By.name("byear")).sendKeys(contact.byear());

        driver.findElement(By.name("aday")).click();
        {
            WebElement dropdown = driver.findElement(By.name("aday"));
            xpathString = "//option[. = '" + contact.aday() + "']"; // make xpath string
            dropdown.findElement(By.xpath(xpathString)).click();
        }

        driver.findElement(By.name("amonth")).click();
        {
            WebElement dropdown = driver.findElement(By.name("amonth"));
            xpathString = "//option[. = '" + contact.amonth() + "']"; // make xpath string
            dropdown.findElement(By.xpath(xpathString)).click();
        }

        driver.findElement(By.name("ayear")).click();
        driver.findElement(By.name("ayear")).sendKeys(contact.ayear());

        //check if group specified;
        String grName = contact.group();
        if (!grName.isEmpty()) {
            driver.findElement(By.name("new_group")).click();
            WebElement dropdown = driver.findElement(By.name("new_group"));
            xpathString = "//option[. = '" + grName + "']";

            //dropdown.findElement(By.xpath(xpathString)).click();
            //createGroup(new GroupData(grName, "group header", "group footer"));
        }

        driver.findElement(By.xpath("(//input[@name=\'submit\'])[2]")).click();
        driver.findElement(By.linkText("home page")).click();
        driver.findElement(By.cssSelector("html")).click();
    }

    protected boolean isGroupPresent() {
        return isElementPresent(By.name("selected[]"));
    }

    protected boolean isContactPresent() {
        return isElementPresent(By.name("selected[]"));
    }

    protected static void createGroup(GroupData group) {
        driver.findElement(By.name("new")).click();
        driver.findElement(By.name("group_name")).click();
        driver.findElement(By.name("group_name")).sendKeys(group.name());
        driver.findElement(By.name("group_header")).click();
        driver.findElement(By.name("group_header")).sendKeys(group.header());
        driver.findElement(By.name("group_footer")).click();
        driver.findElement(By.name("group_footer")).sendKeys(group.footer());
        driver.findElement(By.name("submit")).click();
        driver.findElement(By.linkText("group page")).click();
    }

    protected static void removeGroup() {
        driver.findElement(By.name("selected[]")).click();
        driver.findElement(By.name("delete")).click();
        driver.findElement(By.linkText("group page")).click();
    }

    protected static void removeContact() {
        driver.findElement(By.name("selected[]")).click();
        driver.findElement(By.name("Delete")).click();
        //driver.switchTo().alert().accept();
        //driver.findElement(By.linkText("group page")).click();
    }

    @BeforeEach
    public void setUp() {
        if (driver == null) {
            driver = new ChromeDriver();
            Runtime.getRuntime().addShutdownHook(new Thread(driver::quit));
            driver.get("http://localhost/ADDRESSBOOK/");
            driver.manage().window().setSize(new Dimension(1296, 1400));
            driver.findElement(By.name("user")).sendKeys("admin");
            driver.findElement(By.name("pass")).click();
            driver.findElement(By.name("pass")).sendKeys("secret");
            driver.findElement(By.xpath("//input[@value=\'Login\']")).click();
        }
    }

    protected boolean isElementPresent(By locator) {
        try {
            driver.findElement(locator);
            return true;
        } catch (NoSuchElementException exception) {
            return false;
        }
    }

    protected void openGroupsPage() {
        if (!isElementPresent(By.name("new"))) {
            driver.findElement(By.linkText("groups")).click();
        }
    }

    protected void openAddContactPage() {
        if (!isElementPresent(By.name("Enter"))) {
            driver.findElement(By.linkText("add new")).click();
        }
    }
    protected void openHomePage() {
        if (!isElementPresent(By.name("Delete"))) {
            driver.findElement(By.linkText("home")).click();
        }
    }

}
