import model.ContactData;
import model.GroupData;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

public class TestBase {
    protected static WebDriver driver;

    protected static void createContact(ContactData contactData)
    {
        String xpathString = ""; // create variable for xpath string
        driver.findElement(By.name("firstname")).click();
        driver.findElement(By.name("firstname")).sendKeys(contactData.firstname());
        driver.findElement(By.name("middlename")).click();
        driver.findElement(By.name("middlename")).sendKeys(contactData.middlename());
        driver.findElement(By.name("lastname")).click();
        driver.findElement(By.name("lastname")).sendKeys(contactData.lastname());
        driver.findElement(By.name("nickname")).click();
        driver.findElement(By.name("nickname")).sendKeys(contactData.nickname());
        driver.findElement(By.name("title")).click();
        driver.findElement(By.name("title")).sendKeys(contactData.title());
        driver.findElement(By.name("company")).click();
        driver.findElement(By.name("company")).sendKeys(contactData.company());
        driver.findElement(By.name("address")).click();
        driver.findElement(By.name("address")).sendKeys(contactData.address());
        driver.findElement(By.name("home")).click();
        driver.findElement(By.name("home")).sendKeys(contactData.home());
        driver.findElement(By.name("mobile")).click();
        driver.findElement(By.name("mobile")).sendKeys(contactData.mobile());
        driver.findElement(By.name("work")).click();
        driver.findElement(By.name("work")).sendKeys(contactData.work());
        driver.findElement(By.name("fax")).click();
        driver.findElement(By.name("fax")).sendKeys(contactData.fax());
        driver.findElement(By.name("email")).click();
        driver.findElement(By.name("email")).sendKeys(contactData.email());
        driver.findElement(By.name("email2")).click();
        driver.findElement(By.name("email2")).sendKeys(contactData.email2());
        driver.findElement(By.name("email3")).click();
        driver.findElement(By.name("email3")).sendKeys(contactData.email3());
        driver.findElement(By.name("homepage")).click();
        driver.findElement(By.name("homepage")).sendKeys(contactData.homepage());

        driver.findElement(By.name("bday")).click();
        {
            WebElement dropdown = driver.findElement(By.name("bday"));
            xpathString = "//option[. = '" + contactData.bday() + "']"; // make xpath string
            dropdown.findElement(By.xpath(xpathString)).click();
        }

        driver.findElement(By.name("bmonth")).click();
        {
            WebElement dropdown = driver.findElement(By.name("bmonth"));
            xpathString = "//option[. = '" + contactData.bmonth() + "']"; // make xpath string
            dropdown.findElement(By.xpath(xpathString)).click();
        }

        driver.findElement(By.name("byear")).click();
        driver.findElement(By.name("byear")).sendKeys(contactData.byear());

        driver.findElement(By.name("aday")).click();
        {
            WebElement dropdown = driver.findElement(By.name("aday"));
            xpathString = "//option[. = '" + contactData.aday() + "']"; // make xpath string
            dropdown.findElement(By.xpath(xpathString)).click();
        }

        driver.findElement(By.name("amonth")).click();
        {
            WebElement dropdown = driver.findElement(By.name("amonth"));
            xpathString = "//option[. = '" + contactData.amonth() + "']"; // make xpath string
            dropdown.findElement(By.xpath(xpathString)).click();
        }

        driver.findElement(By.name("ayear")).click();
        driver.findElement(By.name("ayear")).sendKeys(contactData.ayear());

        driver.findElement(By.name("new_group")).click();
        {
            WebElement dropdown = driver.findElement(By.name(contactData.group()));
            dropdown.findElement(By.xpath("//option[. = " + "'" + contactData.group() + "'" + "]")).click();
        }
        driver.findElement(By.xpath("(//input[@name=\'submit\'])[2]")).click();
        driver.findElement(By.linkText("home page")).click();
        driver.findElement(By.cssSelector("html")).click();
    }

    protected boolean isGroupPresent() {
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

    protected void openContactPage() {
        if (!isElementPresent(By.name("new"))) {
            driver.findElement(By.linkText("add new")).click();
        }
    }


}
