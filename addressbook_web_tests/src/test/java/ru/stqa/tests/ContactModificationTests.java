package ru.stqa.tests;

import ru.stqa.model.ContactData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.stqa.model.GroupData;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;

public class ContactModificationTests  extends TestBase {

    @Test
    public void canModifyContact(){
        String newFirstName = "modifiedName";
        //check contact presence
        if (app.jdbc().getContactList().size() == 0){
            app.contacts().openContactsPage(app);
            app.contacts().createContact(new ContactData().withFirstName("toModify"));
        }
        var oldContacts = app.jdbc().getContactList();

        var rnd = new Random();
        var index = rnd.nextInt(oldContacts.size());
        System.out.printf("Index is '%s'%n",index);
        var testData = new ContactData().withFirstName(newFirstName);
        // modify and get new list
        var contactToModify = oldContacts.get(index);
        System.out.printf("Contact ID is '%s', 1st name is '%s'%n",contactToModify.id(), contactToModify.name());
        app.contacts().modifyContact(contactToModify, testData);
        var newContacts = app.jdbc().getContactList();

        // expected
        var expectedList = new ArrayList<>(oldContacts);
        expectedList.set(index, contactToModify.withFirstName(newFirstName).withLastName(""));
        //prepare to sort
        Comparator<ContactData> compareByName = (o1, o2) -> {
            return String.CASE_INSENSITIVE_ORDER.compare(o1.name(),o2.name());
        };
        Comparator<ContactData> compareByLastName = (o1, o2) -> {
            return String.CASE_INSENSITIVE_ORDER.compare(o1.lastname(),o2.lastname());
        };
        //sort and check
        expectedList.sort(compareByName);
        expectedList.sort(compareByLastName);

        newContacts.sort(compareByName);
        newContacts.sort(compareByLastName);

        Assertions.assertEquals(expectedList, newContacts);

}

    @Test
    public void canAddContactToGroup(){
            // check if any group exists
            if (app.hbm().getGroupCount() == 0){
                app.hbm().createGroup(new GroupData("", "GroupForContactsToAdd", "group header", "group footer"));
            }
            var group  = app.hbm().getGroupList().get(0);
            System.out.printf("Group is '%s'%n",group.id());

            // work with contacts
            if (app.jdbc().getContactList().size() == 0){
                app.contacts().openContactsPage(app);
                app.contacts().createContact(new ContactData().withFirstName("ContactToAddToGroup"));
            }
            var oldContacts = app.jdbc().getContactList();
            var rnd = new Random();
            var index = rnd.nextInt(oldContacts.size());
            var contact = oldContacts.get(index);
            System.out.printf("Contact name is '%s', group Id is '%s'", contact.name(), group.id());
            //Add to group
            app.contacts().addContact(contact, group);
            var newContacts = app.jdbc().getContactList();
            // Check
            var expectedList = new ArrayList<>(oldContacts);

            System.out.println(newContacts);
            expectedList.set(index, contact.withGroup(group.name()));


            //prepare to sort
            Comparator<ContactData> compareByName = (o1, o2) -> {
                return String.CASE_INSENSITIVE_ORDER.compare(o1.name(),o2.name());
            };
            Comparator<ContactData> compareByLastName = (o1, o2) -> {
                return String.CASE_INSENSITIVE_ORDER.compare(o1.lastname(),o2.lastname());
            };
            //sort and check
            expectedList.sort(compareByName);
            expectedList.sort(compareByLastName);

            newContacts.sort(compareByName);
            newContacts.sort(compareByLastName);

            Assertions.assertEquals(expectedList, newContacts);

        }

}
