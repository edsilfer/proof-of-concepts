package br.com.edsilfer.poc_map_struct.mapper;

import org.junit.Test;

import br.com.edsilfer.poc_map_struct.domain.Contacts;
import br.com.edsilfer.poc_map_struct.dto.ContactsDTO;
import br.com.edsilfer.poc_map_struct.util.ResourceProvider;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Created by edgar on 05/07/17.
 */

public class TestContactsMapper {

    @Test
    public void phone_dto_must_have_same_attributes_as_domain_phone () {
        Contacts source = ResourceProvider.provideMockedContact();
        ContactsDTO target = ContactsMapper.INSTANCE.transformFrom(source);

        //assertThat(source.getPhones().size(), is(target.getPhones().size()));
        assertThat(source.getEmail(), is(target.getEmail()));
    }

}
