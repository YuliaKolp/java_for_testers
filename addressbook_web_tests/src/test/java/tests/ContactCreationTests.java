package tests;

import model.ContactData;
import model.GroupData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;

public class ContactCreationTests extends TestBase {

    public static List<ContactData> contactProvider() {
        var result = new ArrayList<ContactData>();
        for (var name : List.of("", "ContactName")){
            for (var middleame : List.of("", "ContactMiddleName")){
                for (var lastname : List.of("", "ContactLastName")){
                    for (var nickname : List.of("", "ContactNickName")) {
                        //for (var title : List.of("", "ContactTitle")) {
                            //for (var company : List.of("", "ContactCompany")) {
                                //for (var address : List.of("", "ContactTitle")) {
                                    result.add(new ContactData(name, middleame, lastname, nickname, "title",
                                            "company", "address", "7", "123", "456",
                                            "789",  "jdoe@qamail.com", "jdoe2@qamail.com",
                                            "jdoe3@qamail.com", "www.jdoe.com", "15",
                                            "October", "2000", "11", "March", "1990", ""));
                                //}
                            //}
                        //}
                    }
                }
            }
        }

        for (int i = 0; i < 5; i++){
            result.add(new ContactData(randomString(i*5), randomString(i*5), randomString(i*5),
                    randomString(i*5), randomString(i*5), randomString(i*5),
                    randomString(i*5), randomString(i*5), randomString(i*5),
                    randomString(i*5), randomString(i*5), randomString(i*5),
                    randomString(i*5), randomString(i*5), randomString(i*5),
                    "", "",  "",
                    "", "",  "",
                    ""));
        }
        return result;
    }

    @ParameterizedTest
    @MethodSource("contactProvider")
    public void canCreateMultipleContacts(ContactData contact) {
        int contactCount = app.contacts().getCount();
        app.contacts().createContact(contact);
        int newContactCount = app.contacts().getCount();
        Assertions.assertEquals(contactCount + 1, newContactCount);
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
