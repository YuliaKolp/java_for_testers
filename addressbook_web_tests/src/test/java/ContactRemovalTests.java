import model.ContactData;
import org.junit.jupiter.api.Test;

public class ContactRemovalTests extends TestBase   {

    @Test
    public void canRemoveContact(){
        openHomePage();
        if (!isContactPresent()){
            System.out.println("CREATE CONT");
            createContact(new ContactData().withFirstName("toRemove"));
        }
        removeContact();

    }
}
  /*
    driver.findElement(By.xpath("//tr[5]/td/input")).click();
    driver.findElement(By.xpath("//input[@value=\'Delete\']")).click();

  */
