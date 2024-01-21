import model.GroupData;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

public class TestBase {
    protected static WebDriver driver;

    protected static void createContact(String firstname, String middlename, String lastname, String nickname,
                                        String title, String company, String address, String home, String mobile,
                                        String work, String fax, String email, String email2, String email3,
                                        String homepage, String bday, String byear,
                                        String group)
    {
        //  String, bday, String bmonth, String byear, String aday,
        // String amonth, String ayear,
        driver.findElement(By.name("firstname")).click();
        driver.findElement(By.name("firstname")).sendKeys(firstname);
        driver.findElement(By.name("middlename")).click();
        driver.findElement(By.name("middlename")).sendKeys(middlename);
        driver.findElement(By.name("lastname")).click();
        driver.findElement(By.name("lastname")).sendKeys(lastname);
        driver.findElement(By.name("nickname")).click();
        driver.findElement(By.name("nickname")).sendKeys(nickname);
        driver.findElement(By.name("title")).click();
        driver.findElement(By.name("title")).sendKeys(title);
        driver.findElement(By.name("company")).click();
        driver.findElement(By.name("company")).sendKeys(company);
        driver.findElement(By.name("address")).click();
        driver.findElement(By.name("address")).sendKeys(address);
        driver.findElement(By.name("home")).click();
        driver.findElement(By.name("home")).sendKeys(home);
        driver.findElement(By.name("mobile")).click();
        driver.findElement(By.name("mobile")).sendKeys(mobile);
        driver.findElement(By.name("work")).click();
        driver.findElement(By.name("work")).sendKeys(work);
        driver.findElement(By.name("fax")).click();
        driver.findElement(By.name("fax")).sendKeys(fax);
        driver.findElement(By.name("email")).click();
        driver.findElement(By.name("email")).sendKeys(email);
        //driver.findElement(By.cssSelector("body")).click();
        driver.findElement(By.name("email2")).click();
        driver.findElement(By.name("email2")).sendKeys(email2);
        driver.findElement(By.name("email3")).click();
        driver.findElement(By.name("email3")).sendKeys(email3);
        driver.findElement(By.name("homepage")).click();
        driver.findElement(By.name("homepage")).sendKeys(homepage);

        driver.findElement(By.name("bday")).click();
        {
            WebElement dropdown = driver.findElement(By.name("bday"));
            String xpathString = "//option[. = '" + bday + "']"; // make xpath string
            System.out.println(xpathString);
            dropdown.findElement(By.xpath(xpathString)).click();
        }

        driver.findElement(By.name("bmonth")).click();
        {
            WebElement dropdown = driver.findElement(By.name("bmonth"));
            dropdown.findElement(By.xpath("//option[. = 'August']")).click();
        }

        driver.findElement(By.name("byear")).click();
        driver.findElement(By.name("byear")).sendKeys(byear);

        driver.findElement(By.name("aday")).click();
        {
            WebElement dropdown = driver.findElement(By.name("aday"));
            dropdown.findElement(By.xpath("//option[. = '12']")).click();
        }
        driver.findElement(By.name("amonth")).click();
        {
            WebElement dropdown = driver.findElement(By.name("amonth"));
            dropdown.findElement(By.xpath("//option[. = 'December']")).click();
        }
        driver.findElement(By.name("ayear")).click();
        driver.findElement(By.name("ayear")).sendKeys("2000");
        driver.findElement(By.name("new_group")).click();
        {
            WebElement dropdown = driver.findElement(By.name(group));
            dropdown.findElement(By.xpath("//option[. = " + "'" + group + "'" + "]")).click();
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
