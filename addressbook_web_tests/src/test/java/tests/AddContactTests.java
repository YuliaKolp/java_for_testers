package tests;

import model.ContactData;
import org.junit.jupiter.api.Test;

public class AddContactTests extends TestBase {

    @Test
    public void canAddContact() {
        app.openAddContactPage();
        app.createContact(
                new ContactData("Bill", "Jack", "Doedoe", "nick", "QA",
                        "QAInc", "green st", "7", "123", "456", "789",
                        "jdoe@qamail.com", "jdoe2@qamail.com", "jdoe3@qamail.com",
                        "www.jdoe.com", "15", "October", "2000",
                        "11", "March", "1990", ""));
    }

    @Test
    public void canAddContactWithEmptyName() {
        app.openAddContactPage();
        app.createContact(
                new ContactData("", "Mickel", "Doedoe", "nick", "QA",
                        "QAInc", "green st", "7", "123", "456", "789",
                        "jdoe@qamail.com", "jdoe2@qamail.com", "jdoe3@qamail.com",
                        "www.jdoe.com", "15", "September", "2001",
                        "12", "April", "1991", ""));
    }

    @Test
    public void canAddContactWitnFirstnameOnly(){
        app.openAddContactPage();
        var emptyContact = new ContactData();
        var contactWithName = emptyContact.withFirstName("some first name");
        app.createContact(contactWithName);
    }

    @Test
    public void canAddContactWithNotEmptyGroup() {
        app.openAddContactPage();
        //createContact(firstname, middlename, lastname, nickname, title, company, address, home, mobile,work,  fax, email, email2, email3, homepage, bday, bmonth, byear, aday, amonth, ayear, group);
        app.createContact(
                new ContactData("Sam", "Mickel", "Doedoe", "nick", "QA",
                        "QAInc", "green st", "7", "123", "456", "789",
                        "jdoe@qamail.com", "jdoe2@qamail.com", "jdoe3@qamail.com",
                        "www.jdoe.com", "15", "September", "2001",
                        "12", "April", "1991", "Lalal"));
    }

}