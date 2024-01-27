import model.ContactData;
import org.junit.jupiter.api.Test;

public class AddContactTests extends TestBase {

    @Test
    public void canAddContact() {
        openContactPage();
        createContact(
                new ContactData("Bill", "Jack", "Doedoe", "nick", "QA",
                        "QAInc", "green st", "7", "123", "456", "789",
                        "jdoe@qamail.com", "jdoe2@qamail.com", "jdoe3@qamail.com",
                        "www.jdoe.com", "15", "October", "2000",
                        "11", "March", "1990", "new_group"));
    }

    @Test
    public void canAddContactWithEmptyName() {
        openContactPage();
        //createContact(firstname, middlename, lastname, nickname, title, company, address, home, mobile,work,  fax, email, email2, email3, homepage, bday, bmonth, byear, aday, amonth, ayear, group);
        createContact(
                new ContactData("", "Mickel", "Doedoe", "nick", "QA",
                        "QAInc", "green st", "7", "123", "456", "789",
                        "jdoe@qamail.com", "jdoe2@qamail.com", "jdoe3@qamail.com",
                        "www.jdoe.com", "15", "September", "2001",
                        "12", "April", "1991", "new_group"));
    }
}
