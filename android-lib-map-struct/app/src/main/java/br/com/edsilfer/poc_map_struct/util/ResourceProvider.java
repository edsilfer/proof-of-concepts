package br.com.edsilfer.poc_map_struct.util;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.edsilfer.poc_map_struct.domain.Address;
import br.com.edsilfer.poc_map_struct.domain.Contacts;
import br.com.edsilfer.poc_map_struct.domain.Person;
import br.com.edsilfer.poc_map_struct.domain.Phone;

/**
 * Created by edgar on 05/07/17.
 */
public class ResourceProvider {

    public static Phone provideMockedPhone () {
        Phone phone = new Phone();
        phone.setCountry(55);
        phone.setArea(61);
        phone.setNumber(981997641);
        return phone;
    }

    public static Address provideAddress () {
        Address address = new Address();
        address.setStreet("Washington Luis");
        address.setNumber(123);
        address.setZipCode("12229-908");
        address.setComplement("Ap. 182, Bloco H");
        return address;
    }

    public static Contacts provideMockedContact () {
        Contacts contacts = new Contacts();
        contacts.setEmail("sample.email@email.com");
        List<Phone> phones = new ArrayList<>();
        phones.add(provideMockedPhone());
        //contacts.setPhones(phones);
        return contacts;
    }

    public static Person provideMockedPerson() {
        Person person = new Person();
        person.setAddress(provideAddress());
        person.setContacts(provideMockedContact());
        person.setName("Eteovaldo De Nunes Barros");
        person.setDateOfBirth(new Date());
        return person;
    }

}
