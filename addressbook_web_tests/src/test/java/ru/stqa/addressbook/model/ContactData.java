package ru.stqa.addressbook.model;

public record ContactData(String id, String name, String middlename, String lastname, String nickname, String title,
                          String company, String address,
                          String home, String mobile, String work, String secondary,
                          String fax,
                          String email, String email2, String email3, String homepage, String bday, String bmonth,
                          String byear, String aday, String amonth, String ayear, String group, String photo) {
    public ContactData() {

        this("", "", "", "", "", "", "", "", "", "",
                "", "", "", "", "", "", "", "-", "-", "",
                "-", "-", "", "", "");

    }


    public ContactData withId(String id) {
        return new ContactData(id, this.name, this.middlename, this.lastname, this.nickname,  this.title, this.company,
                this.address, this.home, this.mobile, this.work, this.secondary, this.fax, this.email, this.email2, this.email3,
                this.homepage, this.bday, this.bmonth, this.byear, this.aday, this.amonth, this.ayear, this.group,
                this.photo);

    }


    public ContactData withFirstName(String firstname) {
        return new ContactData(this.id, firstname, this.middlename, this.lastname, this.nickname,  this.title, this.company,
                this.address, this.home, this.mobile, this.work, this.secondary, this.fax, this.email, this.email2, this.email3,
                this.homepage, this.bday, this.bmonth, this.byear, this.aday, this.amonth, this.ayear, this.group,
                this.photo);
    }

    public ContactData withMiddleName(String middlename) {
        return new ContactData(this.id, this.name, middlename, this.lastname, this.nickname,  this.title, this.company,
                this.address, this.home, this.mobile, this.work, this.secondary, this.fax, this.email, this.email2, this.email3,
                this.homepage, this.bday, this.bmonth, this.byear, this.aday, this.amonth, this.ayear, this.group,
                this.photo);
    }

    public ContactData withLastName(String lastname) {
        return new ContactData(this.id, this.name, this.middlename, lastname, this.nickname,  this.title, this.company,
                this.address, this.home, this.mobile, this.work, this.secondary, this.fax, this.email, this.email2, this.email3,
                this.homepage, this.bday, this.bmonth, this.byear, this.aday, this.amonth, this.ayear, this.group,
                this.photo);
    }

    public ContactData withPhoto(String photo) {
        return new ContactData(this.id, this.name, this.middlename, this.lastname, this.nickname,  this.title, this.company,
                this.address, this.home, this.mobile, this.work, this.secondary, this.fax, this.email, this.email2, this.email3,
                this.homepage, this.bday, this.bmonth, this.byear, this.aday, this.amonth, this.ayear, this.group,
                photo);
    }
    public ContactData withAddress(String address) {
        return new ContactData(this.id, this.name, this.middlename, this.lastname, this.nickname,  this.title, this.company,
                address, this.home, this.mobile, this.work, this.secondary, this.fax, this.email, this.email2, this.email3,
                this.homepage, this.bday, this.bmonth, this.byear, this.aday, this.amonth, this.ayear, this.group,
                this.photo);

    }
    public ContactData withHomePhone(String home) {
        return new ContactData(this.id, this.name, this.middlename, this.lastname, this.nickname,  this.title, this.company,
                this.address, home, this.mobile, this.work, this.secondary, this.fax, this.email, this.email2, this.email3,
                this.homepage, this.bday, this.bmonth, this.byear, this.aday, this.amonth, this.ayear, this.group,
                this.photo);
    }
    public ContactData withMobilePhone(String mobile) {
        return new ContactData(this.id, this.name, this.middlename, this.lastname, this.nickname,  this.title, this.company,
                this.address, this.home, mobile, this.work, this.secondary, this.fax, this.email, this.email2, this.email3,
                this.homepage, this.bday, this.bmonth, this.byear, this.aday, this.amonth, this.ayear, this.group,
                this.photo);
    }
    public ContactData withWorkPhone(String work) {
        return new ContactData(this.id, this.name, this.middlename, this.lastname, this.nickname,  this.title, this.company,
                this.address, this.home, this.mobile, work, this.secondary, this.fax, this.email, this.email2, this.email3,
                this.homepage, this.bday, this.bmonth, this.byear, this.aday, this.amonth, this.ayear, this.group,
                this.photo);
    }
    public ContactData withSeconadryPhone(String secondary) {
        return new ContactData(this.id, this.name, this.middlename, this.lastname, this.nickname,  this.title, this.company,
                this.address, this.home, this.mobile, this.work, secondary, this.fax, this.email, this.email2, this.email3,
                this.homepage, this.bday, this.bmonth, this.byear, this.aday, this.amonth, this.ayear, this.group,
                this.photo);
    }

    public ContactData withEmail(String email) {
        return new ContactData(this.id, this.name, this.middlename, this.lastname, this.nickname,  this.title, this.company,
                this.address, this.home, this.mobile, this.work, this.secondary, this.fax, email, this.email2, this.email3,
                this.homepage, this.bday, this.bmonth, this.byear, this.aday, this.amonth, this.ayear, this.group,
                this.photo);
    }
    public ContactData withEmail2(String email2) {
        return new ContactData(this.id, this.name, this.middlename, this.lastname, this.nickname,  this.title, this.company,
                this.address, this.home, this.mobile, this.work, this.secondary, this.fax, this.email, email2, this.email3,
                this.homepage, this.bday, this.bmonth, this.byear, this.aday, this.amonth, this.ayear, this.group,
                this.photo);
    }
    public ContactData withEmail3(String email3) {
        return new ContactData(this.id, this.name, this.middlename, this.lastname, this.nickname,  this.title, this.company,
                this.address, this.home, this.mobile, this.work, this.secondary, this.fax, this.email, this.email2, email3,
                this.homepage, this.bday, this.bmonth, this.byear, this.aday, this.amonth, this.ayear, this.group,
                this.photo);
    }

}