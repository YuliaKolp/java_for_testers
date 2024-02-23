package tests;

import common.CommonFunctions;
import model.ContactData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ContactCreationTests extends TestBase {

    public static List<ContactData> contactProvider() {
        var result = new ArrayList<ContactData>();
        for (var name : List.of("", "ContactName")){
            for (var middleame : List.of("", "ContactMiddleName")){
                for (var lastname : List.of("", "ContactLastName")){
                   //for (var nickname : List.of("", "ContactNickName")) {
                        //for (var title : List.of("", "ContactTitle")) {
                            //for (var company : List.of("", "ContactCompany")) {
                                //for (var address : List.of("", "ContactTitle")) {
                                    result.add(new ContactData()
                                            .withFirstName(name)
                                            .withMiddleName(middleame)
                                            .withLastName(lastname));
                                //}
                            //}
                        //}
                    //}
                }
            }
        }

        for (int i = 0; i < 5; i++){
            result.add(new ContactData()
                    .withFirstName(CommonFunctions.randomString(i*5)).withMiddleName(CommonFunctions.randomString(i*5)).withLastName(CommonFunctions.randomString(i*5)));
        }
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
        System.out.println(String.format("New id is '%s', name '%s'", newContact.id(), newContact.name()));

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

}
