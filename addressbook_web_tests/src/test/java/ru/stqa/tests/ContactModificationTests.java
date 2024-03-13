package ru.stqa.tests;

import org.hibernate.tool.schema.internal.exec.ScriptSourceInputFromUrl;
import ru.stqa.common.CommonFunctions;
import ru.stqa.model.ContactData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.stqa.model.GroupData;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;
import java.util.logging.SocketHandler;

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
        // Create a new group to make sure contact does not belong to it
        var groupName = CommonFunctions.randomString(7);
        app.hbm().createGroup(new GroupData("", groupName, "group header", "group footer"));
        System.out.printf("Group name is '%s'", groupName);
        //refresh page to make new group appear
        app.contacts().returnToContactsPage();

        // check if any contact exists
        if (app.jdbc().getContactList().size() == 0){
            app.contacts().openContactsPage(app);
            app.contacts().createContact(new ContactData().withFirstName("ModifCont_" + CommonFunctions.randomString(4)));
        }
        // work with contacts. Get random contact
        var oldContacts = app.jdbc().getContactList();
        var rnd = new Random();
        var index = rnd.nextInt(oldContacts.size());
        var contact = oldContacts.get(index);

        // get contacts's groups
        var oldRelatedGroups = app.hbm().getGroupsInContact(contact);
        var groups = app.hbm().getGroupList();
        // sort groups by id
        Comparator<GroupData> compareById = (o1,o2) -> {
            return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
        };
        groups.sort(compareById);
        var groupToAdd  = groups.get(groups.size() - 1);
        System.out.println();
        System.out.printf("Group to add is '%s'", groupToAdd);
        System.out.println(groupToAdd.name());
        Assertions.assertEquals(groupName, groupToAdd.name());

        //Add to group-------------------------------------------------
        System.out.printf("Contact name is '%s' and id is '%s'.", contact.name(), contact.id());
        app.contacts().addContact(contact, groupToAdd);
        var newRelatedGroups = app.hbm().getGroupsInContact(contact);

        //prepare to sort contact's groups
        Comparator<GroupData> compareByGroup = (o1, o2) -> {
            return String.CASE_INSENSITIVE_ORDER.compare(o1.name(),o2.name());
        };

        // Check
        var expectedRelatedGroups = new ArrayList<>(oldRelatedGroups);
        expectedRelatedGroups.add(groupToAdd);

        //sort and check
        expectedRelatedGroups.sort(compareByGroup);
        newRelatedGroups.sort(compareByGroup);
        Assertions.assertEquals(expectedRelatedGroups, newRelatedGroups);
    }

    @Test
    public void canRemoveContactFromGroup(){
        // check if any contact exists
        if (app.jdbc().getContactList().size() == 0){
            app.contacts().openContactsPage(app);
            app.contacts().createContact(new ContactData().withFirstName("ModifCont_" + CommonFunctions.randomString(4)));
        }
        // work with contacts. Get random contact
        var oldContacts = app.jdbc().getContactList();
        var rnd = new Random();
        var index = rnd.nextInt(oldContacts.size());
        var contact = oldContacts.get(index);

        // get contact's groups. Add contact to group if necessary
        var oldRelatedGroups = app.hbm().getGroupsInContact(contact);
        if (oldRelatedGroups.size() == 0){
            //create group and add contact to it
            app.hbm().createGroup(new GroupData("", "group_" + CommonFunctions.randomString(4), "group header", "group footer"));
            app.contacts().returnToContactsPage();

            var groups = app.hbm().getGroupList();
            var groupToAdd  = groups.get(groups.size() - 1);
            app.contacts().addContact(contact, groupToAdd);
            app.contacts().returnToContactsPage();
        }
        //get group to remove from
        oldRelatedGroups = app.hbm().getGroupsInContact(contact);
        var groupToRemoveFrom =  oldRelatedGroups.get(0);
        //Remove from group-------------------------------------------------
        System.out.printf("Contact name is '%s' and id is '%s'. Group to remove from is '%s'", contact.name(), contact.id(), groupToRemoveFrom.name());
        app.contacts().removeContactFromGroup(contact, groupToRemoveFrom);

        var newRelatedGroups = app.hbm().getGroupsInContact(contact);

        //prepare to sort contact's groups
        Comparator<GroupData> compareByGroup = (o1, o2) -> {
            return String.CASE_INSENSITIVE_ORDER.compare(o1.name(),o2.name());
        };

        // Expected list
        var expectedRelatedGroups = new ArrayList<>(oldRelatedGroups);
        expectedRelatedGroups.remove(groupToRemoveFrom);

        //sort and check
        expectedRelatedGroups.sort(compareByGroup);
        newRelatedGroups.sort(compareByGroup);
        Assertions.assertEquals(expectedRelatedGroups, newRelatedGroups);
    }

}