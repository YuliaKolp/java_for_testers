package model;
import model.GroupData;

public record ContactData(String name, String middlename, String lastname, String nickname, String title,
                          String company, String address, String home, String mobile, String work, String fax,
                          String email, String email2, String email3, String homepage, String bday, String bmonth,
                          String byear, String aday, String amonth, String ayear, String group) {
    public ContactData() {
        this("", "", "", "", "", "", "", "", "",
                "", "", "", "", "", "", "", "", "",
                "", "", "", "");

    }


    public ContactData withFirstName(String firstname) {
        return new ContactData(firstname, this.middlename, this.lastname, this.nickname,  this.title, this.company,
                this.address, this.home, this.mobile, this.work, this.fax, this.email, this.email2, this.email3,
                this.homepage, this.bday, this.bmonth, this.byear, this.aday, this.amonth, this.ayear, this.group);
    }

    public ContactData withMiddleName(String middlename) {
        return new ContactData(this.name, middlename, this.lastname, this.nickname,  this.title, this.company,
                this.address, this.home, this.mobile, this.work, this.fax, this.email, this.email2, this.email3,
                this.homepage, this.bday, this.bmonth, this.byear, this.aday, this.amonth, this.ayear, this.group);
    }

    public ContactData withLastName(String lastname) {
        return new ContactData(this.name, this.middlename, lastname, this.nickname,  this.title, this.company,
                this.address, this.home, this.mobile, this.work, this.fax, this.email, this.email2, this.email3,
                this.homepage, this.bday, this.bmonth, this.byear, this.aday, this.amonth, this.ayear, this.group);
    }

}