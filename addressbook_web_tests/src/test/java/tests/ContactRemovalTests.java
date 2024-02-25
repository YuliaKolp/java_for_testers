package tests;

import model.ContactData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Random;

public class ContactRemovalTests extends TestBase {

    @Test
    public void canModifyContact(){
        if (app.contacts().getList().size() == 0){
            app.contacts().openContactsPage(app);
            app.contacts().createContact(new ContactData().withFirstName("toRemove"));
        }
        var oldContacts  = app.contacts().getList();
        var rnd = new Random();
        var index = rnd.nextInt(oldContacts.size());
        System.out.printf("Index is '%s'%n",index);
        app.contacts().removeContact(oldContacts.get(index));
        var newContacts = app.contacts().getList();
        var expectedList = new ArrayList<>(oldContacts);
        expectedList.remove(index);
        Assertions.assertEquals(newContacts, expectedList);

    }

    @Test
    public void canRemoveAllContacts(){
        if (app.contacts().getCount() == 0){
            app.contacts().openContactsPage(app);
            app.contacts().createContact(new ContactData().withFirstName("toRemove"));
        }
        app.contacts().removeAllContacts();
        Assertions.assertEquals(0, app.contacts().getCount());
    }
}
