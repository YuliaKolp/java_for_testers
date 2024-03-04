package ru.stqa.tests;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import ru.stqa.common.CommonFunctions;
import ru.stqa.model.ContactData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import ru.stqa.model.GroupData;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ContactCreationTests extends TestBase {

    public static List<ContactData> contactProvider() throws IOException {
        var result = new ArrayList<ContactData>();
        var mapper = new XmlMapper();
        var value = mapper.readValue(new File("contacts.xml"), new TypeReference<List<ContactData>>() {});
        result.addAll(value);
        return result;
    }

    @ParameterizedTest
    @MethodSource("contactProvider")
    public void canCreateMultipleContacts(ContactData contact) {
        System.out.println("-----------------------------------------------------");
        //Sort functions
        Comparator<ContactData> compareById = (o1, o2) -> {
            return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
        };
        Comparator<ContactData> compareByName = (o1, o2) -> {
            return String.CASE_INSENSITIVE_ORDER.compare(o1.name(),o2.name());
        };
        Comparator<ContactData> compareByLastName = (o1, o2) -> {
            return String.CASE_INSENSITIVE_ORDER.compare(o1.lastname(),o2.lastname());
        };
        Comparator<ContactData> compareByMiddleName = (o1, o2) -> {
            return String.CASE_INSENSITIVE_ORDER.compare(o1.middlename(),o2.middlename());
        };
        //get current groups
        var oldContacts = app.jdbc().getContactList();
        //create contact
        app.contacts().createContact(contact);
        var newContacts = app.jdbc().getContactList();
        //sort new contacts by ID
        newContacts.sort(compareById);
        var newContact = newContacts.get(newContacts.size()-1);
        System.out.printf("New id is '%s', name '%s'%n", newContact.id(), newContact.name());

        //expected
        var expectedList = new ArrayList<>(oldContacts);
        expectedList.add(contact.withId(newContact.id()));

        //sort and check
        expectedList.sort(compareByName);
        expectedList.sort(compareByMiddleName);
        expectedList.sort(compareByLastName);
        newContacts.sort(compareByName);
        newContacts.sort(compareByMiddleName);
        newContacts.sort(compareByLastName);

        Assertions.assertEquals(expectedList, newContacts) ;
    }

    @Test
    public void canCreateContact() {
        var contact = new ContactData()
                .withFirstName(CommonFunctions.randomString(10))
                .withLastName(CommonFunctions.randomString(10))
                .withPhoto(CommonFunctions.randomFile("src/test/resources/images"));
        app.contacts().createContact(contact);
    }

    @Test
    public void canCreateContactNoPhoto() {
        var contact = new ContactData()
                .withFirstName(CommonFunctions.randomString(10))
                .withLastName(CommonFunctions.randomString(10));
        app.contacts().createContact(contact);
    }

    @Test
    public void canCreateContactInGroup() {
        var contact = new ContactData()
                .withFirstName(CommonFunctions.randomString(10))
                .withLastName(CommonFunctions.randomString(10))
                .withPhoto(CommonFunctions.randomFile("src/test/resources/images"));
        if (app.hbm().getGroupCount() == 0){
            app.hbm().createGroup(new GroupData("", "group name!!!!", "group header", "group footer"));
        }
        var group  = app.hbm().getGroupList().get(0);

        var oldRelated = app.hbm().getContactsInGroup(group);
        app.contacts().createContact(contact, group);
        var newRelated = app.hbm().getContactsInGroup(group);
        Assertions.assertEquals(oldRelated.size() + 1, newRelated.size());
        // add comparison by content

    }

}
