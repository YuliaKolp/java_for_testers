package tests;

import model.ContactData;
import org.junit.jupiter.api.Test;

public class ContactRemovalTests extends TestBase {

    @Test
    public void canRemoveContact(){
       //app.openContactPage();
        if (!app.contacts.isContactPresent(app)){
            app.contacts.openContactPage(app);
            app.contacts().createContact(new ContactData().withFirstName("toRemove"));
        }
        app.contacts().removeContact();
    }
}
