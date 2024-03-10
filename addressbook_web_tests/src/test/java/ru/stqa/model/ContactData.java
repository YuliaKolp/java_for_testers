package ru.stqa.model;

public record ContactData(String id, String name, String middlename, String lastname, String nickname, String title,
                          String company, String address, String home, String mobile, String work, String fax,
                          String email, String email2, String email3, String homepage, String bday, String bmonth,
                          String byear, String aday, String amonth, String ayear, String group, String photo) {
    public ContactData() {

        this("", "", "", "", "", "", "", "", "", "",
                "", "", "", "", "", "", "-", "-", "",
                "-", "-", "", "", "");

    }


    public ContactData withId(String id) {
        return new ContactData(id, this.name, this.middlename, this.lastname, this.nickname,  this.title, this.company,
                this.address, this.home, this.mobile, this.work, this.fax, this.email, this.email2, this.email3,
                this.homepage, this.bday, this.bmonth, this.byear, this.aday, this.amonth, this.ayear, this.group,
                this.photo);

    }


    public ContactData withFirstName(String firstname) {
        return new ContactData(this.id, firstname, this.middlename, this.lastname, this.nickname,  this.title, this.company,
                this.address, this.home, this.mobile, this.work, this.fax, this.email, this.email2, this.email3,
                this.homepage, this.bday, this.bmonth, this.byear, this.aday, this.amonth, this.ayear, this.group,
                this.photo);
    }

    public ContactData withMiddleName(String middlename) {
        return new ContactData(this.id, this.name, middlename, this.lastname, this.nickname,  this.title, this.company,
                this.address, this.home, this.mobile, this.work, this.fax, this.email, this.email2, this.email3,
                this.homepage, this.bday, this.bmonth, this.byear, this.aday, this.amonth, this.ayear, this.group,
                this.photo);
    }

    public ContactData withLastName(String lastname) {
        return new ContactData(this.id, this.name, this.middlename, lastname, this.nickname,  this.title, this.company,
                this.address, this.home, this.mobile, this.work, this.fax, this.email, this.email2, this.email3,
                this.homepage, this.bday, this.bmonth, this.byear, this.aday, this.amonth, this.ayear, this.group,
                this.photo);
    }

    public ContactData withPhoto(String photo) {
        return new ContactData(this.id, this.name, this.middlename, this.lastname, this.nickname,  this.title, this.company,
                this.address, this.home, this.mobile, this.work, this.fax, this.email, this.email2, this.email3,
                this.homepage, this.bday, this.bmonth, this.byear, this.aday, this.amonth, this.ayear, this.group,
                photo);
    }
    public ContactData withAddress(String address) {
        return new ContactData(this.id, this.name, this.middlename, this.lastname, this.nickname,  this.title, this.company,
                address, this.home, this.mobile, this.work, this.fax, this.email, this.email2, this.email3,
                this.homepage, this.bday, this.bmonth, this.byear, this.aday, this.amonth, this.ayear, this.group,
                this.photo);

    }

}