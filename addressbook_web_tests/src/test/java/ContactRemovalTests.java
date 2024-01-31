import model.ContactData;
import org.junit.jupiter.api.Test;

public class ContactRemovalTests extends TestBase   {

    @Test
    public void canRemoveContact(){
        openHomePage();
        if (!isContactPresent()){
            openAddContactPage();
            createContact(new ContactData().withFirstName("toRemove"));
        }
        removeContact();

    }
}
