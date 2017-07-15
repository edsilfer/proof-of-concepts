package br.com.edsilfer.poc_map_struct.dto;

import java.util.Date;

/**
 * Created by edgar on 05/07/17.
 */
public class PersonDTO {

    private String name;
    private Date age;
    private AddressDTO address;
    private ContactsDTO contacts;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getAge() {
        return age;
    }

    public void setAge(Date age) {
        this.age = age;
    }

    public AddressDTO getAddress() {
        return address;
    }

    public void setAddress(AddressDTO address) {
        this.address = address;
    }

    public ContactsDTO getContacts() {
        return contacts;
    }

    public void setContacts(ContactsDTO contacts) {
        this.contacts = contacts;
    }
}
