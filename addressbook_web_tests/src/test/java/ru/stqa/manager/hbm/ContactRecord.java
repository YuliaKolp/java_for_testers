package ru.stqa.manager.hbm;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "addressbook")
public class ContactRecord {
    @Id
    public int id;
    public String firstname;
    public String lastname;
    public String address;

    public String group;

    @ManyToMany//(fetch = )
    @JoinTable(name = "address_in_groups",
            joinColumns = @JoinColumn(name = "id"),
            inverseJoinColumns = @JoinColumn(name = "group_id"))
    public List<GroupRecord> groups;

    public ContactRecord(){}

    public ContactRecord(int id, String firstname, String lastname, String address, String group){
        this.id= id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.address = address;
        this.group = group;
    }

}
