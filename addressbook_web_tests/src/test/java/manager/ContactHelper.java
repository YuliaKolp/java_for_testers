package manager;

import model.ContactData;
import org.openqa.selenium.By;

public class ContactHelper extends HelperBase {
    public ContactHelper(ApplicationManager manager){
        super(manager);
    }

    public void createContact(ContactData contact){
        //openContactPage();
        initContactCreation();
        fillContactForm(contact);
        submitContactCreation();
        returnToHomePage();
    }

    public void openContactPage() {
        if (!manager.isElementPresent(By.name("Enter"))) {
            click(By.linkText("add new"));
            //driver.findElement(By.linkText("add new")).click();
        }
    }

    private void initContactCreation() {
        click(By.linkText("add new"));
    }
    private void fillContactForm(ContactData contact) {
        String xpathString = ""; // create variable for xpath string
        type(By.name("firstname"), contact.name());
        type(By.name("middlename"), contact.middlename());
        type(By.name("lastname"), contact.lastname());
        type(By.name("nickname"), contact.nickname());
        type(By.name("title"), contact.title());
        type(By.name("company"), contact.company());
        type(By.name("address"), contact.address());
        type(By.name("home"), contact.home());
        type(By.name("mobile"), contact.mobile());
        type(By.name("work"), contact.work());
        type(By.name("fax"), contact.fax());
        type(By.name("email"), contact.email());
        type(By.name("email2"), contact.email2());
        type(By.name("email3"), contact.email3());
        type(By.name("homepage"), contact.homepage());

        /*driver.findElement(By.name("bday")).click();
        {
            WebElement dropdown = driver.findElement(By.name("bday"));
            xpathString = "//option[. = '" + contact.bday() + "']"; // make xpath string
            dropdown.findElement(By.xpath(xpathString)).click();
        }*/

        dropDownType(By.name("bday"), contact.bday());
        dropDownType(By.name("bmonth"), contact.bmonth());
        type(By.name("byear"), contact.byear());

        dropDownType(By.name("aday"), contact.aday());
        dropDownType(By.name("amonth"), contact.amonth());
        type(By.name("ayear"), contact.ayear());

        //check if group specified;
        String grName = contact.group();
        if (!grName.isEmpty()) {
            dropDownType(By.name("new_group"), contact.group());
        }
    }

    private void submitContactCreation() {
        click(By.name("submit"));
    }

    private void returnToHomePage() {
        click(By.linkText("home page"));
    }

}
