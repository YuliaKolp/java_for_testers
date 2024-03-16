package ru.stqa.tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ContactInfoTests extends TestBase{
    @Test
    void testPhones() {
        var contacts = app.hbm().getContactList();
        var expected = contacts.stream().collect(Collectors.toMap(contact -> contact.id(), contact ->
            Stream.of(contact.home(), contact.mobile(), contact.work(), contact.secondary())
                    .filter(s -> s != null && ! "".equals(s))
                    .collect(Collectors.joining("\n"))
        ));
        var phones = app.contacts().getPhones();
        Assertions.assertEquals(expected, phones);
        /*for (var contact: contacts) {
            var expected = Stream.of(contact.home(), contact.mobile(), contact.work(), contact.secondary())
                    .filter(s -> s != null && ! "".equals(s))
                    .collect(Collectors.joining("\n"));
            Assertions.assertEquals(expected, phones.get(contact.id()));
        }*/
    }

    @Test
    void testAddress() {
        var contacts = app.hbm().getContactList();
        var expected = contacts.stream().collect(Collectors.toMap(contact -> contact.id(), contact ->
                Stream.of(contact.address())
                        .filter(s -> s != null && ! "".equals(s))
                        .collect(Collectors.joining("\n"))
        ));
        var address = app.contacts().getAddress();
        System.out.println(expected);
        Assertions.assertEquals(expected, address);
        System.out.println(address);
    }

    @Test
    void testEmails() {
        var contacts = app.hbm().getContactList();
        var expected = contacts.stream().collect(Collectors.toMap(contact -> contact.id(), contact ->
                Stream.of(contact.email(), contact.email2(), contact.email3())
                        .filter(s -> s != null && ! "".equals(s))
                        .collect(Collectors.joining("\n"))
        ));
        var Emails = app.contacts().getEmails();
        System.out.println(Emails);
        System.out.println(expected);
        Assertions.assertEquals(expected, Emails);
    }
    @Test
    void testEditAndInfoData(){
        // get contacts
        var contacts = app.contacts().getList();
        // check that contacts presents
        Assertions.assertNotEquals(0, contacts.size());
        //get 1st contact data
        var contact = contacts.get(0);
        String contactId = contact.id();
        //get compound properties
        var contactPhones = app.contacts().getPhones().get(contactId);
        var contactAddress = app.contacts().getAddress().get(contactId);
        var contactEmails =app.contacts().getEmails().get(contactId);
        //get data from edit form
        var editContactData = app.contacts().getUiEditContactData(contact);
        var editContactDataEmails = String.join("\n", editContactData.get("email"),
                editContactData.get("email2"), editContactData.get("email3"));
        var editContactDataPhones = String.join("\n", editContactData.get("home"),
                editContactData.get("mobile"), editContactData.get("work"));
        //compare
        Assertions.assertEquals(contact.name(), editContactData.get("firstname"));
        Assertions.assertEquals(contact.lastname(), editContactData.get("lastname"));
        Assertions.assertEquals(contactAddress, editContactData.get("address"));
        Assertions.assertEquals(contactEmails, editContactDataEmails);
        Assertions.assertEquals(contactPhones, editContactDataPhones);
    }
}
