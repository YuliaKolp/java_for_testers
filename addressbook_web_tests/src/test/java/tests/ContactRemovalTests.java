package tests;

import model.ContactData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ContactRemovalTests extends TestBase {

    @Test
    public void canRemoveContact(){
       //app.openContactPage();
        if (app.contacts().getCount() == 0){
            app.contacts().openContactsPage(app);
            app.contacts().createContact(new ContactData().withFirstName("toRemove"));
        }
        int contactCount = app.contacts().getCount();
        app.contacts().removeContact();
        int newContactCount = app.contacts().getCount();
        Assertions.assertEquals(contactCount - 1, newContactCount);
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
