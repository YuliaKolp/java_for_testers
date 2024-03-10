package ru.stqa.manager;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.ui.Select;
import ru.stqa.model.ContactData;
import org.openqa.selenium.By;
import ru.stqa.model.GroupData;

import java.util.ArrayList;
import java.util.List;

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

    public void createContact(ContactData contact, GroupData group){
        //openContactPage();
        initContactCreation();
        fillContactForm(contact);
        selectGroup(group);
        submitContactCreation();
        returnToHomePage();
    }

    private void selectGroup(GroupData group) {
        new Select(manager.driver.findElement(By.name("new_group"))).selectByValue(group.id());
    }


    public void removeContact(ContactData contact) {
        selectContact(contact);
        removeSelectedContacts();
        //manager.driver.switchTo().alert().accept();
    }
    private void removeSelectedContacts() {
        click(By.xpath("//input[@value='Delete']"));
        manager.openHomePage();
    }

    private void selectContact(ContactData contact) {
        //click(By.name("selected[]"));
        click(By.cssSelector(String.format("input[value='%s']",contact.id())));
    }

    public void modifyContact(ContactData contact, ContactData modifiedContact) {
        //openContactPage();
        selectContactById(contact.id());
        fillContactForm(modifiedContact);
        submitContactModification();
        returnToContactsPage();
    }

    public void addContact(ContactData contact, GroupData group) {
        selectContact(contact);
        selectGroupToAdd(group);
        submitContactAddition();
    }

    private void submitContactAddition() {
        click(By.name("add"));
    }

    private void selectGroupToAdd(GroupData group) {
        new Select(manager.driver.findElement(By.name("to_group"))).selectByValue(group.id());
    }

    public void removeContactFromGroup(ContactData contact, GroupData group) {
        selectGroupToRemove(group);
        selectContact(contact);
        submitContactRemoval();
    }

    private void submitContactRemoval() {
        click(By.name("remove"));
    }

    private void selectGroupToRemove(GroupData group) {
        new Select(manager.driver.findElement(By.name("group"))).selectByValue(group.id());
    }

    private void selectContactById(String id) {
        click(By.xpath(String.format("//a[@href='edit.php?id=%s']/img",id)));
    }

    private void submitContactModification() {
        click(By.name("update"));
    }

    public void returnToContactsPage() {
        click(By.linkText("home"));
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
        //photo
        if (!contact.photo().isEmpty()){
            attach(By.name("photo"), contact.photo());
        }
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

        dropDownType(By.name("bday"), contact.bday());
        dropDownType(By.name("bmonth"), contact.bmonth());
        type(By.name("byear"), contact.byear());

        dropDownType(By.name("aday"), contact.aday());
        dropDownType(By.name("amonth"), contact.amonth());
        type(By.name("ayear"), contact.ayear());

        //check if group specified;
        String groupName = contact.group();
        if (!groupName.isEmpty()) {
            try {
                dropDownType(By.name("new_group"), groupName);
            } catch (NoSuchElementException exception) {
                System.out.printf("Group '%s' for a new contact is absent!%n", groupName);
            }
        }
    }

    private void submitContactCreation() {
        click(By.name("submit"));
    }

    private void returnToHomePage() {
        click(By.linkText("home page"));
    }

    public void openContactsPage(ApplicationManager applicationManager) {
        if (!applicationManager.isElementPresent(By.name("Enter"))) {
            click(By.linkText("add new"));
            //applicationManager.driver.findElement(By.linkText("add new")).click();
        }
    }

    public boolean isContactPresent(ApplicationManager applicationManager) {
        return applicationManager.isElementPresent(By.name("selected[]"));
    }

    public int getCount() {
        return manager.driver.findElements(By.name("selected[]")).size();
    }

    public void removeAllContacts() {
        //openContactsPage();
        selectAllContacts();
        removeSelectedContacts();
    }

    private void selectAllContacts() {
        var checkboxes = manager.driver.findElements(By.name("selected[]"));
        for (var checkbox : checkboxes) {
            checkbox.click();
        }
    }

    public List<ContactData> getList() {
        //openGroupsPage();
        var contacts = new ArrayList<ContactData>();
        var trs = manager.driver.findElements(By.name("entry"));
        //go through table rows
        for (var tr:trs) {
            var checkbox = tr.findElement(By.name("selected[]"));
            var id = checkbox.getAttribute("value");
            var lastName = tr.findElement(By.xpath("td[2]")).getText();
            var firstName = tr.findElement(By.xpath("td[3]")).getText();
            var address = tr.findElement(By.xpath("td[4]")).getText();
            ContactData contact = new ContactData().withId(id).withFirstName(firstName).withLastName(lastName);
            contacts.add(contact);
        }
        return contacts;
    }


}
