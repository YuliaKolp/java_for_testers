package tests;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import common.CommonFunctions;
import model.ContactData;
import model.GroupData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ContactCreationTests extends TestBase {

    public static List<ContactData> contactProvider() throws IOException {
        var result = new ArrayList<ContactData>();
//        for (var name : List.of("", "ContactName")){
//            for (var middleame : List.of("", "ContactMiddleName")){
//                for (var lastname : List.of("", "ContactLastName")){
//                    result.add(new ContactData()
//                            .withFirstName(name)
//                            .withMiddleName(middleame)
//                            .withLastName(lastname));
//                }
//            }
//        }
//
//        for (int i = 0; i < 5; i++){
//            result.add(new ContactData()
//                    .withFirstName(CommonFunctions.randomString(i*5)).withMiddleName(CommonFunctions.randomString(i*5)).withLastName(CommonFunctions.randomString(i*5)));
//        }

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
        //get current groups
        var oldContacts = app.contacts().getList();
        //create contact
        app.contacts().createContact(contact);
        var newContacts = app.contacts().getList();
        //sort new contacts by ID
        newContacts.sort(compareById);
        var newContact = newContacts.get(newContacts.size()-1);
        System.out.printf("New id is '%s', name '%s'%n", newContact.id(), newContact.name());

        //expected
        var expectedList = new ArrayList<>(oldContacts);
        expectedList.add(contact.withId(newContact.id())
                .withFirstName(newContact.name())
                .withMiddleName(newContact.middlename())
                .withLastName(newContact.lastname()));

        //sort and check
        expectedList.sort(compareByName);
        expectedList.sort(compareByLastName);
        newContacts.sort(compareByName);
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

}
