package tests;

import model.ContactData;
import org.junit.jupiter.api.Test;

public class ContactCreationTests extends TestBase {

    @Test
    public void canAddContact() {
        app.contacts().createContact(
                new ContactData("B", "Jack", "Awesomyyy", "nick", "QA",
                        "QAInc", "green st", "7", "123", "456", "789",
                        "jdoe@qamail.com", "jdoe2@qamail.com", "jdoe3@qamail.com",
                        "www.jdoe.com", "15", "October", "2000",
                        "11", "March", "1990", ""));
    }
    @Test
    public void canAddContactWithEmptyName() {
        app.contacts().createContact(new ContactData("", "Mickel", "Doedoe", "nick", "QA",
                "QAInc", "green st", "7", "123", "456", "789",
                "jdoe@qamail.com", "jdoe2@qamail.com", "jdoe3@qamail.com",
                "www.jdoe.com", "15", "September", "2001",
                "12", "April", "1991", ""));
    }

    @Test
    public void canAddContactWitnFirstnameOnly(){
        var emptyContact = new ContactData();
        var contactWithName = emptyContact.withFirstName("some first name");
        app.contacts().createContact(contactWithName);
    }

    /*@Test
    public void canAddContactWithNotEmptyGroup() {
        app.contacts().createContact(
                new ContactData("Sam", "Mickel", "Doedoe", "nick", "QA",
                        "QAInc", "green st", "7", "123", "456", "789",
                        "jdoe@qamail.com", "jdoe2@qamail.com", "jdoe3@qamail.com",
                        "www.jdoe.com", "15", "September", "2001",
                        "12", "April", "1991", "Lalal"));
    }*/

}
