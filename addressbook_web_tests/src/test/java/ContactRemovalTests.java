import model.ContactData;
import model.GroupData;
import org.junit.jupiter.api.Test;

public class ContactRemovalTests extends TestBase   {

    @Test
    public void canRemoveContact(){
        openContactPage();
        if (!isContactPresent()){
            createContact(new ContactData().withFirstName("toRemove"));
        }
        //removeContact();

    }
}
  /*
    driver.findElement(By.xpath("//tr[5]/td/input")).click();
    driver.findElement(By.xpath("//input[@value=\'Delete\']")).click();

  */
