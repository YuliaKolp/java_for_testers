package tests;

import model.ContactData;
import model.GroupData;
import org.junit.jupiter.api.Assertions;
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
                    .withFirstName(randomString(i*5)).withMiddleName(randomString(i*5)).withLastName(randomString(i*5)));
        }
        return result;
    }

    @ParameterizedTest
    @MethodSource("contactProvider")
    public void canCreateMultipleContacts(ContactData contact) {
        var oldContacts = app.contacts().getList();
        app.contacts().createContact(contact);
        var newContacts = app.contacts().getList();
        var newContact = newContacts.get(newContacts.size()-1);
        // First name
        Comparator<ContactData> compareByName = (o1, o2) -> {
            return String.CASE_INSENSITIVE_ORDER.compare(o1.name(),o2.name());
        };
        newContacts.sort(compareByName);
        // Middle name
        Comparator<ContactData> compareByMiddleName = (o1, o2) -> {
            return String.CASE_INSENSITIVE_ORDER.compare(o1.middlename(),o2.middlename());
        };
        newContacts.sort(compareByMiddleName);
        // Last name
        Comparator<ContactData> compareByLastName = (o1, o2) -> {
            return String.CASE_INSENSITIVE_ORDER.compare(o1.lastname(),o2.lastname());
        };
        newContacts.sort(compareByLastName);

        var expectedList = new ArrayList<>(oldContacts);

        expectedList.add(contact.withId(newContact.id())
                .withFirstName(newContact.name())
                .withMiddleName(newContact.middlename())
                .withLastName(newContact.lastname()));

    }
/*
    @Test
    public void canAddContactWithNotEmptyGroup() {
        app.contacts().createContact(
                new ContactData("Sammy", "Mickel", "Doedoe", "nick", "QA",
                        "QAInc", "green st", "7", "123", "456", "789",
                        "jdoe@qamail.com", "jdoe2@qamail.com", "jdoe3@qamail.com",
                        "www.jdoe.com", "15", "September", "2001",
                        "12", "April", "1991", "abc"));
    }
*/
}
