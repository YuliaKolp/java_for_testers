package tests;

import model.ContactData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;

public class ContactModificationTests  extends TestBase {

    @Test
    public void canModifyContact(){
        String newFirstName = "modifiedName";
        //check contact presence
        if (app.contacts().getList().size() == 0){
            app.contacts().openContactsPage(app);
            app.contacts().createContact(new ContactData().withFirstName("toModify"));
        }
        var oldContacts = app.contacts().getList();

        //System.out.println(oldContacts);
        var rnd = new Random();
        var index = rnd.nextInt(oldContacts.size());
        System.out.println(String.format("Index is '%s'",index));
        var testData = new ContactData().withFirstName(newFirstName);
        // modify and get new list
        var contactToModify = oldContacts.get(index);
        System.out.println(String.format("Contact ID is '%s', 1st name is '%s'",contactToModify.id(), contactToModify.name()));
        app.contacts().modifyContact(contactToModify, testData);
        var newContacts = app.contacts().getList();

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

        Assertions.assertEquals(expectedList, newContacts) ;

}
}
